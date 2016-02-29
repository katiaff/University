#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAXIMUMSIZE 20
#define DEFAULT_NAME_POSITION = 1
#define DEFAULT_AGE_POSITION = 2
#define DEFAULT_INCOME_POSITION = 3
struct person {
  char name[MAXIMUMSIZE];
  int age;
  float income;
}

main (int argc, char *argv[]) {
  
  struct person teacher={"Fernando", 23, 777.11};
  
  struct person student;
  
  
    strcpy(teacher.name,argv[1]);
    teacher.age=atoi(argv[2]);
    teacher.income=atof(argv[3]);
    
  printf("Teacher name: %s, teacher age: %d, income: %f\n",teacher.name, teacher.age, teacher.income);
  exit(1);
}

// 1. Compile using "gcc EN08Structs.c"
// 2. Execute using "./a.out"
// 3. Modify the programa so that the teacher's name, age and income are obtained from the command line 


