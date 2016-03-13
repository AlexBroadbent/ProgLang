package model;

import com.google.common.collect.Maps;
import eval.Variable;

import java.util.Map;

/**
 * ProgLang.model
 *
 * @author Alexander Broadbent
 * @version 10/03/2016
 */
public class VarDomain {

    protected Domain parent;
    protected Map<String, Variable> variables;


    public VarDomain(Domain parent) {
        this.parent = parent;
        variables = Maps.newHashMap();
    }


    public boolean hasVariable(String name) {
        return variables.containsKey(name);
    }

    public Variable getVariable(String name) {
        if (variables.get(name) != null)
            return variables.get(name);
        return parent.getVariable(name);
    }

    public Variable getOrCreateVariable(String name) {
        if (hasVariable(name))
            return getVariable(name);

        if (parent.hasVariable(name)) {
            Variable var = parent.getVariable(name);
            variables.put(name, var);
            return var;
        }
        else {
            Variable var = parent.createVariable(name);
            variables.put(name, var);
            return var;
        }
    }

}
