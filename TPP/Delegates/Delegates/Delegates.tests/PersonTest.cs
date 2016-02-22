using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Delegates
{
    [TestClass]
    public class PersonTest
    {
        Person[] people;

        [TestInitialize]
        public void Init()
        {
            people = Factory.CreatePeople();
        }

        // ------------------------Find tests------------------------

        [TestMethod]
        public void TestFindByName()
        {
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
        public void TestFindNameNotFound() 
        {
            people.Find<Person>(person => person.FirstName.Equals("1111"));
            people.Find<Person>(person => person.FirstName.Equals("2222"));
            people.Find<Person>(person => person.FirstName.Equals("3333"));
            people.Find<Person>(person => person.FirstName.Equals("4444"));
            people.Find<Person>(person => person.FirstName.Equals("5555"));
        }

        [TestMethod]
        public void TestFindBySurname()
        {
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
        public void TestFindByID()
        {
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
    }
}
