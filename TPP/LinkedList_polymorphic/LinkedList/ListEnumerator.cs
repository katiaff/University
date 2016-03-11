using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LinkedList {
    class ListEnumerator<T> : IEnumerator<T> {
        private MyLinkedList<T> myLinkedList;
        private int pos;

        public ListEnumerator(MyLinkedList<T> myLinkedList) {
            this.myLinkedList = myLinkedList;
            this.pos = -1;
        }
        public T Current {
            get {
                return myLinkedList.GetElementByIndex(pos);
            }
        }

        public void Dispose() {

        }

        object System.Collections.IEnumerator.Current {
            get { return Current; }
        }

        public bool MoveNext() {

            if (pos++ != myLinkedList.NumberOfElements - 1) {
                return true;
            } else {
                return false;
            }
        }

        public void Reset() {
            this.pos = -1;
        }
    }
}
