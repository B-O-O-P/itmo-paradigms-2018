package expression.exceptions;

public class OverflowException extends ArithmeticException {
    public OverflowException(int first, String operator, int second) {
        super(String.format("Overflow at: %d %s %d", first, operator, second));
    }

    public OverflowException(String operator, int argument) {
        super(String.format("Overflow at: %s%d", operator, argument));
    }
}
