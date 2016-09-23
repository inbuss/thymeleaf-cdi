package com.github.inbuss.thymeleaf.cdi;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import org.thymeleaf.context.IContext;

/**
 * @author PÁLFALVI Tamás &lt;tamas.palfalvi@inbuss.hu&gt;
 */
public class CDIContext implements IContext {
    private final Locale locale;
    private final Variables vars;
    private final Set<String> names;

    public CDIContext(final Locale locale, final CDIVariables vars) {
        this.locale = locale;
        this.vars = vars;
        names = new HashSet<String>(vars.keySet());
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    @Override
    public boolean containsVariable(final String name) {
        return names.contains(name);
    }

    @Override
    public Set<String> getVariableNames() {
        return names;
    }

    @Override
    public Object getVariable(String name) {
        final Object value = vars.get(name);
        if (value == Variables.NOT_PRESENT)
            return null;
        else
            return value;
    }
}
