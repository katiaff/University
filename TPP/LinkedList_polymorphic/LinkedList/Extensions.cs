using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LinkedList {
    public static class Extensions {
        public static T Find<T>(this IEnumerable<T> items, Predicate<T> pred) {
            foreach (T item in items) {
                if (pred(item)) {
                    return item;
                }
            }
            throw new Exception();

        }

        public static T[] Filter<T>(this IEnumerable<T> items, Predicate<T> pred) {
            List<T> ret = new List<T>();
            foreach (T item in items) {
                if (pred(item)) {
                    ret.Add(item);
                }
            }
            if (ret.Count != 0) {
                return ret.ToArray<T>();
            } else {
                throw new Exception();
            }
        }

        public static TRet Reduce<T, TRet>(this IEnumerable<T> items, 
            Func<TRet, T, TRet> function, TRet accumulator = default(TRet)) {

            TRet result = accumulator;
            foreach (T item in items) {
                result = function(result, item);
            }
            return result;
        }

        public static IEnumerable<TResult> Map<TElement, TResult>(this IEnumerable<TElement> collection, Func<TElement, TResult> function) {
            foreach (TElement x in collection) {
                yield return function(x);
            }
            yield break;
        }

        public static void ForEach<T>(this IEnumerable<T> items, Action<T> action) {
            foreach(T item in items) {
                action(item);
            }
        }

        public static void Show<T>(this IEnumerable<T> items) {
            foreach (T item in items) {
                Console.WriteLine(item);
            }
            Console.WriteLine();
        }

        public static IEnumerable<T> Invert<T> (this IEnumerable<T> items) {
            List<T> ret = new List<T>();
            for (int i = items.Count()-1; i >= 0; i--) {
                ret.Add(items.ElementAt(i));
            }
            return ret;
        }

    }
}
