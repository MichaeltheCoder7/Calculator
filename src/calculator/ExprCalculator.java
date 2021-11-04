package calculator;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Stack;

public class ExprCalculator implements CalculatorInterface {
    private String expression = "";
    private String errorState = "";

    private int getPrecedence(String operator) {
        switch (operator) {
            case "^":
                return 4;
            case "*":
            case "/":
            case "%":
                return 3;
            case "+":
            case "-":
                return 2;
            case "(":
                return 1;
            default: // unary operators
                return 5;
        }
    }

    private double factorial(double number) {
        double fac = 1;

        for (int i = 1; i <= number; i++) {
            fac *= i;
        }
        return fac;
    }

    private double calculateUnary(double number, String operator) {
        switch (operator) {
            case "sin":
                return Math.sin(number);
            case "cos":
                return Math.cos(number);
            case "tan":
                return Math.tan(number);
            case "log":
                return Math.log10(number);
            case "ln":
                return Math.log(number);
            case "sqrt":
                return Math.sqrt(number);
            case "fac":
                return factorial(number);
            default:
                return 0;
        }
    }

    // return -1 if divide by 0
    private double calculateBinary(double number1, double number2, String operator) {
        switch (operator) {
            case "^":
                return Math.pow(number1, number2);
            case "*":
                return number1 * number2;
            case "/":
                // divide by 0
                if (number2 == 0) {
                    return -1;
                } else {
                    return number1 / number2;
                }
            case "%":
                return number1 % number2;
            case "+":
                return number1 + number2;
            case "-":
                return number1 - number2;
            default:
                return 0;
        }
    }

    private void eval() {
        ArrayList<String> tokens = new ArrayList<String>();
        ArrayList<String> output = new ArrayList<String>();
        Stack<String> stack = new Stack<String>();
        Stack<Double> evalStack = new Stack<Double>();
        char character;
        String number = "";
        String topToken;
        int parenCount1 = 0;
        int parenCount2 = 0;
        double num1;
        double num2;
        double result;

        expression = expression.replaceAll("\\s+",""); // replace all non-visible characters
        // check errors
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                parenCount1++;
            } else if (expression.charAt(i) == ')') {
                parenCount2++;
            }
        }
        // mismatched parentheses
        if (parenCount1 != parenCount2) {
            errorState = "Error:Parentheses";
            return;
        }
        // decimal point followed by +/-/*/./^/mod or vice versa
        // typing +/-/*/./^/mod for multiple times
        for (int i = 0; i < expression.length(); i++) {
            if (i < expression.length() - 1) {
                if (expression.charAt(i) == '.' || expression.charAt(i) == '+' || expression.charAt(i) == '-'
                        || expression.charAt(i) == '*' || expression.charAt(i) == '/' || expression.charAt(i) == '^'
                        || expression.charAt(i) == 'd') {
                    if (expression.charAt(i + 1) == '.' || expression.charAt(i + 1) == '+'
                            || expression.charAt(i + 1) == '-' || expression.charAt(i + 1) == '*'
                            || expression.charAt(i + 1) == '/' || expression.charAt(i + 1) == '^'
                            || expression.charAt(i + 1) == 'm') {
                        errorState = "Error";
                        return;
                    }
                }
            }
        }
        // operations followed by equal sign button
        if (expression.endsWith("+") || expression.endsWith("-") || expression.endsWith("*")
                || expression.endsWith("/") || expression.endsWith("^") || expression.endsWith("mod")) {
            errorState = "Error";
            return;
        }

        // create tokens
        for (int i = 0; i < expression.length(); i++) {
            character = expression.charAt(i);
            if (Character.isDigit(character) || character == '.') { // parse numbers
                // multiple dots in a number
                if (character == '.' && number.indexOf('.') != -1) {
                    errorState = "Error";
                    return;
                }
                number = number + character;
                // last digit before =
                if (i == expression.length() - 1) {
                    tokens.add(number);
                    number = "";
                }
            } else if (Character.isLetter(character)) { //parse special operators
                // add numbers
                if (i > 0 && Character.isDigit(expression.charAt(i - 1))) {
                    tokens.add(number);
                    number = "";
                }
                switch (character) {
                    case 'm':
                        tokens.add("%");
                        i += 2;
                        break;
                    case 's':
                        if (expression.charAt(i + 1) == 'i') {
                            tokens.add("sin");
                            i += 2;
                        } else {
                            tokens.add("sqrt");
                            i += 3;
                        }
                        break;
                    case 'c':
                        tokens.add("cos");
                        i += 2;
                        break;
                    case 't':
                        tokens.add("tan");
                        i += 2;
                        break;
                    case 'l':
                        if (expression.charAt(i + 1) == 'o') {
                            tokens.add("log");
                            i += 2;
                        } else {
                            tokens.add("ln");
                            i += 1;
                        }
                        break;
                    case 'f':
                        tokens.add("fac");
                        i += 2;
                        break;
                    default:
                        break;
                }
            } else { //operators and parentheses
                // add numbers
                if (i > 0 && Character.isDigit(expression.charAt(i - 1))) {
                    tokens.add(number);
                    number = "";
                }
                // add other symbols
                tokens.add("" + character);
            }
        }

        // convert to postfix
        for (int i = 0; i < tokens.size(); i++) {
            // append numbers to output
            if (Character.isDigit(tokens.get(i).charAt(0))) {
                output.add(tokens.get(i));
            } else if (tokens.get(i).equals("(")) { // push ( to stack
                stack.push(tokens.get(i));
            } else if (tokens.get(i).equals(")")) {
                topToken = stack.pop();
                while (!topToken.equals("(")) { // pop until ( is found
                    output.add(topToken);
                    topToken = stack.pop();
                }
            } else {
                // for operators, pop operators with higher precedence and add to output
                while (!stack.isEmpty() && getPrecedence(stack.peek()) >= getPrecedence(tokens.get(i))) {
                    output.add(stack.pop());
                }
                stack.push(tokens.get(i)); // push the operator to the stack
            }
        }
        // add the rest of the stack to output
        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }

        // calculate
        for (int i = 0; i < output.size(); i++) {
            // push the numbers to the stack
            if (Character.isDigit(output.get(i).charAt(0))) {
                evalStack.push(Double.parseDouble(output.get(i)));
            } else if (Character.isLetter(output.get(i).charAt(0))) { // unary operators
                num1 = evalStack.pop(); // pop one number
                result = calculateUnary(num1, output.get(i));
                evalStack.push(result);
            } else { //binary operators
                num2 = evalStack.pop(); // pop two numbers
                if (!evalStack.isEmpty()) {
                    num1 = evalStack.pop();
                } else {
                    num1 = 0; // in case of a negative sign at the beginning of the operation
                }
                result = calculateBinary(num1, num2, output.get(i));
                if (result == -1) { // divide by 0 detected
                    errorState = "NaN";
                    return;
                }
                evalStack.push(result);
            }
        }

        // get the final result
        if (evalStack.peek() == evalStack.peek().intValue()) {
            expression = "" + evalStack.pop().intValue(); // trim the .0 if the result is integer
        } else {
            expression = "" + evalStack.pop();
        }
    }

    @Override
    public void acceptInput(String s) {
        if (s == "=") {
            eval();
        } else if (s == "Backspace") {
            expression = expression.substring(0, expression.length() - 1);
        } else if (s == "C") {
            expression = ""; // clear!
        } else { // accumulate input String
            expression = expression + s;
        }
    }

    @Override
    public String getDisplayString() {
        String temp = "";

        if (errorState != "") { //return error message if there is one
            temp = errorState;
            errorState = "";
            return temp;
        }
        return expression;
    }
}
