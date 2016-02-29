using System;

namespace TPP.Laboratory.Functional.Lab06 {

    /// <summary>
    /// Try to guess the behavior of this program without running it
    /// </summary>
    class Closures
    {

        /// <summary>
        /// Version with a single method
        /// </summary>
        static void Counter(out Func<int> inc, out Func<int> dec, out Action<int> assign)
        {
            int counter = 0;
            inc = () => ++counter;
            dec = () => --counter;
            assign = (x) => counter = x;
        }

        static void Main()
        {
            Func<int> aux;
            Func<int> aux1;
            Action<int> aux2;

            Counter(out aux, out aux1, out aux2); // instantiation of closure

            Console.WriteLine(aux()); // write 1
            Console.WriteLine(aux()); // write 2
            Console.WriteLine(aux()); // write 3

            Console.WriteLine(aux1()); // write 2

            Func<int> a;
            Func<int> b;
            Action<int> c;
            Counter(out aux, out b, out c); // lose pointer to other function
            Console.WriteLine(b()); //write -1
            Console.WriteLine(aux()); // write 3
            Console.WriteLine();

        }

    }
}
