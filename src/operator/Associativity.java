package operator;

/**
 * x++.operator
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public enum Associativity {

    NONE(0), LEFT_TO_RIGHT(1), RIGHT_TO_LEFT(2);

    private int id;

    Associativity(int id) {
        this.id = id;
    }

}
