package expression;

public class Subtract extends NumOperator {
    public Subtract(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    public Subtract(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    protected int evaluateImpl(int first, int second) {
        return first - second;
    }
}
