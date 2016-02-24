using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Delegates {
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

        public static TRet Reduce<T, TRet>(this IEnumerable<T> items, Func<T, TRet, TRet> function) {
            TRet result = default(TRet);
            foreach (T item in items) {
                result = function(item, result);
            }

            return result;
        }

    }
}
