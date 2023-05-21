package org.billiards;

import java.util.Random;

public class Point {
    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getLength() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public Point normalize() {
        double length = getLength();
        return new Point(x / length, y / length);
    }

    public static Point generateRandomPointInsideUnitCircle(){
        Random random = new Random();
        double r = Math.sqrt(random.nextDouble());
        double theta = random.nextDouble() * 2 * Math.PI;

        double x = r * Math.cos(theta);
        double y = r * Math.sin(theta);

        return new Point(x, y);
    }

    public static Point add(Point p1, Point p2) {
        return new Point(p1.x + p2.x, p1.y + p2.y);
    }

    public static Point subtract(Point p1, Point p2) {
        return new Point(p1.x - p2.x, p1.y - p2.y);
    }

    public static Point divide(Point p, double a) {
        return new Point(p.x / a, p.y / a);
    }

    public static Point multiply(Point p, double a) {
        return new Point(p.x * a, p.y * a);
    }
}
