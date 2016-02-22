using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Delegates
{
    class Program
    {
        public delegate double Function(double x);

        static void Main(string[] args)
        {
            Function myFunction = DoubleTimes2;

            double a = myFunction(3);
            Console.WriteLine("a = {0}",a);

            a = myFunction(1);
            Console.WriteLine("a = {0}", a);

            // myFunction = Sum;    compilation error

            List<double> myList = new List<double>();
            myList.Add(10);
            myList.Add(0.5);
            myList.Add(-16);

            var result = Map(myList, myFunction);
            foreach (var num in result)
            {
                Console.WriteLine(num);
            }
            
        }

        public static List<double> Map(List<double> items, Function function){
            List<double> ret = new List<double>();
            foreach (double num in items)
            {
                ret.Add(function(num));
            }
            return ret;
        }

        static double DoubleTimes2(double x)
        {
            return x * 2;
        }

        static int Sum(int a, int b)
        {
            return a + b;
        }

    }
}
