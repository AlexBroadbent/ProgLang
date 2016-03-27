package operator;

import eval.ICalculable;

import java.util.List;

/**
 * x++.operator
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public interface IOperator extends ICalculable {

    String getToken();

    int getNumOperands();

    Associativity getAssociativity();

    int getPrecedence();

    List<String> getAllowedExecutionTypes();

}
