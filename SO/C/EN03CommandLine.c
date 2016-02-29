#include <stdio.h>
#include <stdlib.h>

main (int argc, char *argv[]) {

  int i;
  
  printf("The number of strings in the command line is: %d\n", argc);
  
  printf("The complete command line is: \"");
  for (i=1; i<argc;i+=2){
      printf("%s ",argv[i]);
  }
  printf("\"\n");
  exit(1);
}

// 1. Compile using "gcc EN03CommandLine.c"
// 2. Execute using "./a.out"
// 2 Execute using "./a out a e i o u"
// 2 Execute using "./a out 1 2 3 4 5 6 7 8 9"
// 3. Modify the program so that it only shows the strings located in odd positions in the command line
