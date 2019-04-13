package expression.exceptions;

public class ParseException extends Exception {
    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, int i) {
        super(String.format("%s at %d position", message, i + 1));
    }

}
