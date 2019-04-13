package expression;

public class Multiply extends NumOperator {
    public Multiply(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    @Override
    protected int evaluateImpl(int first, int second) {
        return first * second;
    }
}
