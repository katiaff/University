using Microsoft.VisualStudio.TestTools.UnitTesting;
using LinkedList;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace LinkedList.tests {
    [TestClass()]
    public class ExtensionsTests {
        private MyLinkedList<int> l;

        [TestInitialize]
        public void Init() {
            l = new MyLinkedList<int>();
            for (int i = 0; i < 1000; i++) {
                l.Add(i);
            }
        }

        [TestMethod()]
        public void FindTest() {
            Predicate<int> isEven = (x => x % 2 == 0);
            Assert.AreEqual(2, l.Find(isEven));

            Predicate<int> isOdd = (x => x % 2 != 0);
            Assert.AreEqual(1, l.Find(isOdd));

            Predicate<int> isDivisibleBy15And20 = (x => x % 15 == 0 && x % 20 == 0);
            Assert.AreEqual(60, l.Find(isDivisibleBy15And20));

        }

        [TestMethod()]
        public void FilterTest() {
            Assert.Fail();
        }

        [TestMethod()]
        public void ReduceTest() {
            Assert.Fail();
        }
    }
}