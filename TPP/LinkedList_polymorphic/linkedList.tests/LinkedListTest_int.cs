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
    public class LinkedListTest_int
    {
        MyLinkedList l;

        [TestInitialize()]
        public void CreateList()
        {
            l = new MyLinkedList(0);
        }

        [TestMethod()]
        public void ThenItShouldHaveOneElement()
        {
            Assert.AreEqual(1, l.NumberOfElements);
        }

        [TestMethod()]
        public void BeforeAddingElementsDontExist()
        {
            for (int i = 1; i < 6; i++)
            {
                Assert.AreEqual(null, l.GetElement(i));
            }
            Assert.AreEqual(0, l.GetElement(0));
        }

        [TestMethod()]
        public void ThenAddAndSizeGrows()
        {
            for (int i = 1; i < 6; i++)
            {
                l.Add(i);
                Assert.AreEqual(i + 1, l.NumberOfElements);
            }
            Assert.AreEqual("0 1 2 3 4 5", l.ToString());
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
                Assert.IsFalse(l.Contains(i+6));
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
                Assert.AreEqual(null, l.GetElement(i));
            }
        }

        [TestMethod()]
        public void ButCantRemoveUnexistingItems()
        {
            for (int i = 1; i < 6; i++)
            {
                Assert.AreEqual(null, l.Remove(i));
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
                Assert.AreEqual(null, l.GetElement(i + 30));
            }
        }


    }
}