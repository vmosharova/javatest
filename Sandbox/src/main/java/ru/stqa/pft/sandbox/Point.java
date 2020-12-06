package ru.stqa.pft.sandbox;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point p2) {
        double result = (p2.x - this.x) * (p2.x - this.x) + (p2.y - this.y) * (p2.y - this.y);
        return Math.sqrt(result);
    }


}
