package io.testomat.e2e_tests_light_1;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProjectsPageTests {

    @Test
    public void firstTest() {
        open("https://app.testomat.io/");

        //login user
        $("#content-desktop #user_email").setValue("smashnyyvova@gmail.com");
        $("#content-desktop #user_password").setValue("Tests240861V1!");
        $("#content-desktop #user_remember_me").click();
        $("#content-desktop [name='commit']").click();
        $("#user-menu-button").shouldBe(visible);
        $(".common-flash-success").shouldBe(visible);

        //search project
        $("#search").setValue("manufacture light");

        //select project
        $("[title='manufacture light']").click();

        //wait for project is loaded
        $("h2").shouldHave(text("manufacture light"));
        $("#ember40").shouldBe(visible);
    }
}
