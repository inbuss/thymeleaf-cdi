package com.github.inbuss.thymeleaf.cdi;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.thymeleaf.context.IContext;
import org.thymeleaf.context.IWebContext;

/**
 * @author PÁLFALVI Tamás &lt;tamas.palfalvi@inbuss.hu&gt;
 */
public class WebContextFactory implements IWebContextFactory {
    private final ServletContext servletContext;

    @Inject public WebContextFactory(final ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override public IWebContext create(final IContext base, final HttpServletRequest request, final HttpServletResponse response) {
        return new WebContext(base, request, response, servletContext);
    }
}
