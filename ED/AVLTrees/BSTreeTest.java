package trees;

import static org.junit.Assert.*;

import org.junit.Test;

public class BSTreeTest {
	private class Information implements Comparable<Information> {
		private int key;
		private String name;
		
		/**
		 * Constructor with 2 parameters
		 * 
		 * @param c Node's key
		 * @param n Information stored in the node
		 */
		public Information(int c, String n){
			key=c;
			name=n;
		}
		
		/**
		 * Constructor with just the key
		 * @param c Node's key
		 * 
		 * Useful for searching and removing, we only provide the key, not actual information
		 */
		public Information(int c) {
			key = c;
		}

		/* (non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		public int compareTo (Information element) {
			if (key< (element.key))
				return -1;
			else if (key > (element.key))
				return 1;
			else
				return 0;
		}
		
		public boolean equals (Object element) {
			if (element==null) return false;
			if (element==this) return true;
			if (!(element instanceof Information)) return false;
			return this.key==((Information)element).key && this.name.equals(((Information)element).name);
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			return "["+key+":"+name+"]";
		}


	}
	
	@Test
	public void testAddSi1() {
		BSTree<Integer> b = new BSTree<Integer>();
		String res;
		
		for (int i=5;i!=0;i--) {
			assertTrue("add("+i+") ",b.add(i));
			assertTrue("add("+(-i)+") ",b.add(-i));
		}
		
		res=b.inOrder();
		System.out.println(res);
		assertEquals("-5\t-4\t-3\t-2\t-1\t1\t2\t3\t4\t5",res);
		
		res=b.preOrder();
		System.out.println(res);
		assertEquals("5\t-5\t4\t-4\t3\t-3\t2\t-2\t1\t-1",res);

		res=b.postOrder();
		System.out.println(res);
		assertEquals("-1\t1\t-2\t2\t-3\t3\t-4\t4\t-5\t5",res);
	}

	@Test
	public void testAddSi2() {
		BSTree<String> b = new BSTree<String>();
		String res;
		String[] cad=new String[]{"40","60","20","10","90","30","50","80","70"};
		
		for (int i=0;i<cad.length;i++) {
			assertTrue("add("+i+") ",b.add(cad[i]));
		}
		
		res=b.inOrder();
		assertEquals("10\t20\t30\t40\t50\t60\t70\t80\t90",res);
		
		res=b.preOrder();
		assertEquals("40\t20\t10\t30\t60\t50\t90\t80\t70",res);

		res=b.postOrder();
		assertEquals("10\t30\t20\t50\t70\t80\t90\t60\t40",res);
	}

	@Test
	public void testAddNO1() {
		BSTree<Integer> b = new BSTree<Integer>();
		String res;
		
		assertFalse(b.add(null));
		for (int i=1;i<10;i++) {
			assertTrue("add("+i+") ",b.add(i));
			assertFalse("add("+i+") ",b.add(i));
			assertTrue("add("+(-i)+") ",b.add(-i));
			assertFalse("add("+(-i)+") ",b.add(i));
		}
		
		res=b.inOrder();
		System.out.println(res);
		assertEquals("-9\t-8\t-7\t-6\t-5\t-4\t-3\t-2\t-1\t1\t2\t3\t4\t5\t6\t7\t8\t9",res);
		
		res=b.preOrder();
		System.out.println(res);
		assertEquals("1\t-1\t-2\t-3\t-4\t-5\t-6\t-7\t-8\t-9\t2\t3\t4\t5\t6\t7\t8\t9",res);

		res=b.postOrder();
		System.out.println(res);
		assertEquals("-9\t-8\t-7\t-6\t-5\t-4\t-3\t-2\t-1\t9\t8\t7\t6\t5\t4\t3\t2\t1",res);
		
		b = new BSTree<Integer>();
		
	}

	@Test
	public void testAddNO2() {
		BSTree<String> b = new BSTree<String>();
		String res;
		String[] cad=new String[]{"10","10","10","10","10","10","10","10","10","10","10","10","10"};
		
		assertTrue("add(\"10\") ",b.add(cad[0]));
		for (int i=1;i<cad.length;i++) {
			assertFalse("add(\""+cad[i]+"\") ",b.add(cad[i]));
		}
		
		res=b.inOrder();
		assertEquals("10",res);
		
		res=b.preOrder();
		assertEquals("10",res);

		res=b.postOrder();
		assertEquals("10",res);
	}
	
	@Test
	public void testAddSiNo() {
		BSTree<Information> b = new BSTree<Information>();
		String res;
		int[] num=new int[]{1,2,0,9,7,3,5,8,6,4};
		String[] cad=new String[]{"1-one","2-two","0-zero","9-nine","7-seven","3-three","5-five","8-eight","6-six","4-cuatro"};
		
		for (int i=0;i<cad.length;i++) {
			assertTrue(b.add(new Information(num[i],cad[i])));
		}

		res=b.inOrder();
		assertEquals("[0:0-zero]\t[1:1-one]\t[2:2-two]\t[3:3-three]\t[4:4-cuatro]\t[5:5-five]\t[6:6-six]\t[7:7-seven]\t[8:8-eight]\t[9:9-nine]",res);
		
		res=b.preOrder();
		assertEquals("[1:1-one]\t[0:0-zero]\t[2:2-two]\t[9:9-nine]\t[7:7-seven]\t[3:3-three]\t[5:5-five]\t[4:4-cuatro]\t[6:6-six]\t[8:8-eight]",res);

		res=b.postOrder();
		assertEquals("[0:0-zero]\t[4:4-cuatro]\t[6:6-six]\t[5:5-five]\t[3:3-three]\t[8:8-eight]\t[7:7-seven]\t[9:9-nine]\t[2:2-two]\t[1:1-one]",res);


		for (int i=0;i<cad.length;i++) {
			assertFalse(b.add(new Information(num[i],"It doesn't really matter")));  // Repeated keys
		}
	}
	
	@Test
	public void testSearchRemove() {
		BSTree<Information> b = new BSTree<Information>();
		
		int[] num=new int[]{4,2,6,1,3,5,7};
		String [] cad=new String[]{"one","two","three","four","five","six","seven"};
		
		for (int i=0;i<cad.length;i++) 
			assertTrue(b.add(new Information(num[i],cad[num[i]-1])));
		
		for (int i=1;i<=7;i++) {
			Information dummy=new Information(i);
			Information actual=new Information(i,cad[i-1]);
			
			assertEquals(actual,b.search(dummy));
		}
		
		int rootValue=4;
		Information dummy=new Information(rootValue);
		assertTrue(b.remove(dummy));
		assertEquals(null,b.search(dummy));
		for (int i=7;i>=1;i--) {
			if (i!=rootValue) {
				dummy=new Information(i);
				assertTrue(b.remove(dummy));
				for (int j=1;j<i;j++) {
					if (j!=rootValue) {
						dummy=new Information(j);
						Information actual=new Information(j,cad[j-1]);
						assertEquals(actual,b.search(dummy));
					}
				}
				for (int j=i;j<=7;j++) {
					dummy=new Information(j);
					assertEquals(null,b.search(dummy));
				}
			}
		}

		b = new BSTree<Information>();
		
		int[] num2=new int[]{3,1,5,2,4,6,7};
		
		for (int i=0;i<cad.length;i++) 
			assertTrue(b.add(new Information(num2[i],cad[num2[i]-1])));
		
		for (int i=1;i<=7;i++) {
			dummy=new Information(i);
			Information actual=new Information(i,cad[i-1]);
			
			assertEquals(actual,b.search(dummy));
		}

		rootValue=3;
		dummy=new Information(rootValue);
		assertTrue(b.remove(dummy));
		assertEquals(null,b.search(dummy));
		for (int i=7;i>=1;i--) {
			if (i!=rootValue) {
				dummy=new Information(i);
				assertTrue(b.remove(dummy));
				for (int j=1;j<i;j++) {
					if (j!=rootValue) {
						dummy=new Information(j);
						Information actual=new Information(j,cad[j-1]);
						assertEquals(actual,b.search(dummy));
					}
				}
				for (int j=i;j<=7;j++) {
					dummy=new Information(j);
					assertEquals(null,b.search(dummy));
				}
			}
		}

		b = new BSTree<Information>();
		
		int[] num3=new int[]{2,1,5,4,6,3,7};
		
		for (int i=0;i<cad.length;i++) 
			assertTrue(b.add(new Information(num3[i],cad[num3[i]-1])));
		
		for (int i=1;i<=7;i++) {
			dummy=new Information(i);
			Information actual=new Information(i,cad[i-1]);
			
			assertEquals(actual,b.search(dummy));
		}

		rootValue=2;
		dummy=new Information(rootValue);
		assertTrue(b.remove(dummy));
		assertEquals(null,b.search(dummy));
		for (int i=7;i>=1;i--) {
			if (i!=rootValue) {
				dummy=new Information(i);
				assertTrue(b.remove(dummy));
				for (int j=1;j<i;j++) {
					if (j!=rootValue) {
						dummy=new Information(j);
						Information actual=new Information(j,cad[j-1]);
						assertEquals(actual,b.search(dummy));
					}
				}
				for (int j=i;j<=7;j++) {
					dummy=new Information(j);
					assertEquals(null,b.search(dummy));
				}
			}
		}

		b = new BSTree<Information>();
		
		int[] num4=new int[]{1,4,3,6,2,5,7};
		
		for (int i=0;i<cad.length;i++) 
			assertTrue(b.add(new Information(num4[i],cad[num4[i]-1])));
		
		for (int i=1;i<=7;i++) {
			dummy=new Information(i);
			Information actual=new Information(i,cad[i-1]);
			
			assertEquals(actual,b.search(dummy));
		}

		rootValue=1;
		dummy=new Information(rootValue);
		assertTrue(b.remove(dummy));
		assertEquals(null,b.search(dummy));
		for (int i=7;i>=1;i--) {
			if (i!=rootValue) {
				dummy=new Information(i);
				assertTrue(b.remove(dummy));
				for (int j=1;j<i;j++) {
					if (j!=rootValue) {
						dummy=new Information(j);
						Information actual=new Information(j,cad[j-1]);
						assertEquals(actual,b.search(dummy));
					}
				}
				for (int j=i;j<=7;j++) {
					dummy=new Information(j);
					assertEquals(null,b.search(dummy));
				}
			}
		}

		b = new BSTree<Information>();
		
		int[] num5=new int[]{1,2,3,4,5,6,7};
		
		for (int i=0;i<cad.length;i++) 
			assertTrue(b.add(new Information(num5[i],cad[num5[i]-1])));
		
		for (int i=1;i<=7;i++) {
			dummy=new Information(i);
			Information actual=new Information(i,cad[i-1]);
			
			assertEquals(actual,b.search(dummy));
		}

		rootValue=1;
		dummy=new Information(rootValue);
		assertTrue(b.remove(dummy));
		assertEquals(null,b.search(dummy));
		for (int i=7;i>=1;i--) {
			if (i!=rootValue) {
				dummy=new Information(i);
				assertTrue(b.remove(dummy));
				for (int j=1;j<i;j++) {
					if (j!=rootValue) {
						dummy=new Information(j);
						Information actual=new Information(j,cad[j-1]);
						assertEquals(actual,b.search(dummy));
					}
				}
				for (int j=i;j<=7;j++) {
					dummy=new Information(j);
					assertEquals(null,b.search(dummy));
				}
			}
		}

		b = new BSTree<Information>();

		int[] num6=new int[]{7,5,3,1,6,2,4};
		
		for (int i=0;i<cad.length;i++) 
			assertTrue(b.add(new Information(num6[i],cad[num6[i]-1])));
		
		for (int i=1;i<=7;i++) {
			dummy=new Information(i);
			Information actual=new Information(i,cad[i-1]);
			
			assertEquals(actual,b.search(dummy));
		}

		rootValue=5;
		dummy=new Information(rootValue);
		assertTrue(b.remove(dummy));
		assertEquals(null,b.search(dummy));
		for (int i=7;i>=1;i--) {
			if (i!=rootValue) {
				dummy=new Information(i);
				assertTrue(b.remove(dummy));
				for (int j=1;j<i;j++) {
					if (j!=rootValue) {
						dummy=new Information(j);
						Information actual=new Information(j,cad[j-1]);
						assertEquals(actual,b.search(dummy));
					}
				}
				for (int j=i;j<=7;j++) {
					dummy=new Information(j);
					assertEquals(null,b.search(dummy));
				}
			}
		}

		b = new BSTree<Information>();
		
		int[] num7=new int[]{7,6,4,2,5,1,3};
		
		for (int i=0;i<cad.length;i++) 
			assertTrue(b.add(new Information(num7[i],cad[num7[i]-1])));
		
		for (int i=1;i<=7;i++) {
			dummy=new Information(i);
			Information actual=new Information(i,cad[i-1]);
			
			assertEquals(actual,b.search(dummy));
		}

		rootValue=4;
		dummy=new Information(rootValue);
		assertTrue(b.remove(dummy));
		assertEquals(null,b.search(dummy));
		for (int i=7;i>=1;i--) {
			if (i!=rootValue) {
				dummy=new Information(i);
				assertTrue(b.remove(dummy));
				for (int j=1;j<i;j++) {
					if (j!=rootValue) {
						dummy=new Information(j);
						Information actual=new Information(j,cad[j-1]);
						assertEquals(actual,b.search(dummy));
					}
				}
				for (int j=i;j<=7;j++) {
					dummy=new Information(j);
					assertEquals(null,b.search(dummy));
				}
			}
		}

		b = new BSTree<Information>();
		
		int[] num8=new int[]{7,3,6,4,5,1,2};
		
		for (int i=0;i<cad.length;i++) 
			assertTrue(b.add(new Information(num8[i],cad[num8[i]-1])));
		
		for (int i=1;i<=7;i++) {
			dummy=new Information(i);
			Information actual=new Information(i,cad[i-1]);
			
			assertEquals(actual,b.search(dummy));
		}

		rootValue=3;
		dummy=new Information(rootValue);
		assertTrue(b.remove(dummy));
		assertEquals(null,b.search(dummy));
		for (int i=7;i>=1;i--) {
			if (i!=rootValue) {
				dummy=new Information(i);
				assertTrue(b.remove(dummy));
				for (int j=1;j<i;j++) {
					if (j!=rootValue) {
						dummy=new Information(j);
						Information actual=new Information(j,cad[j-1]);
						assertEquals(actual,b.search(dummy));
					}
				}
				for (int j=i;j<=7;j++) {
					dummy=new Information(j);
					assertEquals(null,b.search(dummy));
				}
			}
		}

		b = new BSTree<Information>();
		
		int[] num9=new int[]{7,5,2,6,3,4,1};
		
		for (int i=0;i<cad.length;i++) 
			assertTrue(b.add(new Information(num9[i],cad[num9[i]-1])));
		
		for (int i=1;i<=7;i++) {
			dummy=new Information(i);
			Information actual=new Information(i,cad[i-1]);
			
			assertEquals(actual,b.search(dummy));
		}

		rootValue=5;
		dummy=new Information(rootValue);
		assertTrue(b.remove(dummy));
		assertEquals(null,b.search(dummy));
		for (int i=7;i>=1;i--) {
			if (i!=rootValue) {
				dummy=new Information(i);
				assertTrue(b.remove(dummy));
				for (int j=1;j<i;j++) {
					if (j!=rootValue) {
						dummy=new Information(j);
						Information actual=new Information(j,cad[j-1]);
						assertEquals(actual,b.search(dummy));
					}
				}
				for (int j=i;j<=7;j++) {
					dummy=new Information(j);
					assertEquals(null,b.search(dummy));
				}
			}
		}

		b = new BSTree<Information>();
		
		int[] num10=new int[]{7,6,5,4,3,2,1};
		
		for (int i=0;i<cad.length;i++) 
			assertTrue(b.add(new Information(num10[i],cad[num10[i]-1])));
		
		for (int i=1;i<=7;i++) {
			dummy=new Information(i);
			Information actual=new Information(i,cad[i-1]);
			
			assertEquals(actual,b.search(dummy));
		}

		for (int i=7;i>=1;i--) {
			dummy=new Information(i);
			assertTrue(b.remove(dummy));
			for (int j=1;j<i;j++) {
				dummy=new Information(j);
				Information actual=new Information(j,cad[j-1]);
				assertEquals(actual,b.search(dummy));
			}
			for (int j=i;j<=7;j++) {
				dummy=new Information(j);
				assertEquals(null,b.search(dummy));
			}
		}

		b = new BSTree<Information>();
		
		int[] num11=new int[]{7,5,3,1,6,4,2};
		
		for (int i=0;i<cad.length;i++) 
			assertTrue(b.add(new Information(num11[i],cad[num11[i]-1])));
		
		for (int i=1;i<=7;i++) {
			dummy=new Information(i);
			Information actual=new Information(i,cad[i-1]);
			
			assertEquals(actual,b.search(dummy));
		}
	
		rootValue=3;
		dummy=new Information(rootValue);
		assertTrue(b.remove(dummy));
		assertEquals(null,b.search(dummy));
		for (int i=7;i>=1;i--) {
			if (i!=rootValue) {
				dummy=new Information(i);
				assertTrue(b.remove(dummy));
				for (int j=1;j<i;j++) {
					if (j!=rootValue) {
						dummy=new Information(j);
						Information actual=new Information(j,cad[j-1]);
						assertEquals(actual,b.search(dummy));
					}
				}
				for (int j=i;j<=7;j++) {
					dummy=new Information(j);
					assertEquals(null,b.search(dummy));
				}
			}
		}
	}
}