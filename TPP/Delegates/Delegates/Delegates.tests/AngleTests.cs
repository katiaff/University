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

    }
}

