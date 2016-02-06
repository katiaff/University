using System;
using System.Collections.Generic;

namespace LinkedList {
    /// <summary>
    /// Provides a LinkedListNode implementation
    /// </summary>
    public class Node<T> {
        /// <summary>
        /// Value for the node
        /// </summary>
        public T Value { get; protected internal set; }

        /// <summary>
        /// Pointer to the next node
        /// </summary>
        public Node<T> Next { get; protected internal set; }

        /// <summary>
        /// Constructor for a linked node
        /// </summary>
        /// <param name="value">Value for the node</param>
        /// <param name="next">Link to the following node</param>
        public Node(T value, Node<T> next) {
            this.Value = value;
            this.Next = next;
        }

        /// <summary>
        /// Returns the value of the Value property
        /// </summary>
        /// <returns>Value</returns>
        public override string ToString() {
            return Value.ToString();
        }


    }
}
