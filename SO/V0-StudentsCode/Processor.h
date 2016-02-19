#ifndef PROCESSOR_H
#define PROCESSOR_H

#include "MainMemory.h"

#define POWEROFF 1

// Functions prototypes
void Processor_InitializeRegisters(int, int, int);
void Processor_InstructionCycleLoop();
void Processor_FetchInstruction();
void Processor_DecodeAndExecuteInstruction();
void Processor_ManageInterruptions();
int Processor_GetMAR();
void Processor_SetMAR(int);
void Processor_GetMBR(MEMORYCELL *);
void Processor_SetMBR(MEMORYCELL *);

#endif
