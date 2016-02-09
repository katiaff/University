using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TPP.Laboratory.ObjectOrientation.Lab03
{
    class PersonWithSameName : IEqualityPredicate
    {
        public bool Compare(object o1, object o2)
        {
            Person p1 = o1 as Person;
            Person p2 = o2 as Person;

            if (p1 == null || p2 == null)
                return false;

            return p1.FirstName.GetHashCode() == p2.FirstName.GetHashCode();
        }
    }
}
