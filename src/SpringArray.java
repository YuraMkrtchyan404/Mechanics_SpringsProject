import java.nio.charset.CoderResult;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.Objects;
import java.util.Stack;

public class SpringArray {

    /**
     * Prerequisite: springExpr is valid
     * @param springExpr
     * @return
     */
    public static Spring equivalentSpring(String springExpr) {
        ArrayList<Double> stiffnessStorage = new ArrayList<>();
        Stack<Double> main = new Stack<>();
        Stack<Double> temp = new Stack<>();

        replaceExpressionIntoStack_UnitStiffness(springExpr, main);

        while (!(main.size() == 1)) {
            while (!main.isEmpty()) {
                CombineSpringsAndChangeTheStack(stiffnessStorage, main, temp);
            }
            Stack<Double> tempForSwap = temp;
            temp = main;
            main = tempForSwap;
        }

        return new Spring(main.pop());
    }

    /**
     * Prerequisite: springExpr is valid
     * Prerequisite: springs are valid Springs with positive stiffness
     * @param springExpr
     * @param springs
     * @return
     */
    static Spring equivalentSpring(String springExpr, Spring[] springs){
        ArrayList<Double> stiffnessStorage = new ArrayList<>();
        Stack<Double> main = new Stack<>();
        Stack<Double> temp = new Stack<>();

        replaceExpressionIntoStack_SpecifiedStiffness(springExpr, springs, main);

        while (!(main.size() == 1)) {
            while (!main.isEmpty()) {
                CombineSpringsAndChangeTheStack(stiffnessStorage, main, temp);
            }
            Stack<Double> tempForSwap = temp;
            temp = main;
            main = tempForSwap;
        }

        return new Spring(main.pop());
    }

    private static void CombineSpringsAndChangeTheStack(ArrayList<Double> stiffnessStorage, Stack<Double> main, Stack<Double> temp) {
        if (main.peek() <= 0) {
            temp.push(main.pop());
        }
        else {
            while (main.peek() > 0) {
                stiffnessStorage.add(main.pop());
            }
            if (Objects.equals(main.peek(), temp.peek())) {
                replaceSpringsWithEquivalent(stiffnessStorage, main, temp);
            }
            else {
                putSpringsIntoTempWithNoChange(stiffnessStorage, temp);
            }
            stiffnessStorage.clear();
        }
    }

    private static void putSpringsIntoTempWithNoChange(ArrayList<Double> stiffnessStorage, Stack<Double> temp) {
        for (Double stiffness : stiffnessStorage) {
            temp.push(stiffness);
        }
    }

    private static void replaceSpringsWithEquivalent(ArrayList<Double> stiffnessStorage, Stack<Double> main, Stack<Double> temp) {
        double tempStiffness = stiffnessStorage.get(0);
        if (main.peek() == 0) {
            for (int i = 1; i < stiffnessStorage.size(); i++) {
                tempStiffness = 1 / ((1 / tempStiffness) + (1 / stiffnessStorage.get(i)));
            }
        }
        else if (main.peek() == -1) {
            for (int i = 1; i < stiffnessStorage.size(); i++) {
                tempStiffness += stiffnessStorage.get(i);
            }
        }
        main.pop();
        temp.pop();
        temp.push(tempStiffness);
    }

    private static void replaceExpressionIntoStack_UnitStiffness(String springExpr, Stack<Double> main) {
        for (int i = springExpr.length() - 1; i >= 0; i--) {

            if (isEmptyBracketsOrBraces(springExpr, i)) {
                main.push(1.0);
                i--;
            } else if (springExpr.charAt(i) == '[' || springExpr.charAt(i) == ']') {
                main.push(-1.0);
            } else if (springExpr.charAt(i) == '{' || springExpr.charAt(i) == '}') {
                main.push(0.0);
            }
        }
    }

    private static boolean isEmptyBracketsOrBraces(String springExpr, int i) {
        return (springExpr.charAt(i) == ']' && springExpr.charAt(i - 1) == '[')
                || (springExpr.charAt(i) == '}' && springExpr.charAt(i - 1) == '{');
    }


    private static void replaceExpressionIntoStack_SpecifiedStiffness(String springExpr, Spring[] springs, Stack<Double> main) {
        int springIndex = springs.length - 1;
        //fill the main stack with numbers corresponding to the spring expression
        for (int i = springExpr.length() - 1; i >= 0; i--) {

            if (isEmptyBracketsOrBraces(springExpr, i)) {
                main.push(springs[springIndex].getStiffness());
                springIndex--;
                i--;
            } else if (springExpr.charAt(i) == '[' || springExpr.charAt(i) == ']') {
                main.push(-1.0);
            } else if (springExpr.charAt(i) == '{' || springExpr.charAt(i) == '}') {
                main.push(0.0);
            }
        }
    }

    public static void main(String[] args) {
        String str1 = "[]"; // 1
        String str2 = "{[][][]}"; // 1/3
        String str3 = "[{{}{}{}}[[][][]]]"; // 10/3
        String str4 = "[[]{{[][][]}[{}{}{}]}]"; // 13/10

        System.out.println(equivalentSpring(str1, new Spring[]{new Spring(5)}).getStiffness());
        System.out.println(equivalentSpring(str2, new Spring[]{new Spring(2), new Spring(4), new Spring(6)}).getStiffness());
        System.out.println(equivalentSpring(str3).getStiffness());
        System.out.println(equivalentSpring(str4).getStiffness());
    }
}

