#include <stdio.h>
#include <stdarg.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "ComputerSystem.h"
#include "OperatingSystem.h"
#include "Processor.h"


// Array that contains basic data about all user programs specified
// in the command line 
USER_PROGRAMS_DATA *userProgramsList[USERPROGRAMSMAXNUMBER];

// String specified in the command line to tell the simulator which of its
// sections are interesting for the user so it must show debug messages
// related to them
char *debugLevel;

// Only one colour messages. Set to 1 for more colours checking uppercase in debugLevel
int COLOURED = 0 ;
  
// Powers on of the Computer System.
void ComputerSystem_PowerOn(int argc, char *argv[]) {


	// Obtain a list of programs in the command line
	ComputerSystem_ObtainProgramList(argc, argv);
	
	ComputerSystem_PrintProgramList();
	
	// Request the OS to do the initial set of tasks. The last one will be
	// the processor allocation to the process with the highest priority
	OperatingSystem_Initialize();
	
	// Tell the processor to begin its instruction cycle 
	Processor_InstructionCycleLoop();
	

}

// Powers off the CS (the C program ends)
void ComputerSystem_PowerOff() {
	ComputerSystem_DebugMessage(HARDWARE,"Rs"," END OF SIMULATION\n");
	exit(0);
}

// Fill in the array named userProgramsList with the information given
// by the user in the command line
// IT IS NOT NECESSARY TO COMPLETELY UNDERSTAND THIS FUNCTION
void ComputerSystem_ObtainProgramList(int argc, char *argv[]) {
	
	int i;
	int count=0;
	USER_PROGRAMS_DATA *upd;

	// To remember the simulator sections to be message-debugged
	debugLevel = argv[1];
	for (i=0; i< strlen(debugLevel);i++)
	  if (isupper(debugLevel[i])){
		COLOURED = 1;
		debugLevel[i]=tolower(debugLevel[i]);
	  }
	
	for (i=0; i<USERPROGRAMSMAXNUMBER;i++)
	      userProgramsList[i]=NULL;

	// Store the names of the programs
	for (i = 2; i < argc && count<USERPROGRAMSMAXNUMBER;) { // check number of programs < USERPROGRAMSMAXNUMBER
		upd=(USER_PROGRAMS_DATA *) malloc(sizeof(USER_PROGRAMS_DATA));
		// Guarda el nombre del fichero
		upd->executableName = (char *) malloc((strlen(argv[i])+1)*sizeof(char));
		strcpy(upd->executableName,argv[i]);
		i++;
		// Try to store the arrival time
		if (i < argc) {
			if (sscanf(argv[i], "%d", &(upd->arrivalTime)) == 1)
				// An arrival time has been read. Increment i
				i++;
			else
				// An arrival time has not been read. Assume it is 0 and do not increment i 
				upd->arrivalTime = 0;
		}
		// Store the structure in the list
		userProgramsList[count]=upd;
		
 		count++; // There is one program more
	}
//	return count;
}

void ComputerSystem_PrintProgramList(){
	int i;

	ComputerSystem_DebugMessage(INIT, "s", "User program list:\n");

	for (i = 0; i<USERPROGRAMSMAXNUMBER; i++){
		USER_PROGRAMS_DATA * prog = userProgramsList[i];
		if (prog != NULL){
		ComputerSystem_DebugMessage(INIT, "sBssBds", "\tProgram [",
		userProgramsList[i] -> executableName,"] with arrival time [",
		userProgramsList[i] -> arrivalTime, "]\n");
		}
	}
	
}

// Function used to show messages with details of the internal working of
// the simulator
// IT IS NOT NECESSARY TO UNDERSTAND ALL THE DETAILS OF THIS FUNCTION
void ComputerSystem_DebugMessage(char section, char *format, ...) {

	va_list lp;
	char c;
	int count, youHaveToContinue, colour;

	if ((strchr(debugLevel, ALL) != NULL)           // Print the message because the user specified ALL
	  || section == ERROR                                           //  Always print ERROR section
	  || (strchr(debugLevel,section)) != NULL){ //  or the section argument is included in the debugLevel string
		va_start(lp, format);
		for (count = 0, youHaveToContinue = 1, colour=0; youHaveToContinue == 1; count++) {
			switch (format[count]) {
			case 'R': // Text in red
				if (COLOURED){
				  printf("%c[%d;%dm", 0x1B, 1, 31);
				  if (!colour) colour=1;
				}
				break;
			case 'G': // Text in green
				if (COLOURED){
				  printf("%c[%d;%dm", 0x1B, 1, 32);
				  if (!colour) colour=1;
				}
				break;
			case 'Y': // Text in yellow
  				if (COLOURED){
				  printf("%c[%d;%dm", 0x1B, 1, 33);
				  if (!colour) colour=1;
				}
				break;
			case 'B': // Text in blue
				if (COLOURED){
				  printf("%c[%d;%dm", 0x1B, 1, 34);
				  if (!colour) colour=1;
				}
				break;
			case 'M': // // Text in magenta
				if (COLOURED){
				  printf("%c[%d;%dm", 0x1B, 1, 35);
				  if (!colour) colour=1;
				}
				break;
			case 'C': // Text in cyan
				if (COLOURED){
				  printf("%c[%d;%dm", 0x1B, 1, 36);
				  if (!colour) colour=1;
				}
				break;		
			case 'W': // Text in white
				if (COLOURED){
				  printf("%c[%d;%dm", 0x1B, 1, 37);
				  if (!colour) colour=1;
				}
				break;		
			case 's':
				printf("%s",va_arg(lp, char *));
				if (colour)
					printf("%c[%dm", 0x1B, 0);
				break;
			case 'd':
				printf("%d",va_arg(lp, int));
				if (colour) 
					printf("%c[%dm", 0x1B, 0);
				break;
			case 'f':
				printf("%f",va_arg(lp, double));
				if (colour) 
					printf("%c[%dm", 0x1B, 0);
				break;
			case 'c':
				c = (char) va_arg(lp, int);
				printf("%c", c);
				if (colour) 
					printf("%c[%dm", 0x1B, 0);
				break;
			default:
				youHaveToContinue = 0;
			} // switch
		} // for
		va_end(lp);
	} // if
	if (COLOURED && colour)
	    printf("%c[%dm", 0x1B, 0);
} // ComputerSystem_DebugMessage()

