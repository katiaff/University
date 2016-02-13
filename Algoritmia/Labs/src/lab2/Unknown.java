package lab2;

public class Unknown {

	public static long unknown(int n) {
		/* What is the complexity of this algorithm? */
		long cont = 0;
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= i; j++)
				for (int k = 1; k <= j; k++)
					cont++;
		return cont;
	}

	public static void main(String arg[]) {
		long c = 0;
		long t1, t2;
		int nTimes = 1000000000;

		for (int n = 1; n <= 100000; n *= 2) {
			t1 = System.currentTimeMillis();

			for (int repetitions = 1; repetitions <= nTimes; repetitions++) {
				c += unknown(n);
			}

			t2 = System.currentTimeMillis();

			System.out.println(c + "**n=" + n + "**TIME=" + (t2 - t1) + "**nTimes=" + nTimes);

			if (t2 - t1 > 3000) {
				nTimes /= 100;
			}
		} // for

	} // main
} // class