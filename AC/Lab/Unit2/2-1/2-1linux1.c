#include <stdio.h>
int main()
{
 __asm__(
    "cli" /* Reset IF (bit 9) of EFLAGS */
  );

  __asm__(
    "sti" /* Set IF (bit 9) of EFLAGS */
  );

  return 0;
}
