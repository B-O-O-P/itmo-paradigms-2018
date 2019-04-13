package expression.exceptions;

public class DivideByZeroException extends ArithmeticException {
    public DivideByZeroException(int number) {
        super(String.format("Division by zero: %d / 0", number));
    }
}
