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
            Func<int> inc;
            Func<int> dec;
            Action<int> assign;

            Counter(out inc, out dec, out assign); // instantiation of closure

            Console.WriteLine(inc()); // write 1
            Console.WriteLine(inc()); // write 2
            Console.WriteLine(inc()); // write 3

            Console.WriteLine(dec()); // write 2

            assign(666);

            Console.WriteLine(inc()); // write 667

            Func<int> a;
            Func<int> b;
            Action<int> c;
            Counter(out inc, out b, out c); // lose pointer to other function, create new counter
            Console.WriteLine(b()); //write -1
            Console.WriteLine(inc()); // write 0
            Console.WriteLine();

        }

    }
}
