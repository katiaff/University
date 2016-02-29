using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Collections.Generic;

namespace LinkedList
{
    [TestClass]
    public class VectorTests
    {
        IList<int> list;

        [TestInitialize]
        public void Initialize() {
            list = new List<int>();
        }

        [TestMethod]
        public void AddElementsAndSizeGrows()
        {
            for (int i = 0; i < 10; i++)
            {
                list.Add(i);
                Assert.AreEqual(i + 1, list.Count);
            }

        }

        public void AddElementsAndElementsExist() {
            AddElementsAndSizeGrows();
            for (int i = 0; i < 10; i++) {
                Assert.IsTrue(list.Contains(i));
            }
        }

        [TestMethod]
        public void SetAndGetNumberOfElements()
        {
            Assert.AreEqual(0, list.Count);
            list.Add(0);
            Assert.AreEqual(1, list.Count);
            list.Add(1);
            Assert.AreEqual(2, list.Count);
            list.Add(2);
            Assert.AreEqual(3, list.Count);
            list.Add(3);
            Assert.AreEqual(4, list.Count);
        }

        [TestMethod]
        public void GetElements()
        {
            SetAndGetNumberOfElements();
            Assert.AreEqual(0, list[0]);
            Assert.AreEqual(1, list[1]);
            Assert.AreEqual(2, list[2]);
            Assert.AreEqual(3, list[3]);


        }

        [TestMethod]
        public void SetElements()
        {
            SetAndGetNumberOfElements();
            for (int i = 0; i<list.Count; i++) {
                list[i] = list[i] + 2;
            }
            Assert.AreEqual(2, list[0]);
            Assert.AreEqual(3, list[1]);
            Assert.AreEqual(4, list[2]);
            Assert.AreEqual(5, list[3]);


        }

        [TestMethod]
        public void ExistsElement() {
            SetAndGetNumberOfElements();

            Assert.IsTrue(list.Contains(0));
            Assert.IsTrue(list.Contains(1));
            Assert.IsTrue(list.Contains(2));
            Assert.IsTrue(list.Contains(3));

            Assert.IsFalse(list.Contains(4));
            Assert.IsFalse(list.Contains(5));
            Assert.IsFalse(list.Contains(6));
            Assert.IsFalse(list.Contains(7));
        }

        [TestMethod]
        public void IndexOfElement() {
            SetAndGetNumberOfElements();

            Assert.AreEqual(0, list.IndexOf(0));
            Assert.AreEqual(1, list.IndexOf(1));
            Assert.AreEqual(2, list.IndexOf(2));
            Assert.AreEqual(3, list.IndexOf(3));

            Assert.AreEqual(-1, list.IndexOf(4));
            Assert.AreEqual(-1, list.IndexOf(5));
            Assert.AreEqual(-1, list.IndexOf(6));
            Assert.AreEqual(-1, list.IndexOf(7));

        }

        [TestMethod]
        public void RemoveElementAndSizeDecreases() {
            AddElementsAndSizeGrows();
            int size = list.Count;
            for (int i = 0; i < 5; i++) {
                list.Remove(i);
                Assert.AreEqual(size - 1, list.Count);
                size--;
            }
        }

        [TestMethod]
        public void RemoveElementAndElementsDoNotExist() {
            RemoveElementAndSizeDecreases();
            for (int i = 0; i < 5; i++) {
                Assert.IsFalse(list.Contains(i));
            }
        }

        [TestMethod]
        public void IterateWithForEach() {
            AddElementsAndSizeGrows();
            foreach (int num in list) {
                Assert.IsTrue(list.Contains(num));
            }
        }

    }
}
