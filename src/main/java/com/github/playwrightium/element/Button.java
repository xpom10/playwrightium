package com.github.playwrightium.element;

import com.github.playwrightium.core.Extension;
import com.github.playwrightium.core.Target;
import com.microsoft.playwright.Page;

import java.util.List;

public class Button extends Element {

    public Button(Page page, Target target, List<Extension> extensions) {
        super(page, target, extensions);
    }

    public void click() {
        execute("click", () -> {
            locator().click();
            return null;
        });
    }

}
