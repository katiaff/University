package lab1;

/**
 * This program serves to measure times automatically increasing the size of the
 * problem (n) and also using a time scale determined by nTimes, which is taken
 * from the argument arg[1] It also gets as an execution argument (arg[0]) the
 * operation on which we will focus the measurement (options 0,1,2 respectively)
 * 
 * @author viceg
 */
public class Diagonal2 {
	static int[][] a;

	public static void main(String arg[]) {
		int option = Integer.parseInt(arg[0]); // selected option
		int nTimes = Integer.parseInt(arg[1]); // nTimes
		long t1, t2;
		int n = 3;
		int sum = 0;

		while (n <= 768000) {
			a = new int[n][n];
			t1 = System.currentTimeMillis();
			if (option == 0) { // fill in the matrix
				for (int i = 0; i < nTimes; i++) {
					Diagonal1.fillIn(a);
				}
			} // if
			else if (option == 1) { // sum of the diagonal (one way)
				for (int i = 0; i < nTimes; i++) {
					sum = Diagonal1.sum1Diagonal(a);
				}
			} // else if
			else if (option == 2) { // sum of the diagonal (another way)
				for (int i = 0; i < nTimes; i++) {
					sum = Diagonal1.sum2Diagonal(a);
				}
			} // else if
			else
				System.out.println("INCORRECT OPTION");
			System.out.println(sum);
			t2 = System.currentTimeMillis();
			System.out.println("SIZE = " + n + " ** " + "TIME = " + (t2 - t1) + "ms ** " + "SUM: " + sum
					+ " ** nTimes = " + nTimes);

			n *= 2;
			if (t2 - t1 > 3000) {
				nTimes /= 100;
			}
		}
	} // main
} // class
