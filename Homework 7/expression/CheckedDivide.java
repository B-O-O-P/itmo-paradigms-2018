package expression;

import expression.exceptions.DivideByZeroException;
import expression.exceptions.OverflowException;

public class CheckedDivide extends NumOperator {
    public CheckedDivide(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    @Override
    protected int evaluateImpl(int first, int second) throws ArithmeticException {
        if (second == 0) throw new DivideByZeroException(first);
        if (first == Integer.MIN_VALUE && second == -1) throw new OverflowException(first, "/", second);
        return first / second;
    }

}
