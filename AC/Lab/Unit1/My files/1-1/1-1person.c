#include <stdio.h>
#include <string.h>

struct _Person
{
	char name[30];
	int	heightcm;
	double weightkg;
};
// This abbreviates the type name
typedef struct _Person Person;

int main(int argc, char* argv[])
{
	Person Peter;
	strcpy(Peter.name, "Peter");
	Peter.heightcm = 175;

	// Assign the weight
	Peter.weightkg = 78.7;
	
	// Show the information of the Peter data structure on the screen
	printf("Peter's height: %d cm; Peter's weight: %f kg\n", Peter.heightcm, Peter.weightkg);

	return 0;
}
