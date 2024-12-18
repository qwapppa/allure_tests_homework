
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class WebTest extends TestBase {

    @DisplayName("Проверка c помощью Listener")
    @Test
    void selenideIssueNameTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("");

        $(".header-search-button").click();
        $("#query-builder-test").sendKeys("eroshenkoam/allure-example");
        $("#query-builder-test").submit();

        $(linkText("eroshenkoam/allure-example")).click();
        $("#issues-tab").click();
        $(withText("с днем археолога!")).shouldBe(Condition.exist);
    }

    @DisplayName("Проверка названия с помощью Лямбда шагов")
    @Test
    void lambdaIssueNameTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открыть главную страницу гитхаб", () -> {
            open("");
        });
        step("Найти репозиторий " + "eroshenkoam/allure-example", () -> {
            $(".header-search-button").click();
            $("#query-builder-test").sendKeys("eroshenkoam/allure-example");
            $("#query-builder-test").submit();
        });
        step("Кликнуть по ссылке репозитория " + "eroshenkoam/allure-example", () -> {
            $(linkText("eroshenkoam/allure-example")).click();
        });
        step("Открыть таб Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверить наличие Issue с именем " + "с днем археолога!", () -> {
            $(withText("с днем археолога!")).should(Condition.exist);
        });

    }

    @DisplayName("Проверка названия с помощью шагов с аннотацией")
    @Test
    void annotationIssueNameTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository("eroshenkoam/allure-example");
        steps.clickOnRepositoryLink("eroshenkoam/allure-example");
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber("с днем археолога!");

    }
}
