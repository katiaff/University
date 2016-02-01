using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TPP.Laboratory.ObjectOrientation.Lab02
{
    public class Vector
    {
        public int[] numbers;
        public int Size { get; set; }

        public Vector()
        {
            Size = 0;
            numbers = new int[10];
        }

        public void Add(int value)
        {
            numbers[Size] = value;
            Size++;
        }

        public int GetElement(int pos)
        {
            if (isRightPos(pos))
            {
                return numbers[pos];
            }
            else
            {
                throw new ArgumentException();
            }
        }

        public void SetElement(int pos, int value)
        {
            if (isRightPos(pos))
            {
                numbers[pos] = value;
            }
            else
            {
                throw new ArgumentException();
            }
        }

        private bool isRightPos(int pos)
        {
            return pos >= 0 && pos < Size;
        }
    }
}
