package hashtables;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class ClosedHashTableTest {
	protected String[] linearStrings, quadraticStrings;

	@Test
	public void testLinear() {
		ClosedHashTable<String> table = new ClosedHashTable<String>();

		int counter = 0;
		linearStrings = new String[60];

		table.setProbeSequence(table.LINEAR_PROBING);

		String[] insertions = { "one", "two", "three", "four", "five", "six",
				"seven", "eight", "nine", "ten", "eleven", "twelve",
				"thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
				"eighteen", "nineteen", "twenty" };
		String[] unexisting = { "A", "B", "C", "D" };
		int[] insertionSizes = { 3, 5, 7, 11, 11, 13, 17, 17, 19, 23, 23, 29,
				29, 29, 31, 37, 37, 37, 41, 41 };
		int[] removals = { 0, 14, 15, 17, 1, 9, 3, 13, 7, 4, 11, 2, 16, 6, 18,
				12, 8, 19, 5, 10 };
		int[] removalSizes = { 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41,
				41, 19, 19, 19, 7, 7, 3, 1 };

		String[] collisions = { "AaAaAa", "AaAaBB", "AaBBAa", "AaBBBB",
				"BBAaAa", "BBAaBB", "BBBBAa", "BBBBBB" };
		int[] collisionInsertionSizes = { 3, 5, 7, 11, 11, 13, 17, 17 };
		int[] collisionRemovalSizes = { 17, 17, 17, 17, 17, 7, 3, 1 };

		for (int i = 0; i < insertions.length; i++) {
			// We add an element
			assertTrue(table.add(insertions[i]));

			// We check the size of the table
			assertEquals(insertionSizes[i], table.getSize());

			// We check the number of elements
			assertEquals(i + 1, table.getNumElem());

			// We check the element is in the table
			assertEquals(insertions[i], table.find(insertions[i]));

			// We try to add a repeated element
			assertFalse(table.add(insertions[i]));

			// We check the size of the table has not changed
			assertEquals(insertionSizes[i], table.getSize());

			// We check the number of elements has not changed
			assertEquals(i + 1, table.getNumElem());

			// We try to find the element
			assertEquals(insertions[i], table.find(insertions[i]));

			linearStrings[counter] = table.toString();
			counter++;
		}

		// We look for unexisting elements and we try to remove them too
		//
		for (int i = 0; i < unexisting.length; i++) {
			assertEquals(null, table.find(unexisting[i]));
			assertFalse(table.remove(unexisting[i]));
			assertEquals(41, table.getSize());
			assertEquals(20, table.getNumElem());

			linearStrings[counter] = table.toString();
			counter++;
		}

		// We look again for the elements but in a different order
		//
		String element;
		for (int i = insertions.length - 1; i >= 0; i--) {
			element = table.find(insertions[i]);
			assertEquals(insertions[i], element);
		}

		// We (1) find the elements (in an unordered fashion),
		// (2) remove them, (3) try to find them again,
		// (4) try to remove them again, (4) find them again
		// (5) check the size of the table and the number of
		// elements
		for (int i = 0; i < removals.length; i++) {
			element = insertions[removals[i]];
			assertEquals(element, table.find(element));
			assertTrue(table.remove(element));
			assertEquals(null, table.find(element));
			assertEquals(removalSizes[i], table.getSize());
			assertFalse(table.remove(element));
			assertEquals(null, table.find(element));
			assertEquals(removalSizes[i], table.getSize());
			assertEquals(20 - i - 1, table.getNumElem());

			linearStrings[counter] = table.toString();
			counter++;
		}

		// We add now a number of strings that are going to collide
		//
		for (int i = 0; i < collisions.length; i++) {
			String colliding = collisions[i];
			assertTrue(table.add(colliding));
			assertEquals(collisionInsertionSizes[i], table.getSize());
			assertEquals(i + 1, table.getNumElem());

			linearStrings[counter] = table.toString();
			counter++;
		}

		// Now we look for the colliding strings in an unordered way
		//
		for (int i = 0; i < removals.length; i++) {
			int position = removals[i];
			if (position >= 0 && position < collisions.length) {
				String colliding = collisions[position];
				assertEquals(colliding, table.find(colliding));
			}
		}

		// Now we remove the colliding strings and try to look for them
		//
		int j = 0;
		for (int i = 0; i < removals.length; i++) {
			int position = removals[i];
			if (position >= 0 && position < collisions.length) {
				String colliding = collisions[position];
				assertEquals(colliding, table.find(colliding));
				assertTrue(table.remove(colliding));
				assertEquals(collisionRemovalSizes[j], table.getSize());
				assertEquals(8 - j - 1, table.getNumElem());
				j++;
				assertEquals(null, table.find(colliding));
				assertFalse(table.remove(colliding));

				linearStrings[counter] = table.toString();
				counter++;
			}
		}
	}

	@Test
	public void testQuadratic() {
		ClosedHashTable<String> table = new ClosedHashTable<String>();

		int counter = 0;
		quadraticStrings = new String[60];

		table.setProbeSequence(table.QUADRATIC_PROBING);

		String[] insertions = { "one", "two", "three", "four", "five", "six",
				"seven", "eight", "nine", "ten", "eleven", "twelve",
				"thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
				"eighteen", "nineteen", "twenty" };
		String[] unexisting = { "A", "B", "C", "D" };
		int[] insertionSizes = { 3, 5, 7, 11, 11, 13, 17, 17, 19, 23, 23, 29,
				29, 29, 31, 37, 37, 37, 41, 41 };
		int[] removals = { 0, 14, 15, 17, 1, 9, 3, 13, 7, 4, 11, 2, 16, 6, 18,
				12, 8, 19, 5, 10 };
		int[] removalSizes = { 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41,
				41, 19, 19, 19, 7, 7, 3, 1 };

		String[] collisions = { "AaAaAa", "AaAaBB", "AaBBAa", "AaBBBB",
				"BBAaAa", "BBAaBB", "BBBBAa", "BBBBBB" };
		int[] collisionInsertionSizes = { 3, 5, 7, 11, 11, 13, 17, 17 };
		int[] collisionRemovalSizes = { 17, 17, 17, 17, 17, 7, 3, 1 };

		for (int i = 0; i < insertions.length; i++) {
			// We add an element
			assertTrue(table.add(insertions[i]));

			// We check the size of the table
			assertEquals(insertionSizes[i], table.getSize());

			// We check the number of elements
			assertEquals(i + 1, table.getNumElem());

			// We check the element is in the table
			assertEquals(insertions[i], table.find(insertions[i]));

			// We try to add a repeated element
			assertFalse(table.add(insertions[i]));

			// We check the size of the table has not changed
			assertEquals(insertionSizes[i], table.getSize());

			// We check the number of elements has not changed
			assertEquals(i + 1, table.getNumElem());

			// We try to find the element
			assertEquals(insertions[i], table.find(insertions[i]));

			quadraticStrings[counter] = table.toString();
			counter++;
		}

		// We look for unexisting elements and we try to remove them too
		//
		for (int i = 0; i < unexisting.length; i++) {
			assertEquals(null, table.find(unexisting[i]));
			assertFalse(table.remove(unexisting[i]));
			assertEquals(41, table.getSize());
			assertEquals(20, table.getNumElem());

			quadraticStrings[counter] = table.toString();
			counter++;
		}

		// We look again for the elements but in a different order
		//
		String element;
		for (int i = insertions.length - 1; i >= 0; i--) {
			element = table.find(insertions[i]);
			assertEquals(insertions[i], element);
		}

		// We (1) find the elements (in an unordered fashion),
		// (2) remove them, (3) try to find them again,
		// (4) try to remove them again, (4) find them again
		// (5) check the size of the table and the number of
		// elements
		for (int i = 0; i < removals.length; i++) {
			element = insertions[removals[i]];
			assertEquals(element, table.find(element));
			assertTrue(table.remove(element));
			assertEquals(null, table.find(element));
			assertEquals(removalSizes[i], table.getSize());
			assertFalse(table.remove(element));
			assertEquals(null, table.find(element));
			assertEquals(removalSizes[i], table.getSize());
			assertEquals(20 - i - 1, table.getNumElem());

			quadraticStrings[counter] = table.toString();
			counter++;
		}

		// We add now a number of strings that are going to collide
		//
		for (int i = 0; i < collisions.length; i++) {
			String colliding = collisions[i];
			assertTrue(table.add(colliding));
			assertEquals(collisionInsertionSizes[i], table.getSize());
			assertEquals(i + 1, table.getNumElem());

			quadraticStrings[counter] = table.toString();
			counter++;
		}

		// Now we look for the colliding strings in an unordered way
		//
		for (int i = 0; i < removals.length; i++) {
			int position = removals[i];
			if (position >= 0 && position < collisions.length) {
				String colliding = collisions[position];
				assertEquals(colliding, table.find(colliding));
			}
		}

		// Now we remove the colliding strings and try to look for them
		//
		int j = 0;
		for (int i = 0; i < removals.length; i++) {
			int position = removals[i];
			if (position >= 0 && position < collisions.length) {
				String colliding = collisions[position];
				assertEquals(colliding, table.find(colliding));
				assertTrue(table.remove(colliding));
				assertEquals(collisionRemovalSizes[j], table.getSize());
				assertEquals(8 - j - 1, table.getNumElem());
				j++;
				assertEquals(null, table.find(colliding));
				assertFalse(table.remove(colliding));

				quadraticStrings[counter] = table.toString();
				counter++;
			}
		}
	}

	@Test
	public void compareProbing() {
		// We make lots of operations using linear probing
		testLinear();

		// We make the same operations using quadratic probing
		testQuadratic();

		// A number of operations should produce a different
		// internal arrangement of the hash table
		//
		HashSet<Integer> differentResults = new HashSet<Integer>(Arrays.asList(
				5, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27,
				28, 29, 30, 31, 32, 33, 34, 35, 36, 46, 47, 48, 49, 50, 51, 52,
				53, 54, 55, 56, 57));

		// We check that results that should be different are
		// different and those that should be equal are equal...
		//
		for (int i = 0; i < linearStrings.length; i++) {
			if (differentResults.contains(i))
				assertNotEquals(linearStrings[i], quadraticStrings[i]);
			else
				assertEquals(linearStrings[i], quadraticStrings[i]);
		}
	}
}
