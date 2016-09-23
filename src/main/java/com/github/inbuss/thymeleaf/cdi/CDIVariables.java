package com.github.inbuss.thymeleaf.cdi;

import java.util.HashSet;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.AmbiguousResolutionException;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import org.thymeleaf.context.LazyContextVariable;

/**
 * @author PÁLFALVI Tamás &lt;tamas.palfalvi@inbuss.hu&gt;
 */
@ApplicationScoped
public class CDIVariables implements Variables {
    @Inject private BeanManager beanManager;

    @Override public Set<String> keySet() {
        final Set<String> res = new HashSet<String>();
        final Set<Bean<?>> beans = beanManager.getBeans(Object.class, new AnnotationLiteral<Any>() {});
        for (final Bean<?> b : beans) {
            final String name = b.getName();
            if (name != null)
                res.add(name);
        }
        return res;
    }

    @Override public Object get(String key) {
        final Set<Bean<?>> beans = beanManager.getBeans(key);
        if (beans.isEmpty())
            return NOT_PRESENT;

        return new LazyContextVariable<Object>() {
            @Override
            protected Object loadValue() {
                try {
                    final Bean<?> bean = beanManager.resolve(beans);
                    final CreationalContext<?> cctx = beanManager.createCreationalContext(bean);
                    return beanManager.getReference(bean, Object.class, cctx);
                } catch (final AmbiguousResolutionException are) {
                    return null;
                }
            }
        };
    }
}
