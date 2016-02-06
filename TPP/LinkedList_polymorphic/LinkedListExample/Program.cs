using System;
using LinkedList;

namespace LinkedListExample
{
    class Program
    {

        static void Main(string[] args)
        {
            MyLinkedList<int> list = new MyLinkedList<int>(1);

            // Adding numbers and checking if they are on the list
            Console.WriteLine("Adding numbers and checking if they are on the list \n");
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
            Console.WriteLine("------------------------------- \n");

            // Removing non-existent elements
            Console.WriteLine("Removing non-existent elements \n");
            Console.WriteLine("Removing element '4', result: " + list.Remove(4) + "\n");
            Console.WriteLine("Removing element '5', result: " + list.Remove(4) + "\n");
            Console.WriteLine("Removing element '6', result: " + list.Remove(4) + "\n");
            Console.WriteLine("------------------------------- \n");

            // Removing numbers and checking if they are on the list
            Console.WriteLine("Removing numbers and cheking if they are on the list \n");
            list.Remove(1);
            Console.WriteLine("Removed 1, list: " + list.ToString());
            Console.WriteLine("Exists 1? " + list.GetElement(1) + "\n");

            list.Remove(2);
            Console.WriteLine("Removed 2, list: " + list.ToString());
            Console.WriteLine("Exists 2? " + list.GetElement(2) + "\n");

            list.Remove(3);
            Console.WriteLine("Removed 3, list: " + list.ToString());
            Console.WriteLine("Exists 3? " + list.GetElement(3) + "\n");
            Console.WriteLine("------------------------------- \n");

            // Adding on empty list
            list.Add(1);
            Console.WriteLine("Added element 1 on empty list... " + list.ToString() + "\n");
            Console.WriteLine("------------------------------- \n");


            // Adding 5 "1"
            Console.WriteLine("Adding 5 '1' and removing each one \n");
            int num = 5;
            for (int i = 0; i < num; i++)
            {
                list.Add(1);
            }
            Console.WriteLine("Added {0} ones, list: " + list.ToString() + "\n", num);

            // Removing each "1"
            for (int i = 0; i < num + 1; i++)
            {
                list.Remove(1);
                Console.WriteLine("Removed one 1, list: " + list.ToString() + "\n");
            }
            Console.WriteLine("------------------------------- \n");

            // Adding 0-1-2-3-4-0-1-2-3-4
            Console.WriteLine("Adding different repeated numbers and deleting them \n");
            for (int i = 0; i < num; i++)
            {
                list.Add(i);

            }
            for (int i = 0; i < num; i++)
            {
                list.Add(i);

            }
            Console.WriteLine("Added 0 1 2 3 4 0 1 2 3 4, list: " + list.ToString() + "\n");

            // Removing only one number (repeated)
            for (int i = 0; i < num + 1; i++)
            {
                list.Remove(i);
                Console.WriteLine("Removed {0}, list: " + list.ToString() + "\n", i);
            }
            Console.WriteLine("------------------------------- \n");

            // Getting elements
            Console.WriteLine("Getting some more elements \n");
            for (int i = 0; i < num + 1; i++)
            {
                Console.WriteLine("Getting element {0}: " + list.GetElement(i) + "\n", i);
            }
            for (int i = num; i < num + num; i++)
            {
                Console.WriteLine("Getting element {0}: " + list.GetElement(i) + "\n", i);
            }
            Console.WriteLine("------------------------------- \n");

        }




    }
}
