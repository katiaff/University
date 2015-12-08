package trees;

import static org.junit.Assert.*;

import org.junit.Test;

public class AVLnodeTest {

	protected AVLnode<Integer> t5;
	protected AVLnode<Integer> t4;
	protected AVLnode<Integer> t3;
	protected AVLnode<Integer> t2;
	protected AVLnode<Integer> t8;

	@Test
	public void testGetBalanceFactor() {
		t5 = new AVLnode<Integer>(5);
		assertEquals(0, t5.getBalanceFactor());
		
		t4 = new AVLnode<Integer>(4);
		t5.setLeft(t4);
		assertEquals(-1, t5.getBalanceFactor());
		assertEquals(0, t4.getBalanceFactor());
		
		t8 = new AVLnode<Integer>(8);
		t5.setRight(t8);
		assertEquals(0, t5.getBalanceFactor());
		assertEquals(0, t4.getBalanceFactor());
		assertEquals(0, t8.getBalanceFactor());
		
		t3 = new AVLnode<Integer>(3);
		t4.setLeft(t3);
		assertEquals(-1, t5.getBalanceFactor());
		assertEquals(-1, t4.getBalanceFactor());
		assertEquals(0, t8.getBalanceFactor());
		assertEquals(0, t3.getBalanceFactor());
		
		t2 = new AVLnode<Integer>(2);
		t3.setLeft(t2);
		assertEquals(-2, t5.getBalanceFactor());
		assertEquals(-2, t4.getBalanceFactor());
		assertEquals(0, t8.getBalanceFactor());
		assertEquals(-1, t3.getBalanceFactor());
	}

	@Test
	public void testGetHeight() {
		t5 = new AVLnode<Integer>(5);
		assertEquals(0, t5.getHeight());

		t4 = new AVLnode<Integer>(4);
		t5.setLeft(t4);
		assertEquals(1, t5.getHeight());
		assertEquals(0, t4.getHeight());

		t8 = new AVLnode<Integer>(8);
		t5.setRight(t8);
		assertEquals(1, t5.getHeight());
		assertEquals(0, t4.getHeight());
		assertEquals(0, t8.getHeight());

		t3 = new AVLnode<Integer>(3);
		t4.setLeft(t3);
		assertEquals(2, t5.getHeight());
		assertEquals(1, t4.getHeight());
		assertEquals(0, t8.getHeight());
		assertEquals(0, t3.getHeight());


	}

}
