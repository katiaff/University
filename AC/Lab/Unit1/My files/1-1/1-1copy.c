#include <stdio.h>

void copy(char* source, char* destination, unsigned int lengthDestination){
	for (int i = 0; i < lengthDestination; i++){
		char fromSource = source[i];
		if (fromSource != 0){
			destination[i] = fromSource;
		}	
	
	}

}

int main(){
	char* source = "source";
	char* destination = "destination";

	copy(source, destination, 11);

	printf("source is: %s; destination is %s", source, destination);

}
