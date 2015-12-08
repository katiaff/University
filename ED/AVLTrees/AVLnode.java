package trees;

public class AVLnode<T extends Comparable<T>> extends BSTnode<T> {
	//
	// Please note that I am not adding new attributes such as
	// BF or height. I much prefer to compute that any time is
	// needed...
	//

	public AVLnode(T info) {
		super(info);
	}

	// Homework due lesson 3. Clue: use AVLnode's getHeight method.
	//
	// It can be nice to produce some output to the console to see the
	// node's content, its left and right subtrees heights, and the balance
	// factor.
	//
	public int getBalanceFactor() {

		int balance = 0;
		String aux = "Node: " + this + ", ";

		AVLnode<T> left = getLeft();
		AVLnode<T> right = getRight();

		if (left == null || right == null) {
			if (left != null) {
				balance = -getHeight();
				aux += "left height: " + left.getHeight() + ", right height: 0" + ". BF: " + balance;
			} else if (right != null) {
				balance = getHeight();
				aux += "left height: 0" + ", right height: " + right.getHeight() + ". BF: " + balance;
			} else {
				aux += "left height: 0" + ", right height: 0" + ". BF: " + balance;
			}
		} else {
			balance = right.getHeight() - left.getHeight();
			aux += "left height: " + left.getHeight() + ", right height: " + right.getHeight() + ". BF: " + balance;
		}

		System.out.println(aux);
		return balance;
	}

	/// Homework due lesson 3. Clue: you have to invoke getHeight for both
	// the left and right subtree.
	public int getHeight() {
		AVLnode<T> left = getLeft();
		AVLnode<T> right = getRight();

		// if both nodes exist
		if (left != null && right != null) {
			return Math.max(left.getHeight(), right.getHeight()) + 1;
		}
		// else, if they both don't exist
		else {
			if (left == null && right == null) {
				return 0;
			}
			// if only one exists
			else if (left != null && right == null) {
				return left.getHeight() + 1;
			} else {
				return right.getHeight() + 1;
			}
		}

	}

	// To be explained during lesson 2
	//
	public AVLnode<T> getLeft() {
		// Take notice of the casting and the use of the method in the
		// superclass.
		//
		return (AVLnode<T>) super.getLeft();
	}

	// To be explained during lesson 1
	//
	public AVLnode<T> getRight() {
		// Take notice of the casting and the use of the method in the
		// superclass.
		//
		return (AVLnode<T>) super.getRight();
	}
}