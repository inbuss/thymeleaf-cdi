package com.github.inbuss.thymeleaf.cdi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.thymeleaf.context.IContext;
import org.thymeleaf.context.IWebContext;

/**
 * @author PÁLFALVI Tamás &lt;tamas.palfalvi@inbuss.hu&gt;
 */
public interface IWebContextFactory {
    IWebContext create(IContext base, HttpServletRequest request, HttpServletResponse response);
}
