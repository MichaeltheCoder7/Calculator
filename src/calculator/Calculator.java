package calculator;
import java.util.ArrayList;

public class Calculator implements CalculatorInterface {
    private ArrayList<Double> numbers = new ArrayList<Double>();
    private ArrayList<String> operators = new ArrayList<String>();
    private String buffer = "0";
    private String output = "0";
    private boolean dotPressed = false;
    private String lastEntry = "";
    private boolean negative = false;

    private void calculate() {
        double num1 = 0;
        double num2 = 0;
        double result = 0;
        int index = 0;

        // order of operation, do multiplication and division first
        while (operators.contains("*")) {
            index = operators.indexOf("*");
            num1 = numbers.get(index);
            num2 = numbers.get(index + 1);
            result = num1 * num2;
            output = "" + result; // convert double to string
            numbers.remove(index + 1); // remove the numbers
            numbers.remove(index);
            operators.remove(index); // remove the operator
            numbers.add(index, result); // add the result to the list
        }
        while (operators.contains("/")) {
            index = operators.indexOf("/");
            num1 = numbers.get(index);
            num2 = numbers.get(index + 1);
            // handle divide by 0
            if (num2 == 0) {
                output = "NaN"; // show NaN and break
                break;
            } else {
                result = num1 / num2;
                output = "" + result; // convert double to string
                numbers.remove(index + 1); // remove the numbers
                numbers.remove(index);
                operators.remove(index); // remove the operator
                numbers.add(index, result); // add the result to the list
            }
        }
        // do addition and subtraction next
        for (int x = 0; x < operators.size(); x++) {
            num1 = numbers.get(x);
            num2 = numbers.get(x + 1);
            switch (operators.get(x)) {
                case "+":
                    result = num1 + num2;
                    output = "" + result; // convert double to string
                    break;
                case "-":
                    result = num1 - num2;
                    output = "" + result; // convert double to string
                    break;
            }
        }
        numbers.clear(); // clear the ArrayLists
        operators.clear();
        numbers.add(result); // add the result to the first entry of numbers list
    }

    public void acceptInput(String s) {
        //only C can clear Error and NaN
        if ((output != "Error" && output != "NaN") || s == "C") {
            switch (s) {
                case ".":
                    // make sure dot is only entered once in a number
                    if (!dotPressed) {
                        buffer = buffer + s;
                    }
                    dotPressed = true;
                    output = buffer;
                    lastEntry = s; // record the last entry
                    break;
                case "C":
                    // clear all variables
                    numbers.clear();
                    operators.clear();
                    buffer = "0";
                    output = "0";
                    dotPressed = false;
                    lastEntry = "";
                    negative = false;
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    // error if last entry is "." or when the operator is pressed twice
                    if (lastEntry == "." || lastEntry == "+" || lastEntry == "-" || lastEntry == "*"
                            || lastEntry == "/") {
                        output = "Error";
                    } else {
                        // negative sign after Clear or Initialization
                        if (s == "-" && lastEntry == "") {
                            negative = true;
                        } else {
                            dotPressed = false;
                            if (lastEntry != "=") {
                                numbers.add(Double.parseDouble(buffer)); // add the number to the list
                            }
                            buffer = "0";
                            operators.add(s); // add the operator to the list
                        }
                    }
                    lastEntry = s;
                    break;
                case "=":
                    dotPressed = false;
                    numbers.add(Double.parseDouble(buffer)); // add the number entered to the list
                    buffer = "0";
                    calculate(); // calculate
                    lastEntry = s;
                    break;
                // numbers
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                    // new number after "="
                    if (lastEntry == "=") {
                        numbers.clear();
                    }
                    // make sure the first digit is not 0
                    if (buffer.charAt(0) != '0' || dotPressed) {
                        buffer = buffer + s; // concatenate numbers
                    } else {
                        if (negative) {
                            buffer = "-" + s; //add negative sign before the number
                            negative = false;
                        } else {
                            buffer = s;
                        }
                    }
                    output = buffer; // get the output from the buffer
                    lastEntry = s;
                    break;
            }
        }
    }

    public String getDisplayString() {
        return output;
    }
}
