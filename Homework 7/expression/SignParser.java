package expression;

import expression.exceptions.InvalidSignException;
import expression.exceptions.ParseException;

import java.util.ArrayList;

public class SignParser {
    private ArrayList<Sign> signs = new ArrayList<>();
    private int current = -1;
    private String expression;

    public SignParser(String expression) throws ParseException {
        this.expression = expression;
        sparse(expression);
        signs.add(new Sign(TypeOfSigns.Ending, "end", expression.length()));
        if (signs.size() == 1) throw new ParseException("no expression");
    }

    private void sparse(String expr) throws InvalidSignException {
        for (int i = 0; i < expr.length(); i++) {
            if (Character.isWhitespace(expr.charAt(i))) {
                continue;
            }
            switch (expr.charAt(i)) {
                case '(':
                    signs.add(new Sign(TypeOfSigns.Opening_Bracket, "(", i));
                    break;
                case ')':
                    signs.add(new Sign(TypeOfSigns.Closing_Bracket, ")", i));
                    break;
                case '+':
                    signs.add(new Sign(TypeOfSigns.Plus, "+", i));
                    break;
                case '-':
                    signs.add(new Sign(TypeOfSigns.Minus, "-", i));
                    break;
                case '*':
                    signs.add(new Sign(TypeOfSigns.Multiplier, "*", i));
                    break;
                case '/':
                    signs.add(new Sign(TypeOfSigns.Dividier, "/", i));
                    break;
                case 'x':
                    signs.add(new Sign(TypeOfSigns.Variable, "x", i));
                    break;
                case 'y':
                    signs.add(new Sign(TypeOfSigns.Variable, "y", i));
                    break;
                case 'z':
                    signs.add(new Sign(TypeOfSigns.Variable, "z", i));
                    break;
                case '&':
                    signs.add(new Sign(TypeOfSigns.And, "&", i));
                    break;
                case '^':
                    signs.add(new Sign(TypeOfSigns.Xor, "^", i));
                    break;
                case '|':
                    signs.add(new Sign(TypeOfSigns.Or, "|", i));
                    break;
                case 'l':
                    i = checkSign("log10", TypeOfSigns.Log10, i);
                    break;
                case 'p':
                    i = checkSign("pow10", TypeOfSigns.Pow10, i);
                    break;
                default:
                    if (!Character.isDigit(expr.charAt(i)))
                        throw new InvalidSignException(String.valueOf(expr.charAt(i)), i);
                    int j = i;
                    while (j < expr.length() && Character.isDigit(expr.charAt(j))) j++;
                    String constant = expr.substring(i, j);
                    signs.add(new Sign(TypeOfSigns.Const, constant, i));
                    i = j - 1;
            }
        }
    }

    public Sign next() {
        return signs.get(++current);
    }

    public Sign prev() {
        return signs.get(--current);
    }

    public Sign current() {
        return signs.get(current);
    }

    public boolean end() {
        return current < signs.size() - 1;
    }

    public boolean start() {
        return current == 0;
    }

    private int checkSign(String sign, TypeOfSigns type, int ind) throws InvalidSignException {
        if (expression.length() <= ind + sign.length()) throw new InvalidSignException(expression.substring(ind), ind);
        if (expression.substring(ind, ind + sign.length()).equals(sign)) {
            int j = ind + sign.length();
            if (Character.isAlphabetic(expression.charAt(j))) {
                throw new InvalidSignException(sign + expression.charAt(j), ind);
            }
            signs.add(new Sign(type, sign, ind));
            ind += sign.length() - 1;
            return ind;
        } else {
            int j = ind;
            while (j < expression.length() && !Character.isWhitespace(expression.charAt(j))) {
                j++;
            }
            throw new InvalidSignException(expression.substring(ind, j) + String.format(" expected other operator '%s'", sign), ind);
        }
    }
}
