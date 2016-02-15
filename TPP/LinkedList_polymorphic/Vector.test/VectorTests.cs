using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Collections.Generic;

namespace Vector.test
{
    [TestClass]
    public class VectorTests
    {
        IList<int> list;

        [TestMethod]
        public void AddElementsAndSizeGrows()
        {
            for (int i = 0; i < 10; i++)
            {
                list.Add(i);
                Assert.AreEqual(i + 1, list.Count);
            }

        }

        [TestMethod]
        public void GetNumberOfElements()
        {
            list.Add(0);
            Assert.AreEqual(0, list.Count);
            list.Add(1);
            Assert.AreEqual(1, list.Count);
            list.Add(2);
            Assert.AreEqual(2, list.Count);
            list.Add(3);
            Assert.AreEqual(3, list.Count);
        }

        [TestMethod]
        public void GetElements()
        {
            GetNumberOfElements();
            //Assert.AreEqual(0, 0);


        }

        [TestMethod]
        public void SetElements()
        {
            GetNumberOfElements();
            


        }


    }
}
