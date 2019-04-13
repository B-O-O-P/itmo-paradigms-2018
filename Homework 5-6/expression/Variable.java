package expression;

public class Variable implements CommonExpression {
    private String data;

    public Variable(String value) {
        data = value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (data) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                return 0;
        }
    }

    @Override
    public int evaluate(int x) {
        return x;
    }
}
