package trees;

import static org.junit.Assert.*;

import org.junit.Test;

public class AVLtreeTest {

	protected AVLtree<String> t;

	/**
	 * Basic tree for some tests. It tests insertion as well.
	 */
	@Test
	public void populateTree() {
		t = new AVLtree<String>();

		// Empty tree
		assertEquals("", t.toStringTest());
		// Only one element
		t.add("D");
		assertEquals("D", t.toStringTest());
		t.add("A");
		t.add("C");
		t.add("F");
		assertEquals("[A(C)[_(D)F]]", t.toStringTest());
		t.add("B");
		t.add("E");
		t.add("G");
		assertEquals("[[_(A)B](C)[D(E)[_(F)G]]]", t.toStringTest());
	}

	/**
	 * Used in some rotations tests.
	 */
	@Test
	public void populateTreeForRotations() {
		t = new AVLtree<String>();

		t.add("H");
		t.add("D");
		t.add("J");
		t.add("B");
		t.add("F");
		t.add("E");
		t.add("G");

		assertEquals("[[B(D)E](F)[G(H)J]]", t.toStringTest());
	}

	@Test
	public void testInsertionAVL() {
		t = new AVLtree<String>();

		t.add("H");
		t.add("B");
		t.add("J");
		t.add("D");
		t.add("C");
		assertEquals("[[B(C)D](H)J]", t.toStringTest());
	}

	/**
	 * Used for some deletions in AVL trees.
	 */
	@Test
	public void populateTreeForAVLDeletions() {
		t = new AVLtree<String>();

		t.add("F");
		assertEquals("F", t.toStringTest());

		t.add("C");
		assertEquals("[C(F)_]", t.toStringTest());

		t.add("K");
		assertEquals("[C(F)K]", t.toStringTest());

		t.add("B");
		assertEquals("[[B(C)_](F)K]", t.toStringTest());

		t.add("E");
		assertEquals("[[B(C)E](F)K]", t.toStringTest());

		t.add("H");
		assertEquals("[[B(C)E](F)[H(K)_]]", t.toStringTest());

		t.add("L");
		assertEquals("[[B(C)E](F)[H(K)L]]", t.toStringTest());

		t.add("A");
		assertEquals("[[[A(B)_](C)E](F)[H(K)L]]", t.toStringTest());

		t.add("D");
		assertEquals("[[[A(B)_](C)[D(E)_]](F)[H(K)L]]", t.toStringTest());

		t.add("G");
		assertEquals("[[[A(B)_](C)[D(E)_]](F)[[G(H)_](K)L]]", t.toStringTest());

		t.add("J");
		assertEquals("[[[A(B)_](C)[D(E)_]](F)[[G(H)J](K)L]]", t.toStringTest());

		t.add("I");
		assertEquals("[[[A(B)_](C)[D(E)_]](F)[[G(H)I](J)[_(K)L]]]",
				t.toStringTest());

		t.add("M");
		assertEquals("[[[A(B)_](C)[D(E)_]](F)[[G(H)I](J)[K(L)M]]]",
				t.toStringTest());

		t.add("R");
		assertEquals("[[[A(B)_](C)[D(E)_]](F)[[G(H)I](J)[K(L)[_(M)R]]]]",
				t.toStringTest());

		t.add("O");
		assertEquals("[[[A(B)_](C)[D(E)_]](F)[[G(H)I](J)[K(L)[M(O)R]]]]",
				t.toStringTest());
	}

	@Test
	public void populateTreeForAVLDeletions2() {
		t = new AVLtree<String>();

		t.add("F");
		t.add("B");
		t.add("G");
		t.add("A");
		t.add("D");
		t.add("H");
		t.add("C");
		t.add("E");

		assertEquals("[[A(B)[C(D)E]](F)[_(G)H]]", t.toStringTest());
	}

	@Test
	public void populateTreeForAVLDeletions3() {
		t = new AVLtree<String>();

		t.add("C");
		t.add("B");
		t.add("G");
		t.add("A");
		t.add("E");
		t.add("H");
		t.add("D");
		t.add("F");

		assertEquals("[[A(B)_](C)[[D(E)F](G)H]]", t.toStringTest());
	}

	@Test
	public void testRemove() {
		// Removing leaf
		populateTree();
		t.remove("B");
		assertEquals("[[A(C)D](E)[_(F)G]]", t.toStringTest());

		// Removing node with only left subtree
		populateTree();
		t.remove("C");
		assertEquals("[[A(B)D](E)[_(F)G]]", t.toStringTest());

		// Removing node with only right subtree
		populateTree();
		t.remove("A");
		assertEquals("[[B(C)D](E)[_(F)G]]", t.toStringTest());

		populateTree();
		t.remove("D");
		assertEquals("[[_(A)B](C)[E(F)G]]", t.toStringTest());
	}

	@Test
	public void testHeight() {
		t = new AVLtree<String>();

		assertEquals(0, t.getHeight());
		t.add("D");
		System.out.println(t.toStringTest());
		assertEquals(1, t.getHeight());
		t.add("B");
		System.out.println(t.toStringTest());
		assertEquals(2, t.getHeight());
		t.add("A");
		System.out.println(t.toStringTest());
		assertEquals(2, t.getHeight());
		t.add("G");
		System.out.println(t.toStringTest());
		assertEquals(3, t.getHeight());
		t.add("F");
		System.out.println(t.toStringTest());
		assertEquals(3, t.getHeight());
		t.add("E");
		System.out.println(t.toStringTest());
		assertEquals(3, t.getHeight());
		t.remove("F");
		System.out.println(t.toStringTest());
		assertEquals(3, t.getHeight());
	}

	@Test
	public void testDeletionAVL() {
		populateTreeForAVLDeletions();

		t.remove("M");
		assertEquals("[[[A(B)_](C)[D(E)_]](F)[[G(H)I](J)[K(L)[_(O)R]]]]",
				t.toStringTest());

		t.remove("D");
		assertEquals("[[[A(B)_](C)E](F)[[G(H)I](J)[K(L)[_(O)R]]]]",
				t.toStringTest());

		t.remove("E");
		assertEquals("[[[A(B)C](F)[G(H)I]](J)[K(L)[_(O)R]]]", t.toStringTest());

		t.remove("L");
		assertEquals("[[[A(B)C](F)[G(H)I]](J)[K(O)R]]", t.toStringTest());

		populateTreeForAVLDeletions2();
		t.remove("H");
		assertEquals("[[A(B)C](D)[E(F)G]]", t.toStringTest());

		populateTreeForAVLDeletions3();
		t.remove("A");
		assertEquals("[[B(C)D](E)[F(G)H]]", t.toStringTest());
	}
	
	@Test	
	public void testRotations(){
		t = new AVLtree<String>();
		
		t.add("J");
		assertEquals("J", t.toStringTest());	
		t.add("B");
		assertEquals("[B(J)_]", t.toStringTest());		
		t.add("C");
		assertEquals("[B(C)J]", t.toStringTest());
		t.add("D");
		assertEquals("[B(C)[D(J)_]]", t.toStringTest());
		t.add("E");
		assertEquals("[B(C)[D(E)J]]", t.toStringTest());
		t.add("A");
		assertEquals("[[A(B)_](C)[D(E)J]]", t.toStringTest());
		t.add("F");
		assertEquals("[[A(B)_](C)[D(E)[F(J)_]]]", t.toStringTest());
		
		t.remove("C");
		assertEquals("[[A(B)D](E)[F(J)_]]", t.toStringTest());
		t.remove("B");
		assertEquals("[[_(A)D](E)[F(J)_]]", t.toStringTest());
		t.remove("A");
		assertEquals("[D(E)[F(J)_]]", t.toStringTest());
		
		t.add("C");
		assertEquals("[[C(D)_](E)[F(J)_]]", t.toStringTest());
		t.add("B");
		assertEquals("[[B(C)D](E)[F(J)_]]", t.toStringTest());
		
		t.remove("J");
		assertEquals("[[B(C)D](E)F]", t.toStringTest());
		//ambiguity, should perform a single left rotation
		t.remove("F");	
		assertEquals("[B(C)[D(E)_]]", t.toStringTest());
		t.remove("C");
		assertEquals("[B(D)E]", t.toStringTest());
		
		
		
	}
}