package com.github.playwrightium;

import com.github.playwrightium.extension.LoggingExtension;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GithubTestIT {

    private static Playwright pw;
    private static Browser browser;
    private static Playwrightium pwm;

    @BeforeAll
    public static void setup() {
        pw = Playwright.create();
        browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        pwm = new Playwrightium(page, List.of(new LoggingExtension()));
        page.navigate("https://github.com/xpom10/playwrightium");
    }

    @AfterAll
    public static void tearDown() {
        browser.close();
        pw.close();
    }

    @Test
    public void test() {
        GithubPage githubPage = pwm.create(GithubPage.class);
        String text = githubPage.repoName().text();
        System.out.println(text);
        githubPage.typeToSearchButton().click();
        githubPage.searchInput().clean().fill("test");
    }
}
