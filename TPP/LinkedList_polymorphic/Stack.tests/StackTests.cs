using Microsoft.VisualStudio.TestTools.UnitTesting;
using LinkedList;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LinkedList.tests {
    [TestClass()]
    public class StackTests {
        Stack<int> stack;

        [TestInitialize]
        public void CreateNewStack() {
            stack = new Stack<int>(5);
        }

        [TestMethod()]
        public void ThenItShouldBeEmpty() {
            Assert.IsTrue(stack.IsEmpty);
            Assert.AreEqual(0, stack.NumElements);
        }

        [TestMethod()]
        public void PlayWithMaxNumElems() {
            Assert.AreEqual(5, (int) stack.MaxNumberOfElements);
            stack.MaxNumberOfElements = 10;
            Assert.AreEqual(10, (int) stack.MaxNumberOfElements);
            stack.MaxNumberOfElements = 100;
            Assert.AreEqual(100, (int) stack.MaxNumberOfElements);
            stack.MaxNumberOfElements = 0;
            Assert.AreEqual(0, (int) stack.MaxNumberOfElements);

        }

        [TestMethod()]
        public void StackTest1() {

        }

        [TestMethod()]
        public void PushTest() {

        }

        [TestMethod()]
        public void PopTest() {

        }
    }
}