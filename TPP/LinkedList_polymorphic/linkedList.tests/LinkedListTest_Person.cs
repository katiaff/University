using Microsoft.VisualStudio.TestTools.UnitTesting;
using LinkedList;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LinkedList {
    [TestClass()]
    public class LinkedListTest_Person {
        MyLinkedList<Person> l;
        Person[] people;

        [TestInitialize()]
        public void CreateList() {
            people = GeneratePersons();
            l = new MyLinkedList<Person>(people[0]);
        }

        [TestMethod()]
        public void ThenItShouldHaveOneElement_Person() {
            Assert.AreEqual(1, l.NumberOfElements);
        }

        [TestMethod()]
        public void BeforeAddingElementsDontExist_Person() {
            for (int i = 1; i < people.Length; i++) {
                Assert.AreEqual(null, l.GetElement(people[i]));
                Assert.AreEqual(null, l.GetElementByIndex(i));
            }

        }

        [TestMethod()]
        public void ThenAddAndSizeGrows_Person() {
            int num = l.NumberOfElements;
            for (int i = 1; i < people.Length; i++) {
                l.Add(people[i]);
                Assert.AreEqual(num + 1, l.NumberOfElements);
                num = l.NumberOfElements;
                Assert.AreEqual(people[i], l.GetElement(people[i]));
            }
        }

        [TestMethod()]
        public void AfterAddingElementsExist_Person() {
            ThenAddAndSizeGrows_Person();
            for (int i = 1; i < people.Length; i++) {
                Assert.AreEqual(people[i], l.GetElement(people[i]));
                Assert.AreEqual(people[i], l.GetElementByIndex(i));
            }
        }

        [TestMethod()]
        public void AfterAddingListContainsTheElements_Person() {
            ThenAddAndSizeGrows_Person();
            for (int i = 1; i < people.Length; i++) {
                Assert.IsTrue(l.Contains(people[i]));
            }

        }

        [TestMethod()]
        public void ThenRemoveAndSizeDecreases_Person() {
            ThenAddAndSizeGrows_Person();
            int num = l.NumberOfElements;
            for (int i = 0; i < people.Length; i++) {
                l.Remove(people[i]);
                Assert.AreEqual(num - 1, l.NumberOfElements);
                num = l.NumberOfElements;
            }

            Assert.AreEqual("", l.ToString());
        }

        [TestMethod()]
        public void AfterRemovingElementsDontExist_Person() {
            ThenRemoveAndSizeDecreases_Person();
            Assert.AreEqual(null, l.GetElement(people[0]));
            Assert.AreEqual(null, l.GetElement(people[1]));
            Assert.AreEqual(null, l.GetElement(people[2]));
            Assert.AreEqual(null, l.GetElement(people[3]));
            Assert.AreEqual(null, l.GetElement(people[4]));

        }

        [TestMethod()]
        public void ButCantRemoveUnexistingItems_Person() {
            Assert.AreEqual(null, l.Remove(new Person("blabla", "blabla", "123")));
            Assert.AreEqual(null, l.Remove(new Person("blabla2", "blabla", "123")));
            Assert.AreEqual(null, l.Remove(new Person("blabla3", "blabla", "123")));
            Assert.AreEqual(null, l.Remove(new Person("blabla4", "blabla", "123")));
            Assert.AreEqual(1, l.NumberOfElements);
        }

        [TestMethod()]
        public void GetElementTest_Person() {
            for (int i = 1; i < people.Length; i++) {
                l.Add(people[i]);
                Assert.AreEqual(people[i], l.GetElementByIndex(i));
                Assert.AreEqual(null, l.GetElementByIndex(i + 30));
            }
        }

        Person[] GeneratePersons() {
            Person[] p = new Person[5];
            p[0] = new Person("Carla", "Fernandez", idNumber: null);
            p[1] = new Person("Carlos", "Fernandez", "123456789");
            p[2] = new Person("Marisa", "Gonzalez", "123456789A");
            p[3] = new Person("Diego", "Freijo", "234567890");
            p[4] = new Person("Julio", "Gonzalez", "111111111");

            return p;
        }


    }
}