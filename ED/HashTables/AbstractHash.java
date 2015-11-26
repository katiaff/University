package hashtables;

public abstract class AbstractHash <T> {
	
	/**
	 * Returns the number of elements in the hash table 
	 * 
	 * @return The number of elements.
	 */
	abstract public int getNumElem();
	
	/**
	 * Returns the size of the hash table
	 * @return the size of the hash table
	 */
	public abstract int getSize();	

	/** Adds a new element into the hash table
	 * @param elem element to be added
	 * @return boolean true if the element has been added, false if the element hasn't been added 
	 */
	// only false if the element is repeated
	public abstract boolean add(T elem);
	
	/** Looks for and returns an element in the hash table
	 * @param elem element to be found
	 * @return the element if it is found, null if the element is not found 
	 */
	public abstract T find (T elem);
	
	/** Removes an element into the hash table
	 * @param elem element to be deleted
	 * @return boolean true if the element has been deleted, false if the element hasn't been deleted 
	 */	
	public abstract boolean remove(T elem);

	/**
	 * Dynamic Resizing increasing size if Load Factor is high
	 * 
	 * @return true if resized, false otherwise
	 */
	abstract protected boolean upsize ();

	/**
	 * Dynamic Resizing decreasing size if Load Factor is low
	 * 
	 * @return true if resized, false otherwise
	 */
	abstract protected boolean downsize ();
	
	/**
	 * Produces a String to show the hash table
	 *
	 * @return the String with the information stored in the hash table.
	 */
	abstract public String toString ();

   /**
    * Finds out if a positive number is a prime number. Negative numbers will
    * be returned as non prime.
    * 
    * @param number the number to be evaluated
    * @return true if it is a prime number, false if it is not a prime number
    */
    protected boolean isPrime(long n){
        if (n < 0 || n % 2 == 0)
            return false;
        if (n <= 7)
            return true;
        for (int i = 3 ; i * i <= n ; i += 2) //odd
            if (n % i == 0)
                return false; // not prime
        return true;
    }

    /**
     * Returns the closest bigger prime number
     * @param number the base number
     * @return the closest bigger prime number calculated
     */
    protected int nextBiggerPrime(int number){
        int num = number;
        while (!isPrime(num))
            num++;
        return num;
    } 

    /**
     * Returns the closest smaller prime number
     * @param number the base number
     * @return the closest smaller prime number calculated
     */
    protected int nextSmallerPrime(int number){
    	if (number<1) number=1;
        int num = number;
        while (!isPrime(num))
            num--;
        return num;
    } 
    
	/**
	 * Computes the result of applying the hash function on the parameter.
	 *
	 * @return the corresponding result
	 */
	 protected int fHash(T elem){
		 int prime=getSize();
		 return (elem.hashCode()%prime+prime)%prime;
	 }
}