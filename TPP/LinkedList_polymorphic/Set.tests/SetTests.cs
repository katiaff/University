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
    public class SetTests
    {
        Set s;

        [TestInitialize]
        public void createSet()
        {
            s = new Set(0);
        }

        [TestMethod()]
        public void AddRepeatedNumbers()
        {
            s.Add(0);
            Assert.AreEqual(1, s.NumberOfElements);
            s.Add(1);
            Assert.AreEqual(2, s.NumberOfElements);
            s.Add(1);
            Assert.AreEqual(2, s.NumberOfElements);
            s.Add(2);
            Assert.AreEqual(3, s.NumberOfElements);
            s.Add(2);
            Assert.AreEqual(3, s.NumberOfElements);

            Assert.AreEqual("0 1 2", s.ToString());
        }

        [TestMethod()]
        public void PlusOperatorTest()
        {
            s = s + 1;
            Assert.AreEqual(2, s.NumberOfElements);
            s = s + 1;
            Assert.AreEqual(2, s.NumberOfElements);
            s = s + 2;
            Assert.AreEqual(3, s.NumberOfElements);
            s = s + 2;
            Assert.AreEqual(3, s.NumberOfElements);
            s = s + 3;
            Assert.AreEqual(4, s.NumberOfElements);

            Assert.AreEqual("0 1 2 3", s.ToString());
        }

        [TestMethod()]
        public void MinusOperatorTest()
        {
            PlusOperatorTest();
            s = s-0;
            Assert.AreEqual(3, s.NumberOfElements);
            s = s-0;
            Assert.AreEqual(3, s.NumberOfElements);
            s = s - 1;
            Assert.AreEqual(2, s.NumberOfElements);
            s = s - 1;
            Assert.AreEqual(2, s.NumberOfElements);
            s = s - 2;
            Assert.AreEqual(1, s.NumberOfElements);
            s = s - 2;
            Assert.AreEqual(1, s.NumberOfElements);
            s = s - 3;
            Assert.AreEqual(0, s.NumberOfElements);
            s = s - 3;
            Assert.AreEqual(0, s.NumberOfElements);

            Assert.AreEqual("", s.ToString());

        }

        [TestMethod()]
        public void BracketOperatorTest()
        {
            PlusOperatorTest();
            Assert.AreEqual(0, s[0]);
            Assert.AreEqual(1, s[1]);
            Assert.AreEqual(2, s[2]);
            Assert.AreEqual(3, s[3]);
        }

        [TestMethod()]
        public void UnionOperatorTest()
        {
            s.Add(1);
            s.Add(2);
            s.Add(3);

            Set s2 = new Set(4);
            s2.Add(5);
            s2.Add(6);

            s = s | s2;
            Assert.AreEqual("0 1 2 3 4 5 6", s.ToString());

            s = new Set(0);
            s.Add(1);
            s.Add(2);
            s.Add(3);

            s2 = new Set(3);
            s2.Add(2);
            s2.Add(1);

            s = s | s2;
            Assert.AreEqual("0 1 2 3", s.ToString());

        }

        [TestMethod()]
        public void IntersectionOperatorTest()
        {
            s.Add(1);
            s.Add(2);
            s.Add(3);

            Set s2 = new Set(4);
            s2.Add(5);
            s2.Add(6);

            s = s & s2;
            Assert.AreEqual("", s.ToString());

            s = new Set(0);
            s.Add(1);
            s.Add(2);
            s.Add(3);

            s2 = new Set(3);
            s2.Add(2);
            s2.Add(1);
            s2.Add(4);

            s = s & s2;
            Assert.AreEqual("1 2 3", s.ToString());

        }


    }
}