package ru.stqa.pft.sandbox;

public class HometaskClassPointMain {

    public static void main(String[] args) {

        /*System.out.println(distance());*/

        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);
        System.out.println(p1.distance(p2));

    }

    /***public static double distance() {
     Point p1 = new Point(1, 2);
     Point p2 = new Point(3, 4);
     double result = (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
     return Math.sqrt(result);
     }***/

}