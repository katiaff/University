package hashtables;

public class ClosedHashTable<T> extends AbstractHash<T> {
	private int numElements = 0;
	private HashNode<T>[] table;

	// Load Factor Limits (Open Addressing)
	public static final double MINIMUM_LOAD_FACTOR = 0.16; // See slide 40
	public static final double MAXIMUM_LOAD_FACTOR = 0.5; // See slide 30

	// Probe sequence
	public static final short LINEAR_PROBING = 1;
	public static final short QUADRATIC_PROBING = 2;

	// Set probe sequence
	public static short PROBE_SEQUENCE = QUADRATIC_PROBING;

	@SuppressWarnings("unchecked")
	public ClosedHashTable() {
		// DON'T CHANGE ANYTHING HERE
		//
		table = (HashNode<T>[]) new HashNode[1];
	}

	public void setProbeSequence(short probe_sequence) {
		// DON'T CHANGE ANYTHING HERE
		//
		if (probe_sequence == LINEAR_PROBING
				|| probe_sequence == QUADRATIC_PROBING)
			PROBE_SEQUENCE = probe_sequence;
		else
			PROBE_SEQUENCE = QUADRATIC_PROBING;
	}

	@Override
	public int getNumElem() {
		// DON'T CHANGE ANYTHING HERE
		//
		return numElements;
	}

	@Override
	public int getSize() {
		// DON'T CHANGE ANYTHING HERE
		//
		return table.length;
	}

	protected int linearProbing(T elem, int timesSearched) {
		// YOU MUST IMPLEMENT THIS
		//
		// See slide 28

		// i iteration
		// B size of the table
		// x is the fHash of the element to be stored
		int x = fHash(elem);
		int counter = timesSearched;
		int position = (x + counter) % getSize();
		HashNode<T> node = table[position];
		while (node.getState() == HashNode.FULL) {
			counter++;
			position = (x + counter) % getSize();
			node = table[position];
		}
		return position;
	}

	protected int quadraticProbing(T elem, int timesSearched) {
		// YOU MUST IMPLEMENT THIS
		//
		// See slide 34

		int x = fHash(elem);
		int counter = timesSearched;
		int position = (x + counter) % getSize();
		HashNode<T> node = table[position];
		while (node.getState() == HashNode.FULL) {
			counter++;
			position = (x + counter * counter) % getSize();
			node = table[position];
		}
		return position;
	}

	protected int probing(T elem, int timesSearched) {
		// DON'T CHANGE ANYTHING HERE
		//
		int position = 0;
		switch (PROBE_SEQUENCE) {
		case LINEAR_PROBING:
			position = linearProbing(elem, timesSearched);
			break;
		case QUADRATIC_PROBING:
			position = quadraticProbing(elem, timesSearched);
			break;
		}
		return position;
	}

	@Override
	public boolean add(T elem) {
		// YOU MUST IMPLEMENT THIS
		//
		// If elem is null return false
		// If elem is already in the hash table return false
		// Otherwise
		// - Start at position 0
		// - Iterate while the position you are checking in the table is not
		// null and has a FULL node
		// - Remember to increase the iteration counter each time
		// - In each iteration you invoke probing(elem,timesSearched) and use
		// the return value as the new position to check
		// - Once you have finished iterating you create a new HashNode, you
		// store the element in that node and assign that node to the position
		// - Remember to increase the number of elements and to invoke upsize
		//
		if (elem == null || find(elem) != null) {
			return false;
		}

		int position = 0;
		HashNode<T> node = table[position];
		if (node != null) {
			int counter = 0;
			// change to !isEmpty(position)
			while (node.getState() == HashNode.FULL) {
				position = probing(elem, counter);
				node = table[position];
				counter++;
			}
		}
		table[position] = new HashNode<T>();
		table[position].setInfo(elem);
		numElements++;
		upsize();

		return true;
	}

	@Override
	public T find(T elem) {
		// YOU MUST IMPLEMENT THIS
		//
		// If elem is null return null
		// Otherwise
		// - Start at position 0
		// - Iterate while the position you are checking in the table is not the
		// one you are looking for
		// - Remember to increase the iteration counter each time
		// - In each iteration you invoke probing(elem,timesSearched) and use
		// the return value as the new position to check
		// - Once you have finished iterating check that the position you have
		// is not null and it is FULL and not DELETED
		// - In that case you can return that element, otherwise return null

		if (elem == null) {
			return null;
		}
		int counter = 0;
		int position = 0;
		do {
			position = probing(elem, counter);
			counter++;

		} while (table[position] != null || isEmpty(position));

		return null; // You MUST change this
	}

	private boolean isEmpty(int position) {
		return table[position] == null
				|| table[position].getState() == HashNode.EMPTY
				|| table[position].getState() == HashNode.DELETED;
	}

	@Override
	public boolean remove(T elem) {
		// YOU MUST IMPLEMENT THIS
		//
		// If elem is null return false
		// If elem is not in table return false
		// Otherwise
		// - Start at position 0
		// - Iterate while the position you are checking in the table is not the
		// one you are looking for
		// - Remember to increase the iteration counter each time
		// - In each iteration you invoke probing(elem,timesSearched) and use
		// the return value as the new position to check
		// - Once you have finished iterating check that the position you have
		// is not null and it is FULL and not DELETED
		// - In that case you mark that element as DELETED, you decrease the
		// number of elements, invoke downsize and return true
		// - Otherwise return false

		return false; // You MUST change this
	}

	protected double getLF() {
		// YOU MUST IMPLEMENT THIS
		//
		// Remember that the load factor is the ratio between the number of
		// stored elements and the actual size of the array

		return numElements / getSize(); // You MUST change this
	}

	@SuppressWarnings("unchecked")
	@Override
	protected boolean upsize() {
		// YOU MUST IMPLEMENT THIS
		//
		// Check the current load factor of the table
		// If the LF is below the MAXIMUM load factor return false, otherwise:
		// - Compute the new size for the array (use nextBiggerPrime and see
		// slide 38)
		// - Use an auxiliary reference to point to the current table
		// - Create a new table with the new size
		// - Iterate over the auxiliary table to access all of the data, add
		// those elements to the new table
		// - Return true
		//
		// WARNING: be careful with numElements...
		// new table, numElements =0; iterate and add full elements
		// invoked from add

		double LF = getLF();
		if (LF < MAXIMUM_LOAD_FACTOR) {
			return false;
		}
		int newSize = nextBiggerPrime(getSize() * 2);
		HashNode<T>[] current = table;
		HashNode<T>[] newTable = new HashNode[newSize];
		for (int i = 0; i < current.length; i++) {
			newTable[i] = current[i];
		}
		table = newTable;
		return true; // You MUST change this
	}

	@SuppressWarnings("unchecked")
	@Override
	protected boolean downsize() {
		// YOU MUST IMPLEMENT THIS
		//
		// Check the current load factor of the table
		// If the LF is above the MINIMUM load factor return false, otherwise:
		// - Compute the new size for the array (use nextSmallerPrime)
		// - Use an auxiliary reference to point to the current table
		// - Create a new table with the new size
		// - Iterate over the auxiliary table to access all of the data, add
		// those elements to the new table
		// - Return true
		//
		// WARNING: be careful with numElements...
		// invoked from temove

		return false; // You MUST change this
	}

	@Override
	public String toString() {
		// DON'T CHANGE ANYTHING HERE
		//
		StringBuilder sb = new StringBuilder();
		sb.append("[Size:").append(table.length);
		sb.append(", Elements:").append(getNumElem());
		sb.append(", LF:").append(getLF()).append("]");
		for (HashNode<T> element : table) {
			sb.append("[");
			if (element == null)
				sb.append("_E_");
			else if (element.getState() == HashNode.DELETED)
				sb.append("_D_");
			else
				sb.append(element.getInfo().toString());
			sb.append("]");
		}
		return sb.toString();
	}
}