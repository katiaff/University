/*
	Compile with the following command:
     gcc 3-5paging.c 3-5pagetable.c  -o 3-5paging -lmem
 */

#include <stdio.h>
#include <stdlib.h>
#include "3-5pagetable.h"

#define ARRAY_LENGTH 10000

int global[ARRAY_LENGTH]; // Global array of ARRAY_LENGTH integers

int main(void)
{
	void * p = NULL;
	void * virtual_addr;

	// Virtual address in the global array depending on the student ID
	virtual_addr = (void *) (global + (DNI % ARRAY_LENGTH));

	// Force the address to be in memory and not in disc
	*((int *)virtual_addr) = 0x12345678;

	if (WriteAddressInformation(virtual_addr) != 0)
	{
		fprintf(stderr, "Error in WriteAddressInformation\n");
		return -1;
	}

	// Print the PDE and PTE of the virtual pages associated to the task
	// that are in physical memory before requesting memory to the OS
	printf("\n---Virtual pages of the task before requesting memory---\n");
	if (WriteMemoryPages((void *)0x00000000, (void *)0xBFFFFFFF,
			0x00000000, 0xFFFFFFFF) != 0)
	{
		fprintf(stderr, "Error in WriteMemoryPages before requesting memory to the OS\n");
		return -1;
	}

	// Request memory to the OS
	if ((p = malloc(((DNI % 20) + 1) * 4000)) == NULL)
    {
		fprintf(stderr, "Error in malloc\n");
		return -1;
    }
	else
	{
		printf("\n---Memory provided from the address %.8Xh---\n",
				(unsigned)p);
	}

	// Print the PDE and PTE of the virtual pages associated to the task
	// that are in physical memory after requesting memory to the OS
	printf("\n---Virtual pages of the task after requesting memory---\n");
	if (WriteMemoryPages((void *)0x00000000, (void *)0xBFFFFFFF,
			0x00000000, 0xFFFFFFFF) != 0)
	{
		fprintf(stderr, "Error in WriteMemoryPages after requesting memory to the OS\n");
		return -1;
	}

	// Free the requested memory
	free(p);

	// Print the PDE and PTE of the virtual pages associated to the peripheral
	// interface that are in physical memory
	printf("\n---Virtual pages of the interface---\n");
	if (WriteMemoryPages((void *)0xC0000000, (void *)0xFFFFFFFF,
			MIN_INTERFACE_ADDR, MAX_INTERFACE_ADDR) != 0)
	{
		fprintf(stderr, "Error in WriteMemoryPages when retrieving the interface\n");
		return -1;
	}

	// Print the PDE and PTE of the virtual pages associated to the OS
	printf("\n---Virtual pages of the OS---\n");
	if (WriteMemoryPages((void *)0xC0000000, (void *)0xFFFFFFFF,
			0x00000000, 0xFFFFFFFF) != 0)
	{
		fprintf(stderr, "Error in WriteMemoryPages while retrieving the OS\n");
		return -1;
	}

	return 0;
}
