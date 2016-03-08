using System.Collections.Generic;
using System;
using System.Linq;

namespace TPP.Laboratory.Functional.Lab07 {

    class Program {

        static void Main() {
            IEnumerable<int> integers = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

            foreach (int num in (integers.Map(x => x * 2))) {
                Console.WriteLine(num);
            }

            integers.ForEach(num => Console.WriteLine(num));
            integers.Map(x => { Console.WriteLine(x + " "); return x; }); // does not print anything
            integers.Map(x => { Console.Write(x + " "); return x; }).Last(); // does not print anything


        }
    }
}
