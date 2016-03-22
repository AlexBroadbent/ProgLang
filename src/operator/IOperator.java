package operator;

import eval.ICalculable;

import java.util.List;
import java.util.Stack;

/**
 * x++.operator
 *
 * @version     01/12/2015
 * @author      Alexander Broadbent
 */
public interface IOperator extends ICalculable {

    String getToken();

    int getNumOperands();

    Associativity getAssociativity();

    int getPrecedence();

    List<String> getAllowedExecutionTypes();

    boolean isValidContext(Stack<IOperator> operatorStack, List<ICalculable> infix, int position);

}
