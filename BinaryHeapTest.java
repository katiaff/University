package heap;

import static org.junit.Assert.*;

import org.junit.Test;

public class BinaryHeapTest {

	public BinaryHeap<Integer> createHeap() {
		int numbers[] = new int[] { 10, 3, 4, 8, 2, 9, 7, 1, 6, 5 };
		BinaryHeap<Integer> heap = new BinaryHeap<Integer>();

		assertTrue(heap.isEmpty());

		assertTrue(heap.add(numbers[0]));
		assertEquals("10", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[1]));
		assertEquals("3 10", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[2]));
		assertEquals("3 10 4", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[3]));
		assertEquals("3 8 4 10", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[4]));
		assertEquals("2 3 4 10 8", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[5]));
		assertEquals("2 3 4 10 8 9", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[6]));
		assertEquals("2 3 4 10 8 9 7", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[7]));
		assertEquals("1 2 4 3 8 9 7 10", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[8]));
		assertEquals("1 2 4 3 8 9 7 10 6", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[9]));
		assertEquals("1 2 4 3 5 9 7 10 6 8", heap.internalString());
		assertFalse(heap.isEmpty());

		return heap;
	}

	public BinaryHeap<Integer> createHeap2() {
		int numbers[] = new int[] { 8, 7, 14, 17, 9, 18, 11, 12, 4, 3, 6, 2, 15, 1, 20, 10 };
		BinaryHeap<Integer> heap = new BinaryHeap<Integer>();

		assertTrue(heap.isEmpty());

		assertTrue(heap.add(numbers[0]));
		assertEquals("8", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[1]));
		assertEquals("7 8", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[2]));
		assertEquals("7 8 14", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[3]));
		assertEquals("7 8 14 17", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[4]));
		assertEquals("7 8 14 17 9", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[5]));
		assertEquals("7 8 14 17 9 18", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[6]));
		assertEquals("7 8 11 17 9 18 14", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[7]));
		assertEquals("7 8 11 12 9 18 14 17", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[8]));
		assertEquals("4 7 11 8 9 18 14 17 12", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[9]));
		assertEquals("3 4 11 8 7 18 14 17 12 9", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[10]));
		assertEquals("3 4 11 8 6 18 14 17 12 9 7", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[11]));
		assertEquals("2 4 3 8 6 11 14 17 12 9 7 18", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[12]));
		assertEquals("2 4 3 8 6 11 14 17 12 9 7 18 15", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[13]));
		assertEquals("1 4 2 8 6 11 3 17 12 9 7 18 15 14", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[14]));
		assertEquals("1 4 2 8 6 11 3 17 12 9 7 18 15 14 20", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[15]));
		assertEquals("1 4 2 8 6 11 3 10 12 9 7 18 15 14 20 17", heap.internalString());
		assertFalse(heap.isEmpty());

		return heap;
	}

	@Test
	public void testAdd() {
		createHeap();
		createHeap2();
	}

	@Test
	public void testRemove() {
		BinaryHeap<Integer> heap = createHeap();
		System.out.println(heap.internalString());
		assertTrue(heap.remove(10));
		System.out.println(heap.internalString());
		assertFalse(heap.remove(100));
		assertTrue(heap.remove(4));
		assertFalse(heap.remove(40));
		assertTrue(heap.remove(8));
		assertFalse(heap.remove(80));
		assertTrue(heap.remove(2));
		assertFalse(heap.remove(20));
		assertTrue(heap.remove(6));
		assertFalse(heap.remove(60));

		String poll = "";
		for (int i = 0; i <= 100; i++) {
			Integer x = heap.poll();
			if (x != null)
				poll += x + "\t";
		}
		poll = poll.trim();
		assertEquals(poll, "1	3	5	7	9");

		heap = createHeap2();
		assertTrue(heap.remove(10));
		assertFalse(heap.remove(100));
		assertTrue(heap.remove(4));
		assertFalse(heap.remove(40));
		assertTrue(heap.remove(8));
		assertFalse(heap.remove(80));
		assertTrue(heap.remove(2));
		assertTrue(heap.remove(20));
		assertTrue(heap.remove(6));
		assertFalse(heap.remove(60));

		poll = "";
		for (int i = 0; i <= 100; i++) {
			Integer x = heap.poll();
			if (x != null)
				poll += x + "\t";
		}
		poll = poll.trim();
		assertEquals(poll, "1	3	7	9	11	12	14	15	17	18");
	}

	@Test
	public void testClear() {
		BinaryHeap<Integer> heap = createHeap();
		heap.clear();
		assertTrue(heap.isEmpty());
		assertEquals("", heap.internalString());

		String poll = "";
		for (int i = 0; i < 100; i++) {
			Integer x = heap.poll();
			if (x != null)
				poll += x + "\t";
		}
		poll = poll.trim();
		assertEquals("", poll);

		heap = createHeap2();
		heap.clear();
		assertTrue(heap.isEmpty());
		assertEquals("", heap.internalString());

		poll = "";
		for (int i = 0; i <= 100; i++) {
			Integer x = heap.poll();
			if (x != null)
				poll += x + "\t";
		}
		poll = poll.trim();
		assertEquals(poll, "");
	}

	@Test
	public void testAddAfterClear() {
		/*
		 * BinaryHeap<Integer> heap=createHeap(); heap.clear();
		 * assertTrue(heap.isEmpty()); assertEquals(heap.toString(),"");
		 */
		BinaryHeap<Integer> heap = new BinaryHeap<Integer>();

		heap = createHeap2();

		String poll = "";
		// for (int i=0;i<20;i++) {
		while (!heap.isEmpty()) {
			Integer x = heap.poll();
			System.out.println(heap.internalString());
			if (x != null)
				poll += x + "\t";
		}
		poll = poll.trim();
		System.out.println(poll);
		assertEquals("1	2	3	4	6	7	8	9	10	11	12	14	15	17	18	20", poll);
	}

	@Test
	public void testAddAndPoll() {
		BinaryHeap<Integer> heap = new BinaryHeap<Integer>();

		assertTrue(heap.isEmpty());
		heap = createHeap3();
		assertFalse(heap.isEmpty());
		// assertTrue(heap.add(8));
		// assertFalse(heap.isEmpty());
		// assertEquals("8", heap.toString());
		// assertTrue(heap.add(7));
		// assertEquals("8(7)", heap.toString());
		// assertTrue(heap.add(14));
		// assertEquals("8(7)14", heap.toString());
		// assertTrue(heap.add(17));
		// assertEquals("[17(8)](7)14", heap.toString());
		// assertTrue(heap.add(9));
		// assertEquals("[17(8)9](7)14", heap.toString());
		// assertTrue(heap.add(18));
		// assertEquals("[17(8)9](7)[18(14)]", heap.toString());
		// assertTrue(heap.add(11));
		// assertEquals("[17(8)9](7)[18(11)14]", heap.toString());
		// assertTrue(heap.add(12));
		// assertEquals("[[17(12)](8)9](7)[18(11)14]", heap.toString());
		// assertTrue(heap.add(4));
		// assertEquals("[[17(8)12](7)9](4)[18(11)14]", heap.toString());
		// assertTrue(heap.add(3));
		// assertEquals("[[17(8)12](4)[9(7)]](3)[18(11)14]", heap.toString());
		// assertTrue(heap.add(6));
		// assertEquals("[[17(8)12](4)[9(6)7]](3)[18(11)14]", heap.toString());
		// assertTrue(heap.add(2));
		// assertEquals("[[17(8)12](4)[9(6)7]](2)[[18(11)](3)14]",
		// heap.toString());
		// assertTrue(heap.add(15));
		// assertEquals("[[17(8)12](4)[9(6)7]](2)[[18(11)15](3)14]",
		// heap.toString());
		// assertTrue(heap.add(1));
		// assertEquals("[[17(8)12](4)[9(6)7]](1)[[18(11)15](2)[14(3)]]",
		// heap.toString());
		// assertTrue(heap.add(20));
		// assertEquals("[[17(8)12](4)[9(6)7]](1)[[18(11)15](2)[14(3)20]]",
		// heap.toString());

		//
		assertEquals(1, ((int) heap.poll()));
		// assertEquals("[[17(8)12](4)[9(6)7]](2)[[18(11)15](3)[20(14)]]",
		// heap.toString());
		assertEquals("2 4 3 8 6 11 14 17 12 9 7 18 15 20", heap.internalString());
		assertEquals(2, ((int) heap.poll()));
		// assertEquals("[[17(8)12](4)[9(6)7]](3)[[18(15)20](11)14]",
		// heap.toString());
		assertEquals("3 4 11 8 6 15 14 17 12 9 7 18 20", heap.internalString());
		assertEquals(3, ((int) heap.poll()));
		// assertEquals("[[17(8)12](6)[9(7)20]](4)[[18(15)](11)14]",
		// heap.toString());
		assertEquals("4 6 11 8 7 15 14 17 12 9 20 18", heap.internalString());
		assertEquals(4, ((int) heap.poll()));
		// assertEquals("[[17(8)12](7)[18(9)20]](6)[15(11)14]",
		// heap.toString());
		assertEquals("6 7 11 8 9 15 14 17 12 18 20", heap.internalString());
		assertEquals(6, ((int) heap.poll()));
		// assertEquals("[[17(12)20](8)[18(9)]](7)[15(11)14]", heap.toString());
		assertEquals("7 8 11 12 9 15 14 17 20 18", heap.internalString());
		assertEquals(7, ((int) heap.poll()));
		assertEquals("8 9 11 12 18 15 14 17 20", heap.internalString());
		// assertEquals("[[17(12)20](9)18](8)[15(11)14]", heap.toString());
		assertEquals(8, ((int) heap.poll()));
		assertEquals("9 12 11 17 18 15 14 20", heap.internalString());
		// assertEquals("[[20(17)](12)18](9)[15(11)14]", heap.toString());
		assertEquals(9, ((int) heap.poll()));
		assertEquals("11 12 14 17 18 15 20", heap.internalString());
		// assertEquals("[17(12)18](11)[15(14)20]", heap.toString());
		assertEquals(11, ((int) heap.poll()));
		assertEquals("12 17 14 20 18 15", heap.internalString());
		// assertEquals("[20(17)18](12)[15(14)]", heap.toString());
		assertEquals(12, ((int) heap.poll()));
		assertEquals("14 17 15 20 18", heap.internalString());
		// assertEquals("[20(17)18](14)15", heap.toString());
		assertEquals(14, ((int) heap.poll()));
		assertEquals("15 17 18 20", heap.internalString());
		// assertEquals("[20(17)](15)18", heap.toString());
		assertEquals(15, ((int) heap.poll()));
		assertEquals("17 20 18", heap.internalString());
		// assertEquals("20(17)18", heap.toString());
		assertEquals(17, ((int) heap.poll()));
		assertEquals("18 20", heap.internalString());
		// assertEquals("20(18)", heap.toString());
		assertEquals(18, ((int) heap.poll()));
		assertEquals("20", heap.internalString());
		// assertEquals("20", heap.toString());
		assertEquals(20, ((int) heap.poll()));
		assertEquals("", heap.internalString());
		// assertEquals("", heap.toString());
		assertTrue(heap.isEmpty());
	}

	private BinaryHeap<Integer> createHeap3() {
		int numbers[] = new int[] { 8, 7, 14, 17, 9, 18, 11, 12, 4, 3, 6, 2, 15, 1, 20 };
		BinaryHeap<Integer> heap = new BinaryHeap<Integer>();

		assertTrue(heap.isEmpty());

		assertTrue(heap.add(numbers[0]));
		assertEquals("8", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[1]));
		assertEquals("7 8", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[2]));
		assertEquals("7 8 14", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[3]));
		assertEquals("7 8 14 17", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[4]));
		assertEquals("7 8 14 17 9", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[5]));
		assertEquals("7 8 14 17 9 18", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[6]));
		assertEquals("7 8 11 17 9 18 14", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[7]));
		assertEquals("7 8 11 12 9 18 14 17", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[8]));
		assertEquals("4 7 11 8 9 18 14 17 12", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[9]));
		assertEquals("3 4 11 8 7 18 14 17 12 9", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[10]));
		assertEquals("3 4 11 8 6 18 14 17 12 9 7", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[11]));
		assertEquals("2 4 3 8 6 11 14 17 12 9 7 18", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[12]));
		assertEquals("2 4 3 8 6 11 14 17 12 9 7 18 15", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[13]));
		assertEquals("1 4 2 8 6 11 3 17 12 9 7 18 15 14", heap.internalString());
		assertFalse(heap.isEmpty());

		assertTrue(heap.add(numbers[14]));
		assertEquals("1 4 2 8 6 11 3 17 12 9 7 18 15 14 20", heap.internalString());
		assertFalse(heap.isEmpty());

		return heap;
	}

	@Test
	public void testAddAndRemove() {
		BinaryHeap<Integer> heap = new BinaryHeap<Integer>();

		assertTrue(heap.isEmpty());
		assertTrue(heap.add(8));
		assertFalse(heap.isEmpty());
		assertEquals("8", heap.toString());
		assertTrue(heap.add(7));
		assertEquals("8(7)", heap.toString());
		assertTrue(heap.add(14));
		assertEquals("8(7)14", heap.toString());
		assertTrue(heap.add(17));
		assertEquals("[17(8)](7)14", heap.toString());
		assertTrue(heap.add(9));
		assertEquals("[17(8)9](7)14", heap.toString());
		assertTrue(heap.add(18));
		assertEquals("[17(8)9](7)[18(14)]", heap.toString());
		assertTrue(heap.add(11));
		assertEquals("[17(8)9](7)[18(11)14]", heap.toString());
		assertTrue(heap.add(12));
		assertEquals("[[17(12)](8)9](7)[18(11)14]", heap.toString());
		assertTrue(heap.add(4));
		assertEquals("[[17(8)12](7)9](4)[18(11)14]", heap.toString());
		assertTrue(heap.add(3));
		assertEquals("[[17(8)12](4)[9(7)]](3)[18(11)14]", heap.toString());
		assertTrue(heap.add(6));
		assertEquals("[[17(8)12](4)[9(6)7]](3)[18(11)14]", heap.toString());
		assertTrue(heap.add(2));
		assertEquals("[[17(8)12](4)[9(6)7]](2)[[18(11)](3)14]", heap.toString());
		assertTrue(heap.add(15));
		assertEquals("[[17(8)12](4)[9(6)7]](2)[[18(11)15](3)14]", heap.toString());
		assertTrue(heap.add(1));
		assertEquals("[[17(8)12](4)[9(6)7]](1)[[18(11)15](2)[14(3)]]", heap.toString());
		assertTrue(heap.add(20));
		assertEquals("[[17(8)12](4)[9(6)7]](1)[[18(11)15](2)[14(3)20]]", heap.toString());
		assertFalse(heap.isEmpty());

		assertFalse(heap.remove(null));
		assertTrue(heap.remove(7));
		assertEquals("[[17(8)12](4)[9(6)20]](1)[[18(11)15](2)[14(3)]]", heap.toString());
		assertFalse(heap.remove(100));
		assertTrue(heap.remove(11));
		assertEquals("[[17(8)12](4)[9(6)20]](1)[[18(14)15](2)3]", heap.toString());
		assertTrue(heap.remove(8));
		assertEquals("[[17(12)15](4)[9(6)20]](1)[[18(14)](2)3]", heap.toString());
		assertTrue(heap.remove(1));
		assertEquals("[[17(12)15](4)[9(6)20]](2)[14(3)18]", heap.toString());
		assertTrue(heap.remove(17));
		assertEquals("[[20(12)15](4)[9(6)]](2)[14(3)18]", heap.toString());
		assertTrue(heap.remove(20));
		assertEquals("[[12(9)15](4)6](2)[14(3)18]", heap.toString());
		assertTrue(heap.remove(15));
		assertEquals("[[12(9)](4)6](2)[14(3)18]", heap.toString());
		assertTrue(heap.remove(4));
		assertEquals("[9(6)12](2)[14(3)18]", heap.toString());
		assertTrue(heap.remove(3));
		assertEquals("[9(6)12](2)[18(14)]", heap.toString());
		assertTrue(heap.remove(9));
		assertEquals("[18(6)12](2)14", heap.toString());
		assertTrue(heap.remove(18));
		assertEquals("[12(6)](2)14", heap.toString());
		assertTrue(heap.remove(6));
		assertEquals("12(2)14", heap.toString());
		assertTrue(heap.remove(12));
		assertEquals("14(2)", heap.toString());
		assertTrue(heap.remove(2));
		assertEquals("14", heap.toString());
		assertTrue(heap.remove(14));
		assertEquals("", heap.toString());
		assertFalse(heap.remove(14));
		assertTrue(heap.isEmpty());
	}

	@Test
	public void testAddALot() {
		BinaryHeap<Integer> heap = new BinaryHeap<Integer>();
		int[] num = new int[BinaryHeap.DEFAULT_SIZE * 2];

		for (int i = 0; i < num.length; i++) {
			if (i < BinaryHeap.DEFAULT_SIZE)
				assertTrue(heap.add(i));
			else
				assertFalse(heap.add(i));
			num[i] = i;
		}

		for (int j = 0; j < num.length; j++) {
			assertEquals(j, num[j]);
		}

		for (int i = 0; i < num.length; i++) {
			assertFalse(heap.add(i));
		}

		heap.clear();

		for (int j = 0; j < num.length; j++) {
			assertFalse(heap.add(null));
			heap.isEmpty();
		}
	}

	@Test
	public void toStringTest() {
		BinaryHeap<Integer> heap = new BinaryHeap<Integer>();
		heap = createHeap();
		assertEquals("[[[10(3)6](2)[8(5)_]](1)[9(4)7]", heap.toString());
	}

}