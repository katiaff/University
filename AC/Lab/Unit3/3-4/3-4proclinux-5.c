// This source file must be compiled with the following command:
//   gcc -Wall 3-4proclinux-5.c -o 3-4proclinux-5 -lmem

#include <stdio.h>
#include <stdlib.h>
#include "atc/linmem.h"

// Given a virtual address, this virtual address is displayed on
// the screen, as well as its associated physical address and the
// 32-bit data item stored in memory from this address. Above these
// addresses the title passed as argument is also displayed.
void print_virtual_physical_data(void *virtual_addrp, char title[])
{
  unsigned int virtual_addr;
  unsigned int physical_addr;
  unsigned int data_item;

  // Converts the virtual address in an unsigned integer
  virtual_addr = (unsigned int)virtual_addrp;

  // Print the title
  printf("\n");
  printf("%s", title);

  // Retrieves the physical address and the data item associated to the virtual address
  switch (linmem(virtual_addr, &physical_addr, &data_item, NULL, NULL, NULL, NULL))
  {
  case 0: // OK
    printf("Virtual address: \t%.8Xh\n"
           "Physical address:\t%.8Xh\n"
           "Data item stored:\t%.8Xh\n",
	   virtual_addr, physical_addr, data_item);
    break;
  case -1: // The virtual page has not associated a page frame
    fprintf(stderr, "The virtual page %.5Xh does not have a page frame associated\n", 
	    (virtual_addr>>12));
    break;
  case -2: // Driver unavailable
    fprintf(stderr, "Error: linmem driver is not available\n");
    break;
  }
}
 

//////////////////////////////////////////////////////////////////////////

int main(void)
{
  void *p;         /* Pointer to the requested memory area */

  ////////////////////////////////////
  // A 16-KiB memory area is requested
  ////////////////////////////////////

  if ((p = malloc(16*1024)) == NULL)
    {
      fprintf(stderr, "Error in malloc\n");
      return(-1);
    }
    
  
  ////////////////////////////////////////////////////////////////
  // What is the physical address associated with the memory area?
  ////////////////////////////////////////////////////////////////
  print_virtual_physical_data(p, "The requested memory area\n"
                                 "-----------------------------\n");

  printf("\n---- Press [ENTER] to continue");
  getchar();

  /////////////////////////////////////
  // The 16-KiB memory are is released
  /////////////////////////////////////
  free(p);

  return 0;
}
