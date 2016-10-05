thymeleaf-cdi
=============

An extension for Thymeleaf that allows referencing CDI named beans as
top-level objects in expressions. This works with the Standard Dialect
and its OGNL-based expression language - Spring users would probably not
be interested in CDI integration anyway.

Dependencies
------------

This extension is a bridge between the CDI standard and Thymeleaf; your
project should already contain both Thymeleaf and the CDI API.

The extension targets the CDI 1.2 standard. It may run on earlier versions
as well, but this was not tested. An implementation of the standard must
be provided by the environment or the application at runtime. (The extension
was tested using Weld 2.3.5, but any compliant implementation should work.)

The Thymeleaf side is written against the Thymeleaf 3 API. Earlier versions
cannot be reasonably supported due to the lack of lazy context variable
evaluation.

Usage
-----

1. Include the library in your project. For example with Maven:

    ```xml
        <dependency>
            <groupId>hu.inbuss</groupId>
            <artifactId>thymeleaf-cdi</artifactId>
            <version>0.0.9</version>
        </dependency>
    ```
    
    or with Gradle:

    ```gradle
    dependencies {
        compile 'hu.inbuss:thymeleaf-cdi:0.0.9'
    }
    ```

2.  When you invoke `TemplateEngine.process()`, you have to pass in an
    `IContext` object. Instead of constructing a new instance of Thymeleaf's
    `WebContext` class, get a reference to a `CDIWebContextFactory` bean
    (probably via dependency injection) and get a `CDIWebContext` instance
    from it. For example:

    ```java
    import hu.inbuss.thymeleaf.cdi.CDIWebContextFactory;
    import javax.inject.Inject;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import org.thymeleaf.TemplateEngine;
    import org.thymeleaf.context.IWebContext;
    
    public class Example extends HttpServlet {
        @Inject private CDIWebContextFactory ctxFactory;

        public void doGet(HttpServletRequest req, HttpServletResponse resp) {
            final IWebContext ctx = ctxFactory.create(req, resp);
            final TemplateEngine engine = /* ... */;
            engine.process(/* template name */, ctx, resp.getWriter());
        }
    }
    ```
    
    (If your project was using plain `Context` instead of `WebContext`, replace it
    with a `CDIContext` instance obtained from the `CDIContextFactory` bean.)

3. You're ready. Define CDI named beans, e.g.

    ```java
    @javax.enterprise.context.SessionScoped
    @javax.inject.Named("cart")
    public class ShoppingCart implements java.io.Serializable {
        public int getItemCount() {
            /* ... */     
        }
        /* ... */
    }
    ```
    
    then reference them from a template:
    
    ```html
    <span>You have <em th:text="${cart.itemCount}">many</em> products in your cart.</span>
    ```