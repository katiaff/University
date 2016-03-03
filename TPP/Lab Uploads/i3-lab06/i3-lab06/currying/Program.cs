using System;
using System.Collections.Generic;
using System.Linq;

namespace TPP.Laboratory.Functional.Lab06 {
    public static class ExtensionClass
    {        
        public static IEnumerable<T2> Map<T1, T2>(this IEnumerable<T1> items, Func<T1, T2> function)
        {
            List<T2> ret = new List<T2>(items.Count());
            foreach (T1 num in items)
            {
                ret.Add(function(num));
            }
            return ret;
        }

        public static void Show<T>(this IEnumerable<T> items)
        {
            foreach (T num in items)
            {
                Console.WriteLine(num);
            }
            Console.WriteLine();
        }
    }
    public static class Program {

        static Func<int,int> Addition(int a) {
            return b => a + b;
        }

        static void Main() {            
            Console.WriteLine(Addition(1)(2));
            Func<int, int> increment = Addition(1);
            Console.WriteLine(increment(1));

            List<int> l = new List<int>();
            for (int i = 0; i < 10; i++)
            {
                l.Add(i);
            }
            Console.WriteLine("List l: \n");
            l.Show<int>();

            List<int> lNew = (List<int>)l.Map(increment);
            Console.WriteLine("List lNew: \n");
            lNew.Show<int>();


        }

    }
}
