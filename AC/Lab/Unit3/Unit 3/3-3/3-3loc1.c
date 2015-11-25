#define NROWS     8192 // 2^13 rows
#define NCOLUMNS  8192 // 2^13 columns
#define NTIMES    10   // Repeat 10 times

// Matrix size 2^26 = 64 MiB
char matrix[NROWS][NCOLUMNS];  

int main(void) {
  int i, j, rep;

  // Repeat NTIMES to obtain an easy-to-measure elapsed time
  for (rep = 0; rep < NTIMES; rep++) {

    for (i = 0; i < NROWS; i++) {
      for(j = 0; j < NCOLUMNS; j++) {
        matrix[i][j] = 'A';
      }
    }
  }
}
