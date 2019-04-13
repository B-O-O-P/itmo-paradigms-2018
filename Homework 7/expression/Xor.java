package expression;

public class Xor extends NumOperator {
    public Xor(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    @Override
    protected int evaluateImpl(int first, int second) {
        return first ^ second;
    }
}
