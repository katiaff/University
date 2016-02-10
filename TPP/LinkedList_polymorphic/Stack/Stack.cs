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

        private uint maxNumberOfElements;

        /// <summary>
        /// Maximum number of elements in the stack
        /// </summary>
        public uint MaxNumberOfElements {
            get {
                return maxNumberOfElements;
            }
            set {
                if (value >= Elems.NumberOfElements) {
                    maxNumberOfElements = value;
                }
            }
        }

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
            Debug.Assert(IsEmpty);
        }

        /// <summary>
        /// Constructs a stack with one element
        /// </summary>
        /// <param name="maxNumberOfElements">>Maximum number of elements</param>
        /// <param name="firstValue">First element</param>
        public Stack(uint maxNumberOfElements, T firstValue) {
            Elems = new MyLinkedList<T>(firstValue);
            this.MaxNumberOfElements = maxNumberOfElements;
            Debug.Assert(!IsEmpty);
            Debug.Assert(Elems.NumberOfElements == 1);
        }

        /// <summary>
        /// Pushes an element to the stack
        /// </summary>
        /// <param name="element">Element to be pushed</param>
        public void Push(T element) {
            Debug.Assert(!IsFull);
            if (!IsFull) {
                Elems.AddBeginning(element);
            }
            Debug.Assert(!IsEmpty);

        }
        /// <summary>
        /// Pops the top element from the stack
        /// </summary>
        /// <returns>The element on top if the stack was not empty;
        /// default(T) otherwise</returns>
        public T Pop() {
            Debug.Assert(!IsEmpty);
            if (!IsEmpty) {
                T removed = Elems.RemoveFirst();
                Debug.Assert(!IsFull);
                return removed;
            } else {
                return default(T);
            }


        }

        /// <summary>
        /// Checks if the stack contains an element
        /// </summary>
        /// <param name="elem">Element</param>
        /// <returns>True if it contains the element;
        /// False, otherwise</returns>
        public bool Contains(T elem) {
            return Elems.Contains(elem);
        }

        /// <summary>
        /// Gets an element from the stack
        /// </summary>
        /// <param name="elem">Element to get</param>
        /// <returns>Element if it is contained in the stack;
        /// Default(T) otherwise</returns>
        public T GetElement(T elem) {
            return Elems.GetElement(elem);
        }

        public T GetElementByIndex(int index) {
            return Elems.GetElementByIndex(index);
        }
    }
}
