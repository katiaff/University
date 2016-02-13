package lab1;

/**
 * Class that changes automatically the nTimes variable based on the number of
 * ms the last iteration took
 **/
public class Runner {
	static int[] v;

	public static void main(String arg[]) {
		int nTimes = 1000000000;
		long t1 = 0, t2 = 0;
		int sum = 0;

		for (int n = 10; n <= 100000000; n *= 5) { // n is increased *5
			v = new int[n];
			Vector1.fillIn(v);

			t1 = System.currentTimeMillis();
			// We have to repeat the whole process to be measured
			for (int repetition = 1; repetition <= nTimes; repetition++) {
				Vector1.maximum(v, new int[] { 0, 0 });
			}
			t2 = System.currentTimeMillis();
			System.out.println("SIZE = " + n + " ** " + "TIME = " + (t2 - t1) + "ms ** " + " SUM = " + sum
					+ " ** nTimes = " + nTimes);

			if (t2 - t1 > 2000) {
				nTimes /= 100;
			}

		}

	}
}
