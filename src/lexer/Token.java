package lexer;

/**
 * JavaCCProgrammingLanguage.parser
 *
 * @author Alexander Broadbent
 * @version 31/10/2015
 */
public class Token implements IToken {

    public final int    token;
    public final String sequence;


    public Token(int token, String sequence) {
        this.token = token;
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return sequence;
    }

}
