namespace LinkedList
{
    /// <summary>
    /// Provides a LinkedListNode implementation
    /// </summary>
    public class Node
    {
        /// <summary>
        /// Value for the node
        /// </summary>
        public int Value { get; protected internal set; }

        /// <summary>
        /// Pointer to the next node
        /// </summary>
        public Node Next { get; protected internal set; }

        /// <summary>
        /// Constructor for a linked node
        /// </summary>
        /// <param name="value">Value for the node</param>
        /// <param name="next">Link to the following node</param>
        public Node(int value, Node next)
        {
            this.Value = value;
            this.Next = next;
        }

        /// <summary>
        /// Returns the value of the Value property
        /// </summary>
        /// <returns>Value</returns>
        public override string ToString()
        {
            return Value.ToString();
        }


    }
}
