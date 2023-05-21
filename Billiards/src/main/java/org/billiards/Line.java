package org.billiards;

public class Line {
    private Point start;
    private Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public double getSlope() {
        if (start.getX() == end.getX()) {
            return 0.0;
        }

        return (end.getY() - start.getY()) / (end.getX() - start.getX());
    }

    public double getYIntercept() {
        return start.getY() - getSlope() * start.getX();
    }

    @Override
    public String toString() {
        return "Line{ " +
                "start = " + start.toString() +
                ", end = " + end.toString() +
                " }" + "\n";
    }
}