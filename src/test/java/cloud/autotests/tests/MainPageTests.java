package cloud.autotests.tests;

import cloud.autotests.helpers.DriverUtils;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
//import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.SetValueOptions.withText;
import static com.codeborne.selenide.files.DownloadActions.click;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;


public class MainPageTests extends TestBase {
    @Test
    @DisplayName("Login modal form should appear on main page")
    void generatedTest() {
        step("Open https://habr.com/", () -> {
            open("https://habr.com/ru/feed/");
        });

        step("Click on Profile button", () -> {
            $x("//a[contains(text(),'Войти')]").click();
        });

        step("Login modal should be visible", () -> {
            $x("//*[contains(text(),'Вход')]").shouldBe(visible);
        });
    }

    @Test
    @DisplayName("Page title should have header text")
    void titleTest() {
        step("Open url 'https://habr.com/ru/feed/'", () ->
            open("https://habr.com/ru/feed/"));

        step("Page title should have text 'Публикации / Моя лента / Хабр'", () -> {
            String expectedTitle = "Публикации / Моя лента / Хабр";
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @DisplayName("Page console log should not have errors")
    void consoleShouldNotHaveErrorsTest() {
        step("Open url 'https://habr.com/'", () ->
            open("https://habr.com/"));

        step("Console logs should not contain text 'SEVERE'", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }
}