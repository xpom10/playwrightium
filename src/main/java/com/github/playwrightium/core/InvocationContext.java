package com.github.playwrightium.core;

import java.lang.reflect.Method;

public class InvocationContext {
    private final Method method;

    public InvocationContext(Method method) {
        this.method = method;
    }

    public Method getMethod() {
        return method;
    }
}