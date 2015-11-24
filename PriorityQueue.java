package heap;

public interface PriorityQueue<T extends Comparable<T>> {

	/**
	 * @param element The element to be inserted in the queue
	 * @return true if inserted, false otherwise
	 */
	public boolean add(T element);

	/** 
	 * Returns (and removes from the queue) the element with maximum priority
	 * @return The element with maximum priority, or null if the queue is empty
	 */
	public T poll();
	
	/**
	 * Deletes an element from the queue
	 * @param element The element to be removed
	 * @return true if removed, false otherwise
	 */
	public boolean remove (T element);

	/**
	 * @return true if queue is empty
	 */
	public boolean isEmpty();
	
	/**
	 * Removes all of the elements from the queue
	 */
	public void clear();
	
	/**
	 * @return A string representing the status of the queue
	 */
	public String toString();
}