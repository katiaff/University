/* Complete the --(1)-- to --(18)-- gaps */

#include <stdio.h>
#include <atc/linmem.h>
#include "3-5pagetable.h" 

/* From the virtual address in the argument print:
   - The virtual address
   - The associated physical address
   - The content
   - Virtual address of its PTE
   - Physical address of its PTE
   - Content of its PTE
   - Virtual address of its PDE
   - Physical address of its PDE
   - Content of its PDE */
int WriteAddressInformation(void * virtual_addr)
{
	unsigned physical_addr;
	unsigned data_item_32bit;
	void *   pde_addr;
	void *   pte_addr;
	unsigned pde;
	unsigned pte;

	// Retrieve the information associated with the virtual address
	if (linmem((unsigned)virtual_addr, &physical_addr, &data_item_32bit, (unsigned *)(&pde_addr), &pde,
			(unsigned *)(&pte_addr), &pte) != 0)
	{
		fprintf(stderr, "Error in linmem while retrieving virtual_addr\n");
		return -1;
	}
	
	// Print the information associated with the virtual address
	printf("Virtual addr. = %.8Xh\n", (unsigned)virtual_addr);
	printf("Physical addr = %.8Xh\n", physical_addr);
	printf("Content       = %.8Xh\n", data_item_32bit);
	printf("-----------------------------\n");

	// Retrieve the physical address of the PTE
	if (linmem((unsigned)pte_addr, &physical_addr, NULL, NULL, NULL, NULL, NULL) != 0)
	{
		fprintf(stderr, "Error in linmen while retrieving pte_addr\n");
		return -1;
    }

	// Print the information associated with the PTE
	printf("PTE virtual addr.  = %.8Xh\n", (unsigned)pte_addr);
	printf("PTE physical addr. = %.8Xh\n", physical_addr);
	printf("PTE content        = %.8Xh\n", pte);
	printf("------------------------------\n");

	// Retrieve the physical address of the PDE
	if (linmem((unsigned)pde_addr, &physical_addr, NULL, NULL, NULL, NULL, NULL) != 0)
	{
		fprintf(stderr, "Error in linmem while retrieving pde_addr\n");
		return -1;
	}

	// Print the information associated with the PDE
	printf("PDE virtual addr.  = %.8Xh\n", (unsigned)pde_addr);
	printf("PDE physical addr. = %.8Xh\n", physical_addr);
	printf("PDE content        = %.8Xh\n", pde);
	printf("------------------------------\n");

	return 0;
}


/* Print the virtual page number, as well as the PDE and PTE of all
	 the pages in physical memory with, at least, a virtual address in
	 the [min_virtual_addr, max_virtual_addr] range and its physical
	 address in the [min_physical_addr, max_physical_addr] range. */
int WriteMemoryPages(void * min_virtual_addr, void * max_virtual_addr,
		unsigned min_physical_addr, unsigned max_physical_addr)
{
	unsigned min_pdi, max_pdi;
	unsigned pdi, pti;
	unsigned pde, pte;
	void *   initial_virtual_addr;
	void *   final_virtual_addr;
	unsigned initial_physical_addr, final_physical_addr;

	printf("Virtual page\tPDE\t\tPTE\n");
	printf("------------\t---\t\t---\n");

	min_pdi = (unsigned)min_virtual_addr >> 22;  /* Minimum PDI */
	max_pdi = (unsigned)max_virtual_addr >> 22 ; /* Maximum PDI */

	// For each entry in the page directory...
	for (pdi = min_pdi; pdi <= max_pdi; pdi++)
	{
		// Calculate the first virtual address with PDI = pdi
		initial_virtual_addr = (void *)(pdi << 22);

		// Get the PDE
/*
extern int linmem(unsigned int virtual_addr, unsigned int *physical_addrp,
unsigned int *data_itemp, unsigned int *pde_addressp, unsigned int *pdep,
unsigned int *pte_addressp, unsigned int *ptep);

*/
// pointers are output parameters
// load linmem 
		if (linmem((unsigned)initial_virtual_addr, NULL, NULL, NULL, &pde,NULL, NULL) == -2)
			return -1;

		// If the present bit of the PDE is set...
		if (pde & 0x01)
		{
			if (pde & 0x80)  // If it is a 4 MiB page...
			{
				// Final virtual address of the page
				// 0000 0000 0011 1111 1111 1111 1111 1111
				final_virtual_addr = initial_virtual_addr + 0x003FFFFF;

				// Initial physical address of the page
				initial_physical_addr = pde & 0xFFC00000;

				// Final physical address of the page
				final_physical_addr = initial_physical_addr + 0x003FFFFF;

				// The page is in the virtual address range
				// Check whether the virtual page includes any of the physical addresses
				if (initial_physical_addr <= max_physical_addr &&
						final_physical_addr >= min_physical_addr)
					printf("%.3Xh  \t\t%.8Xh\t---------\n", pdi, pde);
			}
			else // If the PDE points to a page table...
			{
				for (pti = 0 ; pti < 1024 ; pti++) // For each PTI...
				{
					// Calculate the first virtual address with PDI = pdi and PTI = pti
					initial_virtual_addr = (void *)((pdi << 22) | (pti << 12));

					// Retrieve the PTE
					if (linmem((unsigned)initial_virtual_addr, NULL, NULL, NULL, NULL,NULL, &pte) == -2)
						return -1;

					// If the present bit of the PTE is set...
					if (pte & 0x01)
					{
						// Final virtual address of the page
						// 4 KiB = 2^12 bytes = 0x00000FFF
						final_virtual_addr = initial_virtual_addr + 0x00000FFF;

						// Initial physical address of the page
						initial_physical_addr = pte & 0xFFFFF000;

						// Final physical address of the page
						final_physical_addr = initial_physical_addr + 0x00000FFF;

						// Check whether the virtual page includes any of the
						// virtual or physical addresses of the given ranges
						if (initial_virtual_addr <= max_virtual_addr &&
								final_virtual_addr   >= min_virtual_addr &&
								initial_physical_addr  <= max_physical_addr &&
								final_physical_addr    >= min_physical_addr)
							printf("%.5Xh\t\t%.8Xh\t%.8Xh\n",
									(unsigned)initial_virtual_addr >> 12, pde, pte);
					}
				}
			}
		}
	}
	
	return 0;
}
