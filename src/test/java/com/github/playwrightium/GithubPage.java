package com.github.playwrightium;

import com.github.playwrightium.annotation.Locator;
import com.github.playwrightium.annotation.LocatorParam;
import com.github.playwrightium.element.BaseElement;
import com.github.playwrightium.element.Button;
import com.github.playwrightium.element.Input;

public interface GithubPage {

    @Locator("//span[@data-content='${name}']")
    Button upperButton(@LocatorParam("name") String name);

    @Locator("//div[@id=\"repository-container-header\"]//a[@data-turbo-frame=\"repo-content-turbo-frame\"]")
    BaseElement repoName();

    @Locator("//button[@class=\"header-search-button placeholder input-button form-control d-flex flex-1 flex-self-stretch flex-items-center no-wrap width-full py-0 pl-2 pr-0 text-left border-0 box-shadow-none\"]")
    Button typeToSearchButton();

    @Locator("//*[@id=\"query-builder-test\"]")
    Input searchInput();

}
