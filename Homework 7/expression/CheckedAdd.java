package expression;

import expression.exceptions.OverflowException;

public class CheckedAdd extends NumOperator {
    public CheckedAdd(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    @Override
    protected int evaluateImpl(int first, int second) throws ArithmeticException {
        if (second > 0 && Integer.MAX_VALUE - second < first || second < 0 && Integer.MIN_VALUE - second > first)
            throw new OverflowException(first, "+", second);
        return first + second;
    }
}
