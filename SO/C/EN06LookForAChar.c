#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define DEFAULT_CHAR 'a'
#define DEFAULT_STRING "Fernando Alvarez Garcia"

#define STRING_COMMAND_LINE_POSITION 1
#define CHAR_COMMAND_LINE_POSITION 2

main (int argc, char *argv[]) {
  
  if (argc==1)
    searchAndCount(DEFAULT_CHAR, DEFAULT_STRING);
  else
    if (argc==2)
      searchAndCount(DEFAULT_CHAR, argv[STRING_COMMAND_LINE_POSITION]);
    else
      searchAndCount(argv[CHAR_COMMAND_LINE_POSITION][0], argv[STRING_COMMAND_LINE_POSITION]);
  exit(1);
}

void searchAndCount(char character, char *string) {
  
  int i;
  int length=strlen(string);
  int sum = 0;
  
  printf("Searching in string %s\n",string);
  for (i=0;i<length;i++){
    if (string[i]==character){
      printf("Position %d of the string contains character [%c]\n",i,character);
      sum++;    
    }
  }
  printf("Total number of characters '%c' in the string is %d\n", character, sum);

}
  
// 1. Compile using "gcc EN06LookForAChar.c"
// 2. Execute using "./a.out"
// 3. Modify the searchAndCount function so that it counts and returns the number of matching characters in the string

