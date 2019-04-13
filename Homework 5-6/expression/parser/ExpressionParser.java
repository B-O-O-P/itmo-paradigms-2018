package expression.parser;

import expression.*;

public class ExpressionParser implements Parser {
    private SignParser signs;

    @Override
    public TripleExpression parse(String expression) {
        signs = new SignParser(expression);
        return expression();
    }


    private TripleExpression expression() {
        TripleExpression expression = sixthStep();
        if (signs.end()) {
            signs.next();
        }
        return expression;
    }

    private TripleExpression sixthStep() {
        TripleExpression sixth = fifthStep();

        while (signs.end()) {
            Sign sign = signs.next();

            if (sign.getType() == TypeOfSigns.Or) {
                sixth = new Or(sixth, fifthStep());

            } else {
                signs.prev();
                return sixth;
            }
        }
        return sixth;
    }

    private TripleExpression fifthStep() {
        TripleExpression fifth = fourthStep();

        while (signs.end()) {
            Sign sign = signs.next();

            if (sign.getType() == TypeOfSigns.Xor) {
                fifth = new Xor(fifth, fourthStep());

            } else {
                signs.prev();
                return fifth;
            }
        }
        return fifth;
    }

    private TripleExpression fourthStep() {
        TripleExpression fourth = thirdStep();

        while (signs.end()) {
            Sign sign = signs.next();

            if (sign.getType() == TypeOfSigns.And) {
                fourth = new And(fourth, thirdStep());

            } else {
                signs.prev();
                return fourth;
            }
        }
        return fourth;
    }

    private TripleExpression thirdStep() {
        TripleExpression third = secondStep();

        while (signs.end()) {
            Sign sign = signs.next();

            switch (sign.getType()) {
                case Plus:
                    third = new Add(third, secondStep());
                    break;
                case Minus:
                    third = new Subtract(third, secondStep());
                    break;
                default:
                    signs.prev();
                    return third;
            }
        }
        return third;
    }

    private TripleExpression secondStep() {
        TripleExpression second = staringPoint();

        while (signs.end()) {
            Sign sign = signs.next();

            switch (sign.getType()) {
                case Multiplier:
                    second = new Multiply(second, staringPoint());
                    break;
                case Dividier:
                    second = new Divide(second, staringPoint());
                    break;
                default:
                    signs.prev();
                    return second;
            }
        }
        return second;
    }

    private TripleExpression staringPoint() {
        Sign sign = signs.next();
        TripleExpression start;

        switch (sign.getType()) {
            case Ending:
            case Closing_Bracket:
            case Const:
                start = new Const(Integer.parseInt(sign.getValue()));
                break;
            case Variable:
                start = new Variable(sign.getValue());
                break;
            case Minus:
                if (signs.end() && signs.next().getType() == TypeOfSigns.Const) {
                    Sign constant = signs.current();
                    start = new Const(Integer.parseInt("-" + constant.getValue()));
                } else {
                    signs.prev();
                    start = new Negate(staringPoint());
                }
                break;
            case Opening_Bracket:
                start = expression();
                return start;
            default:
                start = null;
        }
        return start;
    }
}
