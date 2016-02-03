using Microsoft.VisualStudio.TestTools.UnitTesting;
using LinkedList;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LinkedList
{
    [TestClass()]
    public class LinkedListTest_String
    {
        MyLinkedList l;

        [TestInitialize()]
        public void CreateList()
        {
            l = new MyLinkedList("a");
        }

        [TestMethod()]
        public void ThenItShouldHaveOneElement()
        {
            Assert.AreEqual(1, l.NumberOfElements);
        }

        [TestMethod()]
        public void BeforeAddingElementsDontExist()
        {
            Assert.AreEqual(-1, l.GetElement("b"));
            Assert.AreEqual(-1, l.GetElement("c"));
            Assert.AreEqual(-1, l.GetElement("d"));
            Assert.AreEqual(-1, l.GetElement("e"));
        }

        [TestMethod()]
        public void ThenAddAndSizeGrows()
        {
            int num = l.NumberOfElements;
            l.Add("b");
            Assert.AreEqual(num + 1, l.NumberOfElements);
            l.Add("c");
            Assert.AreEqual(num + 2, l.NumberOfElements);
            l.Add("d");
            Assert.AreEqual(num + 3, l.NumberOfElements);
            l.Add("e");
            Assert.AreEqual(num + 4, l.NumberOfElements);

            Assert.AreEqual("a b c d e", l.ToString());
        }

        [TestMethod()]
        public void AfterAddingElementsExist()
        {
            ThenAddAndSizeGrows();
            for (int i = 0; i < 6; i++)
            {
                Assert.AreEqual(i, l.GetElement(i));
            }
        }

        [TestMethod()]
        public void AfterAddingListContainsTheElements()
        {
            ThenAddAndSizeGrows();
            for (int i = 0; i < 6; i++)
            {
                Assert.IsTrue(l.Contains(i));
                Assert.IsFalse(l.Contains(i + 6));
            }
        }

        [TestMethod()]
        public void ThenRemoveAndSizeDecreases()
        {
            ThenAddAndSizeGrows();
            int currentSize;
            for (int i = 0; i < 6; i++)
            {
                currentSize = l.NumberOfElements;
                l.Remove(i);
                Assert.AreEqual(currentSize - 1, l.NumberOfElements);
            }
            Assert.AreEqual("", l.ToString());
        }

        [TestMethod()]
        public void AfterRemovingElementsDontExist()
        {
            ThenRemoveAndSizeDecreases();
            for (int i = 0; i < 6; i++)
            {
                Assert.AreEqual(-1, l.GetElement(i));
            }
        }

        [TestMethod()]
        public void ButCantRemoveUnexistingItems()
        {
            for (int i = 1; i < 6; i++)
            {
                Assert.AreEqual(-1, l.Remove(i));
                Assert.AreEqual(1, l.NumberOfElements);
            }
        }

        [TestMethod()]
        public void GetElementTest()
        {
            for (int i = 1; i < 30; i++)
            {
                l.Add(i);
                Assert.AreEqual(i, l.GetElement(i));
                Assert.AreEqual(-1, l.GetElement(i + 30));
            }
        }


    }
}