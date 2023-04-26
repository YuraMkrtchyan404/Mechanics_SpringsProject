
import static java.lang.Math.*;

public class Spring {
    private double k;
    public Spring() {
        k = 1.0;
    }

    public Spring(double stiffness) {
        k = stiffness;
    }

    public double getStiffness() {
        return k;
    }

    private void setStiffness(double stiffness) {
        k = stiffness;
    }

    public double[] move(double t, double dt, double x0, double v0) {
        return move(0, t, dt, x0, v0, 1);
    }

    public double[] move(double t, double dt, double x0) {
        return move(0, t, dt, x0, 0, 1);
    }

    public double[] move(double t0, double t1, double dt, double x0, double v0) {
        return move(t0, t1, dt, x0, v0, 1);
    }

    public double[] move(double t0, double t1, double dt, double x0, double v0, double m) {
        double omega = Math.sqrt(k / m);
        double amplitude = Math.sqrt(x0 * x0 + (v0 / omega) * (v0 / omega));
        double phi = Math.atan2(x0, v0 / omega);
        int numSteps = (int) ((t1 - t0) / dt) + 1;
        double[] coordinates = new double[numSteps];

        for (int i = 0; i < numSteps; i++) {
            double t = t0 + i * dt; // current time
            double x = amplitude * sin(omega * t + phi);
            x = Math.round(x * 1000.0) / 1000.0;
            coordinates[i] = x;
        }
        return coordinates;
    }

    public Spring inSeries(Spring that) {
        double equivalentK = 1 / (1 / this.k + 1 / that.k);
        return new Spring(equivalentK);
    }

    public Spring inParallel(Spring that) {
        double equivalentK = this.k + that.k;
        return new Spring(equivalentK);
    }
}
