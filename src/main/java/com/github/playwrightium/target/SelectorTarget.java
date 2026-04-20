package com.github.playwrightium.target;


import com.github.playwrightium.core.Target;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SelectorTarget implements Target {

    private final String selector;

    public SelectorTarget(String selector) {
        this.selector = selector;
    }

    @Override
    public Locator resolve(Page page) {
        return page.locator(selector);
    }

}