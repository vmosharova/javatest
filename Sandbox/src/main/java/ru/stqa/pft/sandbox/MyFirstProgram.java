package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("world");
        hello("user");

        double l = 5;
        System.out.println("Площадь квадрата со стороной" + l + "=" + area(l));
    }

    public static void hello(String somebody) {
        System.out.println("Hello" + somebody + "!");
    }

    public static double area(double len) {
        return len + len;
    }

    public static double area(double a, double b) {
        return a * b;
    }

}