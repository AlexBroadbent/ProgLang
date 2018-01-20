package uk.co.alexbroadbent.lexer;

import java.util.List;

/**
 * x++.parser
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public interface ILexer {

    List<Token> readAllTokens(String input) throws UnknownSequenceException;

}
