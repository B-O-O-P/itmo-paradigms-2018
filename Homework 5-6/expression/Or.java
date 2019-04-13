package expression;

public class Or extends NumOperator {
    public Or(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    @Override
    protected int evaluateImpl(int first, int second) {
        return first | second;
    }
}
