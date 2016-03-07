using System.Linq;
using System.Collections.Generic;
using System;

namespace TPP.Laboratory.Functional.Lab07 {

    static public class Functions {

        public static IEnumerable<TResult> Map<TElement, TResult>(this IEnumerable<TElement> collection, Func<TElement, TResult> function) {
            TResult[] result = new TResult[collection.Count()];
            int i = 0;
            foreach (TElement x in collection)
                result[i++] = function(x);
            return result;
        }

    }
}
