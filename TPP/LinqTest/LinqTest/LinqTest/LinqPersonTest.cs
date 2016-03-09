using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Linq;
using Delegates;

namespace LinqTest {
    [TestClass]
    public class LinqPersonTest {
        Person[] people;

        [TestInitialize]
        public void Init() {
            people = Factory.CreatePeople();
        }

        //--------------FIND-------------

        [TestMethod]
        public void TestFindByEndingLetterID() {
            Assert.AreEqual(people[0], people.First(person => person.IDNumber.EndsWith("A")));
            Assert.AreEqual(people[1], people.First(person => person.IDNumber.EndsWith("F")));
            Assert.AreEqual(people[2], people.First(person => person.IDNumber.EndsWith("R")));
            Assert.AreEqual(people[4], people.First(person => person.IDNumber.EndsWith("B")));
        }

        // ----------------FILTER----------------------

        [TestMethod]
        public void TestFilterByEndingLetterName() {
            Person[] endsA = new Person[] { people[0], people[6], people[7] };
            Person[] filteredA = people.Where<Person>(person => person.FirstName.EndsWith("a")).ToArray();
            for (int i = 0; i < filteredA.Length; i++) {
                Assert.AreEqual(endsA[i], filteredA[i]);
            }

            Person[] endsN = new Person[] { people[1], people[8], };
            Person[] filteredN = people.Filter<Person>(person => person.FirstName.EndsWith("n")).ToArray();
            for (int i = 0; i < filteredN.Length; i++) {
                Assert.AreEqual(endsN[i], filteredN[i]);
            }

            Person[] endsE = new Person[] { people[2] };
            Person[] filteredE = people.Filter<Person>(person => person.FirstName.EndsWith("pe")).ToArray();
            for (int i = 0; i < filteredE.Length; i++) {
                Assert.AreEqual(endsE[i], filteredE[i]);
            }

            Person[] endsS = new Person[] { people[3], people[4] };
            Person[] filteredS = people.Filter<Person>(person => person.FirstName.EndsWith("uis")).ToArray();
            for (int i = 0; i < filteredS.Length; i++) {
                Assert.AreEqual(endsS[i], filteredS[i]);
            }

        }

        // --------------REDUCE--------------

        [TestMethod]
        public void TestReduceCountByName() {
            double result = people.Aggregate(0, (res, person) => person.FirstName.Equals("María") ? res += 1 : res);
            Assert.AreEqual(2, (int) result);

            result = people.Aggregate(0, (res, person) => person.FirstName.Equals("Juan") ? res += 1 : res);
            Assert.AreEqual(2, (int) result);

            result = people.Aggregate(0, (res, person) => person.FirstName.Equals("Carlos") ? res += 1 : res);
            Assert.AreEqual(1, (int) result);

            result = people.Aggregate(0, (res, person) => person.FirstName.Equals("Blablabla") ? res += 1 : res);
            Assert.AreEqual(0, (int) result);
        }

        // ----------------MAP---------------

        [TestMethod]
        public void TestMapPersonsNameAndSurname() {
            String[] toCompare = new String[people.Length];
            for (int i = 0; i< people.Length; i++) {
                toCompare[i] = people[i].FirstName + 
                     ", " + people[i].Surname;
            }
            String[] result = people.Select(person => person.FirstName + ", " + person.Surname).ToArray();

            for (int i = 0; i < result.Length; i++) {
                Assert.AreEqual(toCompare[i],(result[i]));
            }

        }


    }
}
