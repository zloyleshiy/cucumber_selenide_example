package ru.zloyleshiy.steps;

import com.codeborne.selenide.Condition;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import ru.zloyleshiy.pageobjects.*;
import java.util.ArrayList;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StepDef {

    YandexMainMenu yandexMainMenu = page(YandexMainMenu.class);
    YandexMarketPage yandexMarketPage = page(YandexMarketPage.class);

    @Given("^open url '(.*)'$")
    public void openAnyUrl(String url) {
        if (url.contains("http") || url.contains("www.")) {
            open(url);
        } else {
            open("http://".concat(url));
        }
    }

    @And("^Yandex Logo is displayed$")
    public void yandexLogoShouldBeDisplayed() {
        yandexMainMenu.get("Yandex Logo").should(Condition.appear);
    }


    @And("^Market Logo is displayed$")
    public void yandexMarketLogoShouldBeDisplayed() {
        yandexMarketPage.get("Market Logo").should(Condition.appear);
    }

    @And("^URL contains '(.*)'$")
    public void urlShouldBeContains(String urlForCheck) {
        assertThat(url(), containsString(urlForCheck));
    }

    @When("^Hover mouse on element with text link '(.*)'$")
    public void hoverMouseOnElementByTextLink(String textLink) {
        $(By.linkText(textLink)).hover();
    }

    @When("^Click on element with text link '(.*)'$")
    public void clickOnElementByTextLink(String textLink) {
        $(By.linkText(textLink)).click();
    }

    @Then("^Check subtitle Yandex Market section is '(.*)'")
    public void checkSubtitleIs(String subtitle) {
        assertEquals(subtitle, yandexMarketPage.get("Subtitle").getText());
    }

    @When("^Choose a manufacturer with a name '(.*)'$")
    public void selectManufacturerByName(String manufacturer) {
        $(By.name("Производитель ".concat(manufacturer)))
                .shouldNotBe(selected)
                .shouldNotBe(checked)
                .parent()
                .click();
    }

    @And("^Set minimum price (\\d*) rubles$")
    public void setMinumumPrice(int price) {
        yandexMarketPage.get("Minimum Price").sendKeys(Integer.toString(price));
    }

    @And("^Set maximum price (\\d*) rubles$")
    public void setMaximumPrice(int price) {
        yandexMarketPage.get("Maximum Price").sendKeys(Integer.toString(price));
    }

    @And("^Order by '(по популярности|по цене|по рейтингу|по отзывам|по размеру скидки|по новизне)'$")
    public void orderByFilter(String filter) {
        yandexMarketPage.get("Filter block").find(By.linkText(filter)).click();
    }

    @Then("^Check price ordered by ascending$")
    public void checkPriceOrderedByAsc() {
        ArrayList<Integer> listOfPrices = yandexMarketPage.getListOfPriceIntValue();
        int minPrice = listOfPrices.get(0);
        for (int i = 1; i < listOfPrices.size(); i++) {
            assertThat("Check order price", minPrice <= listOfPrices.get(i));
            minPrice = listOfPrices.get(i);
        }
    }

    @And("^Check that the names of the products found contain '(.*)'")
    public void checkNamesContainText(String textForCheck) {
        ArrayList<String> listOfNames = yandexMarketPage.getListOfNamesStrValue();
        for (String currentName : listOfNames) {
            assertTrue("Check names '".concat(currentName).concat("' contain '").concat(textForCheck).concat("'"),
                    currentName.toLowerCase().contains(textForCheck.toLowerCase()));

        }

    }

    @And("^Make a pause (\\d*) second$")
    public void makeAPause(int second) {
        sleep(second * 1000);
    }

    @And("^Scroll Yandex Market page to last footer (?:end page)$")
    public void scrollYMPToLastFooter() {
        yandexMarketPage.get("Last footer").scrollTo();
    }
}
