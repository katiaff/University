package lab3;

/* This program can be used to sort n elements with 
 * a "bad" algorithm (quadratic). 
 * It is the SELECTION */
public class Selection1 {
	static int[] v;

	public static void main(String arg[]) {
		int n = Integer.parseInt(arg[0]); // size of the problem
		v = new int[n];

		Vector.sorted(v);
		System.out.println("VECTOR TO BE SORTED:");
		Vector.write(v);
		selection(v);
		System.out.println("SORTED VECTOR:");
		Vector.write(v);

		Vector.inverselySorted(v);
		System.out.println("VECTOR TO BE SORTED:");
		Vector.write(v);
		selection(v);
		System.out.println("SORTED VECTOR:");
		Vector.write(v);

		Vector.random(v, 1000000);
		System.out.println("VECTOR TO BE SORTED:");
		Vector.write(v);
		selection(v);
		System.out.println("SORTED VECTOR:");
		Vector.write(v);
	}

	public static void selection(int[] elements) {
		for (int i = 0; i < elements.length; i++) {
			int minPos = Util.findPosMin(elements, i);
			Util.interchange(elements, i, minPos);
		}
	}
}
