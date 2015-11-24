package heap;

public class BinaryHeap<T extends Comparable<T>> implements PriorityQueue<T> {
	protected T vector[];
	protected int numElements;
	protected static final int DEFAULT_SIZE = 100;

	@SuppressWarnings("unchecked")
	public BinaryHeap(int numElements) {
		vector = (T[]) new Comparable[numElements];
	}

	public BinaryHeap() {
		this(DEFAULT_SIZE);
	}

	protected T getParent(int pos) {
		// if the element is the root, return the root
		if (pos == 0) {
			return vector[0];
		}
		int parentIndex = getParentIndex(pos);
		return vector[parentIndex];
	}

	private int getParentIndex(int pos) {
		int parentIndex = (pos - 1) / 2;
		return parentIndex;
	}

	protected T getLeft(int pos) {
		try {
			return vector[2 * pos + 1];
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Left child does not exist. Returning null...");
			return null;
		}
	}

	protected T getRight(int pos) {
		try {
			return vector[2 * pos + 2];
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Right child does not exist. Returning null...");
			return null;
		}
	}

	public boolean add(T element) {
		try {
			if (element == null || numElements == vector.length) {
				return false;
			} else {
				// add the element and apply ascending filter
				vector[numElements] = element;
				ascendingFilter(numElements);

				// after the filter, update the number of elements
				numElements++;
				return true;
			}

		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}

	private void ascendingFilter(int position) throws IndexOutOfBoundsException {
		// until the element reaches the root
		while (position > 0) {
			// if the element's key is lower than the father's,
			// interchange elements and update references
			T element = vector[position];
			T parent = getParent(position);
			int parentPos = getParentIndex(position);

			if (element.compareTo(parent) < 0) {
				vector[position] = parent;
				vector[parentPos] = element;

				position = parentPos;
			}
			// we cannot go on ascending, break
			else {
				break;
			}
		}
	}

	public T poll() {
		if (isEmpty()) {
			return null;
		}
		T root = vector[0];
		remove(root);
		return root;

	}

	public boolean remove(T element) {
		if (element == null || isEmpty()) {
			return false;
		} else {
			int elemPos = getPosition(element);
			// if the element is not in the heap
			if (elemPos == -1) {
				return false;
			}
			// the last element of the heap will occupy the
			// place of the element to remove
			vector[elemPos] = vector[numElements - 1];
			vector[numElements - 1] = null;
			// apply descending filter to every element of the heap
			for (int i = 0; i < numElements; i++) {
				descendingFilter(i);
			}

			numElements--;
			return true;
		}

	}

	private void descendingFilter(int position) {
		// until the element is a leaf
		while (getRight(position) != null || getLeft(position) != null) {
			T element = vector[position];
			T left = getLeft(position);
			T right = getRight(position);
			T min;
			// get the minimum among the children
			if (right == null && left != null) {
				min = left;
			} else if (right != null && left == null) {
				min = right;
			} else {
				min = getMin(left, right);
			}
			// if the element is greater than the minimum,
			// interchange them
			if (element.compareTo(min) > 0) {
				int minPos = getPosition(min);
				vector[position] = min;
				vector[minPos] = element;
				position = minPos;
			} else {
				break;
			}
		}
	}

	private T getMin(T left, T min) {
		if (left.compareTo(min) < 0) {
			min = left;
		}
		return min;
	}

	public int getPosition(T element) {
		for (int i = 0; i < numElements; i++) {
			if (element.compareTo(vector[i]) == 0) {
				return i;
			}
		}
		return -1;

	}

	public boolean isEmpty() {
		return numElements == 0;
	}

	public void clear() {
		numElements = 0;
	}

	protected String toString(int pos) {
		String leftString = "";
		String rightString = "";
		String element = vector[pos].toString();
		T left = getLeft(pos);
		T right = getRight(pos);

		if (isEmpty()) {
			return "";
		}

		if (left == null && right == null) {
			return element;
		}

		/*
		 * if there is a left child, leftString must be whatever we get from its
		 * toString. if it has children, it will have a notation like
		 * [childLeft(left)childRight](pos)[blabla] so we must add "[ ]" to the
		 * string. The same thing goes for the right.
		 */ if (left == null && right != null) {
			leftString = "_";
		}
		if (left != null) {
			int leftPos = getPosition(left);
			leftString = toString(leftPos);
			if (leftString.contains("(")) {
				leftString = "[" + leftString + "]";
			} else {
				return element;
			}
		}

		if (right == null && left != null) {
			rightString = "_";
		}
		if (right != null) {
			int rightPos = getPosition(right);
			rightString = toString(rightPos);
			if (rightString.contains("(")) {
				rightString = "[" + rightString + "]";
			} else {
				return element;

			}
		}

		return leftString + "(" + element + ")" + rightString;
	}

	// tree string
	public String toString() {
		return toString(0);

	}

	// array string
	public String internalString() {
		String aux = "";
		for (int i = 0; i < numElements; i++) {
			aux += vector[i] + " ";
		}
		return aux.trim();
	}
}