import java.util.List;

import java.util.List;
import java.util.Stack;

public class Converter8Bit extends Converter {

    @Override
    public String convertToSprings(List<Integer> binary) {

        checkInput(binary);

        char outerOpen;
        char outerClose;
        int sum = 0;
        for (Integer integer : binary) {
            if (integer == 0)
                sum -= 1;
            else if (integer == 1) {
                sum += 1;
            }
        }
        if (sum >= 0){
            outerOpen = '[';
            outerClose = ']';
        }else {
            outerOpen = '{';
            outerClose = '}';
        }

        Stack<Character> stack = new Stack<>();
        stack.push(outerOpen);
        for (int i = 0; i < 8; i++) {
            if (binary.get(i) == 1) {
                stack.push('{');
                stack.push('}');
            } else {
                stack.push('[');

                stack.push(']');
            }
        }
        stack.push(outerClose);

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        return sb.toString();
    }

    private static void checkInput(List<Integer> binary) {
        if (binary.size() != 8)
            throw new IllegalArgumentException("The input should contain exactly 8 bits");
        for (Integer bit : binary) {
            if (bit != 0 && bit != 1)
                throw new IllegalArgumentException("The input should contain only 0 and 1");
        }
    }

    @Override
    public double evaluateBinary(List<Integer> binary, double[] frequencyAmplitudes) {
        double sum = 0.0;
        for (int i = 0; i < 8; i++) {
            sum += binary.get(i) * frequencyAmplitudes[i];
        }
        return sum;
    }
}