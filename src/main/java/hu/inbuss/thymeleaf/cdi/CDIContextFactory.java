package hu.inbuss.thymeleaf.cdi;

import java.util.Locale;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import org.thymeleaf.context.IContext;

/**
 * @author PÁLFALVI Tamás &lt;tamas.palfalvi@inbuss.hu&gt;
 */
@Dependent
public class CDIContextFactory {
    private final BeanManager beanManager;

    @Inject protected CDIContextFactory(final BeanManager beanManager) {
        this.beanManager = beanManager;
    }

    public IContext create(final Locale locale) {
        return new CDIContext(locale, beanManager);
    }
}
