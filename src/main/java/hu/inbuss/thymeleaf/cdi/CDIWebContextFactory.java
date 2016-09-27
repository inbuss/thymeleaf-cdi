package hu.inbuss.thymeleaf.cdi;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.thymeleaf.context.IWebContext;

/**
 * @author PÁLFALVI Tamás &lt;tamas.palfalvi@inbuss.hu&gt;
 */
@Dependent
public class CDIWebContextFactory {
    private final BeanManager beanManager;
    private final ServletContext servletContext;

    @Inject public CDIWebContextFactory(final BeanManager beanManager, final ServletContext servletContext) {
        this.beanManager = beanManager;
        this.servletContext = servletContext;
    }

    public IWebContext create(final HttpServletRequest request, final HttpServletResponse response) {
        return new CDIWebContext(request, response, beanManager, servletContext);
    }
}
