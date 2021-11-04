package calculator;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        CalculatorInterface calc = new ExprCalculator();

        calc.acceptInput("18*179-98/2");

        System.out.println(calc.getDisplayString()); // show 18*179-98/2

        calc.acceptInput("=");

        System.out.println("Display: " + calc.getDisplayString()); // show 3173

        calc.acceptInput(".");

        System.out.println("Display: " + calc.getDisplayString()); // show 3173.

        calc.acceptInput(".");

        System.out.println("Display: " + calc.getDisplayString()); // show 3173..

        calc.acceptInput("9");

        System.out.println("Display: " + calc.getDisplayString()); // show 3173..9

        calc.acceptInput("+3");

        System.out.println("Display: " + calc.getDisplayString()); // show 3173..9+3

        calc.acceptInput("=");

        System.out.println("Display: " + calc.getDisplayString()); // show Error

        calc.acceptInput("C");

        System.out.println("Display: " + calc.getDisplayString()); // show
    }
}
