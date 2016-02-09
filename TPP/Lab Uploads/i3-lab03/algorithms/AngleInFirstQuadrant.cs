using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TPP.Laboratory.ObjectOrientation.Lab03
{
    class AngleInFirstQuadrant : IEqualityPredicate
    {
        public bool Compare(object o1, object o2)
        {
            Angle a1 = o1 as Angle;
            Angle a2 = o2 as Angle;

            if (a1 == null | a2 == null)
                return false;

            return AreEquals(a1, a2) && IsInFirstQuadrant(a1) && IsInFirstQuadrant(a2);
        }

        public bool AreEquals(Angle a1, Angle a2)
        {
            return a1.Radians.Equals(a2.Radians);
        }

        public bool IsInFirstQuadrant(Angle a)
        {
            return a.Degrees <= 90 && a.Degrees >= 0;
        }
    }
}
