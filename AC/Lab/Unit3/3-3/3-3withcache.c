#include <stdlib.h>
#include <stdio.h>

#define NTIMES 50000 // Repeat 50000 times

char page[4096]; // 4096 bytes

int main(void) {
  int rep, i;

  for (rep = 0; rep < NTIMES; rep++) {

    // Print message each 1000 iterations
    if (rep % 1000 == 0) {
      printf("N= %d\n", rep);
      fflush(stdout);
    }

    // Access to all page locations
    for(i = 0; i < 4096; i++)
      page[i] = 'A';

  }

  exit(0);
}
