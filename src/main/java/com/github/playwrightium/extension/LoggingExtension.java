package com.github.playwrightium.extension;

import com.github.playwrightium.core.Extension;
import com.github.playwrightium.core.Invocation;
import com.github.playwrightium.core.InvocationContext;

public class LoggingExtension implements Extension {

    @Override
    public Object invoke(InvocationContext context, Invocation next) {
        System.out.println("→ " + context.getMethod().getName());
        Object result = next.proceed();
        System.out.println("← " + context.getMethod().getName());
        return result;
    }
}