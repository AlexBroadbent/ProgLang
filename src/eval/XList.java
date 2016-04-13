package eval;

import com.google.common.collect.Lists;
import model.Domain;

import java.util.Iterator;
import java.util.List;

/**
 * x++.eval
 * <p>
 * A class to be used when a literal is created with a LinkedList object as the data. This
 * provides a better testing
 *
 * @author Alexander Broadbent
 * @version 01/12/2015
 */
public class XList implements Iterable<Literal> {

    private List<Literal> list;


    public XList() {
        list = Lists.newLinkedList();
    }

    public XList(List<Literal> list) {
        this.list = list;
    }

    private static Literal wrap(Object object) {
        return Domain.wrapLiteral(object);
    }

    public static XList parse(Object... elements) {
        XList list = new XList();
        for (Object element : elements)
            list.push(wrap(element));
        return list;
    }

    public boolean push(Literal literal) {
        return list.add(literal);
    }

    public boolean pushAll(XList list) {
        return this.list.addAll(list.list);
    }

    public Literal pop() {
        Literal literal = list.get(0);
        list.remove(0);
        return literal;
    }

    public Literal get(int index) {
        return list.get(index);
    }

    public Literal peek() {
        return list.get(0);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public XList subList(int fromIndex, int toIndex) {
        return new XList(list.subList(fromIndex, toIndex));
    }

    @Override
    public Iterator<Literal> iterator() {
        return list.iterator();
    }

    @Override
    public String toString() {
        String out = "";

        for (Literal literal : list) {
            out += literal;
            if (list.indexOf(literal) != list.size() - 1) out += " -> ";
        }

        return out;
    }

}
