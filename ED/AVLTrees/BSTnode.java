package trees;

public class BSTnode<T extends Comparable<T>> {
	private T info;
	private BSTnode<T> left;
	private BSTnode<T> right;

	/**
	 * @param info
	 *            a comparable object
	 */
	public BSTnode(T info) {
		this.info = info;
		left = null;
		right = null;
	}

	/**
	 * @param info
	 *            The info to be stored at the node
	 */
	protected void setInfo(T info) {
		this.info = info;
	}

	/**
	 * @return The info stored in the node
	 */
	public T getInfo() {
		return info;
	}

	/**
	 * @param x
	 *            The node to be linked in the left subtree
	 */
	public void setLeft(BSTnode<T> x) {
		left = x;
	}

	/**
	 * @param x
	 *            The node to be linked in the right subtree
	 */
	public void setRight(BSTnode<T> x) {
		right = x;
	}

	/**
	 * @return The left subtree
	 */
	public BSTnode<T> getLeft() {
		return left;
	}

	/**
	 * @return The right subtree
	 */
	public BSTnode<T> getRight() {
		return right;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return info.toString();
	}

	public String toStringTest() {
		String strLeft, strRight;
		// Leaf nodes are very simple
		if (getLeft() == null && getRight() == null)
			return getInfo().toString();
		// The rest... with brackets
		if (getLeft() == null)
			strLeft = "_";
		else
			strLeft = getLeft().toStringTest();
		if (getRight() == null)
			strRight = "_";
		else
			strRight = getRight().toStringTest();
		return "[" + strLeft + "(" + getInfo().toString() + ")" + strRight + "]";
	}
}