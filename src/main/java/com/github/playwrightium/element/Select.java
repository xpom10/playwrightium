package com.github.playwrightium.element;

import com.github.playwrightium.core.Extension;
import com.github.playwrightium.core.Target;
import com.microsoft.playwright.Page;

import java.util.List;

public class Select extends Element{

    public Select(Page page, Target target, List<Extension> extensions) {
        super(page, target, extensions);
    }
}
