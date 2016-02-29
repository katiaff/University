using System;

namespace Delegates {

    public class Person {

        public String FirstName { get; private set; }

        public String Surname { get; private set; }

        public string IDNumber { get; private set; }

        public override String ToString() {
            return String.Format("{0} {1} with {2} ID number", FirstName, Surname, IDNumber);
        }

        public Person(String firstName, String surname, string idNumber) {
            this.FirstName = firstName;
            this.Surname = surname;
            this.IDNumber = idNumber;
        }

        public override bool Equals(object obj) {
            Person person = obj as Person;
            if (person != null) {
                return this.GetHashCode().Equals(person.GetHashCode());
            }
            return false;
        }

        public override int GetHashCode() {
            return IDNumber.ToLower().GetHashCode();
        }


    }
}
