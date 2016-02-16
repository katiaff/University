package lab3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Bubble1Test {

	@Test
	public void test() {
		int[] elements = new int[] { 0, 4, 2, 1, 3, 9, 5, 7, 8, 6 };

		Bubble1.bubble(elements);

		assertCorrectOrder(elements);

	}

	private void assertCorrectOrder(int[] elements) {
		for (int i = 0; i < elements.length; i++) {
			assertEquals(i, elements[i]);
		}
	}

}
