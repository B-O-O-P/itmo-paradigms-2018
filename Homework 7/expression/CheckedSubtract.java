package expression;

import expression.exceptions.OverflowException;

public class CheckedSubtract extends NumOperator {
    public CheckedSubtract(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    public CheckedSubtract(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    protected int evaluateImpl(int first, int second) {
        if (second < 0 && Integer.MAX_VALUE + second < first ||
                second > 0 && Integer.MIN_VALUE + second > first)
            throw new OverflowException(first, "-", second);
        return first - second;
    }
}
