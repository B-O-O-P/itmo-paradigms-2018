package expression;

import expression.exceptions.ConstantOverflowException;

public class CheckedConst implements CommonExpression {
    private int data;

    public CheckedConst(String value) throws ConstantOverflowException {
        try {
            data = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new ConstantOverflowException(value);
        }

    }

    public int evaluate(int x, int y, int z) {
        return data;
    }

    public int evaluate(int x) {
        return data;
    }
}
