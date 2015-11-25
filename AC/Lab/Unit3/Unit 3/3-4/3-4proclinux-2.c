// This source file must be compiled with the following command:
//   gcc -Wall 3-4proclinux-2.c -o 3-4proclinux-2 -lrt -lmem 

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/mman.h>
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
  char string[30]; /* String with the memory area name */
  void *p;         /* Pointer to the requested memory area */
  int memd;        /* Descriptor of the requested memory area */

  /////////////////////////////////////
  // A 16-KiB area memory is requested
  ////////////////////////////////////

  /* Opens a descriptor of the memory area which depends on the PID */
  sprintf(string, "/mem-%d", getpid());
  if ((memd = shm_open(string, O_RDWR|O_CREAT|O_EXCL, S_IRWXU)) < 0)
    return(-1); /* If an error occurs the task is finished */

  /* Defines the size of the memory area */
  if (ftruncate(memd, 16*1024) < 0)
    {
      shm_unlink(string); /* Closes the memory descriptor */
      return(-1); /* If an error occurs the task is finished */
    }

  /* Maps the memory area in the virtual address space of the task */
  if ((p = mmap(NULL, 16*1024, PROT_READ|PROT_WRITE, 
                MAP_SHARED, memd, 0)) == MAP_FAILED)
    {
      shm_unlink(string); /* Closes the memory descriptor */
      return(-1); /* If an error occurs the task is finished */
    }

  
  ////////////////////////////////////////////////////////////////
  // What is the physical address associated with the memory area?
  ////////////////////////////////////////////////////////////////
  print_virtual_physical_data(p, "The requested memory area\n"
                                 "-----------------------------\n");

  printf("\n---- Press [ENTER] to continue");
  getchar();

  return 0;
}
