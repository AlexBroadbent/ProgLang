package parser;

import eval.ICalculable;
import lexer.Token;
import model.Domain;

import java.util.List;

/**
 * x++.parser
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public interface IParser {

    List<ICalculable> parse(Domain domain, List<Token> tokens) throws ParserException;

}
