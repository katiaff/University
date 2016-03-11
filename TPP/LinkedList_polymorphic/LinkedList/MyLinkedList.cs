using System;
using System.Text;
using System.Collections.Generic;
using System.Collections;

namespace LinkedList {
    /// <summary>
    /// Provides a MyLinkedList implementation
    /// with Add, Remove, GetElement and Contains methods.
    /// </summary>
    /// 
    public class MyLinkedList<T> : IEnumerable<T> {
        /// <summary>
        /// First element of the list.
        /// </summary>
        public Node<T> Head { get; private set; }

        /// <summary>
        /// Number of elements on the list.
        /// </summary>
        public int NumberOfElements { get; private set; }

        /// <summary>
        /// Constructs a new empty list
        /// </summary>
        public MyLinkedList() {
            Head = null;
            NumberOfElements = 0;
        }

        /// <summary>
        /// Constructs a new list with a first value
        /// </summary>
        /// <param name="firstValue">First value</param>
        public MyLinkedList(T firstValue) {
            Head = new Node<T>(firstValue, new Node<T>(default(T), null));
            NumberOfElements = 1;
        }

        /// <summary>
        /// Adds an element to the beginning of the list
        /// </summary>
        /// <param name="value">Element to add</param>
        public void AddBeginning(T value) {
            if (NumberOfElements != 0) {
                Node<T> newFirst = new Node<T>(value, Head);
                Head = newFirst;
            } else {
                Head = new Node<T>(value, null);
            }
            NumberOfElements++;
        }

        /// <summary>
        /// Adds a new element at the end of the list.
        /// </summary>
        /// <param name="value">New element to be added to the list.</param>
        public virtual void Add(T value) {
            if (NumberOfElements != 0) {
                Node<T> newLast = new Node<T>(value, null);
                Node<T> last;
                last = GetNodeFromIndex(NumberOfElements - 1);
                last.Next = newLast;
            } else {
                Head = new Node<T>(value, null);
            }
            NumberOfElements++;

        }

        /// <summary>
        /// Removes an element from the list.
        /// </summary>
        /// <param name="value">Element to be removed.</param>
        /// <returns>Removed element or -1 
        /// if element was not found.</returns>
        public T Remove(T value) {
            T ret = default(T);

            if (NumberOfElements != 0) {
                int index = GetIndex(value);

                // if the value exists on the list
                if (index != -1) {
                    Node<T> current = GetNodeFromIndex(index);
                    T val = current.Value;

                    // if we find the element

                    ret = val;
                    // if it is not the first element
                    if (current != Head) {
                        Node<T> previous = GetNodeFromIndex(index - 1);
                        previous.Next = current.Next;
                    } else {
                        Head = Head.Next;
                    }
                    NumberOfElements--;

                }
            }

            return ret;
        }

        /// <summary>
        /// Removes and returns the first element of the list
        /// </summary>
        /// <returns></returns>
        public T RemoveFirst() {
            T value = Head.Value;
            Remove(value);
            return value;
        }

        /// <summary>
        /// Gets the index of a value on the list
        /// </summary>
        /// <param name="value">Value to be found</param>
        /// <returns>Index of the value. If two values are the same, returns the first found.</returns>
        private int GetIndex(T value) {
            Node<T> ptr = Head;
            for (int i = 0; i < NumberOfElements; i++) {
                if (ptr.Value.Equals(value)) {
                    return i;
                }
                ptr = ptr.Next;
            }
            return -1;
        }

        /// <summary>
        /// Returns the node at index i.
        /// </summary>
        /// <param name="index">Index of the node.</param>
        /// <returns></returns>
        private Node<T> GetNodeFromIndex(int i) {
            if (isRightIndex(i)) {
                Node<T> newHead = Head;
                for (int n = 0; n < i; n++) {
                    newHead = newHead.Next;
                }
                return newHead;
            } else {
                return null;
            }
        }

        /// <summary>
        /// Checks if a specific T is in the list
        /// </summary>
        /// <param name="value">True if it is in the list; false, otherwise.</param>
        /// <returns></returns>
        public bool Contains(T value) {
            return GetIndex(value) != -1;

        }

        /// <summary>
        /// Gets an element on the list
        /// </summary>
        /// <param name="value"></param>
        /// <returns>The element if it is on the list,
        /// default(T) if it is not on the list.</returns>
        public T GetElement(T value) {
            for (int i = 0; i < NumberOfElements; i++) {
                Node<T> current = GetNodeFromIndex(i);
                T val = current.Value;

                // if we find the element
                if (val.Equals(value)) {
                    return val;
                }
            }
            return default(T);
        }
        /// <summary>
        /// Gets the element from a specific index
        /// </summary>
        /// <param name="i">index >= 0 && index < NumberOfElements</param>
        /// <returns>The element if the index is valid;
        /// default(T) otherwise</returns>
        public T GetElementByIndex(int i) {
            if (isRightIndex(i)) {
                Node<T> node = GetNodeFromIndex(i);
                return node.Value;
            } else {
                return default(T);
            }
        }

        /// <summary>
        /// Checks if it is a valid index
        /// </summary>
        /// <param name="i">The index</param>
        /// <returns>True if it is valid; False, otherwise</returns>
        private bool isRightIndex(int i) {
            return i >= 0 && i < NumberOfElements;
        }

        /// <summary>
        /// [] operator overloading, get an item from an index
        /// </summary>
        /// <param name="list">The list</param>
        /// <param name="index">Index to get the element</param>
        /// <returns>Element at index i of the list</returns>
        public T this[int index] {
            get { return GetElementByIndex(index); }

            set { GetElementByIndex(index); }

        }

        /// <summary>
        /// Returns a string showing all the elements
        /// on the list.
        /// </summary>
        /// <returns>List string</returns>
        public override string ToString() {
            StringBuilder sb = new StringBuilder();
            Node<T> ptr = Head;
            for (int i = 0; i < NumberOfElements; i++) {
                sb.Append(ptr + " ");
                ptr = ptr.Next;
            }
            return sb.ToString().TrimEnd();
        }

        /*/// <summary>
        /// Gets the IEnumerator for this list
        /// </summary>
        /// <returns></returns>
       public IEnumerator<T> GetEnumerator() {
            return new ListEnumerator<T>(this);
        }*/

        /// <summary>
        /// Generator for  the IEnumerator for this list
        /// </summary>
        /// <returns></returns>
        public IEnumerator<T> GetEnumerator() {
            for (int i = 0; i < NumberOfElements; i++) {
                yield return GetElementByIndex(i);
            }
        }


        /// <summary>
        /// Gets the IEnumerator for this list
        /// </summary>
        /// <returns></returns>
        IEnumerator IEnumerable.GetEnumerator() {
            return GetEnumerator();
        }

    }
}
