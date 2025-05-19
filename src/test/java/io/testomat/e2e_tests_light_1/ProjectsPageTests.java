package io.testomat.e2e_tests_light_1;

import com.codeborne.selenide.SelenideElement;
import io.testomat.e2e_tests_light_1.utils.StringParsers;
import io.testomat.e2e_tests_light_1.web.pages.ProjectPage;
import io.testomat.e2e_tests_light_1.web.pages.ProjectsPage;
import io.testomat.e2e_tests_light_1.web.pages.SignInPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProjectsPageTests extends BaseTest {

    private static final ProjectsPage projectsPage = new ProjectsPage();
    private static final SignInPage signInPage = new SignInPage();
    private static final ProjectPage projectPage = new ProjectPage();
    static String userName = env.get("EMAIL");
    static String password = env.get("PASSWORD");
    String targetProjectName = "manufacture light";
    String testSuiteName = "First Test suite";

    @BeforeAll
    static void openTestomatAndLogin() {
        signInPage.open();
        signInPage.loginUser(userName, password);
        projectsPage.signInSuccess();
    }

    @BeforeEach
    void openProjectsPage() {
        projectsPage.open();
        projectsPage.isLoaded();
    }

    @Test
    public void openingProjectWithTests() {
        projectsPage.searchForProject(targetProjectName);
        projectsPage.selectProject(targetProjectName);
        projectPage.isLoaded(targetProjectName);
    }

    @Test
    public void verificationProjectProperties() {
        projectsPage.searchForProject(targetProjectName);
        SelenideElement targetProject = projectsPage.countOfProjectsShouldBeEqualTo(1, targetProjectName).first();
        Integer actualCountOfTests = projectsPage.getCountOfTestCases(targetProject);
        Assertions.assertEquals(0, actualCountOfTests,
                "Expected number of test cases are not matched with actual: " + actualCountOfTests);
        Integer actualCountOfUsers = projectsPage.getNumberOfProjectUsers(targetProject);
        Assertions.assertTrue(actualCountOfUsers > 10, "Expected number of users are not matched with actual: " + actualCountOfUsers);
    }

    @Test
    public void verificationOfUserStatus() {
        Assertions.assertTrue(projectsPage.getNumberOfLeftDaysOfUserTrialPeriod() > 1,
                "Trial period ended. License update required");
    }

    @Test
    public void creatingNewTestSuite() {
        projectsPage.searchForProject(targetProjectName);
        projectsPage.selectProject(targetProjectName);
        projectPage.isLoaded(targetProjectName);
        projectPage.createNewTestSuite(testSuiteName);
        projectPage.deleteTestSuite(testSuiteName);
        projectPage.acceptDeletingSuite();
    }

    @Test
    public void verificationOfChat() {
        projectsPage.openChat();
        projectsPage.checkChatItems();
        projectsPage.closeChat();
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
        Assertions.assertTrue(Boolean.parseBoolean(value));
    }
}
