package expression;

public class And extends NumOperator {
    public And(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    @Override
    protected int evaluateImpl(int first, int second) {
        return first & second;
    }
}
