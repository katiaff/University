
using System;

namespace TPP.Laboratory.ObjectOrientation.Lab03
{

    class Algorithms
    {

        // Exercise: Implement an IndexOf method that finds the first position (index) 
        // of an object in an array of objects. It should be valid for Person, Angle and future classes.


        static Person[] CreatePeople()
        {
            string[] firstnames = { "María", "Juan", "Pepe", "Luis", "Carlos", "Miguel", "Cristina" };
            string[] surnames = { "Díaz", "Pérez", "Hevia", "García", "Rodríguez", "Pérez", "Sánchez" };
            int numberOfPeople = 100;

            Person[] printOut = new Person[numberOfPeople];
            Random random = new Random();
            printOut[0] = new Person("Carla", "Fernandez", "1234");
            for (int i = 1; i < numberOfPeople; i++)
                printOut[i] = new Person(
                    firstnames[random.Next(0, firstnames.Length)],
                    surnames[random.Next(0, surnames.Length)],
                    random.Next(9000000, 90000000) + "-" + (char)random.Next('A', 'Z')
                    );
            return printOut;
        }

        static Angle[] CreateAngles()
        {
            Angle[] angles = new Angle[(int)(Math.PI * 2 * 10)];
            int i = 0;
            while (i < angles.Length)
            {
                angles[i] = new Angle(i / 10.0);
                i++;
            }
            return angles;
        }

        // to implement as extension
        static int? IndexOf(object[] array, object o)
        {
            for (int i = 0; i < array.Length; i++)
            {
                if (array[i].Equals(o))
                {
                    return i;
                }
            }
            return null;
        }

        static int? IndexOf_v2(object[] array, object o, IEqualityPredicate pred)
        {
            for (int i = 0; i < array.Length; i++)
            {
                if (pred.Compare(array[i], o))
                {
                    return i;
                }
            }
            return null;
        }

        static void Main()
        {
            Person[] people = CreatePeople();
            Person person = new Person("Carla", "Fernandez", "1234");
            
            var res = IndexOf(people, person);
            if (res == null)
                Console.WriteLine("First version: Carla not found");
            else
                Console.WriteLine("First version: Carla's index: {0}", res);


            PersonWithSameName comparator = new PersonWithSameName();
            var res2 = IndexOf_v2(people, person, comparator);
            if (res2 == null)
                Console.WriteLine("Second version: Carla not found");
            else
                Console.WriteLine("Second version: Carla's index: {0}", res2);

        }

    }

}
