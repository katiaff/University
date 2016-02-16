package lab3;

/* This program can be used to sort n elements with 
 * the best algorithm. It is the QUICKSORT. However, the pivot selected
 * is the first one on the partition, so it has a very bad behavior 
 * (quadratic) 
 */
public class QuicksortFateful {
	static int[] v;

	public static void main(String arg[]) {
		System.out.println("WE ARE GOING TO TEST THAT IT WORKS");

		System.out.println("QUADRATIC TIMES USING A BAD PIVOT");
		long t1, t2;

		for (int n = 100; n < 10000; n *= 2) {
			v = new int[n];
			Vector.sorted(v);

			t1 = System.currentTimeMillis();
			for (int nVeces = 1; nVeces <= 1000; nVeces++)
				quicksort(v); // microseconds
			t2 = System.currentTimeMillis();

			System.out.println("n=" + n + "**TIME=" + (t2 - t1)
					+ " MICROSECONDS");
		}

	}

	private static void quickSort(int elements[], int left, int right) {
		int i = left;
		int j = right - 1;
		int pivot;

		/*
		 * if there is one element it is not necessary instead of the median of
		 * three, here we are using the first element (the first or the last is
		 * usually a bad choice)
		 */
		if (left < right) {
			int toParticionate = left;
			// choose the pivot
			pivot = elements[toParticionate];
			// hide the pivot
			Util.interchange(elements, toParticionate, right);

			do {
				while (elements[i] <= pivot && i < right)
					// first element > pivot
					i++;
				while (elements[j] >= pivot && j > left)
					// first element < pivot
					j--;
				if (i < j)
					Util.interchange(elements, i, j);
			} while (i < j);

			// we set the position of the pivot
			Util.interchange(elements, i, right);
			quickSort(elements, left, i - 1);
			quickSort(elements, i + 1, right);
		}
	}

	public static void quicksort(int[] elements) {
		quickSort(elements, 0, elements.length - 1);
	}
}
