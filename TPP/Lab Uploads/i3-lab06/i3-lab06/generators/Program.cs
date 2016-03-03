using System;
using System.Diagnostics;

namespace TPP.Laboratory.Functional.Lab06 {

    // Implement an EagerFibonacci with the same interface as LazyFibonacci.
    // Measure the execution of both to compute the terms 100,000 - 50,000,000
    // and show the first item (100,000)

    class Program {
        static void Main() {
            int i = 0;
            int result = 0;
            var chrono = new Stopwatch();
            chrono.Start();
            foreach (int value in Fibonacci.InfiniteFibonacci()) {
                if (++i == 100000) {
                    result = value;
                    break;
                }
            }
            chrono.Stop();
            long lazyFib1 = chrono.ElapsedTicks;
            Console.WriteLine("Lazy fibonacci up to 100,000. First invocation in {0:N} ticks. Result: {1}", lazyFib1, result);


            i = 0;
            chrono = new Stopwatch();
            chrono.Start();
            foreach (int value in Fibonacci.InfiniteFibonacci()) {
                if (++i == 50000000) {
                    result = value;
                    break;
                }
            }
            chrono.Stop();
            long lazyFib2 = chrono.ElapsedTicks;
            Console.WriteLine("Lazy fibonacci up to 50,000,000. First invocation in {0:N} ticks. Result: {1}", lazyFib2, result);
            Console.WriteLine("Lazy fibonacci time between 100,000-50,000,000: {0:N} ticks", lazyFib2 - lazyFib1);

            Console.WriteLine("\nThe following eager version will take too long:\n");

            i = 0;
            chrono = new Stopwatch();
            chrono.Start();
            foreach (int value in Fibonacci.EagerFibonacci()) {
                Console.WriteLine("iters: {0}, value: {1}", i, value);
                if (++i == 100000) {
                    result = value;
                    break;
                }
            }
            chrono.Stop();
            long eagerFib1 = chrono.ElapsedTicks;
            Console.WriteLine("Eager fibonacci up to 100,000. First invocation in {0:N} ticks. Result: {1}", eagerFib1, result);


            i = 0;
            chrono = new Stopwatch();
            chrono.Start();
            foreach (int value in Fibonacci.EagerFibonacci()) {
                Console.WriteLine("iters: {0}, value: {1}", i, value);
                if (++i == 50000000) {
                    result = value;
                    break;
                }
            }
            chrono.Stop();
            long eagerFib2 = chrono.ElapsedTicks;
            Console.WriteLine("Eager fibonacci up to 50,000,000. First invocation in {0:N} ticks. Result: {1}", eagerFib2, result);
            Console.WriteLine("Eager fibonacci time between 100,000-50,000,000: {0:N} ticks", eagerFib2 - eagerFib1);

        }
    }
}
