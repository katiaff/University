using Microsoft.VisualStudio.TestTools.UnitTesting;
using LinkedList;
using System;
using System.Linq;
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
            Assert.AreEqual(0, l.Find(isEven));

            Predicate<int> isOdd = (x => x % 2 != 0);
            Assert.AreEqual(1, l.Find(isOdd));

            Predicate<int> isDivisibleBy15And20 = (x => x % 15 == 0 && x % 20 == 0 && x != 0);
            Assert.AreEqual(60, l.Find(isDivisibleBy15And20));

        }

        [TestMethod()]
        public void FilterTest() {
            Predicate<int> isEven = (x => x % 2 == 0 || x == 0);
            Predicate<int> isOdd = (x => x % 2 != 0);

            int[] compareEven = new int[l.NumberOfElements];
            int[] compareOdd = new int[l.NumberOfElements];
            int j = 0;
            for (int i = 0; i < l.NumberOfElements; i++) {
                if (i % 2 == 0 || i == 0) {
                    compareEven[j] = i;
                } else if (i % 2 != 0) {
                    compareOdd[j] = i;
                }
                j++;
            }

            int[] resultEven = l.Filter(isEven).ToArray();
            int[] resultOdd = l.Filter(isOdd).ToArray();

        }

        [TestMethod()]
        public void ReduceNoSeedTest() {
            Func<int, int, int> sumAll = ((res, x) => res += x);
            Assert.AreEqual(499500, l.Reduce(sumAll));

            Func<int, int, int> sumEven = ((res, x) => x % 2 == 0 ? res += x : res);
            Assert.AreEqual(249500, l.Reduce(sumEven));

            Func<int, int, int> sumOdd = ((res, x) => x % 2 != 0 ? res += x : res);
            Assert.AreEqual(250000, l.Reduce(sumOdd));

            Func<int, int, int> substractAll = ((res, x) => res -= x);
            Assert.AreEqual(-499500, l.Reduce(substractAll));

            Func<int, int, int> substractLast3 = ((res, x) => x > 996 ? res -= x : res);
            Assert.AreEqual(-2994, l.Reduce(substractLast3));
        }

        [TestMethod()]
        public void ReduceWithSeedTest() {
            Func<int, int, int> sumAll = ((res, x) => res += x);
            Assert.AreEqual(499500 + 10000000, l.Reduce(sumAll, 10000000));

            Func<int, int, int> sumEven = ((res, x) => x % 2 == 0 ? res += x : res);
            Assert.AreEqual(249500 + 10000000, l.Reduce(sumEven), 10000000);

            Func<int, int, int> sumOdd = ((res, x) => x % 2 != 0 ? res += x : res);
            Assert.AreEqual(250000 + 10000000, l.Reduce(sumOdd), 10000000);
        }

        [TestMethod]
        public void MapTest() {
            var toCompare = new MyLinkedList<int>();
            for (int i = 0; i < l.NumberOfElements; i++) {
                toCompare.Add(l[i] + 10000);
            }

            MyLinkedList<int> result = new MyLinkedList<int>(l.Map(x => x + 10000));

            for (int i = 0; i < result.NumberOfElements; i++) {
                Assert.AreEqual(toCompare[i], (result[i]));
            }

        }

        [TestMethod]
        public void ForEachTest() {
            l.ForEach(x => {
                if (x % 30 == 0) Console.WriteLine(x + " DRAGONS over the fence");
                else Console.WriteLine(x + " sheep over the fence");
            });

        }

        [TestMethod]
        public void InvertToBasicTest() {
            MyLinkedList<int> res = new MyLinkedList<int>(l.Invert());

            for (int i = 0; i < l.NumberOfElements; i++) {
                int j = l.NumberOfElements - 1 - i;
                Assert.AreEqual(l[i], res[j]);
            }
        }

        [TestMethod]
        public void InvertWithReduceTest() {

            string[] leftToRight = l.Reduce<int, string>((res, x) => res += "hello " + x + "\n").Trim().Split('\n');
            leftToRight.Show();

            string[] rightToLeft = l.Invert().Reduce<int, string>((res, x) => res += "hello " + x + "\n").Trim().Split('\n');
            rightToLeft.Show();

            for (int i = 0; i < leftToRight.Length; i++) {
                int j = leftToRight.Length - 1 - i;
                Assert.AreEqual(leftToRight[i], rightToLeft[j]);
            }

        }
    }
}