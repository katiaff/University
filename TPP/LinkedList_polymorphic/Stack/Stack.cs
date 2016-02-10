using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LinkedList {
    /// <summary>
    /// Implementation of a stack
    /// </summary>
    /// <typeparam name="T">Type of values for the stack</typeparam>
    public class Stack<T> {
        /// <summary>
        /// Stack implemented via MyLinkedList
        /// </summary>
        private MyLinkedList<T> Elems { get; }

        /// <summary>
        /// Maximum number of elements in the stack
        /// </summary>
        public uint MaxNumberOfElements { get; set; }

        public int NumElements { get { return Elems.NumberOfElements; } }

        /// <summary>
        /// If the stack is empty
        /// </summary>
        public bool IsEmpty {
            get {
                return Elems.NumberOfElements == 0;
            }
        }

        /// <summary>
        /// If the stack is full
        /// </summary>
        public bool IsFull {
            get {
                return Elems.NumberOfElements == this.MaxNumberOfElements;
            }
        }

        /// <summary>
        /// Constructs an empty stack with maximum number 
        /// of elements equal to the parameter
        /// </summary>
        /// <param name="maxNumberOfElements">Maximum number of elements </param>
        public Stack(uint maxNumberOfElements) {
            Elems = new MyLinkedList<T>();
            this.MaxNumberOfElements = maxNumberOfElements;
        }

        /// <summary>
        /// Constructs a stack with one element
        /// </summary>
        /// <param name="maxNumberOfElements">>Maximum number of elements</param>
        /// <param name="firstValue">First element</param>
        public Stack(uint maxNumberOfElements, T firstValue) {
            Elems = new MyLinkedList<T>(firstValue);
            this.MaxNumberOfElements = maxNumberOfElements;
        }

        /// <summary>
        /// Pushes an element to the stack
        /// </summary>
        /// <param name="element">Element to be pushed</param>
        public void Push(T element) {
            Debug.Assert(Elems.NumberOfElements != MaxNumberOfElements);
            Elems.AddBeginning(element);
            Debug.Assert(!IsEmpty);
        }

        /// <summary>
        /// Pops the top element from the stack
        /// </summary>
        public void Pop() {
            Debug.Assert(!IsEmpty);
            Elems.RemoveFirst();
        }
    }
}
