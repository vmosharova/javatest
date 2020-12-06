package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("world");
        hello("user");

        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + "=" + s.area());

        Rectangle r = new Rectangle(4, 6);
        System.out.println("Площадь прямоугольника со сторонами "  + r.a + r.b + "=" + r.area());

        System.out.println(distance());

    }

    public static void hello(String somebody) {
        System.out.println("Hello" + somebody + "!");
    }

    public static double distance() {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);
        double result = (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
        return Math.sqrt(result);
    }

}