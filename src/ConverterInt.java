import java.util.List;

public class ConverterInt extends Converter {

    @Override
    public String convertToSprings(List<Integer> binary) {
        StringBuilder sb = new StringBuilder();
        int size = binary.size();

        for (int i = 0; i < size; i++) {
            int bit = binary.get(i);

            if (bit == 1) {
                sb.append("{");
                int j = i + 1;
                while (j < size && binary.get(j) == 1) {
                    sb.append("}");
                    j++;
                }
                i = j - 1;
            } else {
                sb.append("[");
                int j = i + 1;
                while (j < size && binary.get(j) == 0) {
                    sb.append("]");
                    j++;
                }
                i = j - 1;
            }
        }

        return sb.toString();
    }

    @Override
    public double evaluateBinary(List<Integer> binary, double[] frequencyAmplitudes) {
        int size = binary.size();
        double sum = 0;

        for (int i = 0; i < size; i++) {
            int bit = binary.get(i);
            double amplitude = frequencyAmplitudes[i];
            sum += bit * amplitude;
        }

        return sum;
    }
}
