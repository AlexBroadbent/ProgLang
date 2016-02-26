package operator;

/**
 * LazyLanguage.operator
 *
 * @version     01/12/2015
 * @author      Alexander Broadbent
 */
public interface IPrecedence {

    // https://en.wikipedia.org/wiki/Order_of_operations#Programming_languages
    int FUNCTION                = 1;    // ()
    int ARRAY_ACCESS            = 1;    // []
    int UNARY                   = 2;    // ! ++ --
    int GEOMETRIC               = 3;    // * / MOD
    int ARITHMETIC              = 4;    // + -
    int BITSHIFT                = 5;    // << >>
    int MATH_COMPARATOR         = 6;    // < <= > >=
    int BOOLEAN_COMPARATOR      = 7;    // != ==
    int BITWISE_AND             = 8;    // BITWISE_AND
    int BITWISE_XOR             = 9;    // BITWISE_XOR
    int BITWISE_OR              = 10;   // BITWISE_OR
    int LOGICAL_AND             = 11;   // &
    int LOGICAL_OR              = 12;   // |
    int CONDITIONAL_EXPRESSION  = 13;   // ? :
    int ASSIGNMENT_OPERATOR     = 14;   // = += -= *= /= %= &= |= ^= <<= >>=
    int ARG_SEPARATOR           = 15;   // ,
    int BRACKET                 = 51;	// Brackets are implemented as operators

}
