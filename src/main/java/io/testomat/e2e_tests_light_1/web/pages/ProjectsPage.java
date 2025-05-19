package io.testomat.e2e_tests_light_1.web.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.testomat.e2e_tests_light_1.utils.StringParsers.parseIntegerFromString;

public class ProjectsPage {

    private final SelenideElement searchInput = $("#search");

    public void open() {
        Selenide.open("");
    }

    public void isLoaded() {
        searchInput.shouldBe(visible);
    }

    public void searchForProject(String targetProjectName) {
        searchInput.setValue(targetProjectName);
    }

    public void selectProject(String targetProjectName) {
        $(String.format("[title='%s']", targetProjectName)).click();
    }

    public void signInSuccess() {
        $("#user-menu-button").shouldBe(visible);
        $("#container .common-flash-success").shouldBe(visible);
    }

    public Integer getNumberOfProjectUsers(SelenideElement targetProject) {
        String countOfUsers = targetProject.$(" img + div").getText();
        return parseIntegerFromString(countOfUsers);
    }

    public Integer getCountOfTestCases(SelenideElement targetProject) {
        String countOfTests = targetProject.$(" p").getText();
        return parseIntegerFromString(countOfTests);
    }

    public Integer getNumberOfLeftDaysOfUserTrialPeriod() {
        $(".auth-header-nav-right span").shouldHave(text("Trial"));
        String trialPeriodLeft = $(".auth-header-nav-right span + span").getText();
        return parseIntegerFromString(trialPeriodLeft);

    }

    public ElementsCollection countOfProjectsShouldBeEqualTo(int expectedSize, String targetProjectName) {
        return $$(String.format("#grid ul li [title='%s']", targetProjectName)).filter(visible).shouldHave(CollectionCondition.size(expectedSize));
    }

    public void openChat() {
        $("[data-id=chat_closed] span").click();
    }

    public void checkChatItems() {
        $("[data-mode=chat] [data-mode=search]").shouldBe(visible);
        $("textarea[placeholder*='Отправьте сообщение']").shouldBe(visible);
    }

    public void closeChat() {
        $("[data-id=chat_opened] span").click();
        $("[data-id=chat_opened] span").shouldNotBe(visible);
    }
}
