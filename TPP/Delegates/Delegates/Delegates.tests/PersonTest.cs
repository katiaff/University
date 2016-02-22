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
        [ExpectedException(typeof(Exception))]
        public void TestNameNotFound() 
        {
            Assert.AreEqual(people[0], people.Find<Person>(person => person.FirstName.Equals("María")));
            Assert.AreEqual(people[1], people.Find<Person>(person => person.FirstName.Equals("Juan")));
            Assert.AreEqual(people[2], people.Find<Person>(person => person.FirstName.Equals("Pepe")));
            Assert.AreEqual(people[3], people.Find<Person>(person => person.FirstName.Equals("Luis")));
            Assert.AreEqual(people[4], people.Find<Person>(person => person.FirstName.Equals("Carlos")));
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
        public void TestFindByID()
        {
            Assert.AreEqual(people[0], people.Find<Person>(person => person.IDNumber.Equals("9876384A")));
            Assert.AreEqual(people[1], people.Find<Person>(person => person.IDNumber.Equals("103478387F")));
            Assert.AreEqual(people[2], people.Find<Person>(person => person.IDNumber.Equals("23476293R")));
            Assert.AreEqual(people[3], people.Find<Person>(person => person.IDNumber.Equals("4837649A")));
            Assert.AreEqual(people[4], people.Find<Person>(person => person.IDNumber.Equals("67365498B")));

        }
    }
}
