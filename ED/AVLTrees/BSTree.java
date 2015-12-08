package trees;

public class BSTree<T extends Comparable<T>> {
	protected BSTnode<T> root;

	/**
	 * @param x
	 *            The comparable object to insert
	 * 
	 * @return true when inserted, false otherwise (e.g., the object already
	 *         exists in the tree)
	 */

	// To be explained during lesson 1
	//
	protected BSTnode<T> add(BSTnode<T> node, T x) {
		if (node == null) {
			node = new BSTnode<T>(x);
		} else {
			int comparison = node.getInfo().compareTo(x);
			if (comparison < 0) {
				BSTnode<T> right = node.getRight();
				right = add(right, x);
				node.setRight(right);
			} else if (comparison > 0) {
				BSTnode<T> left = node.getLeft();
				left = add(left, x);
				node.setLeft(left);
			} else {
				throw new RuntimeException("Element already exists");
			}
		}
		return node;
	}

	// To be explained during lesson 1
	//
	public boolean add(T x) {
		boolean result = false;
		if (x != null) {
			try {
				root = add(root, x);
				result = true;
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * It performs an in-order traversal (left-right). It outputs to console but
	 * also returns a String (tab separated).
	 */
	// To be explained during lesson 1
	//
	public String inOrder() {
		String traversal = inOrder(root);
		System.out.println(traversal);
		return traversal;
	}

	// To be explained during lesson 1
	//
	protected String inOrder(BSTnode<T> node) {
		if (node == null)
			return "";
		else {
			String left = inOrder(node.getLeft());
			String right = inOrder(node.getRight());

			String result = "";

			if (!left.equals(""))
				result = left + "\t";

			result += node.getInfo().toString();

			if (!right.equals(""))
				result += "\t" + right;

			return result;
		}
	}

	/**
	 * It performs a pre-order traversal (left-right). It outputs to console but
	 * also returns a String (tab separated).
	 */
	// Homework due next week
	//
	public String preOrder() {
		String traversal = preOrder(root);
		System.out.println(traversal);
		return traversal;
	}

	// Homework due next week
	//
	protected String preOrder(BSTnode<T> node) {
		if (node == null)
			return "";
		else {
			String left = preOrder(node.getLeft());
			String right = preOrder(node.getRight());

			String result = "";
			result += node.getInfo().toString();

			if (!left.equals(""))
				result += "\t" + left;

			if (!right.equals(""))
				result += "\t" + right;

			return result;
		}
	}

	/**
	 * It performs a post-order traversal (left-right). It outputs to console
	 * but also returns a String (tab separated).
	 */
	// Homework due next week
	//
	public String postOrder() {
		String traversal = postOrder(root);
		System.out.println(traversal);
		return traversal;
	}

	// Homework due next week
	//
	protected String postOrder(BSTnode<T> node) {
		if (node == null)
			return "";
		else {
			String left = postOrder(node.getLeft());
			String right = postOrder(node.getRight());

			String result = "";

			if (!left.equals(""))
				result += left + "\t";

			if (!right.equals(""))
				result += right + "\t";

			result += node.getInfo().toString();

			return result;
		}
	}

	// Homework due next week
	//
	public String toString() {
		return inOrder();
	}

	/**
	 * @param x
	 *            The comparable object we are looking for
	 * @return The object we were looking for if found, null otherwise
	 */
	// Homework due next week
	// compareTo
	// x --> key:9; info:null
	// return node.info
	public T search(T x) {
		return search(root, x);
	}

	// Homework due next week
	// node = root
	protected T search(BSTnode<T> root, T x) {
		if (root == null) {
			return null;
		} else {
			T rootInfo = root.getInfo();
			if (rootInfo.compareTo(x) == 0) {
				return rootInfo;
			} else {
				if ((rootInfo.compareTo(x) > 0))
					return search(root.getLeft(), x);
				else
					return search(root.getRight(), x);
			}
		}
	}

	/**
	 * @param x
	 *            The object to remove
	 * @return true if removed, false otherwise
	 */
	// To be done next week during lesson 2.
	//
	public boolean remove(T x) {
		root = remove(root, x);
		return true;
	}

	// To be done next week during lesson 2
	//
	protected BSTnode<T> remove(BSTnode<T> root, T element) {
		if (root == null) {
			throw new RuntimeException("Non-existent element");
		} else {
			T rootInfo = root.getInfo();

			if (rootInfo.compareTo(element) > 0) {
				// If the root has a higher value, go left
				BSTnode<T> left = remove(root.getLeft(), element);
				root.setLeft(left);
			} else if (rootInfo.compareTo(element) < 0) {
				// Otherwise, go right
				BSTnode<T> right = remove(root.getRight(), element);
				root.setRight(right);
			} else {
				// We have found the node, 3 situations
				// 1. No children --> return null
				if (root.getRight() == null && root.getLeft() == null) {
					return null;
				}
				// 2. Just one child --> apply single rotation
				else {
					if (root.getRight() == null) {
						// if the child is on the left, return left branch
						return root.getLeft();
					} else if (root.getLeft() == null) {
						// but if it is on the right, return right branch
						return root.getRight();
					}
					// 3. Two children --> apply double rotation
					else {
						// new root is the node with the immediately previous
						// value
						root.setInfo(getMax(root.getLeft()));
						// new left child is the left child of the new root
						root.setLeft(remove(root.getLeft(), root.getInfo()));
					}
				}

			}
			return root;
		}
	}

	// To be done next week during lesson 2, required for AVL trees
	//
	protected T getMax(BSTnode<T> root) {
		if (root == null) {
			return null;
		} else {
			if (root.getRight() == null) {
				return root.getInfo();
			} else {
				return getMax(root.getRight());
			}
		}

	}

	public String toStringTest() {
		if (root != null)
			return root.toStringTest();
		else
			return "";
	}
}