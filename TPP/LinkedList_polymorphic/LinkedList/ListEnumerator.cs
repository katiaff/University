using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LinkedList
{
    class ListEnumerator<T> : IEnumerator<T>
    {
        private MyLinkedList<T> myLinkedList;
        private int pos;

        public ListEnumerator(MyLinkedList<T> myLinkedList)
        {
            this.myLinkedList = myLinkedList;
            this.pos = 0;
        }
        public T Current
        {
            get { return myLinkedList.GetElementByIndex(pos); }
        }

        public void Dispose()
        {
            throw new NotImplementedException();
        }

        object System.Collections.IEnumerator.Current
        {
            get { return myLinkedList.GetElementByIndex(pos); }
        }

        public bool MoveNext()
        {
            
            if (!myLinkedList.GetElementByIndex(pos+1).Equals(default(T))){
                pos++;
                return true;
            }
            else
            {
                return false;
            }
        }

        public void Reset()
        {
            this.pos = 0;
        }
    }
}
