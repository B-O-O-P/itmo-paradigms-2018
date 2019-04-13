package expression;

public class CheckedLog10 extends UnarOperator {
    public CheckedLog10(TripleExpression value) {
        super(value);
    }

    @Override
    protected int evaluateImpl(int value) {
        if (value <= 0) throw new IllegalArgumentException("log10 " + value);
        int res = -1;
        while (value > 0) {
            value /= 10;
            res++;
        }
        return res;
    }

    @Override
    public int evaluate(int x) {
        return evaluateImpl(x);
    }
}
