package expression;

import expression.exceptions.OverflowException;

public class CheckedMultiply extends NumOperator {
    public CheckedMultiply(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    @Override
    protected int evaluateImpl(int first, int second) {
        boolean f1 = first > 0 && second > 0 && Integer.MAX_VALUE / second < first;
        boolean f2 = first > 0 && second < 0 && Integer.MIN_VALUE / first > second;
        boolean f3 = first < 0 && second > 0 && Integer.MIN_VALUE / second > first;
        boolean f4 = first < 0 && second < 0 && Integer.MAX_VALUE / second > first;

        if (f1 || f2 || f3 || f4) throw new OverflowException(first, "*", second);

        return first * second;
    }
}
