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

        public static MyLinkedList<TRet> Map<T, TRet>(this IEnumerable<T> items, Func<T, TRet> function) {
            MyLinkedList<TRet> ret = new MyLinkedList<TRet>();

            foreach (T num in items) {
                ret.Add(function(num));
            }
            return ret;
        }

        public static void Show<T>(this IEnumerable<T> items) {
            foreach (T num in items) {
                Console.WriteLine(num);
            }
            Console.WriteLine();
        }
    }
}
