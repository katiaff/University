package lab3;

/* This program can be used to sort n elements with 
 * a "bad" algorithm (quadratic). 
 * It is the DIRECT INSERTION */
public class Insertion1 {
	static int[] v;

	public static void main(String arg[]) {
		int n = Integer.parseInt(arg[0]); // size of the problem
		v = new int[n];

		Vector.sorted(v);
		System.out.println("VECTOR TO BE SORTED:");
		Vector.write(v);
		insertion(v);
		System.out.println("SORTED VECTOR:");
		Vector.write(v);

		Vector.inverselySorted(v);
		System.out.println("VECTOR TO BE SORTED:");
		Vector.write(v);
		insertion(v);
		System.out.println("SORTED VECTOR:");
		Vector.write(v);

		Vector.random(v, 1000000);
		System.out.println("VECTOR TO BE SORTED:");
		Vector.write(v);
		insertion(v);
		System.out.println("SORTED VECTOR:");
		Vector.write(v);
	}

	public static void insertion(int[] elements) {
		int j;
		int pivot;
		for (int i = 1; i < elements.length; i++) {
			// pivot = current element
			pivot = elements[i];
			// j = previous index
			j = i - 1;

			// while the current element is less than the previous element
			while (j >= 0 && pivot < elements[j]) {
				// move the elements to the right
				elements[j + 1] = elements[j];
				j--;
			}
			// insert the element in the corresponding position
			elements[j + 1] = pivot;

		}
	}

}
