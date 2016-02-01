
using System;

namespace TPP.Laboratory.ObjectOrientation.Lab02
{

    /// <summary>
    /// Default argument values
    /// </summary>
    class DefaultArgumentValues
    {


        /// <summary>
        /// This method filters all the people with:
        /// - A specific first name
        /// - A specific surname name
        /// - A part of an ID number
        /// Comparison should not be case-sensitive
        /// Returns the people fulfilling the criteria
        /// </summary>
        static Person[] Filter(Person[] people, string firstName = null, string surname = null, string idNumberContains = null)
        {
            // * Array.Resize changes the array size 
            Person[] ret = new Person[people.Length];
            int size = 0;

            if (firstName != null || surname != null || idNumberContains != null)
            {
                foreach (Person person in people)
                {
                    // there is a first name
                    if (firstName != null)
                    {
                        bool bName = false;
                        bool bSurname = false;
                        if (firstName == null || person.FirstName.Equals(firstName)){
                            bName = true;
                        }

                        
                        
                        
                        
                        /*string firstLower = person.FirstName.ToLower();
                        // there is also a surname
                        if (surname != null)
                        {
                            string surnameLower = person.Surname.ToLower();
                            if (firstLower.Equals(firstName.ToLower()) &&
                                surnameLower.Equals(surname.ToLower()))
                            {
                                ret[size] = person;
                                size++;
                            }
                        }
                        // there is only a first name and it is equal to filter
                        else if (firstLower.Equals(firstName.ToLower()))
                        {
                            ret[size] = person;
                            size++;
                        }
                    }
                    // there is only a surname
                    else if (surname != null)
                    {
                        string surnameLower = person.Surname.ToLower();
                        if (surnameLower.Equals(surname.ToLower()))
                        {
                            ret[size] = person;
                            size++;
                        }
                    }*/
                }
            }

            Array.Resize(ref ret, size);
            return ret;
        }


        /// <summary>
        /// Creates a random list of people 
        /// </summary>
        /// <returns></returns>
        static Person[] CreatePeople()
        {
            string[] firstNames = { "María", "Juan", "Pepe", "Luis", "Carlos", "Miguel", "Cristina" };
            string[] surnames = { "Díaz", "Pérez", "Hevia", "García", "Rodríguez", "Pérez", "Sánchez" };
            int numberOfPeople = 100;

            Person[] printout = new Person[numberOfPeople];
            Random random = new Random();
            for (int i = 0; i < numberOfPeople; i++)
                printout[i] = new Person(
                    firstNames[random.Next(0, firstNames.Length)],
                    surnames[random.Next(0, surnames.Length)],
                    random.Next(9000000, 90000000) + "-" + (char)random.Next('A', 'Z')
                    );
            return printout;
        }

        /// <summary>
        /// Shows a list of people in the console
        /// </summary>
        static void Show(Person[] people)
        {
            foreach (Person person in people)
            {
                Console.WriteLine(person);
            }
            Console.WriteLine();
        }

        /// <summary>
        /// Complete the following code
        /// </summary>
        static void Main()
        {
            Person[] people = CreatePeople();
            Console.WriteLine("People named María:");
            Console.WriteLine();
            Person[] filtered = Filter(people, "MARÍA");
            Show(filtered);
            Console.WriteLine();

            Console.WriteLine("People whose surname is Pérez:");
            Console.WriteLine();
            filtered = Filter(people, surname: "PÉREZ");
            Show(filtered);

            filtered = Filter(people, firstName: "María", surname: "PÉREZ");
            Show(filtered);
            //Console.WriteLine("People whose ID number contains A");
            // * Complete the code

        }
    }

}
