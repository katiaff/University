#ifndef PROCESSOR_H
#define PROCESSOR_H

#include "MainMemory.h"

#define INTERRUPTTYPES 10

// Enumerated type that connects bit positions in the PSW register with
// processor events and status
enum PSW_BITS {POWEROFF_BIT=0, EXECUTION_MODE_BIT=7};

// Enumerated type that connects bit positions in the interruptLines with
// interrupt types 
enum INT_BITS {SYSCALL_BIT=2, EXCEPTION_BIT=6, CLOCKINT_BIT=9};

// Functions prototypes
void Processor_InitializeInterruptVectorTable();
void Processor_InstructionCycleLoop();

// The OS needs to access MAR and MBR registers to save the context of
// the process to which the processor is being assigned
int Processor_GetMAR();
void Processor_SetMAR(int);
void Processor_GetMBR(MEMORYCELL *);
void Processor_SetMBR(MEMORYCELL *);
int Processor_GetMBR_Value();
 
// The OS needs to access the accumulator register to restore the context of
// the process to which the processor is being assigned and to save the context
// of the process being preempted for another ready process
// void Processor_SetAccumulator(int);
// int Processor_GetAccumulator();

// The OS needs to access the PC register to restore the context of
// the process to which the processor is being assigned
void Processor_SetPC(int);

// The OS needs to access register A to when executing the system call management
// routine, so it will be able to know the invoked system call identifier
int Processor_GetRegisterA();

#endif
