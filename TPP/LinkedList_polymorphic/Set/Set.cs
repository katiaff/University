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

    }
}
