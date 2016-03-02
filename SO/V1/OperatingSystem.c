#include "OperatingSystem.h"
#include "MMU.h"
#include "Processor.h"
#include "Buses.h"
#include "Heap.h"
#include <string.h>
#include <ctype.h>
#include <stdlib.h>
#include <time.h>

// Functions prototypes
void OperatingSystem_CreateDaemons();
void OperatingSystem_PCBInitialization(int, int, int, int);
void OperatingSystem_MoveToTheREADYState(int);
void OperatingSystem_Dispatch(int);
void OperatingSystem_RestoreContext(int);
void OperatingSystem_SaveContext(int);
void OperatingSystem_TerminateProcess();
int OperatingSystem_LongTermScheduler();
void OperatingSystem_PreemptRunningProcess();
int OperatingSystem_CreateProcess(USER_PROGRAMS_DATA);
int OperatingSystem_ObtainAnEntryInTheProcessTable();
int OperatingSystem_ObtainMainMemory(int, int);
int OperatingSystem_ShortTermScheduler();
int OperatingSystem_ExtractFromReadyToRun();
int OperatingSystem_LoadProgram(FILE *, int, int);
int OperatingSystem_ObtainProgramSize(FILE **, char *);
int OperatingSystem_ObtainPriority(FILE *);
int OperatingSystem_lineBeginsWithANumber(char *);

// The process table
PCB processTable[PROCESSTABLEMAXSIZE];

// Identifier of the current executing process
int executingProcessID=NOPROCESS;

// Identifier of the System Idle Process
int sipID;

// Array that contains the identifiers of the READY processes
int readyToRunQueue[PROCESSTABLEMAXSIZE];
int numberOfReadyToRunProcesses=0;

// Variable containing the number of not terminated user processes
int numberOfNotTerminatedProcesses=0;

// Initial set of tasks of the OS
void OperatingSystem_Initialize() {
	
	int i, selectedProcess;
	
	// Initialize random feed
	srand(time(NULL));

	// Process table initialization (all entries are free)
	for (i=0; i<PROCESSTABLEMAXSIZE;i++)
		processTable[i].busy=0;
	
	// Initialization of the interrupt vector table of the processor
	Processor_InitializeInterruptVectorTable();
	
	// Create all system daemon processes
	OperatingSystem_CreateDaemons();
	
	if (sipID<0) {
	  ComputerSystem_DebugMessage(SHUTDOWN, "Rs", "ERROR: Missing SIP program!\n");
	  exit(1);
	}
	
	// Create all user processes from the information given in the command line
	OperatingSystem_LongTermScheduler();
	
	// At least, one user process has been created
	// Select the first process that is going to use the processor
	selectedProcess=OperatingSystem_ShortTermScheduler();

	// Assign the processor to the selected process
	OperatingSystem_Dispatch(selectedProcess);
	  
}

// Daemon processes are system processes, that is, they work together with the OS.
// The System Idle Process uses the CPU whenever a user process is able to use it
void OperatingSystem_CreateDaemons() {
  
	USER_PROGRAMS_DATA systemIdleProcess;
	
	systemIdleProcess.executableName="SystemIdleProcess";
	sipID=OperatingSystem_CreateProcess(systemIdleProcess);  
}


// The LTS is responsible of the admission of new processes in the system.
// Initially, it creates a process from each program specified in the command line
int OperatingSystem_LongTermScheduler() {
  
	int PID;
 	int i=0;
	int numberOfSuccessfullyCreatedProcesses=0;
	
	while (userProgramsList[i]!=NULL && i<USERPROGRAMSMAXNUMBER) {
		PID=OperatingSystem_CreateProcess(*userProgramsList[i]);
		numberOfSuccessfullyCreatedProcesses++;
		ComputerSystem_DebugMessage(INIT,"GsGdGsGsGs","Process [",PID,"] created from program [",userProgramsList[i]->executableName,"]\n");
		i++;
	}
	numberOfNotTerminatedProcesses+=numberOfSuccessfullyCreatedProcesses;

	// Return the number of succesfully created processes
	return numberOfSuccessfullyCreatedProcesses;
}


// This function creates a process from an executable program
int OperatingSystem_CreateProcess(USER_PROGRAMS_DATA executableProgram) {
  
	int PID;
	int processSize;
	int loadingPhysicalAddress;
	int priority;
	FILE *programFile;

	// Obtain a process ID
	PID=OperatingSystem_ObtainAnEntryInTheProcessTable();
	
	// Obtain the memory requirements of the program
	processSize=OperatingSystem_ObtainProgramSize(&programFile, executableProgram.executableName);
	
	// Obtain the priority for the process
	priority=OperatingSystem_ObtainPriority(programFile);
	
	// Obtain enough memory space
 	loadingPhysicalAddress=OperatingSystem_ObtainMainMemory(processSize, PID);
	
	// Load program in the allocated memory
	OperatingSystem_LoadProgram(programFile, loadingPhysicalAddress, processSize);
	
	// PCB initialization
	OperatingSystem_PCBInitialization(PID, loadingPhysicalAddress, processSize, priority);
	
	return PID;
}


// Search for a free entry in the process table. The index of the selected entry
// will be used as the process identifier
int OperatingSystem_ObtainAnEntryInTheProcessTable() {

	int orig=INITIALPID?rand()%PROCESSTABLEMAXSIZE:INITIALPID;
	int index=0;
	int entry;

	while (index<PROCESSTABLEMAXSIZE) {
	  entry = (orig+index)%PROCESSTABLEMAXSIZE;
		if (processTable[entry].busy==0)
			return entry;
		else
			index++;
	}
	return NOFREEENTRY;
}


// Main memory is assigned in chunks. All chunks are the same size. A process
// always obtains the chunk whose position in memory is equal to the processor identifier
int OperatingSystem_ObtainMainMemory(int processSize, int PID) {

 	if (processSize>MAINMEMORYSECTIONSIZE)
		return TOOBIGPROCESS;
	
 	return PID*MAINMEMORYSECTIONSIZE;
}


// Assign initial values to all fields inside the PCB
void OperatingSystem_PCBInitialization(int PID, int initialPhysicalAddress, int processSize, int priority) {

	processTable[PID].busy=1;
	processTable[PID].initialPhysicalAddress=initialPhysicalAddress;
	processTable[PID].processSize=processSize;
	processTable[PID].copyOfPCRegister=0;
	processTable[PID].priority=priority;
	processTable[PID].state=NEW;
	OperatingSystem_MoveToTheREADYState(PID);
}

// Move a process to the READY state: it will be inserted, depending on its priority, in
// a queue of identifiers of READY processes
void OperatingSystem_MoveToTheREADYState(int PID) {
	
	if (Heap_add(PID, readyToRunQueue,QUEUE_PRIORITY ,numberOfReadyToRunProcesses ,PROCESSTABLEMAXSIZE)>=0) {
	  numberOfReadyToRunProcesses++;
	  processTable[PID].state=READY;
	} 
}

// The STS is responsible of deciding which process to execute when specific events occur.
// It uses processes priorities to make the decission. Given that the READY queue is ordered
// depending on processes priority, the STS just selects the process in front of the READY queue
int OperatingSystem_ShortTermScheduler() {
	
	int selectedProcess;

	selectedProcess=OperatingSystem_ExtractFromReadyToRun();
	
	return selectedProcess;
}


// Return PID of more priority process in the READY queue
int OperatingSystem_ExtractFromReadyToRun() {
  
	int selectedProcess=NOPROCESS;

	selectedProcess=Heap_poll(readyToRunQueue,QUEUE_PRIORITY ,numberOfReadyToRunProcesses);
	if (selectedProcess>=0) 
	  numberOfReadyToRunProcesses--;
	
	// Return most priority process or NOPROCESS if empty queue
	return selectedProcess; 
}

// Function that assigns the processor to a process
void OperatingSystem_Dispatch(int PID) {

	// Modify hardware registers with appropriate values for the process identified by PID
	OperatingSystem_RestoreContext(PID);
	// The process identified by PID becomes the current executing process
	executingProcessID=PID;
	// Change the process' state
	processTable[PID].state=EXECUTING;
}


// Modify hardware registers with appropriate values for the process identified by PID
void OperatingSystem_RestoreContext(int PID) {
  
	// New values for the CPU registers are obtained from the PCB
	Processor_SetPC(processTable[PID].copyOfPCRegister);
	// Same thing for the MMU registers
	MMU_SetBase(processTable[PID].initialPhysicalAddress);
	MMU_SetLimit(processTable[PID].processSize);
}


// Function invoked when the executing process leaves the CPU 
void OperatingSystem_PreemptRunningProcess() {

	// Save in the process' PCB essential values stored in hardware registers and the system stack
	OperatingSystem_SaveContext(executingProcessID);
	// Change the process' state
	OperatingSystem_MoveToTheREADYState(executingProcessID);
	// The processor is not assigned until the OS selects another process
	executingProcessID=NOPROCESS;
}

// Save in the process' PCB essential values stored in hardware registers and the system stack
void OperatingSystem_SaveContext(int PID) {
	
	Processor_SetMAR(MAINMEMORYSIZE-1); // Load PC saved for interrupt manager

	Buses_write_AddressBus_From_To(CPU, MAINMEMORY);
	MainMemory_readMemory();
	processTable[PID].copyOfPCRegister= Processor_GetMBR_Value();
	
}


// Exception management routine
void OperatingSystem_HandleException() {
  
  ComputerSystem_DebugMessage(SYSPROC,"RsRdRs","Process [",executingProcessID,"] has generated an exception and is terminating\n");
	
  OperatingSystem_TerminateProcess();

}


// All tasks regarding the removal of the process
void OperatingSystem_TerminateProcess() {
  
	int selectedProcess;
  	
	processTable[executingProcessID].state=EXIT;
	
	// One more process that has terminated
	numberOfNotTerminatedProcesses--;
	
	if (numberOfNotTerminatedProcesses<=0) {
		// Simulation must finish (done by modifying the PC of the System Idle Process so it points to its 'halt' instruction,
		// located at the last memory position used by that process, and dispatching sipId (next ShortTermSheduled)
		processTable[sipID].copyOfPCRegister=processTable[sipID].processSize-1;
	}
	// Select the next process to execute (sipID if no more user processes)
	selectedProcess=OperatingSystem_ShortTermScheduler();
	// Assign the processor to that process
	OperatingSystem_Dispatch(selectedProcess);
}


// System call management routine
void OperatingSystem_HandleSystemCall() {
  
	int systemCallID;

	// Register A contains the identifier of the issued system call
	systemCallID=Processor_GetRegisterA();
	
	switch (systemCallID) {

	  case SYSCALL_PRINTEXECPID:
		ComputerSystem_DebugMessage(SYSPROC,"RsRdRs","Process [",executingProcessID,"] has the processor assigned\n");
		break;

	  case SYSCALL_END:
		ComputerSystem_DebugMessage(SYSPROC,"RsRdRs","Process [",executingProcessID,"] has requested to terminate\n");
		OperatingSystem_TerminateProcess();
		break;
	}
}


// Returns the size of the program, stored in the program file
int OperatingSystem_ObtainProgramSize(FILE **programFile, char *program) {

	char lineRead[MAXLINELENGTH];
	int isComment=1;
	int programSize;
	
	*programFile= fopen(program, "r");
	
	// Check if programFile exists
	if (*programFile==NULL)
		return PROGRAMDOESNOTEXIST;

	// Read the first number as the size of the program. Skip all comments.
	while (isComment==1) {
		if (fgets(lineRead, MAXLINELENGTH, *programFile) == NULL)
		      return PROGRAMNOTVALID;
		else
		      if (lineRead[0]!='/') { // Line IS NOT a comment
			    isComment=0;
			    if (OperatingSystem_lineBeginsWithANumber(lineRead))
				  programSize=atoi(strtok(lineRead," "));
			    else
				  return PROGRAMNOTVALID;
		      }
	}
	// Only sizes above 0 are allowed
	if (programSize<=0)
	      return PROGRAMNOTVALID;
	else
	      return programSize;
}


// Returns the priority of the program, stored in the program file
int OperatingSystem_ObtainPriority(FILE *programFile) {

	char lineRead[MAXLINELENGTH];
	int isComment=1;
	int processPriority;
	
	// Read the second number as the priority of the program. Skip all comments.
	while (isComment==1) {
		if (fgets(lineRead, MAXLINELENGTH, programFile) == NULL)
		      return PROGRAMNOTVALID;
		else
		      if (lineRead[0]!='/') { // Line IS NOT a comment
			    isComment=0;
			    if (OperatingSystem_lineBeginsWithANumber(lineRead))
				  processPriority=atoi(strtok(lineRead," "));
			    else
				  return PROGRAMNOTVALID;
		      }
	}
	return processPriority;
}


// Function that processes the contents of the file named by the first argument
// in order to load it in main memory from the address given as the second
// argument
// IT IS NOT NECESSARY TO COMPLETELY UNDERSTAND THIS FUNCTION

int OperatingSystem_LoadProgram(FILE *programFile, int initialAddress, int size) {

	char lineRead[MAXLINELENGTH];
	char *token0, *token1, *token2;
	MEMORYCELL *data = (MEMORYCELL *) malloc(sizeof(MEMORYCELL));

	Processor_SetMAR(initialAddress);
	int finalAddress = initialAddress + size;
	while (fgets(lineRead, MAXLINELENGTH, programFile) != NULL) {
		// REMARK: if lineRead is greater than MAXLINELENGTH in number of characters, the program
		// loading does not work
		data->operationCode=' ';data->operand1=data->operand2=0;
		token0=strtok(lineRead," ");
		if ((token0!=NULL) && (token0[0]!='/')) {
			// I have an instruction with, at least, an operation code
			data->operationCode=tolower(token0[0]);
			token1=strtok(NULL," ");
			if ((token1!=NULL) && (token1[0]!='/')) {
				// I have an instruction with, at least, an operand
				data->operand1=atoi(token1);
				token2=strtok(NULL," ");
				if ((token2!=NULL) && (token2[0]!='/')) {
				  // The read line is similar to 'sum 2 3 //coment'
				  // I have an instruction with two operands
				  data->operand2=atoi(token2);
				}
			}
			
		      // More instructions than size...
			if (Processor_GetMAR() == finalAddress) {
				  free(data);
				  return PROGRAMNOTVALID;
			  }

			  Processor_SetMBR(data);
		      // Send data to main memory using the system buses
		      Buses_write_DataBus_From_To(CPU, MAINMEMORY);
		      Buses_write_AddressBus_From_To(CPU, MAINMEMORY);
		      // Tell the main memory controller to write
		      MainMemory_writeMemory();
		      Processor_SetMAR(Processor_GetMAR()+1);
		}
	}
	free(data);
	return SUCCESS;
}	

// Auxiliar for check that line begins with positive number
int OperatingSystem_lineBeginsWithANumber(char * line) {
	int i;
	
	for (i=0; i<strlen(line) && line[i]==' '; i++); // Don't consider blank spaces
	// If is there a digit number...
	if (i<strlen(line) && isdigit(line[i]))
		// It's a positive number
		return 1;
	else
		return 0;
}
	  
	  
	  
