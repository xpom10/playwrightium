package com.github.playwrightium.core;

public interface Extension {
    Object invoke(InvocationContext context, Invocation next);
}