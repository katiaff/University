using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace parameters
{
    class Program
    {
        static void Main(string[] args)
        {
            int a = 10;
            int b = 20;

            Swap(ref a, ref b); // Swap method

            // Result
            Console.WriteLine(a); // Prints 20;
            Console.WriteLine(b); // Prints 10;
            Console.WriteLine();

            String param1;
            String param2;
            int param3;
            AskData(out param1, out param2, out param3);
            Console.WriteLine(param1); // Prints "Carla";
            Console.WriteLine(param2); // Prints "Fernandez";
            Console.WriteLine(param3); // Prints 18;  
            Console.WriteLine();
        }

        private static void AskData(out string param1, out string param2, out int param3)
        {
            param1 = "Carla";
            param2 = "Fernandez";
            param3 = 18;
        }

        private static void Swap(ref int a, ref int b)
        {
            int aux = a;
            a = b;
            b = aux;
        }
    }
}
