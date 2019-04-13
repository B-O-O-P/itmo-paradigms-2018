package expression.exceptions;

public class ConstantOverflowException extends ParseException {
    public ConstantOverflowException(String message) {
        super(String.format("Constant overflow: %s", message));
    }
}
