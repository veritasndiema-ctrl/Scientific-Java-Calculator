package src.logic;
public class CalculatorLogic {

    public double calculate(double a, double b, String operator) {
        switch (operator) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (b == 0) throw new ArithmeticException("Cannot divide by zero");
                return a / b;
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }
}