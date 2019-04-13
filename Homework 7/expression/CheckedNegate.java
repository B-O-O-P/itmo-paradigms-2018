package expression;

import expression.exceptions.OverflowException;

public class CheckedNegate extends UnarOperator {
    public CheckedNegate(TripleExpression x) {
        super(x);
    }

    @Override
    protected int evaluateImpl(int value) {
        if (value == Integer.MIN_VALUE) throw new OverflowException("-", value);
        return -value;
    }

    @Override
    public int evaluate(int x) {
        return -x;
    }
}
