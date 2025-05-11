package io.testomat.e2e_tests_light_1;
import com.codeborne.selenide.SelenideElement;
import io.testomat.e2e_tests_light_1.utils.StringParsers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.testomat.e2e_tests_light_1.LoginPage.loginUser;
import static io.testomat.e2e_tests_light_1.MainPaige.*;
import static io.testomat.e2e_tests_light_1.ProjectPage.*;

public class ProjectsPageTests extends BaseTest {

    static String baseUrl = env.get("BASE_URL");
    static String userName = env.get("EMAIL");
    static String password = env.get("PASSWORD");
    String targetProjectName = "manufacture light";
    String testSuiteName = "First Test suite";

    @BeforeAll
    static void openTestomatAndLogin() {
        open(baseUrl);
        loginUser(userName, password);
    }

    @BeforeEach
    void openHomePage() {
        open(baseUrl);
    }

    @Test
    public void openingProjectWithTests() {
        searchForProject(targetProjectName);

        selectProject(targetProjectName);

        waitForProjectPageIsLoaded(targetProjectName);
    }

    @Test
    public void verificationOfProjectProperties() {
        searchForProject(targetProjectName);

        SelenideElement targetProject = countOfProjectsShouldBeEqualTo(1, targetProjectName).first();

        countOfTestCasesShouldBeEqualTo(targetProject, 0);

        numberOfProjectUsersGreaterThan(targetProject, 10);
    }

    @Test
    public void verificationOfUserStatus() {
        numberOfLeftDaysOfUserTrialPeriodShouldBeGreaterThan(1);
    }

    @Test
    public void creatingNewTestSuite() {
        searchForProject(targetProjectName);

        selectProject(targetProjectName);

        waitForProjectPageIsLoaded(targetProjectName);

        createNewTestSuite(testSuiteName);

        deleteTestSuite(testSuiteName);

        acceptDeletingSuite();
    }

    @Test
    public void verificationOfChat() {
        openChat();

        checkChatItems();

        closeChat();
    }

    @Test
    public void exampleParseDouble() {
        Assertions.assertEquals(4.4, StringParsers.parseDoubleFromString("Project rate: 4.4"));
    }

    @Test
    public void exampleParseInteger() {
        Assertions.assertEquals(15, StringParsers.parseIntegerFromString("Total users: 15"));
    }

    @Test
    public void exampleParseBoolean() {
        var value = "true";
        Assertions.assertEquals(true, Boolean.parseBoolean(value));
    }
}
