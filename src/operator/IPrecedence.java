package operator;

/**
 * x++.operator
 * <p>
 * See:
 * https://en.wikipedia.org/wiki/Order_of_operations#Programming_languages
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public interface IPrecedence {

    int FUNCTION            = 1;    // ()
    int ARRAY_ACCESS        = 1;    // []
    int LIST                = 1;    // {}
    int UNARY               = 2;    // ! ++ --
    int GEOMETRIC           = 3;    // * / MOD
    int ARITHMETIC          = 4;    // + -
    int BIT_SHIFT           = 5;    // << >>
    int MATH_COMPARATOR     = 6;    // < <= > >=
    int BOOLEAN_COMPARATOR  = 7;    // != ==
    int BITWISE_AND         = 8;    // BITWISE_AND
    int BITWISE_XOR         = 9;    // BITWISE_XOR
    int BITWISE_OR          = 10;   // BITWISE_OR
    int LOGICAL_AND         = 11;   // &
    int LOGICAL_OR          = 12;   // |
    int CONDITIONAL         = 13;   // ? :
    int LOOP                = 13;   // for in to
    int ASSIGNMENT_OPERATOR = 14;   // =
    int ARG_SEPARATOR       = 15;   // ,
    int BRACKET             = 16;   // ( )

    int NONE                = 99;   // NullaryOperator

}
