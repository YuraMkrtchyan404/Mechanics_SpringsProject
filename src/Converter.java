import java.util.List;

public abstract class Converter {

    // the system of springs will be returned in the form of spring expression
    public abstract String convertToSprings(List<Integer> binary);

    public double[] getOscillations(String springExpression, Spring[] springs, double t0, double t1, double dt, double x0, double v0){
        Spring spring = SpringArray.equivalentSpring(springExpression, springs);
        return spring.move(t0, t1, dt, x0, v0);
    }

    public double[] getFrequencyAmplitudes(String springExpression, Spring[] springs, double t0, double t1, double dt, double x0, double v0){
        double[] oscillations = getOscillations(springExpression, springs, t0, t1, dt, x0, v0);
        FT ft = new FT(oscillations);
        return ft.getAmplitudes();
    }

    public abstract double evaluateBinary(List<Integer> binary, double[] frequencyAmplitudes);
}
