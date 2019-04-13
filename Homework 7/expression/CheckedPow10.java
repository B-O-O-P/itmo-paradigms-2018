package expression;

import expression.exceptions.OverflowException;

public class CheckedPow10 extends UnarOperator {
    public CheckedPow10(TripleExpression expression) {
        super(expression);
    }

    @Override
    protected int evaluateImpl(int value) throws OverflowException {
        if (value < 0) throw new IllegalArgumentException("Pow10 " + value);
        if (value >= 10) throw new OverflowException("pow10", value);
        int res = 1;
        for (int i = 0; i < value; i++) {
            res *= 10;
        }
        return res;
    }

    @Override
    public int evaluate(int x) {
        return evaluateImpl(x);
    }
}
