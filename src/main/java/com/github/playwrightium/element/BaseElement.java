package com.github.playwrightium.element;

import com.github.playwrightium.core.Extension;
import com.github.playwrightium.core.Target;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

public class BaseElement extends Element {

    public BaseElement(Page page, Target target, List<Extension> extensions) {
        super(page, target, extensions);
    }

    public void click() {
        execute("click", () -> {
            locator().click();
            return null;
        });
    }

    public String text() {
        return (String) execute("text", () -> locator().textContent());
    }

    public void input(String value) {
        execute("input", () -> {
            locator().fill(value);
            return null;
        });
    }
}