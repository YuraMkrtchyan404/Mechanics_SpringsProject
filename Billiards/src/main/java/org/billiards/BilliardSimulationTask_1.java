package org.billiards;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class BilliardSimulationTask_1 {
    private static final int DELTA = 5;
    private static final int NUMBER_OF_REFLECTIONS = 10;

    public static List<Line> calculateReflectionPoints(Point initialPoint, Momentum initialMomentum) {
        Point point = initialPoint;
        Momentum momentum = initialMomentum;
        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_REFLECTIONS; i++) {

            Point linePoint = Point.add(point, momentum);
            Point intersection = findIntersectionWithCircle(new Line(point, linePoint));
            lines.add(new Line(point, intersection));

            point = intersection;
            momentum = generateNewMomentum(point, momentum);
        }

        //Check reversed path
        momentum.setX(momentum.getX() * -1);
        momentum.setY(momentum.getY() * -1);

        List<Line> reverseLines = new ArrayList<>();
        boolean deviation = false;
        for (int i = 0; i < NUMBER_OF_REFLECTIONS; i++) {
            Point linePoint = Point.add(point, momentum);
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


    public static Momentum generateNewMomentum(Point point, Momentum momentum) {
        double x = point.getX();
        double y = point.getY();
        double px = momentum.getX();
        double py = momentum.getY();

        double a = pow(y, 2) - pow(x, 2);
        double b = -2 * x * y;

        return new Momentum(a * px + b * py, b * px - a * py);
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

        Point intersection1 = new Point(x1, y1);
        Point intersection2 = new Point(x2, y2);

        if (endX > startX) {
            return intersection1.getX() > intersection2.getX() ? intersection1 : intersection2;
        } else {
            return intersection1.getX() < intersection2.getX() ? intersection1 : intersection2;
        }
    }
}
