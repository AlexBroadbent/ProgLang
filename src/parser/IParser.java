package parser;

import eval.ICalculable;
import eval.Variable;
import lexer.Token;
import model.Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * LazyLanguage.parser
 *
 * @version     01/12/2015
 * @author      Alexander Broadbent
 */
public interface IParser {

    List<ICalculable> parse(Domain domain, List<Token> tokens);

}
