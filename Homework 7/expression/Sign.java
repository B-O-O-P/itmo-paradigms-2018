package expression;

public class Sign {
    private TypeOfSigns type;
    private String value;
    private int position;

    public Sign(TypeOfSigns typeOfSigns, String string, int x) {
        type = typeOfSigns;
        value = string;
        position = x;
    }

    public int getPosition() {
        return position;
    }

    public TypeOfSigns getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
