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
        public void PlayWithMaxNumElemsOnEmptyStack() {
            // MaxNumberOfElements changes
            Assert.AreEqual(5, (int) stack.MaxNumberOfElements);
            stack.MaxNumberOfElements = 10;
            Assert.AreEqual(10, (int) stack.MaxNumberOfElements);
            stack.MaxNumberOfElements = 100;
            Assert.AreEqual(100, (int) stack.MaxNumberOfElements);
            stack.MaxNumberOfElements = 0;
            Assert.AreEqual(0, (int) stack.MaxNumberOfElements);

        }

        [TestMethod()]
        public void PushElementsAndSizeGrows() {
            stack.Push(0);
            Assert.AreEqual(1, stack.NumElements);
            stack.Push(1);
            Assert.AreEqual(2, stack.NumElements);
            stack.Push(2);
            Assert.AreEqual(3, stack.NumElements);
            stack.Push(3);
            Assert.AreEqual(4, stack.NumElements);
            stack.Push(4);
            Assert.AreEqual(5, stack.NumElements);
        }

        [TestMethod()]
        public void PushElementsAndStackContainsThem() {
            PushElementsAndSizeGrows();
            Assert.AreEqual(4, stack.GetElementByIndex(0));
            Assert.AreEqual(3, stack.GetElementByIndex(1));
            Assert.AreEqual(2, stack.GetElementByIndex(2));
            Assert.AreEqual(1, stack.GetElementByIndex(3));
            Assert.AreEqual(0, stack.GetElementByIndex(4));
        }

        [TestMethod()]
        public void PlayWithMaxNumElemsOnFullStack() {
            PushElementsAndSizeGrows();
            stack.MaxNumberOfElements = 0;
            // MaxNumberOfElements does not change because stack contains
            // more elements than specified
            Assert.AreEqual(5, (int)stack.MaxNumberOfElements);
            Assert.AreEqual(5, stack.NumElements);
            stack.MaxNumberOfElements = 3;
            // MaxNumberOfElements does not change because stack contains
            // more elements than specified
            Assert.AreEqual(5, (int) stack.MaxNumberOfElements);
            Assert.AreEqual(5, stack.NumElements);
            stack.MaxNumberOfElements = 10;
            // MaxNumberOfElements changes because stack contains
            // less elements than specified
            Assert.AreEqual(10, (int) stack.MaxNumberOfElements);
            Assert.AreEqual(5, stack.NumElements);
            // MaxNumberOfElements changes because stack contains
            // exactly the same number of elements as specified
            stack.MaxNumberOfElements = 5;
            Assert.AreEqual(5, (int) stack.MaxNumberOfElements);
            Assert.AreEqual(5, stack.NumElements);



        }

        [TestMethod()]
        public void ButCannotAddOnFullStack() {
            PushElementsAndSizeGrows();
            stack.Push(5);
            Assert.AreEqual(5, stack.NumElements);
            stack.Push(6);
            Assert.AreEqual(5, stack.NumElements);
            stack.Push(7);
            Assert.AreEqual(5, stack.NumElements);
        }


        [TestMethod()]
        public void PopElementsAndSizeDecreases() {
            PushElementsAndSizeGrows();
            Assert.AreEqual(4, stack.Pop());
            Assert.AreEqual(4, stack.NumElements);
            Assert.AreEqual(3, stack.Pop());
            Assert.AreEqual(3, stack.NumElements);
            Assert.AreEqual(2, stack.Pop());
            Assert.AreEqual(2, stack.NumElements);
            Assert.AreEqual(1, stack.Pop());
            Assert.AreEqual(1, stack.NumElements);
            Assert.AreEqual(0, stack.Pop());
            Assert.AreEqual(0, stack.NumElements);
        }

        [TestMethod()]
        public void ButCannotPopOnEmptyStack() {
            PopElementsAndSizeDecreases();
            stack.Pop();
            Assert.AreEqual(0, stack.NumElements);
            Assert.IsTrue(stack.IsEmpty);
        }
    }
}