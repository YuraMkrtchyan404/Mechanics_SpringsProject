import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("TESTING CONVERTER8BIT");
        System.out.println();
        Converter8Bit converter = new Converter8Bit();

        List<Integer> binary1 = Arrays.asList(0, 1, 1, 0, 0, 1, 0, 1);
        double[] freqAmp1 = {0, 0.5, 0, 0.25, 0, 0.125, 0, 0.0625};
        double result1 = converter.evaluateBinary(binary1, freqAmp1);
        System.out.println("Test Case 1: " + result1);

        List<Integer> binary2 = Arrays.asList(1, 0, 0, 1, 1, 1, 0, 0);
        double[] freqAmp2 = {0, 0.5, 0, 0.25, 0, 0.125, 0, 0.0625};
        double result2 = converter.evaluateBinary(binary2, freqAmp2);
        System.out.println("Test Case 2: " + result2);

        List<Integer> binary3 = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0);
        double[] freqAmp3 = {0, 0.5, 0, 0.25, 0, 0.125, 0, 0.0625};
        double result3 = converter.evaluateBinary(binary3, freqAmp3);
        System.out.println("Test Case 3: " + result3);


        List<Integer> binary4 = Arrays.asList(1, 0, 1, 0, 1, 0, 1, 0);
        String springExpression1 = converter.convertToSprings(binary4);
        System.out.println(springExpression1);

        List<Integer> binary5 = Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1);
        String springExpression2 = converter.convertToSprings(binary5);
        System.out.println(springExpression2);

        List<Integer> binary6 = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0);
        String springExpression3 = converter.convertToSprings(binary6);
        System.out.println(springExpression3);
        System.out.println();

        System.out.println("TESTING CONVERTERINT");
        System.out.println();
        // Test case 1: binary number 1010 (decimal number 10)
        ConverterInt converterInt = new ConverterInt();
        List<Integer> binary = List.of(1, 0, 1, 0);
        Spring[] springs1 = {new Spring(), new Spring(), new Spring(), new Spring()};
        double[] freqAmplitudes = converterInt.getFrequencyAmplitudes(converterInt.convertToSprings(binary), springs1, 0, 1, 0.01, 0, 0);
        double result = converterInt.evaluateBinary(binary, freqAmplitudes);
        System.out.println("Test case 1: " + binary + " -> " + result);

        // Test case 2: binary number 111111 (decimal number 63)
        binary = List.of(1, 1, 1, 1, 1, 1);
        Spring[] springs2 = {new Spring(), new Spring(), new Spring(), new Spring(), new Spring(), new Spring()};
        freqAmplitudes = converterInt.getFrequencyAmplitudes(converterInt.convertToSprings(binary), springs2, 0, 1, 0.01, 0, 0);
        result = converterInt.evaluateBinary(binary, freqAmplitudes);
        System.out.println("Test case 2: " + binary + " -> " + result);

        // Test case 3: binary number 10011101 (decimal number 157)
        binary = List.of(1, 0, 0, 1, 1, 1, 0, 1);
        Spring[] springs3 = {new Spring(), new Spring(), new Spring(), new Spring(), new Spring(), new Spring(), new Spring(), new Spring()};
        freqAmplitudes = converterInt.getFrequencyAmplitudes(converterInt.convertToSprings(binary), springs3, 0, 1, 0.01, 0, 0);
        result = converterInt.evaluateBinary(binary, freqAmplitudes);
        System.out.println("Test case 3: " + binary + " -> " + result);
        System.out.println();
    }
}
