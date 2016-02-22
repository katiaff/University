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
        public static IEnumerable<double> Map(this IEnumerable<double> items, Function function)
        {
            List<double> ret = new List<double>(items.Count());
            foreach (double num in items)
            {
                ret.Add(function(num));
            }
            return ret;
        }

        public static void Show(this IEnumerable<double> items)
        {
            foreach (double num in items)
            {
                Console.WriteLine(num);
            }
        }
    }

    class Program
    {

        static void Main(string[] args)
        {
            ExtensionClass.Function myFunction = DoubleTimes2;

            double a = myFunction(3);
            Console.WriteLine("a = {0}",a);

            a = myFunction(1);
            Console.WriteLine("a = {0}", a);

            // myFunction = Sum;    compilation error

            List<double> myList = new List<double>();
            myList.Add(10);
            myList.Add(0.5);
            myList.Add(-16);

            var result = myList.Map(myFunction);
            result.Show();

            ExtensionClass.Function sqrtFunc = SquareRoot;
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
            sqrtList.Show();
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
