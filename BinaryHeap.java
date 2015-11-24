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
		// try/catch for pos in numberElements
		return vector[2 * pos + 1];
	}

	protected T getRight(int pos) {
		return vector[2 * pos + 2];
	}

	public boolean add(T element) {
		try {
			if (element == null) {
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
		if (element == null) {
			return false;
		} else {
			vector[0] = element;
			descendingFilter(0);

			numElements--;
			return true;
		}

	}

	private void descendingFilter(int position) {
		// until the element is not a leaf
		while (getRight(position) != null || getLeft(position) != null) {
			T element = vector[position];
			T left = getLeft(position);
			T right = getRight(position);

			// if the element's key is lower than both of
			// its children's, interchange with minimum of the children
			T min = right;
			if (left.compareTo(min) < 0) {
				min = left;
			}

			int minPos = getPosition(min);

			if (element.compareTo(min) > 0) {
				vector[position] = min;
				vector[minPos] = element;
				position = minPos;
			} else {
				break;
			}
		}
	}

	public int getPosition(T element) {
		for (int i = 0; i < vector.length; i++) {
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

	// tree string (inorder in our classes)
	// toString to the left, right and concatenate
	protected String toString(int pos) {
		String aux = "[";
		int top = numElements;
		if (pos == 0) {
			top = numElements / 2;
		}
		for (int i = pos; i < top; i++) {
			T left = getLeft(i);
			T right = getRight(i);
			if (left != null && right != null) {
				aux += "[" + left + "(" + vector[i] + ")" + right + "]";
			} else if (left == null && right != null) {
				aux += "[_(" + vector[i] + ")" + right;
			} else if (left != null && right == null) {
				aux += left + "(" + vector[i] + ")_]";
			}
		}
		return aux + "]";
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