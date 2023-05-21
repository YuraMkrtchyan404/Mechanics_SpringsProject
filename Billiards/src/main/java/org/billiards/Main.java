package org.billiards;

import java.util.List;

import static java.lang.Math.random;
import static org.billiards.BilliardSimulationTask_1.calculateReflectionPoints;

public class Main {
    public static void main(String[] args) {
        List<Integer> sides = List.of(-1, 1);
        double L = 2.0;

        Point point = Point.add(Point.generateRandomPointInsideUnitCircle(),
                Point.multiply(new Point(L / 2, 0), sides.get((int) (random() * sides.size()))));

        Point initialPoint = Point.generateRandomPointInsideUnitCircle();
        Momentum initialMomentum = Momentum.generateRandomUnitMomentum();
        System.out.println(initialPoint);
        System.out.println(initialMomentum);

        System.out.println("Task 1:");
        System.out.println(BilliardSimulationTask_1.calculateReflectionPoints(initialPoint, initialMomentum));

        System.out.println();
        System.out.println("Task 2:");
        System.out.println(BilliardSimulationTask_2.calculateReflectionPoints(initialPoint, initialMomentum));

        System.out.println();
        System.out.println("Task 3:");
        System.out.println(BilliardSimulationTask_3.calculateReflectionPoints(initialPoint, initialMomentum));
    }
}