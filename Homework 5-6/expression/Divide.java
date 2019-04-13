package expression;

public class Divide extends NumOperator {
    public Divide(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    @Override
    protected int evaluateImpl(int first, int second) {
        return first / second;
    }

}
