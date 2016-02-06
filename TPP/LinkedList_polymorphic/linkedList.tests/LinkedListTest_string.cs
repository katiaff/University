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
        MyLinkedList<String> l;

        [TestInitialize()]
        public void CreateList()
        {
            l = new MyLinkedList<String>("a");
        }

        [TestMethod()]
        public void ThenItShouldHaveOneElement_String()
        {
            Assert.AreEqual(1, l.NumberOfElements);
        }

        [TestMethod()]
        public void BeforeAddingElementsDontExist_String()
        {
            Assert.AreEqual(null, l.GetElement("b"));
            Assert.AreEqual(null, l.GetElement("c"));
            Assert.AreEqual(null, l.GetElement("d"));
            Assert.AreEqual(null, l.GetElement("e"));
        }

        [TestMethod()]
        public void ThenAddAndSizeGrows_String()
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
        public void AfterAddingElementsExist_String()
        {
            ThenAddAndSizeGrows_String();
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
        public void AfterAddingListContainsTheElements_String()
        {
            ThenAddAndSizeGrows_String();
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
        public void ThenRemoveAndSizeDecreases_String()
        {
            ThenAddAndSizeGrows_String();
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
        public void AfterRemovingElementsDontExist_String()
        {
            ThenRemoveAndSizeDecreases_String();
            Assert.AreEqual(null, l.GetElement("a"));
            Assert.AreEqual(null, l.GetElement("b"));
            Assert.AreEqual(null, l.GetElement("c"));
            Assert.AreEqual(null, l.GetElement("d"));
            Assert.AreEqual(null, l.GetElement("e"));

        }

        [TestMethod()]
        public void ButCantRemoveUnexistingItems_String()
        {
            Assert.AreEqual(null, l.Remove("blabla"));
            Assert.AreEqual(null, l.GetElement("blabla2"));
            Assert.AreEqual(null, l.GetElement("blabla3"));
            Assert.AreEqual(null, l.GetElement("blabla4"));
            Assert.AreEqual(null, l.GetElement("blabla5"));
            Assert.AreEqual(null, l.GetElement("blabla6"));
            Assert.AreEqual(1, l.NumberOfElements);
        }

        [TestMethod()]
        public void GetElementTest_String()
        {
            Assert.AreEqual(null, l.GetElement("blabla"));
            Assert.AreEqual(null, l.GetElement("blabla2"));
            Assert.AreEqual(null, l.GetElement("blabla3"));
            Assert.AreEqual(null, l.GetElement("blabla4"));
            Assert.AreEqual(null, l.GetElement("blabla5"));
            Assert.AreEqual(null, l.GetElement("blabla6"));
        }


    }
}