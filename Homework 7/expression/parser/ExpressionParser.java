package expression.parser;

import expression.*;
import expression.exceptions.ParseException;
import expression.exceptions.Parser;

public class ExpressionParser implements Parser {
    private SignParser signs;
    private int oddBracketPosition = -1;

    @Override
    public TripleExpression parse(String expression) throws ParseException {
        signs = new SignParser(expression);
        return expression(false);
    }


    private TripleExpression expression(boolean oddBracket) throws ParseException {
        TripleExpression expression = sixthStep();
        if (signs.end()) {
            Sign sign = signs.next();

            switch (sign.getType()) {
                case Ending:
                    if (oddBracket) {
                        throw new ParseException("no closing bracket for opening one", oddBracketPosition);
                    }
                    break;
                case Opening_Bracket:
                    throw new ParseException("found odd (", sign.getPosition());
                case Closing_Bracket:
                    if (!oddBracket) {
                        throw new ParseException("no opening bracket for closing one", sign.getPosition());
                    }
                    break;
                default:
                    throw new ParseException("wrong expression", sign.getPosition());

            }
        }
        return expression;
    }

    private TripleExpression sixthStep() throws ParseException {
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

    private TripleExpression fifthStep() throws ParseException {
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

    private TripleExpression fourthStep() throws ParseException {
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

    private TripleExpression thirdStep() throws ParseException {
        TripleExpression third = secondStep();

        while (signs.end()) {
            Sign sign = signs.next();

            switch (sign.getType()) {
                case Plus:
                    third = new CheckedAdd(third, secondStep());
                    break;
                case Minus:
                    third = new CheckedSubtract(third, secondStep());
                    break;
                default:
                    signs.prev();
                    return third;
            }
        }
        return third;
    }

    private TripleExpression secondStep() throws ParseException {
        TripleExpression second = staringPoint();

        while (signs.end()) {
            Sign sign = signs.next();

            switch (sign.getType()) {
                case Multiplier:
                    second = new CheckedMultiply(second, staringPoint());
                    break;
                case Dividier:
                    second = new CheckedDivide(second, staringPoint());
                    break;
                default:
                    signs.prev();
                    return second;
            }
        }
        return second;
    }

    private TripleExpression staringPoint() throws ParseException {
        Sign sign = signs.next();
        TripleExpression start;

        switch (sign.getType()) {
            case Ending:
            case Closing_Bracket:
                if (signs.start()) {
                    throw new ParseException("no opening bracket for closing one", sign.getPosition());
                }
                if (signs.prev().getType() == TypeOfSigns.Opening_Bracket) {
                    if (sign.getType() == TypeOfSigns.Closing_Bracket) {
                        throw new ParseException(String.format("empty expression between %d - %d", oddBracketPosition, sign.getPosition()));
                    } else {
                        throw new ParseException("no closing bracket for opening one", oddBracketPosition);
                    }
                }
                throw new ParseException("not enough arguments for " + signs.current().getValue(), sign.getPosition());
            case Const:
                start = new CheckedConst(sign.getValue());
                break;
            case Variable:
                start = new Variable(sign.getValue());
                break;
            case Log10:
                start = new CheckedLog10(staringPoint());
                break;
            case Pow10:
                start = new CheckedPow10(staringPoint());
                break;
            case Minus:
                if (signs.end() && signs.next().getType() == TypeOfSigns.Const) {
                    Sign constant = signs.current();
                    start = new CheckedConst("-" + constant.getValue());
                } else {
                    signs.prev();
                    start = new CheckedNegate(staringPoint());
                }
                break;
            case Opening_Bracket:
                oddBracketPosition = sign.getPosition();
                start = expression(true);
                if (!(signs.current().getType() == TypeOfSigns.Closing_Bracket)) {
                    throw new ParseException("no closing bracket for opening one", oddBracketPosition);
                }
                return start;
            default:
                if (sign.getPosition() == 0)
                    throw new ParseException("not enough arguments for operator " + sign.getValue(), sign.getPosition());
                Sign prev = signs.prev();
                if (prev.getType() == TypeOfSigns.Opening_Bracket || prev.getType() == TypeOfSigns.Minus) {
                    throw new ParseException("not enough arguments for operator " + sign.getValue(), sign.getPosition());
                }
                throw new ParseException(String.format("no argument at %d position between %s and %s operators", prev.getPosition() + 1, prev.getValue(), sign.getValue()), sign.getPosition());
        }
        return start;
    }
}
