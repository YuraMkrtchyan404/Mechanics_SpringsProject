import java.util.ArrayList;
import java.util.List;

public class ConverterFloat extends Converter {
    private final int INTEGER_PART_SIZE;
    private final int FRACTIONAL_PART_SIZE;

    public ConverterFloat(int integerPartSize, int fractionalPartSize) {
        this.INTEGER_PART_SIZE = integerPartSize;
        this.FRACTIONAL_PART_SIZE = fractionalPartSize;
    }

    @Override
    public String convertToSprings(List<Integer> binary) {
        int totalSize = INTEGER_PART_SIZE + FRACTIONAL_PART_SIZE;
        if (binary.size() != totalSize) {
            throw new IllegalArgumentException("Binary size must be " + totalSize);
        }

        List<Spring> springs = new ArrayList<>();
        for (int i = 0; i < INTEGER_PART_SIZE; i++) {
            if (binary.get(i) == 0) {
                springs.add(new Spring(1.0));
            } else {
                springs.add(new Spring(0.0));
            }
        }

        Spring integerSpring = new Spring(0);
        for (Spring spring : springs) {
            integerSpring = integerSpring.inSeries(spring);
        }

        springs.clear();
        for (int i = INTEGER_PART_SIZE; i < totalSize; i++) {
            if (binary.get(i) == 0) {
                springs.add(new Spring(1.0));
            } else {
                springs.add(new Spring(0.0));
            }
        }

        Spring fractionalSpring = new Spring(0);
        for (Spring spring : springs) {
            fractionalSpring = fractionalSpring.inSeries(spring);
        }

        Spring[] parallel = {integerSpring, fractionalSpring};
        Spring result = integerSpring.inParallel(fractionalSpring);

        return result.toString();
    }

    @Override
    public double evaluateBinary(List<Integer> binary, double[] frequencyAmplitudes) {
        double decimalValue = 0.0;

        for (int i = 0; i < INTEGER_PART_SIZE; i++) {
            decimalValue += binary.get(i) * Math.pow(2, INTEGER_PART_SIZE - i - 1);
        }

        for (int i = 0; i < FRACTIONAL_PART_SIZE; i++) {
            decimalValue += binary.get(INTEGER_PART_SIZE + i) * Math.pow(2, -(i + 1));
        }

        return decimalValue;
    }
}
