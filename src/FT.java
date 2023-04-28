import java.util.Arrays;

public class FT {
    private double[] x;
    private double[] yReal;
    private double[] yImag;

    public FT(double[] x) {
        this.x = Arrays.copyOf(x, x.length);
        this.yReal = new double[x.length];
        this.yImag = new double[x.length];
        calculate();
    }

    private void calculate() {
        int N = x.length;
        for (int k = 0; k < N; k++) {
            for (int j = 0; j < N; j++) {
                double angle = 2 * Math.PI * k * j / N;
                yReal[k] += x[j] * Math.cos(angle);
                yImag[k] -= x[j] * Math.sin(angle);
            }
        }
    }

    public double[] getAmplitudes() {
        double[] amplitudes = new double[x.length];
        for (int k = 0; k < x.length; k++) {
            amplitudes[k] = Math.sqrt(yReal[k] * yReal[k] + yImag[k] * yImag[k]) / x.length;
        }
        return amplitudes;
    }

    public double[] getFrequencies(double dt) {
        double[] frequencies = new double[x.length];
        double df = 1 / (dt * x.length);
        for (int k = 0; k < x.length; k++) {
            frequencies[k] = k * df;
        }
        return frequencies;
    }
}
