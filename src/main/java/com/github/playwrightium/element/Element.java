package com.github.playwrightium.element;

import com.github.playwrightium.core.*;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.lang.reflect.Method;
import java.util.List;

public abstract class Element {

    protected final Page page;
    protected final Target target;
    protected final List<Extension> extensions;
    protected final InvocationPipeline pipeline;

    public Element(Page page, Target target, List<Extension> extensions) {
        this.page = page;
        this.target = target;
        this.extensions = extensions;
        this.pipeline = new InvocationPipeline(extensions);
    }

    public Locator rawLocator() {
        return locator();
    }

    protected Locator locator() {
        return target.resolve(page).first();
    }

    protected Object execute(String methodName, Invocation action) {
        return execute(methodName, action, new Class<?>[0]);
    }

    protected Object execute(String methodName, Invocation action, Class<?>... parameterTypes) {
        try {
            Method method = this.getClass().getMethod(methodName, parameterTypes);
            InvocationContext context = new InvocationContext(method);
            return pipeline.execute(context, action);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}
