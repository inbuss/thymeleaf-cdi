package com.github.inbuss.thymeleaf.cdi;

import java.util.Locale;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.thymeleaf.context.IContext;
import org.thymeleaf.context.IWebContext;

/**
 * @author PÁLFALVI Tamás &lt;tamas.palfalvi@inbuss.hu&gt;
 */
public class WebContext implements IWebContext {
    private final IContext wrapped;
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final ServletContext servletContext;

    public WebContext(final IContext wrapped, final HttpServletRequest request, final HttpServletResponse response,
                      final ServletContext servletContext) {
        this.wrapped = wrapped;
        this.request = request;
        this.response = response;
        this.servletContext = servletContext;
    }

    /* IContext - forward to wrapped instance */

    @Override public Locale getLocale() {
        return wrapped.getLocale();
    }

    @Override public boolean containsVariable(final String name) {
        return wrapped.containsVariable(name);
    }

    @Override public Set<String> getVariableNames() {
        return wrapped.getVariableNames();
    }

    @Override public Object getVariable(final String name) {
        return wrapped.getVariable(name);
    }

    /* IWebContext */

    @Override public HttpServletRequest getRequest() {
        return request;
    }

    @Override public HttpServletResponse getResponse() {
        return response;
    }

    @Override public HttpSession getSession() {
        return request.getSession(false);
    }

    @Override public ServletContext getServletContext() {
        return servletContext;
    }
}
