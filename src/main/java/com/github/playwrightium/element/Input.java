package com.github.playwrightium.element;

import com.github.playwrightium.core.Extension;
import com.github.playwrightium.core.Target;
import com.microsoft.playwright.Page;

import java.util.List;

public class Input extends Element {

    public Input(Page page, Target target, List<Extension> extensions) {
        super(page, target, extensions);
    }

    public void fill(String value) {
        execute("fill", () -> {
            locator().fill(value);
            return value;
        }, String.class);
    }

    public String value() {
        return (String) execute("value", () -> locator().inputValue());
    }

    public Input clean() {
        return (Input) execute("clean", () -> {
            locator().clear();
            return this;
        });
    }
}
