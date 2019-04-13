package expression;

public class Negate extends UnarOperator {
    public Negate(TripleExpression x) {
        super(x);
    }

    @Override
    protected int evaluateImpl(int value) {
        return -value;
    }

    @Override
    public int evaluate(int x) {
        return -x;
    }
}
