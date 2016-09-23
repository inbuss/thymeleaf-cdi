package com.github.inbuss.thymeleaf.cdi;

import java.util.Set;

/**
 * @author PÁLFALVI Tamás &lt;tamas.palfalvi@inbuss.hu&gt;
 */
public interface Variables {
    Object NOT_PRESENT = new Object();

    Set<String> keySet();
    Object get(String key);
}
