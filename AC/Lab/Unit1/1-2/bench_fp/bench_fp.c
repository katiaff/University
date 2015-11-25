#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <time.h>
#include <math.h>

void Task();
void DoFloatingPoint(unsigned long nElements, unsigned long nTimes);

int main(int argc, char* argv[])
{	
	struct timespec tStart, tEnd;
	double dElapsedTimeS;

	// Start measuring time
	if (<<Complete>>)
	{
		printf("ERROR: clock_gettime: %d.\n", errno);
		exit(EXIT_FAILURE);
	}

	printf("Running task    : ");
	fflush(stdout);
	Task();
	printf("Finished\n");	

	// Finish measuring time
	if (<<Complete>>)
	{
		printf("ERROR: clock_gettime: %d.\n", errno);
		exit(EXIT_FAILURE);
	}

	// Show the elapsed time
	<<Complete>>
	printf("Elapsed time    : %f s.\n", dElapsedTimeS);

	return 0;
}

void Task()
{
	DoFloatingPoint(100000, 100);
	DoFloatingPoint(10000, 1000);
	DoFloatingPoint(1000, 10000);
	DoFloatingPoint(100, 100000);
}

void DoFloatingPoint(unsigned long nElements, unsigned long nTimes)
{
	unsigned long i, j;
	static unsigned int seed = 0;

	double *pdSrc1 = (double*)malloc(nElements * sizeof(double));
	double *pdSrc2 = (double*)malloc(nElements * sizeof(double));
	double *pdDest = (double*)malloc(nElements * sizeof(double));
	if (pdSrc1 == NULL || pdSrc2 == NULL || pdDest == NULL)
	{
		free(pdSrc1);
		free(pdSrc2);
		free(pdDest);
		printf("ERROR in DoFloatingPoint: Cannot allocate memory\n");
		return;
	}

	srand(seed++);
	for (i = 0; i < nElements; i++)
	{
		// Pseudo-random numbers in the interval [1.0-2.0]
		pdSrc1[i] = 1.0 + ((double)rand()/(double)RAND_MAX);
		pdSrc2[i] = 1.0 + ((double)rand()/(double)RAND_MAX);
	}
	
	for (j = 0; j < nTimes; j++)
	{
		for (i = 0; i < nElements; i++)
		{
			pdDest[i] = pdSrc1[i] / pdSrc2[i];
			pdDest[i] = pdDest[i] * pdSrc1[i];
			pdDest[i] = pdDest[i] + pdSrc1[i];
			pdDest[i] = pdDest[i] - pdSrc2[i];		
			pdDest[i] = sqrt(pdDest[i]);
			pdDest[i] = sin(pdDest[i]);					
		}
	} 

	free(pdSrc1);
	free(pdSrc2);
 	free(pdDest);
}