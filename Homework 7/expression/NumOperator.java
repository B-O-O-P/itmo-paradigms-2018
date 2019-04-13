package expression;

public abstract class NumOperator implements CommonExpression {
    private CommonExpression first;
    private CommonExpression second;

    public NumOperator(CommonExpression a, CommonExpression b) {
        first = a;
        second = b;
    }

    public NumOperator(TripleExpression a, TripleExpression b) {
        first = (CommonExpression) a;
        second = (CommonExpression) b;
    }

    public int evaluate(int x) {
        int a = first.evaluate(x);
        int b = second.evaluate(x);
        return evaluateImpl(a, b);
    }

    public int evaluate(int x, int y, int z) {
        return evaluateImpl(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    protected abstract int evaluateImpl(int first, int second);
}
