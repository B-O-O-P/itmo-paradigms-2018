package expression.exceptions;

public class InvalidSignException extends ParseException {
    public InvalidSignException(String message, int i) {
        super(String.format("Unknown sign %s at %d position.", message, i + 1));
    }
}
