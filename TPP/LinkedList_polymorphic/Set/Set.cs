using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LinkedList
{
    /// <summary>
    /// Implementation of a set which does not allow
    /// repeated elements
    /// </summary>
    public class Set : MyLinkedList
    {
        /// <summary>
        /// Constructs a new Set with a specific first value
        /// </summary>
        /// <param name="firstValue">First value of the set</param>
        public Set(object firstValue) : base(firstValue)
        {
        }

        /// <summary>
        /// Adds a new object to the set 
        /// </summary>
        /// <param name="value">Object to be added</param>
        public override void Add(object value)
        {
            if (!base.Contains(value))
            {
                base.Add(value);
            }

        }
        /// <summary>
        /// + operator overloading, adds a new item to the set
        /// </summary>
        /// <param name="set">Current set</param>
        /// <param name="newValue">Value to be added</param>
        /// <returns>Set with the value added</returns>
        public static Set operator +(Set set, Object newValue)
        {
            set.Add(newValue);
            return set;
        }

        /// <summary>
        /// - operator overloading, removes an item from the set
        /// </summary>
        /// <param name="set">The set</param>
        /// <param name="toRemove">Element to remove</param>
        /// <returns>Set with the value removed</returns>
        public static Set operator -(Set set, Object toRemove)
        {
            set.Remove(toRemove);
            return set;
        }

        /// <summary>
        /// [] operator overloading, get an item from an index
        /// </summary>
        /// <param name="set">The set</param>
        /// <param name="index">Index to get the element</param>
        /// <returns>Element at index i of the set</returns>
        public Object this[int index]
        {
            get { return GetElementByIndex(index); }

            set { GetElementByIndex(index); }
           
        }

        /// <summary>
        /// | operator overloading, provides union of two sets
        /// </summary>
        /// <param name="s1">First set</param>
        /// <param name="s2">Second set</param>
        /// <returns>Union of s1 and s2</returns>
        public static Set operator |(Set s1, Set s2)
        {
            Set ret = copySetElements(s1);
            for (int i = 0; i<s2.NumberOfElements; i++)
            {
                Object currentS2 = s2.GetElementByIndex(i);
                if (!s1.Contains(currentS2))
                {
                    ret.Add(currentS2);
                }
            }
            return ret;
        }

        private static Set copySetElements(Set toCopy)
        {
            Set ret = new Set(toCopy.GetElementByIndex(0));
            for (int i = 1; i < toCopy.NumberOfElements; i++)
            {
                ret.Add (toCopy.GetElementByIndex(i));
            }
            return ret;
        }

        /// <summary>
        /// & operator overloading, provides intersection of two sets
        /// </summary>
        /// <param name="s1">First set</param>
        /// <param name="s2">Second set</param>
        /// <returns>Intersection of s1 and s2</returns>
        public static Set operator &(Set s1, Set s2)
        {
            Set ret = new Set(0);
            ret.Remove(0);

            for (int i = 0; i < s1.NumberOfElements; i++)
            {
                Object currentS1 = s1.GetElementByIndex(i);
                if (s2.Contains(currentS1))
                {
                    ret.Add(currentS1);
                }
            }
            return ret;
        }

        /// <summary>
        /// - operator overloading, computes the difference between two sets
        /// </summary>
        /// <param name="s1">Set 1</param>
        /// <param name="s2">Set 2</param>
        /// <returns>Elements in s1 that are not in s2, s1 \ s2</returns>
        public static Set operator -(Set s1, Set s2)
        {
            Set ret = copySetElements(s1);

            for (int i = 0; i < s1.NumberOfElements; i++)
            {
                Object currentS1 = s1.GetElementByIndex(i);
                if (s2.Contains(currentS1))
                {
                    ret.Remove(currentS1);
                }
            }
            return ret;
        }

        /// <summary>
        /// ^ operator overloading, checks if an element is in a set
        /// </summary>
        /// <param name="set">Set</param>
        /// /// <param name="element">Element to be checked</param>
        /// <returns>True if it is, false otherwise</returns>
        public static bool operator ^(Set set, Object element)
        {
            return set.Contains(element);
        }

    }
}
