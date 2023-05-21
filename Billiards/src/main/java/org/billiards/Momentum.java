package org.billiards;

import java.util.Random;

class Momentum extends Point {
    public Momentum(double x, double y) {
        super(x, y);
    }

    public static Momentum generateRandomUnitMomentum(){
        Random random = new Random();
        double t = 2 * Math.PI * random.nextDouble();

        double x = Math.cos(t);
        double y = Math.sin(t);
        return new Momentum(x, y);
    }
}

