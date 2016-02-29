using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TPP.Laboratory.Functional.Lab06 {

    public static class NonMemoizedFibonacci {

        /// <summary>
        /// Non memoized recursive Fibonacci function
        /// </summary>
        public static int Fibonacci(int n) {
            return n <= 2 ? 1 : Fibonacci(n - 2) + Fibonacci(n - 1);
        }


    }
}
