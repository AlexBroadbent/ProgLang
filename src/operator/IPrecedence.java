package operator;

/**
 * LazyLanguage.operator
 *
 * @version     01/12/2015
 * @author      Alexander Broadbent
 */
public interface IPrecedence {

    //Uses C/C++ operator precidence
    //http://en.wikipedia.org/wiki/Operators_in_C_and_C%2B%2B#Operator_precedence
    int SUFFIX_INC_OR_DEC       = 2;	// ++ --
    int PROPERTY_ACCESS         = 2;	// .
    int PREFIX_INC_OR_DEC       = 3;	// ++x or --x
    int SIGN                    = 3;	// +5 or -5
    int LOGICAL_UNARY           = 3;	// !
    int TYPE_CAST               = 3;	// (<type>)
//    int GEOMETRIC               = 5;	// *, /, %
//    int ARITHMETIC              = 6;	// +, -
    int INEQUALITY              = 8;	// <, <=, >, >=
    int EQUALITY                = 9;	// ==, !=
//    int LOGICAL_AND             = 13;	// &&
//    int LOGICAL_OR              = 14;	// ||
    int ASSIGNMENT              = 16;	// =, +=, -=, *=, /=
//    int ARG_SEPARATOR           = 18;	// ,

    //Precedence groups not in the C/C++ spec
    int ESCAPE                  = 0;	// To escape an operator
    int STRING                  = 7;	// String manipulation functions
    int BRACKET                 = 51;	// Brackets are implemented as operators


    // Simple
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

}
