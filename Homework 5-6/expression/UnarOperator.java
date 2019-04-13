package expression;

public abstract class UnarOperator implements CommonExpression {
    private TripleExpression value;

    public UnarOperator(TripleExpression data) {
        value = data;
    }

    public UnarOperator(CommonExpression data) {
        value = data;
    }


    @Override
    public int evaluate(int x, int y, int z) {
        return evaluateImpl(value.evaluate(x, y, z));
    }

    protected abstract int evaluateImpl(int value);
}
