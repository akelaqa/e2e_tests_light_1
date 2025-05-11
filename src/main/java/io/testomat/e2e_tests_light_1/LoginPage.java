package io.testomat.e2e_tests_light_1;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public static void loginUser(String email, String password) {
        $("#content-desktop #user_email").setValue(email);
        $("#content-desktop #user_password").setValue(password);
        $("#content-desktop #user_remember_me").click();
        $("#content-desktop [name='commit']").click();
        $("#user-menu-button").shouldBe(visible);
        $(".common-flash-success").shouldBe(visible);
    }
}
