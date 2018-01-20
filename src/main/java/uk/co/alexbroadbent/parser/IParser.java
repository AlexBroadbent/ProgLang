package uk.co.alexbroadbent.parser;

import uk.co.alexbroadbent.eval.ICalculable;
import uk.co.alexbroadbent.lexer.Token;
import uk.co.alexbroadbent.model.Domain;

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
