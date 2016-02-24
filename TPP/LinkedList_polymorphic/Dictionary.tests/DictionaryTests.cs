using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Collections.Generic;

namespace Dictionary.tests {
    [TestClass]
    public class DictionaryTests {
        IDictionary<int, string> dict;

        [TestInitialize]
        public void Initialize() {
            dict = new Dictionary<int, string>();
        }

        [TestMethod]
        public void AddPairsAndSizeGrows() {
            Assert.AreEqual(0, dict.Count);
            dict.Add(0, "A");
            Assert.AreEqual(1, dict.Count);
            dict.Add(1, "B");
            Assert.AreEqual(2, dict.Count);
            dict.Add(2, "C");
            Assert.AreEqual(3, dict.Count);
            dict.Add(3, "D");
            Assert.AreEqual(4, dict.Count);
        }

        public void AddPairsAndKeysExist() {
            for (int i = 0; i < 4; i++) {
                Assert.IsTrue(dict.ContainsKey(i));
            }
        }

        public void GetSetKeys() {
            AddPairsAndSizeGrows();

            string val;
            dict.TryGetValue(0, out val);
            Assert.AreEqual("A", val);
            dict.TryGetValue(1, out val);
            Assert.AreEqual("B", val);
            dict.TryGetValue(2, out val);
            Assert.AreEqual("C", val);
            dict.TryGetValue(3, out val);
            Assert.AreEqual("D", val);

            dict[1] = "W";
            dict[2] = "X";
            dict[3] = "Y";
            dict[4] = "Z";

            dict.TryGetValue(0, out val);
            Assert.AreEqual("W", val);
            dict.TryGetValue(1, out val);
            Assert.AreEqual("X", val);
            dict.TryGetValue(2, out val);
            Assert.AreEqual("Y", val);
            dict.TryGetValue(3, out val);
            Assert.AreEqual("Z", val);


        }

        public void ExistsKey() {
            AddPairsAndKeysExist();

            Assert.IsFalse(dict.ContainsKey(4));
            Assert.IsFalse(dict.ContainsKey(5));
            Assert.IsFalse(dict.ContainsKey(6));
            Assert.IsFalse(dict.ContainsKey(7));
        }

        public void DeletePairsByKey() {
            AddPairsAndSizeGrows();

            dict.Remove(0);
            dict.Remove(1);
            dict.Remove(2);
            dict.Remove(3);

            Assert.AreEqual(0, dict.Count);
            Assert.IsFalse(dict.ContainsKey(0));
            Assert.IsFalse(dict.ContainsKey(1));
            Assert.IsFalse(dict.ContainsKey(2));
            Assert.IsFalse(dict.ContainsKey(3));

        }

        public void IteratePairsWithForEach() {
            foreach (int key in dict.Keys) {
                foreach(string value in dict.Values) {
                    Assert.AreEqual(value, dict[key]);
                }
            }
        }
    }
}
