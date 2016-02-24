using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Delegates {
    [TestClass]
    public class AngleTests {
        Angle[] angles;

        [TestInitialize]
        public void Init() {
            angles = Factory.CreateAngles();
        }

        // ------------------------Find tests------------------------

        [TestMethod]
        public void TestFindRightAngles() {

            Assert.AreEqual(angles[90], angles.Find<Angle>(angle => angle.Degrees == 90));
            Assert.AreNotEqual(angles[0], angles.Find<Angle>(angle => angle.Degrees == 90));
            Assert.AreNotEqual(angles[180], angles.Find<Angle>(angle => angle.Degrees == 90));
            Assert.AreNotEqual(angles[270], angles.Find<Angle>(angle => angle.Degrees == 90));
            Assert.AreNotEqual(angles[360], angles.Find<Angle>(angle => angle.Degrees == 90));
        }

        [TestMethod]
        public void TestFindAnglesByQuadrant() {

            Assert.AreEqual(angles[0], angles.Find<Angle>(angle => angle.Quadrant == 1));
            Assert.AreNotEqual(angles[91], angles.Find<Angle>(angle => angle.Quadrant == 1));
            Assert.AreNotEqual(angles[181], angles.Find<Angle>(angle => angle.Quadrant == 1));
            Assert.AreNotEqual(angles[271], angles.Find<Angle>(angle => angle.Quadrant == 1));

            Assert.AreEqual(angles[91], angles.Find<Angle>(angle => angle.Quadrant == 2));
            Assert.AreNotEqual(angles[0], angles.Find<Angle>(angle => angle.Quadrant == 2));
            Assert.AreNotEqual(angles[181], angles.Find<Angle>(angle => angle.Quadrant == 2));
            Assert.AreNotEqual(angles[271], angles.Find<Angle>(angle => angle.Quadrant == 2));

            Assert.AreEqual(angles[181], angles.Find<Angle>(angle => angle.Quadrant == 3));
            Assert.AreNotEqual(angles[0], angles.Find<Angle>(angle => angle.Quadrant == 3));
            Assert.AreNotEqual(angles[91], angles.Find<Angle>(angle => angle.Quadrant == 3));
            Assert.AreNotEqual(angles[271], angles.Find<Angle>(angle => angle.Quadrant == 3));

            Assert.AreEqual(angles[271], angles.Find<Angle>(angle => angle.Quadrant == 4));
            Assert.AreNotEqual(angles[0], angles.Find<Angle>(angle => angle.Quadrant == 4));
            Assert.AreNotEqual(angles[91], angles.Find<Angle>(angle => angle.Quadrant == 4));
            Assert.AreNotEqual(angles[181], angles.Find<Angle>(angle => angle.Quadrant == 4));
        }

        // ------------------------Filter tests------------------------

        [TestMethod]
        public void TestFilterRightAngles() {
            Angle[] right = new Angle[] { angles[90] };
            Angle[] filtered = angles.Filter<Angle>(angle => angle.Degrees == 90);
            for (int i = 0; i < filtered.Length; i++) {
                Assert.AreEqual(right[i], filtered[i]);
            }

        }

        [TestMethod]
        public void TestFilterAnglesByQuadrant() {
            Angle[] q1 = Factory.CreateAngles(90);
            Angle[] filteredQ1 = angles.Filter<Angle>(angle => angle.Quadrant == 1);
            for (int i = 0; i<= 90; i++) {
                Assert.AreEqual(q1[i], filteredQ1[i]);
            }

            // Creating angles up to 180 degrees
            Angle[] q2 = Factory.CreateAngles(180);
            Angle[] filteredQ2 = angles.Filter<Angle>(angle => angle.Quadrant == 2);
            for (int i = 0; i < 90; i++) {
                // Checking angles between [91,180]
                Assert.AreEqual(q2[i+91], filteredQ2[i]);
            }

            Angle[] q3 = Factory.CreateAngles(270);
            Angle[] filteredQ3 = angles.Filter<Angle>(angle => angle.Quadrant == 3);
            for (int i = 0; i < 90; i++) {
                Assert.AreEqual(q3[i + 181], filteredQ3[i]);
            }

            Angle[] q4 = Factory.CreateAngles();
            Angle[] filteredQ4 = angles.Filter<Angle>(angle => angle.Quadrant == 4);
            for (int i = 0; i < 90; i++) {
                Assert.AreEqual(q4[i + 271], filteredQ4[i]);
            }

        }

        // ------------------------Reduce tests------------------------

        [TestMethod]
        public void TestReduceSumAllDegrees() {
            Angle[] angles10 = Factory.CreateAngles(10);
            double result10 = angles10.Reduce<Angle, double>((angle, res) => res += angle.Degrees);
            Assert.AreEqual(55, (int)result10);

            Angle[] angles90 = Factory.CreateAngles(90);
            double result90 = angles90.Reduce<Angle, double>((angle, res) => res += angle.Degrees);
            Assert.AreEqual(4095, (int) result90);

            Angle[] angles180 = Factory.CreateAngles(180);
            double result180 = angles180.Reduce<Angle, double>((angle, res) => res += angle.Degrees);
            Assert.AreEqual(16290, (int) result180);

            Angle[] angles270 = Factory.CreateAngles(270);
            double result270 = angles270.Reduce<Angle, double>((angle, res) => res += angle.Degrees);
            Assert.AreEqual(36585, (int) result270);

            Angle[] angles360 = Factory.CreateAngles(360);
            double result360 = angles360.Reduce<Angle, double>((angle, res) => res += angle.Degrees);
            Assert.AreEqual(64980, (int) result360);

        }

        [TestMethod]
        public void TestReduceComputeMaximumSine() {
            Angle[] angles10 = Factory.CreateAngles(10);
            double result10 = angles10.Reduce<Angle, double>((angle, res) => res += angle.Degrees);
            Assert.AreEqual(55, (int) result10);

            Angle[] angles90 = Factory.CreateAngles(90);
            double result90 = angles90.Reduce<Angle, double>((angle, res) => res += angle.Degrees);
            Assert.AreEqual(4095, (int) result90);

            Angle[] angles180 = Factory.CreateAngles(180);
            double result180 = angles180.Reduce<Angle, double>((angle, res) => res += angle.Degrees);
            Assert.AreEqual(16290, (int) result180);

            Angle[] angles270 = Factory.CreateAngles(270);
            double result270 = angles270.Reduce<Angle, double>((angle, res) => res += angle.Degrees);
            Assert.AreEqual(36585, (int) result270);

            Angle[] angles360 = Factory.CreateAngles(360);
            double result360 = angles360.Reduce<Angle, double>((angle, res) => res += angle.Degrees);
            Assert.AreEqual(64980, (int) result360);

        }



    }
}

