using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LinkedList {
    class Stack<T> {
        private MyLinkedList<T> stack;
        private uint MaxNumberOfElements { get; set; }

        public bool IsEmpty {
             get {
               return stack.NumberOfElements == 0;
            }
        }

        public bool IsFull {
            get {
                return stack.NumberOfElements == this.MaxNumberOfElements;
            }
        }

        public Stack(uint maxNumberOfElements) {
            stack = new MyLinkedList<T>();
            this.MaxNumberOfElements = maxNumberOfElements;
        }

        public Stack(uint maxNumberOfElements, T firstValue) {
            stack = new MyLinkedList<T>(firstValue);
            this.MaxNumberOfElements = maxNumberOfElements;
        }

        public void Push(T element) {
            Debug.Assert(stack.NumberOfElements != MaxNumberOfElements);
            stack.AddBeginning(element);
            Debug.Assert(!IsEmpty);
        }

        public void Pop() {
            Debug.Assert(!IsEmpty);
            stack.RemoveFirst();
        }
    }
}
