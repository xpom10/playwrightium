package com.github.playwrightium;

import com.github.playwrightium.core.Extension;
import com.github.playwrightium.core.PageInvocationHandler;
import com.microsoft.playwright.Page;

import java.lang.reflect.Proxy;
import java.util.List;

public class Playwrightium {

    private final Page page;
    private final List<Extension> extensions;

    public Playwrightium(Page page, List<Extension> extensions) {
        this.page = page;
        this.extensions = extensions;
    }

    @SuppressWarnings("unchecked")
    public <T> T create(Class<T> pageClass) {
        return (T) Proxy.newProxyInstance(
                pageClass.getClassLoader(),
                new Class[]{pageClass},
                new PageInvocationHandler(page, extensions)
        );
    }

}