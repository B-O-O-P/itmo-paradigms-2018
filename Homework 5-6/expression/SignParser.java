package expression;

import java.util.ArrayList;

public class SignParser {
    private ArrayList<Sign> signs = new ArrayList<>();
    private int current = -1;

    public SignParser(String expression) {
        sparse(expression);
        signs.add(new Sign(TypeOfSigns.Ending, "end"));
    }

    private void sparse(String expr) {
        for (int i = 0; i < expr.length(); i++) {
            if (Character.isWhitespace(expr.charAt(i))) {
                continue;
            }
            switch (expr.charAt(i)) {
                case '(':
                    signs.add(new Sign(TypeOfSigns.Opening_Bracket, "("));
                    break;
                case ')':
                    signs.add(new Sign(TypeOfSigns.Closing_Bracket, ")"));
                    break;
                case '+':
                    signs.add(new Sign(TypeOfSigns.Plus, "+"));
                    break;
                case '-':
                    signs.add(new Sign(TypeOfSigns.Minus, "-"));
                    break;
                case '*':
                    signs.add(new Sign(TypeOfSigns.Multiplier, "*"));
                    break;
                case '/':
                    signs.add(new Sign(TypeOfSigns.Dividier, "/"));
                    break;
                case 'x':
                    signs.add(new Sign(TypeOfSigns.Variable, "x"));
                    break;
                case 'y':
                    signs.add(new Sign(TypeOfSigns.Variable, "y"));
                    break;
                case 'z':
                    signs.add(new Sign(TypeOfSigns.Variable, "z"));
                    break;
                case '&':
                    signs.add(new Sign(TypeOfSigns.And, "&"));
                    break;
                case '^':
                    signs.add(new Sign(TypeOfSigns.Xor, "^"));
                    break;
                case '|':
                    signs.add(new Sign(TypeOfSigns.Or, "|"));
                    break;
                default:
                    int j = i;
                    while (j < expr.length() && Character.isDigit(expr.charAt(j))) j++;
                    String constant = expr.substring(i, j);
                    signs.add(new Sign(TypeOfSigns.Const, constant));
                    i = j - 1;
            }
        }
    }

    public Sign next() {
        return signs.get(++current);
    }

    public void prev() {
        --current;
    }

    public Sign current() {
        return signs.get(current);
    }

    public boolean end() {
        return current < signs.size() - 1;
    }

}
