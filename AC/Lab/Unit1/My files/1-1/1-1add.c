#include <stdio.h>

#define	NUM_ELEMENTS 7

int add(int vector[], int length){
	int sum = 0;
	for (int i = 0; i < length; i++){
		sum += vector[i];
	}
	return sum;

}

int main()
{
	int vector[NUM_ELEMENTS] = { 2, 5, -2, 9, 12, -4, 3 };
	int result;

	result = add(vector, NUM_ELEMENTS);
	printf("The addition is: %d\n", result);
	return 0;
}


