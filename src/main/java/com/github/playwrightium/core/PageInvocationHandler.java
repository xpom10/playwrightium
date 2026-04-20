package com.github.playwrightium.core;

import com.github.playwrightium.annotation.Locator;
import com.github.playwrightium.annotation.LocatorParam;
import com.github.playwrightium.target.SelectorTarget;
import com.microsoft.playwright.Page;

import java.lang.reflect.*;
import java.util.List;

public class PageInvocationHandler implements InvocationHandler {

    private final Page page;
    private final List<Extension> extensions;

    public PageInvocationHandler(Page page, List<Extension> extensions) {
        this.page = page;
        this.extensions = extensions;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        if (method.getDeclaringClass() == Object.class) {
            try {
                return method.invoke(this, args);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        Locator locator = method.getAnnotation(Locator.class);
        if (locator != null) {
            try {
                Class<?> returnType = method.getReturnType();
                Constructor<?> declaredConstructor = returnType.getDeclaredConstructor(Page.class, Target.class, List.class);
                return declaredConstructor.newInstance(page, new SelectorTarget(resolveTemplate(locator.value(), method, args)), extensions);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException("No supported annotation on method: " + method.getName());
    }

    private String resolveTemplate(String template, Method method, Object[] args) {
        if (args == null || args.length == 0) {
            return template;
        }
        Parameter[] parameters = method.getParameters();
        String result = template;
        for (int i = 0; i < parameters.length; i++) {
            LocatorParam annotation = parameters[i].getAnnotation(LocatorParam.class);
            if (annotation != null) {
                String name = annotation.value();
                Object value = args[i];
                result = result.replace("${" + name + "}", String.valueOf(value));
            }
        }
        return result;
    }
}