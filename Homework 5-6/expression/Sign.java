package expression;

public class Sign {
    private TypeOfSigns type;
    private String value;

    public Sign(TypeOfSigns typeOfSigns, String string) {
        type = typeOfSigns;
        value = string;
    }


    public TypeOfSigns getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
