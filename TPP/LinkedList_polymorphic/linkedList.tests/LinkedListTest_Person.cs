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
    public class LinkedListTest_Person
    {
        MyLinkedList l;

        [TestInitialize()]
        public void CreateList()
        {
            l = new MyLinkedList("a");
        }

        [TestMethod()]
        public void ThenItShouldHaveOneElement_Person()
        {
            Assert.AreEqual(1, l.NumberOfElements);
        }

        [TestMethod()]
        public void BeforeAddingElementsDontExist_Person()
        {
            Assert.AreEqual(null, l.GetElement("b"));
            Assert.AreEqual(null, l.GetElement("c"));
            Assert.AreEqual(null, l.GetElement("d"));
            Assert.AreEqual(null, l.GetElement("e"));
        }

        [TestMethod()]
        public void ThenAddAndSizeGrows_Person()
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
        public void AfterAddingElementsExist_Person()
        {
            ThenAddAndSizeGrows_Person();
            Assert.AreEqual("a", l.GetElement("a"));
            Assert.AreEqual("b", l.GetElement("b"));
            Assert.AreEqual("c", l.GetElement("c"));
            Assert.AreEqual("d", l.GetElement("d"));
            Assert.AreEqual("e", l.GetElement("e"));
            Assert.AreEqual("a", l.GetElementByIndex(0));
            Assert.AreEqual("b", l.GetElementByIndex(1));
            Assert.AreEqual("c", l.GetElementByIndex(2));
            Assert.AreEqual("d", l.GetElementByIndex(3));
            Assert.AreEqual("e", l.GetElementByIndex(4));
        }

        [TestMethod()]
        public void AfterAddingListContainsTheElements_Person()
        {
            ThenAddAndSizeGrows_Person();
            Assert.IsTrue(l.Contains("a"));
            Assert.IsTrue(l.Contains("b"));
            Assert.IsTrue(l.Contains("c"));
            Assert.IsTrue(l.Contains("d"));
            Assert.IsTrue(l.Contains("e"));
            Assert.IsFalse(l.Contains("f"));
            Assert.IsFalse(l.Contains("g"));
            Assert.IsFalse(l.Contains("h"));
            Assert.IsFalse(l.Contains("i"));

        }

        [TestMethod()]
        public void ThenRemoveAndSizeDecreases_Person()
        {
            ThenAddAndSizeGrows_Person();
            int num = l.NumberOfElements;
            l.Remove("a");
            Assert.AreEqual(num - 1, l.NumberOfElements);
            l.Remove("b");
            Assert.AreEqual(num - 2, l.NumberOfElements);
            l.Remove("c");
            Assert.AreEqual(num - 3, l.NumberOfElements);
            l.Remove("d");
            Assert.AreEqual(num - 4, l.NumberOfElements);
            l.Remove("e");
            Assert.AreEqual(num - 5, l.NumberOfElements);

            Assert.AreEqual("", l.ToString());
        }

        [TestMethod()]
        public void AfterRemovingElementsDontExist_Person()
        {
            ThenRemoveAndSizeDecreases_Person();
            Assert.AreEqual(null, l.GetElement("a"));
            Assert.AreEqual(null, l.GetElement("b"));
            Assert.AreEqual(null, l.GetElement("c"));
            Assert.AreEqual(null, l.GetElement("d"));
            Assert.AreEqual(null, l.GetElement("e"));

        }

        [TestMethod()]
        public void ButCantRemoveUnexistingItems_Person()
        {
            for (int i = 1; i < 6; i++)
            {
                Assert.AreEqual(null, l.Remove(i));
                Assert.AreEqual(1, l.NumberOfElements);
            }
        }

        [TestMethod()]
        public void GetElementTest_Person()
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