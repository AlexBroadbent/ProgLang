package eval;

import com.google.common.collect.Lists;
import model.Domain;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

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
            list.add(wrap(element));
        return list;
    }

    public boolean addAll(XList list) {
        return this.list.addAll(list.list);
    }

    public Literal get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean add(Literal literal) {
        return list.add(literal);
    }

    public XList subList(int fromIndex, int toIndex) {
        return new XList(list.subList(fromIndex, toIndex));
    }

    @Override
    public Iterator<Literal> iterator() {
        return list.iterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof XList))
            return false;

        XList other = (XList) obj;
        if (size() != other.size())
            return false;

        try {
            for (int i = 0; i < size(); i++)
                if (!Objects.equals(get(i).getValue(), other.get(i).getValue()))
                    return false;
            return true;
        }
        catch (NoValueException ex) {
            return false;
        }
    }

    @Override
    public String toString() {
        String out = "";

        for (int i = 0; i < list.size(); i++) {
            out += list.get(i) + (i < list.size() - 1 ? " -> " : "");
        }

        return out;
    }

}
