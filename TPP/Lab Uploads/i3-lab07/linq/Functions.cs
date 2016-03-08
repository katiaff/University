using System.Linq;
using System.Collections.Generic;
using System;

namespace TPP.Laboratory.Functional.Lab07 {

    static public class Functions {

        public static IEnumerable<TResult> Map<TElement, TResult>(this IEnumerable<TElement> collection, Func<TElement, TResult> function) {
            foreach (TElement x in collection) {
                yield return function(x);
            }
            yield break;
        }

        public static void ForEach<T>(this IEnumerable<T> collection, Action<T> action) {
            foreach(T elem in collection) {
                action(elem);
            }
        }

    }
}
