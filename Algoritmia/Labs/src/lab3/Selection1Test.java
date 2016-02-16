package lab3;

import static org.junit.Assert.*;

import org.junit.Test;

public class Selection1Test {

	@Test
	public void test() {
		int[] elements = new int[] { 0, 4, 2, 1, 3, 9, 5, 7, 8, 6 };

		Selection1.selection(elements);

		assertCorrectOrder(elements);
		
		elements = new int[] { 1, 0, 3, 2, 5, 4, 8, 7, 9, 6 };
		
		Selection1.selection(elements);

		assertCorrectOrder(elements);

	}

	private void assertCorrectOrder(int[] elements) {
		for (int i = 0; i < elements.length; i++) {
			assertEquals(i, elements[i]);
		}
	}

}
