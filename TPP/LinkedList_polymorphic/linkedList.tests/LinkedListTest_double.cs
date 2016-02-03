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
    public class LinkedListTest_double
    {
        MyLinkedList l;

        [TestInitialize()]
        public void CreateList()
        {
            l = new MyLinkedList(0.0);
        }

        [TestMethod()]
        public void ThenItShouldHaveOneElement_double()
        {
            Assert.AreEqual(1, l.NumberOfElements);
        }

        [TestMethod()]
        public void BeforeAddingElementsDontExist_double()
        {
            for (double i = 1.0; i < 6.0; i++)
            {
                Assert.AreEqual(null, l.GetElement(i));
            }
            Assert.AreEqual(0.0, l.GetElementByIndex(0));
        }

        [TestMethod()]
        public void ThenAddAndSizeGrows_double()
        {
            for (double i = 1.0; i < 6.0; i++)
            {
                l.Add(i);
                Assert.AreEqual(i + 1, l.NumberOfElements);
            }
            Assert.AreEqual("0 1 2 3 4 5", l.ToString());
        }

        [TestMethod()]
        public void AfterAddingElementsExist_double()
        {
            ThenAddAndSizeGrows_double();
            for (double i = 0.0; i < 6.0; i++)
            {
                Assert.AreEqual(i, l.GetElement(i));
                Assert.AreEqual(i, l.GetElementByIndex(Convert.ToInt32(i)));
            }
        }

        [TestMethod()]
        public void AfterAddingListContainsTheElements_double()
        {
            ThenAddAndSizeGrows_double();
            for (double i = 0.0; i < 6.0; i++)
            {
                Assert.IsTrue(l.Contains(i));
                Assert.IsFalse(l.Contains(i+6));
            }
        }

        [TestMethod()]
        public void ThenRemoveAndSizeDecreases_double()
        {
            ThenAddAndSizeGrows_double();
            int currentSize;
            for (double i = 0.0; i < 6.0; i++)
            {
                currentSize = l.NumberOfElements;
                l.Remove(i);
                Assert.AreEqual(currentSize - 1, l.NumberOfElements);
            }
            Assert.AreEqual("", l.ToString());
        }

        [TestMethod()]
        public void AfterRemovingElementsDontExist_double()
        {
            ThenRemoveAndSizeDecreases_double();
            for (double i = 0.0; i < 6.0; i++)
            {
                Assert.AreEqual(null, l.GetElement(i));
            }
        }

        [TestMethod()]
        public void ButCantRemoveUnexistingItems_double()
        {
            for (double i = 1.0; i < 6.0; i++)
            {
                Assert.AreEqual(null, l.Remove(i));
                Assert.AreEqual(1, l.NumberOfElements);
            }
        }

        [TestMethod()]
        public void GetElementTest_double()
        {
            for (double i = 1.0; i < 30.0; i++)
            {
                l.Add(i);
                Assert.AreEqual(i, l.GetElement(i));
                Assert.AreEqual(null, l.GetElement(i + 30));
            }
        }


    }
}