using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Delegates {
    [TestClass]
    public class PersonTest {
        Person[] people;

        [TestInitialize]
        public void Init() {
            people = Factory.CreatePeople();
        }

        // ------------------------Find tests------------------------

        [TestMethod]
        public void TestFindByName() {
            Assert.AreEqual(people[0], people.Find<Person>(person => person.FirstName.Equals("María")));
            Assert.AreEqual(people[1], people.Find<Person>(person => person.FirstName.Equals("Juan")));
            Assert.AreEqual(people[2], people.Find<Person>(person => person.FirstName.Equals("Pepe")));
            Assert.AreEqual(people[3], people.Find<Person>(person => person.FirstName.Equals("Luis")));
            Assert.AreEqual(people[4], people.Find<Person>(person => person.FirstName.Equals("Carlos")));
        }

        [TestMethod]
        public void TestFindByEndingLetterName() {
            Assert.AreEqual(people[0], people.Find<Person>(person => person.FirstName.EndsWith("a")));
            Assert.AreEqual(people[1], people.Find<Person>(person => person.FirstName.EndsWith("n")));
            Assert.AreEqual(people[2], people.Find<Person>(person => person.FirstName.EndsWith("e")));
            Assert.AreEqual(people[3], people.Find<Person>(person => person.FirstName.EndsWith("s")));
        }


        [TestMethod]
        [ExpectedException(typeof(Exception))]
        public void TestFindNameNotFound() {
            people.Find<Person>(person => person.FirstName.Equals("1111"));
            people.Find<Person>(person => person.FirstName.Equals("2222"));
            people.Find<Person>(person => person.FirstName.Equals("3333"));
            people.Find<Person>(person => person.FirstName.Equals("4444"));
            people.Find<Person>(person => person.FirstName.Equals("5555"));
        }

        [TestMethod]
        public void TestFindBySurname() {
            Assert.AreEqual(people[0], people.Find<Person>(person => person.Surname.Equals("Díaz")));
            Assert.AreEqual(people[1], people.Find<Person>(person => person.Surname.Equals("Pérez")));
            Assert.AreEqual(people[2], people.Find<Person>(person => person.Surname.Equals("Hevia")));
            Assert.AreEqual(people[3], people.Find<Person>(person => person.Surname.Equals("García")));
            Assert.AreEqual(people[4], people.Find<Person>(person => person.Surname.Equals("Rodríguez")));
        }

        [TestMethod]
        [ExpectedException(typeof(Exception))]
        public void TestFindSurnameNotFound() {
            people.Find<Person>(person => person.Surname.Equals("1111"));
            people.Find<Person>(person => person.Surname.Equals("2222"));
            people.Find<Person>(person => person.Surname.Equals("3333"));
            people.Find<Person>(person => person.Surname.Equals("4444"));
            people.Find<Person>(person => person.Surname.Equals("5555"));
        }

        [TestMethod]
        public void TestFindByID() {
            Assert.AreEqual(people[0], people.Find<Person>(person => person.IDNumber.Equals("9876384A")));
            Assert.AreEqual(people[1], people.Find<Person>(person => person.IDNumber.Equals("103478387F")));
            Assert.AreEqual(people[2], people.Find<Person>(person => person.IDNumber.Equals("23476293R")));
            Assert.AreEqual(people[3], people.Find<Person>(person => person.IDNumber.Equals("4837649A")));
            Assert.AreEqual(people[4], people.Find<Person>(person => person.IDNumber.Equals("67365498B")));

        }

        [TestMethod]
        public void TestFindByEndingLetterID() {
            Assert.AreEqual(people[0], people.Find<Person>(person => person.IDNumber.EndsWith("A")));
            Assert.AreEqual(people[1], people.Find<Person>(person => person.IDNumber.EndsWith("F")));
            Assert.AreEqual(people[2], people.Find<Person>(person => person.IDNumber.EndsWith("R")));
            Assert.AreEqual(people[4], people.Find<Person>(person => person.IDNumber.EndsWith("B")));

        }

        [TestMethod]
        [ExpectedException(typeof(Exception))]
        public void TestFindIDNotFound() {
            people.Find<Person>(person => person.IDNumber.Equals("1111"));
            people.Find<Person>(person => person.IDNumber.Equals("2222"));
            people.Find<Person>(person => person.IDNumber.Equals("3333"));
            people.Find<Person>(person => person.IDNumber.Equals("4444"));
            people.Find<Person>(person => person.IDNumber.Equals("5555"));
        }

        // ------------------------Filter tests------------------------

        [TestMethod]
        public void TestFilterByEndingLetterName() {
            Person[] endsA = new Person[] { people[0], people[6], people[7] };
            Person[] filteredA = people.Filter<Person>(person => person.FirstName.EndsWith("a"));
            for (int i = 0; i < filteredA.Length; i++) {
                Assert.AreEqual(endsA[i], filteredA[i]);
            }

            Person[] endsN = new Person[] { people[1], people[8], };
            Person[] filteredN = people.Filter<Person>(person => person.FirstName.EndsWith("n"));
            for (int i = 0; i < filteredN.Length; i++) {
                Assert.AreEqual(endsN[i], filteredN[i]);
            }

            Person[] endsE = new Person[] { people[2] };
            Person[] filteredE = people.Filter<Person>(person => person.FirstName.EndsWith("pe"));
            for (int i = 0; i < filteredE.Length; i++) {
                Assert.AreEqual(endsE[i], filteredE[i]);
            }

            Person[] endsS = new Person[] { people[3], people[4] };
            Person[] filteredS = people.Filter<Person>(person => person.FirstName.EndsWith("uis"));
            for (int i = 0; i < filteredS.Length; i++) {
                Assert.AreEqual(endsS[i], filteredS[i]);
            }

        }

        [TestMethod]
        [ExpectedException(typeof(Exception))]
        public void TestFilterByEndingLetterNameNotFound() {
            Person[] filtered = people.Filter<Person>(person => person.FirstName.EndsWith("z"));
            filtered = people.Filter<Person>(person => person.FirstName.EndsWith("j"));
            filtered = people.Filter<Person>(person => person.FirstName.EndsWith("p"));
            filtered = people.Filter<Person>(person => person.FirstName.EndsWith("w"));

        }

        [TestMethod]
        public void TestFilterByEndingLetterID() {

            Person[] endsA = new Person[] { people[0], people[3] };
            Person[] filteredA = people.Filter<Person>(person => person.IDNumber.EndsWith("A"));
            for (int i = 0; i < filteredA.Length; i++) {
                Assert.AreEqual(endsA[i], filteredA[i]);
            }

            Person[] endsF = new Person[] { people[1], people[6], };
            Person[] filteredF = people.Filter<Person>(person => person.IDNumber.EndsWith("F"));
            for (int i = 0; i < filteredF.Length; i++) {
                Assert.AreEqual(endsF[i], filteredF[i]);
            }

            Person[] endsR = new Person[] { people[2] };
            Person[] filteredR = people.Filter<Person>(person => person.IDNumber.EndsWith("R"));
            for (int i = 0; i < filteredR.Length; i++) {
                Assert.AreEqual(endsR[i], filteredR[i]);
            }

            Person[] endsB = new Person[] { people[4] };
            Person[] filteredB = people.Filter<Person>(person => person.IDNumber.EndsWith("B"));
            for (int i = 0; i < filteredB.Length; i++) {
                Assert.AreEqual(endsB[i], filteredB[i]);
            }

        } 

        [TestMethod]
        [ExpectedException(typeof(Exception))]
        public void TestFilterByEndingLetterIDNotFound() {
            Person[] filtered = people.Filter<Person>(person => person.IDNumber.EndsWith("z"));
            filtered = people.Filter<Person>(person => person.IDNumber.EndsWith("j"));
            filtered = people.Filter<Person>(person => person.IDNumber.EndsWith("p"));
            filtered = people.Filter<Person>(person => person.IDNumber.EndsWith("w"));

        }

        // ------------------------Reduce tests------------------------

       [TestMethod]
       public void TestReduceCountByName() {
            Func<Person, string, double, double> countByName = ((person, name, res) => person.FirstName.Equals(name) ? res += 1 : res);

            double result = people.Reduce<Person, string, double>(countByName, "María");
            Assert.AreEqual(2, (int) result);

            result = people.Reduce<Person, string, double>(countByName, "Juan");
            Assert.AreEqual(2, (int) result);

            result = people.Reduce<Person, string, double>(countByName, "Carlos");
            Assert.AreEqual(1, (int) result);

            result = people.Reduce<Person, string, double>(countByName, "SomeStrangeName");
            Assert.AreEqual(0, (int) result);
        }
    }
}
