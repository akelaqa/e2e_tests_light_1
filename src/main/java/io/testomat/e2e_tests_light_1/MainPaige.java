package io.testomat.e2e_tests_light_1;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.testomat.e2e_tests_light_1.utils.StringParsers.parseIntegerFromString;

public class MainPaige {

    public static void searchForProject(String targetProjectName) {
        $("#search").setValue(targetProjectName);
    }

    public static void selectProject(String targetProjectName) {
        $(String.format("[title='%s']", targetProjectName)).click();
    }

    public static void waitForProjectPageIsLoaded(String targetProjectName) {
        $(".first h2").shouldHave(text(targetProjectName));
        $(".first [href]").shouldHave(text("Readme"));
    }

    public static void openChat() {
        $("[data-id=chat_closed] span").click();
    }

    public static void checkChatItems() {
        $("[data-mode=chat] [data-mode=search]").shouldBe(visible);
        $("textarea[placeholder*='Отправьте сообщение']").shouldBe(visible);
    }

    public static void closeChat() {
        $("[data-id=chat_opened] span").click();
        $("[data-id=chat_opened] span").shouldNotBe(visible);
    }

    public static void numberOfLeftDaysOfUserTrialPeriodShouldBeGreaterThan(int expectedNumberOfDays) {
        $(".auth-header-nav-right span").shouldHave(text("Trial"));
        String trialPeriodLeft = $(".auth-header-nav-right span + span").getText();
        Integer actualTrialPeriodLeft = parseIntegerFromString(trialPeriodLeft);
        Assertions.assertTrue(actualTrialPeriodLeft > expectedNumberOfDays, "Trial period ended. License update required");
    }

    public static void numberOfProjectUsersGreaterThan(SelenideElement targetProject, int expectedNumberOfUsers) {
        String countOfUsers = targetProject.$(" img + div").getText();
        Integer actualCountOfUsers = parseIntegerFromString(countOfUsers);
        Assertions.assertTrue(actualCountOfUsers > expectedNumberOfUsers, "Expected number of users are not matched with actual: " + actualCountOfUsers);
    }

    public static void countOfTestCasesShouldBeEqualTo(SelenideElement targetProject, int expectedCount) {
        String countOfTests = targetProject.$(" p").getText();
        Integer actualCountOfTests = parseIntegerFromString(countOfTests);
        Assertions.assertEquals(expectedCount, actualCountOfTests, "Expected number of test cases are not matched with actual: " + actualCountOfTests);
    }

    @NotNull
    public static ElementsCollection countOfProjectsShouldBeEqualTo(int expectedSize, String targetProjectName) {
        return $$(String.format("#grid ul li [title='%s']", targetProjectName)).filter(visible).shouldHave(CollectionCondition.size(expectedSize));
    }
}
