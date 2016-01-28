using System;

namespace LinkedList
{
    class Program
    {
        static void Main(string[] args)
        {
            LinkedList list = new LinkedList(1);
            Console.WriteLine("Added 1: " + list.ToString());
            Console.WriteLine("Exists 1? " + list.GetElement(1));
            Console.WriteLine("Exists 2? " + list.GetElement(2));
            list.Add(2);
            Console.WriteLine("Added 2: " + list.ToString());
            Console.WriteLine("Exists 2? " + list.GetElement(2));
            Console.WriteLine("Exists 3? " + list.GetElement(3));
            list.Add(3);
            Console.WriteLine("Added 3: " + list.ToString());
            Console.WriteLine("Exists 3? " + list.GetElement(3));
            Console.WriteLine("Exists7? " + list.GetElement(7));
            list.Remove(1);
            Console.WriteLine("Removed 1: " + list.ToString());
            Console.WriteLine("Exists 1? " + list.GetElement(1));
            list.Remove(2);
            Console.WriteLine("Removed 2: " + list.ToString());
            Console.WriteLine("Exists 2? " + list.GetElement(2));
            list.Remove(3);
            Console.WriteLine("Removed 3: " + list.ToString());
            Console.WriteLine("Exists 3? " + list.GetElement(3));
        }
    }
}
