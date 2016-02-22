using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Delegates
{
    public static class ExtensionClass
    {

        public delegate double Function(double x);
        public static IEnumerable<T2> Map<T1,T2>(this IEnumerable<T1> items, Func<T1,T2> function)
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
        }
    }

    class Program
    {

        static void Main(string[] args)
        {
            // 1: argument of function
            // 2: return type of function
            Func<double, double> myFunction = DoubleTimes2;

            //Predicate<int> = Func<int,bool>

            double a = myFunction(3);
            Console.WriteLine("a = {0}",a);

            a = myFunction(1);
            Console.WriteLine("a = {0}", a);

            // ---------------------------------------------
            List<double> myList = new List<double>();
            myList.Add(10);
            myList.Add(0.5);
            myList.Add(-16);

            var returnVal = myList.Map(input => Math.Pow(input,2));

            var result = myList.Map(myFunction);
            result.Show();

            // ---------------------------------------------

            Func<double, double> sqrtFunc = SquareRoot;
            List<double> newList = new List<double>();
            for (double i = 1; i <= 5; i++)
            {
                newList.Add(i);
            }
            Console.WriteLine("\nCurrent list: \n");
            newList.Show();
            Console.WriteLine("\nApplying Square...\n");
            var sqrtList = newList.Map(sqrtFunc);
            Console.WriteLine("New list: \n");
            sqrtList.Show<double>();
            Console.WriteLine();
            
        }

        static double DoubleTimes2(double x)
        {
            return x * 2;
        }

        static int Sum(int a, int b)
        {
            return a + b;
        }

        static double SquareRoot(double x)
        {
            return Math.Sqrt(x);
        }

    }
}
