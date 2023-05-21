package org.billiards;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;
import static org.billiards.BilliardSimulationTask_1.generateNewMomentum;

public class BilliardSimulationTask_2 {

    private static final int DELTA = 5;
    private static final int NUMBER_OF_REFLECTIONS = 10;
    private static final Point g = new Point(0, -10);

    public static List<Line> calculateReflectionPoints(Point initialPoint, Momentum initialMomentum) {
        Point point = initialPoint;
        Momentum momentum = initialMomentum;
        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_REFLECTIONS; i++) {

            Point linePoint = Point.add(Point.add(point, momentum), g);
            Point intersection = findIntersectionWithCircle(new Line(point, linePoint));
            lines.add(new Line(point, intersection));

            point = intersection;
            momentum = generateNewMomentum(point, momentum);
        }

        momentum.setX(momentum.getX() * -1);
        momentum.setY(momentum.getY() * -1);

        List<Line> reverseLines = new ArrayList<>();
        boolean deviation = false;
        for (int i = 0; i < NUMBER_OF_REFLECTIONS; i++) {
            Point linePoint = Point.add(Point.add(point, momentum), g);
            Point intersection = findIntersectionWithCircle(new Line(point, linePoint));
            Line line = new Line(point, intersection);
            reverseLines.add(line);

            if (atan(line.getSlope() - lines.get(lines.size() - i - 1).getSlope()) > DELTA && !deviation) {
                System.out.println((i + 1) + "\'th reflection: Deviation is about " + DELTA);
                deviation = true;
            }
            point = intersection;
            momentum = generateNewMomentum(point, momentum);
        }
        return lines;
    }

    private static Point findIntersectionWithCircle(Line line) {
        double startX = line.getStart().getX();
        double endX = line.getEnd().getX();
        double k = line.getSlope();
        double b = line.getYIntercept();

        double c = pow(2 * k * b, 2) - 4 * (1 + pow(k, 2)) * (-1 + pow(b, 2));

        double x1 = (-2 * k * b + sqrt(c)) / (2 * (1 + pow(k, 2)));
        double x2 = (-2 * k * b - sqrt(c)) / (2 * (1 + pow(k, 2)));

        double y1 = k * x1 + b;
        double y2 = k * x2 + b;

        if (endX > startX)
            return new Point(x1, y1);
        else
            return new Point(x2, y2);
    }
}
