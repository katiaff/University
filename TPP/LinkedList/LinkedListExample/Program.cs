using System;
using LinkedList;

namespace LinkedListExample
{
    class Program
    {
        static void Main(string[] args)
        {
            LinkedList list = new LinkedList(1);

            // Adding numbers and checking if they are on the list
            Console.WriteLine("Added 1, list: " + list.ToString());
            Console.WriteLine("Exists 1? " + list.GetElement(1));
            Console.WriteLine("Exists 2? " + list.GetElement(2) + "\n");

            list.Add(2);
            Console.WriteLine("Added 2, list: " + list.ToString());
            Console.WriteLine("Exists 2? " + list.GetElement(2));
            Console.WriteLine("Exists 3? " + list.GetElement(3) + "\n");

            list.Add(3);
            Console.WriteLine("Added 3, list: " + list.ToString());
            Console.WriteLine("Exists 3? " + list.GetElement(3));
            Console.WriteLine("Exists7? " + list.GetElement(7) + "\n");

            // Removing numbers and checking if they are on the list
            list.Remove(1);
            Console.WriteLine("Removed 1, list: " + list.ToString());
            Console.WriteLine("Exists 1? " + list.GetElement(1) + "\n");

            list.Remove(2);
            Console.WriteLine("Removed 2, list: " + list.ToString());
            Console.WriteLine("Exists 2? " + list.GetElement(2) + "\n");

            list.Remove(3);
            Console.WriteLine("Removed 3, list: " + list.ToString());
            Console.WriteLine("Exists 3? " + list.GetElement(3) + "\n");

            Console.WriteLine("Creating new list... \n");

            list = new LinkedList(1);

            // Adding 5 "1"
            int num = 5;
            for ( int i = 0; i< num; i++)
            {
                list.Add(1);
            }
            Console.WriteLine("Added {0} ones, list: " + list.ToString() + "\n", num);

            // Removing each "1"
            for (int i = 0; i< num + 1; i++)
            {
                list.Remove(1);
                Console.WriteLine("Removed one 1, list: " + list.ToString() + "\n");
            }

            // Adding 0-1-2-3-4-0-1-2-3-4
            for (int i = 0; i < num; i++)
            {
                list.Add(i);
                Console.WriteLine("Added {0}, list: " + list.ToString() + "\n", i);
            }
            for (int i = 0; i < num; i++)
            {
                list.Add(i);
                Console.WriteLine("Added {0}, list: " + list.ToString() + "\n", i);
            }

            // Removing only one number (repeated)
            for (int i = 0; i < num + 1; i++)
            {
                list.Remove(i);
                Console.WriteLine("Removed {0}, list: " + list.ToString() + "\n", i);
            }





        }
    }
}
