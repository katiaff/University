/* Gaps (1) to (3) must be completed with the appropriate values */

#define DNI                   71727346 /* Example 12345678   */
#define MIN_INTERFACE_ADDR    0xdfdfc000 /* Example 0x10000000 */
#define MAX_INTERFACE_ADDR    0xdfdfffff /* Example 0x10101FFF */

int WriteAddressInformation(void * virtual_addr);
int WriteMemoryPages(void * min_virtual_addr, void * max_virtual_addr,
		unsigned min_physical_addr, unsigned max_physical_addr);
