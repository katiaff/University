using System;
using System.Text;

namespace LinkedList
{
    /// <summary>
    /// Provides a LinkedList implementation
    /// with Add, Remove and GetElement methods.
    /// </summary>
    class LinkedList
    {
        /// <summary>
        /// First element of the list.
        /// </summary>
        public Node Head { get; private set; }

        /// <summary>
        /// Number of elements on the list.
        /// </summary>
        public int NumberOfElements { get; private set; }

        /// <summary>
        /// Creates a LinkedList with one element.
        /// </summary>
        /// <param name="firstValue">Value for the first
        /// element of the list.</param>
        public LinkedList(int firstValue)
        {
            Head = new Node(firstValue, new Node(-1, null));
            NumberOfElements = 1;
        }

        /// <summary>
        /// Adds a new element at the end of the list.
        /// </summary>
        /// <param name="value">New element to be added to the list.</param>
        public void Add(int value)
        {
            if (NumberOfElements != 0)
            {
                Node newLast = new Node(value, null);
                Node last;
                last = GetNodeFromIndex(NumberOfElements - 1);
                last.Next = newLast;            
            }
            else
            {
                Head = new Node (value, null);
            }
            NumberOfElements++;

        }

        /// <summary>
        /// Removes an element from the list.
        /// </summary>
        /// <param name="value">Element to be removed.</param>
        /// <returns>Removed element or -1 
        /// if element was not found.</returns>
        public int Remove(int value)
        {
            int ret = -1;
            if (NumberOfElements != 0)
            {
                int index = GetIndex(value);

                // if the value exists on the list
                if (index != -1)
                {
                    Node current = GetNodeFromIndex(index);
                    int val = current.Value;

                    // if we find the element

                    ret = val;
                    // if it is not the first element
                    if (current != Head)
                    {
                        Node previous = GetNodeFromIndex(index - 1);
                        previous.Next = current.Next;
                    }
                    else
                    {
                        Head = Head.Next;
                    }
                    NumberOfElements--;

                }
            }


            return ret;
        }

        /// <summary>
        /// Gets the index of a value on the list
        /// </summary>
        /// <param name="value">Value to be found</param>
        /// <returns>Index of the value. If two values are the same, returns the first found.</returns>
        private int GetIndex(int value)
        {
            Node ptr = Head;
            for (int i = 0; i < NumberOfElements; i++)
            {
                if (ptr.Value == value)
                {
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
        private Node GetNodeFromIndex(int i)
        {
            Node newHead = Head;
            for (int n = 0; n < i; n++)
            {
                newHead = newHead.Next;
            }
            return newHead;
        }

        /// <summary>
        /// Gets an element on the list
        /// </summary>
        /// <param name="value"></param>
        /// <returns>The element if it is on the list,
        /// -1 if it is not on the list.</returns>
        public int GetElement(int value)
        {
            for (int i = 0; i < NumberOfElements; i++)
            {
                Node current = GetNodeFromIndex(i);
                int val = current.Value;

                // if we find the element
                if (val == value)
                {
                    return val;
                }
            }
            return -1;
        }

        /// <summary>
        /// Returns a string showing all the elements
        /// on the list.
        /// </summary>
        /// <returns>List string</returns>
        public override string ToString()
        {
            StringBuilder sb = new StringBuilder();
            Node ptr = Head;
            for (int i = 0; i < NumberOfElements; i++)
            {
                sb.Append(ptr + " ");
                ptr = ptr.Next;
            }
            return sb.ToString().TrimEnd();
        }
    }
}
