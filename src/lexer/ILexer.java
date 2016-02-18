package lexer;

import java.util.List;

/**
 * LazyLanguage.parser
 *
 * @version     01/12/2015
 * @author      Alexander Broadbent
 */
public interface ILexer {

    List<Token> readAllTokens(String input) throws UnknownSequenceException;

}
