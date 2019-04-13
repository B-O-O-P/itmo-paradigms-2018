package expression;

public class Add extends NumOperator {
    public Add(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    @Override
    protected int evaluateImpl(int first, int second) {
        return first + second;
    }
}
