using System;

namespace TPP.Laboratory.Functional.Lab06 {

    // Implement and EagerFibonacci with the same interface as LazyFibonacci.
    // Measure the execution of both to compute the terms 100,000 - 50,000,000
    // and show the first item (100,000)

    class Program {
        static void Main() {

            int i = 0;
            foreach (int value in Fibonacci.InfiniteFibonacci()) {
                Console.Write(value + " ");
                if (++i == 10)
                    break;
            }
            Console.WriteLine();

            i = 0;
            foreach (int value in Fibonacci.EagerFibonacci()) {
                Console.Write(value + " ");
                if (++i == 10)
                    break;
            }
            Console.WriteLine();
        }
    }
}
