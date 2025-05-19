package io.testomat.e2e_tests_light_1.web.pages;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class ProjectPage {

    public void isLoaded(String targetProjectName) {
        $(".first h2").shouldHave(text(targetProjectName));
        $(".first [href]").shouldHave(text("Readme"));
    }

    public void createNewTestSuite(String testSuiteName) {
        $("#item-title").setValue(testSuiteName);
        $("[class*=md-icon-plus]").click();
        $("[class*=suites-list-content] a span").shouldHave(partialText(testSuiteName));
    }

    public void deleteTestSuite(String testSuiteName) {
        $("[class*=md-icon-checkbox-multiple-marked-outline]").click();
        $(byXpath(String.format("//a[contains(@href, '%s')]/preceding-sibling::span/input", testSuiteName.replace(" ", "-").toLowerCase()))).click();
        $("[class*=md-icon-delete]").click();
    }

    public void acceptDeletingSuite() {
        $("h3").shouldHave(partialText("Are you sure to delete these"));
        $("[class*=md-icon-delete]").click();
        $("[class*=suites-list-content] a span").shouldNotBe(visible);
    }
}
