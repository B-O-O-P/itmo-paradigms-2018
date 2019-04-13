package expression;

public class Const implements CommonExpression {
    private int data;

    public Const(int value) {
        data = value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return data;
    }

    @Override
    public int evaluate(int x) {
        return data;
    }
}
