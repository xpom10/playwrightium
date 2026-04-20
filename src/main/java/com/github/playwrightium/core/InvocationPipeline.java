package com.github.playwrightium.core;

import java.util.List;

public class InvocationPipeline {

    private final List<Extension> extensions;

    public InvocationPipeline(List<Extension> extensions) {
        this.extensions = extensions;
    }

    public Object execute(InvocationContext context, Invocation finalInvocation) {
        Invocation chain = finalInvocation;
        for (int i = extensions.size() - 1; i >= 0; i--) {
            Extension ext = extensions.get(i);
            Invocation next = chain;
            chain = () -> ext.invoke(context, next);
        }
        return chain.proceed();
    }
}