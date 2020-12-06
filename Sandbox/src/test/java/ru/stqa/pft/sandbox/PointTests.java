package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistanceEqualsZero() {
        Point Testpoint1 = new Point(2, 2);
        Point Testpoint2 = new Point(2, 2);
        Assert.assertEquals(Testpoint1.distance(Testpoint2), 0.0);
    }

    @Test
    public void testDistanceEqualsZeroNegative() {
        Point Testpoint1 = new Point(2, 2);
        Point Testpoint2 = new Point(2, 2);
        Assert.assertEquals(Testpoint1.distance(Testpoint2), 1.9);
    }

    @Test
    public void testDistanceWithCoorditatesBelowZero() {
        Point Testpoint1 = new Point(-1, -1);
        Point Testpoint2 = new Point(-2, -2);
        Assert.assertEquals(Testpoint1.distance(Testpoint2), 1.4142135623730951
        );
    }

    @Test
    public void testDistanceWithCoorditatesBelowZeroNegative() {
        Point Testpoint1 = new Point(-1, -1);
        Point Testpoint2 = new Point(-2, -2);
        Assert.assertEquals(Testpoint1.distance(Testpoint2), -1.4142135623730951
        );
    }

}
