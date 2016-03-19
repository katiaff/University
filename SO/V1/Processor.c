#include "Processor.h"
#include "OperatingSystem.h"
#include "Buses.h"
#include "MMU.h"
#include <stdio.h>
#include <string.h>

// Internals Functions prototypes

void Processor_FetchInstruction();
void Processor_DecodeAndExecuteInstruction();
void Processor_ManageInterrupts();
void Processor_CopyInSystemStack(int, int);
void Processor_RaiseInterrupt(const unsigned int);
void Processor_ACKInterrupt(const unsigned int);
unsigned int Processor_GetInterruptLineStatus(const unsigned int);
void Processor_ActivatePSW_Bit(const unsigned int);
void Processor_DeactivatePSW_Bit(const unsigned int);
unsigned int Processor_PSW_BitState(const unsigned int);

// void Processor_SetAccumulator(int);
// void Processor_SetPC(int);
// void Processor_SetRegisterA(int);
// 
// int Processor_GetAccumulator();


// Processor registers
int registerPC_CPU; // Program counter
int registerAccumulator_CPU; // Accumulator
MEMORYCELL registerIR_CPU; // Instruction register
int registerPSW_CPU; // Processor state word
int registerMAR_CPU; // Memory Address Register
MEMORYCELL registerMBR_CPU; // Memory Buffer Register

int registerA_CPU; // General purpose register

int interruptLines_CPU; // Processor interrupt lines

char * PSW_BITS_names [INTERRUPTTYPES]={"POWEROFF_BIT","EXECUTION_MODE_BIT","USUSED",
"UNSUSED","UNSUSED","UNSUSED","UNSUSED","EXECUTION_MODE_BIT","UNSUSED","UNSUSED"};

// Interrupt vector table: an array of function pointers
void (* interruptVectorTable[INTERRUPTTYPES])();


// Initialization of the interrupt vector table
void Processor_InitializeInterruptVectorTable() {

  	interruptVectorTable[EXCEPTION_BIT]=OperatingSystem_HandleException;
  	interruptVectorTable[SYSCALL_BIT]=OperatingSystem_HandleSystemCall;
}


// This is the instruction cycle loop (fetch, decoding, execution, etc.).
// The processor stops working when an POWEROFF signal is stored in its
// PSW register
void Processor_InstructionCycleLoop() {

	while (!Processor_PSW_BitState(POWEROFF_BIT)) {
		Processor_FetchInstruction();
		Processor_DecodeAndExecuteInstruction();
		Processor_ManageInterrupts();
	}
}

// Fetch an instruction from main memory and put it in the IR register
void Processor_FetchInstruction() {

	// The instruction must be located at the logical memory address pointed by the PC register
	registerMAR_CPU=registerPC_CPU;
	// Send to the MMU the address in which the reading has to take place: use the address bus for this
	Buses_write_AddressBus_From_To(CPU, MMU);
	// Tell the main memory controller to read
	if (MMU_readMemory()==MMU_SUCCESS) {
	  // All the read data is stored in the MBR register. Because it is an instruction
	  // we have to copy it to the IR register
	  memcpy((void *) (&registerIR_CPU), (void *) (&registerMBR_CPU), sizeof(MEMORYCELL));
	  ComputerSystem_DebugMessage(HARDWARE,"csdsd",registerIR_CPU.operationCode," ",registerIR_CPU.operand1," ",registerIR_CPU.operand2);
	}
	else 
	  ComputerSystem_DebugMessage(HARDWARE,"s","_ _ _");
}


// Decode and execute the instruction in the IR register
void Processor_DecodeAndExecuteInstruction() {

	switch (registerIR_CPU.operationCode) {
	  
		// Instruction ADD
		case 'a': registerAccumulator_CPU= registerIR_CPU.operand1 + registerIR_CPU.operand2;
			  registerPC_CPU++;
			  break;
		
		// Instruction SUB
		case 's': registerAccumulator_CPU= registerIR_CPU.operand1 - registerIR_CPU.operand2;
			  registerPC_CPU++;
			  break;
		
		// Instruction DIV
		case 'd': if (registerIR_CPU.operand2 == 0)
				  Processor_RaiseInterrupt(EXCEPTION_BIT); 
			  else {
				  registerAccumulator_CPU=registerIR_CPU.operand1 / registerIR_CPU.operand2;
				  registerPC_CPU++;
			  }
			  break;
			  
		// Instruction TRAP
		case 't': Processor_RaiseInterrupt(SYSCALL_BIT);
			  registerA_CPU=registerIR_CPU.operand1;
			  registerPC_CPU++;
			  break;
		
		// Instruction NOP
		case 'n': registerPC_CPU++;
			  break;
			  
		// Instruction JUMP
		case 'j': registerPC_CPU+= registerIR_CPU.operand1;
			  break;
			  
		// Instruction ZJUMP
		case 'z': if (registerAccumulator_CPU==0)
				  registerPC_CPU+= registerIR_CPU.operand1;
			  else
				  registerPC_CPU++;
			  break;

		// Instruction WRITE
		case 'w': registerMBR_CPU.operationCode= registerMBR_CPU.operand1= registerMBR_CPU.operand2= registerAccumulator_CPU;
			  registerMAR_CPU=registerIR_CPU.operand1;
			  // Send to the main memory controller the data to be written: use the data bus for this
			  Buses_write_DataBus_From_To(CPU, MAINMEMORY);
			  // Send to the main memory controller the address in which the writing has to take place: use the address bus for this
			  Buses_write_AddressBus_From_To(CPU, MMU);
			  // Tell the main memory controller to write
			  MMU_writeMemory();
			  registerPC_CPU++;
			  break;

		// Instruction READ
		case 'r': registerMAR_CPU=registerIR_CPU.operand1;
			  // Send to the main memory controller the address in which the reading has to take place: use the address bus for this
			  Buses_write_AddressBus_From_To(CPU, MMU);
			  // Tell the main memory controller to read
			  MMU_readMemory();
			  // Copy the read data to the accumulator register
			  registerAccumulator_CPU= registerMBR_CPU.operand1;
			  registerPC_CPU++;
			  break;

		// Instruction MEMADD
		case 'm' : registerMAR_CPU=registerIR_CPU.operand2;
			   Buses_write_AddressBus_From_To(CPU, MMU);
			  // Tell the main memory controller to read
			  MMU_readMemory();	
			  // Sum the operand 1 and the read value		  
			  registerAccumulator_CPU= registerIR_CPU.operand1 + Processor_GetMBR_Value();
			  registerPC_CPU++;
			  break;

		// Instruction INC
		case 'i': registerAccumulator_CPU+= registerIR_CPU.operand1;
			  registerPC_CPU++;
			  break;

		// Instruction HALT
		case 'h': 
			  if (Processor_PSW_BitState(EXECUTION_MODE_BIT) == 1){
				  ComputerSystem_DebugMessage(HARDWARE, "s", "\n");
				  Processor_ActivatePSW_Bit(POWEROFF_BIT);
			  }
			  else{
				  Processor_RaiseInterrupt(EXCEPTION_BIT);
			  }
			  break;
			  
			  // Unknown instruction
		default : 
 		  registerPC_CPU++;
			  break;
	}
	ComputerSystem_DebugMessage(HARDWARE,"sRdsRdsRds"," (PC: ",registerPC_CPU,", Accumulator: ",registerAccumulator_CPU, " PSW: ", registerPSW_CPU, ")\n");
}
	
	
// Hardware interrupt processing
void Processor_ManageInterrupts() {
  
	int i;

	// Interrupts are noted from bit position 1 in the IntLines register
	for (i=1;i<INTERRUPTTYPES;i++)
		// If an 'i'-type interrupt is pending
		if (Processor_GetInterruptLineStatus(i)) {
			// Deactivate interrupt
			Processor_ACKInterrupt(i);

			// Copy PC and PSW registers in the system stack
			Processor_CopyInSystemStack(MAINMEMORYSIZE-1, registerPC_CPU);
			Processor_CopyInSystemStack(MAINMEMORYSIZE-2, registerPSW_CPU);	

			// Call the appropriate OS interrupt-handling routine
			interruptVectorTable[i]();
		}
}

// Save in the system stack a given value
void Processor_CopyInSystemStack(int physicalMemoryAddress, int data) {

	registerMBR_CPU.operationCode=registerMBR_CPU.operand1=registerMBR_CPU.operand2=data;
	registerMAR_CPU=physicalMemoryAddress;
	Buses_write_AddressBus_From_To(CPU, MAINMEMORY);
	Buses_write_DataBus_From_To(CPU, MAINMEMORY);	
	MainMemory_writeMemory();
}

// Put the specified interrupt line to a high level 
void Processor_RaiseInterrupt(const unsigned int interruptNumber) {
	unsigned int mask = 1;

	mask = mask << interruptNumber;
	interruptLines_CPU = interruptLines_CPU | mask;
}

// Put the specified interrupt line to a low level 
void Processor_ACKInterrupt(const unsigned int interruptNumber) {
	unsigned int mask = 1;

	mask = mask << interruptNumber;
	mask = ~mask;

	interruptLines_CPU = interruptLines_CPU & mask;
}

// Returns the state of a given interrupt line (1=high level, 0=low level)
unsigned int Processor_GetInterruptLineStatus(const unsigned int interruptNumber) {
	unsigned int mask = 1;

	mask = mask << interruptNumber;
	return (interruptLines_CPU & mask) >> interruptNumber;
}

// Set a given bit position in the PSW register
void Processor_ActivatePSW_Bit(const unsigned int nbit) {
	unsigned int mask = 1;

	mask = mask << nbit;
	registerPSW_CPU = registerPSW_CPU | mask;
	
	ComputerSystem_DebugMessage(HARDWARE,"sRss","Activating PSW bit: ",
	PSW_BITS_names[nbit], nbit!=POWEROFF_BIT?"\n":" ");
}

// Unset a given bit position in the PSW register
void Processor_DeactivatePSW_Bit(const unsigned int nbit) {
	unsigned int mask = 1;

	mask = mask << nbit;
	mask = ~mask;

	registerPSW_CPU = registerPSW_CPU & mask;
	
	ComputerSystem_DebugMessage(HARDWARE,"sRss","Deactivating PSW bit: ",
	PSW_BITS_names[nbit],"\n");
}

// Returns the state of a given bit position in the PSW register
unsigned int Processor_PSW_BitState(const unsigned int nbit) {
	unsigned int mask = 1;

	mask = mask << nbit;
	return (registerPSW_CPU & mask) >> nbit;
}

// Getter for the registerMAR_CPU
int Processor_GetMAR() {
  return registerMAR_CPU;
}

// Setter for the registerMAR_CPU
void Processor_SetMAR(int data) {
  registerMAR_CPU=data;
}

// pseudo-getter for the registerMBR_CPU
void Processor_GetMBR(MEMORYCELL *toRegister) {
  memcpy((void*) toRegister, (void *) (&registerMBR_CPU), sizeof(MEMORYCELL));
}

// pseudo-setter for the registerMBR_CPU
void Processor_SetMBR(MEMORYCELL *fromRegister) {
  memcpy((void*) (&registerMBR_CPU), (void *) fromRegister, sizeof(MEMORYCELL));
}

// pseudo-getter for the registerMBR_CPU value
int Processor_GetMBR_Value(){
  return registerMBR_CPU.operationCode;
}

// Setter for the Accumulator
// void Processor_SetAccumulator(int acc){
//   registerAccumulator_CPU=acc;
// }

// Setter for the PC
void Processor_SetPC(int pc){
  registerPC_CPU= pc;
}

// Setter for the RegisterA
// void Processor_SetRegisterA(int rA) {
//   registerA_CPU=rA;
// }

// Getter for the Accumulator
// int Processor_GetAccumulator() {
//   return registerAccumulator_CPU;
// }

// int Processor_GetPC() {
//   return registerPC_CPU;
// }

int Processor_GetRegisterA() {
  return registerA_CPU;
}
