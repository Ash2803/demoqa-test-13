package test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FormTest {
    String name = "Ashot";
    String lastName = "Abazyan";
    String userEmail = "a.abazyan@bk.ru";
    String address1 = "Address1";

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void successfulTest() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        $("#firstName").setValue(name);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("1234567890");
        $("#dateOfBirth").click();
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("April");
        $(".react-datepicker__year-select").selectOption("1993");
        $(byText("28")).click();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#subjectsInput").sendKeys("E");
        $(byText("English")).click();
        $("#uploadPicture").uploadFromClasspath("image.jpg");
        $("#currentAddress").setValue(address1);
        $("#state").click();
        $(byText("Rajasthan")).click();
        $("#city").click();
        $(byText("Jaipur")).click();
        $("#submit").click();

        $(".modal-body").shouldHave(
                text("Ashot Abazyan"),
                text("a.abazyan@bk.ru"),
                text("Male"),
                text("1234567890"),
                text("28 March,1993"),
                text("English"),
                text("Reading"),
                text("image.jpg"),
                text("Address1"),
                text("Rajasthan Jaipur")
        );
    }
}
