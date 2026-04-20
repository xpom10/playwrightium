package com.github.playwrightium.core;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public interface Target {

    Locator resolve(Page page);

}