package ru.zloyleshiy.pageobjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class YandexMainMenu extends AbstractPage {

        @NameOfElement("Yandex Logo")
        @FindBy(xpath = "//a[@class='home-link home-logo__link']")
        public SelenideElement yandexLogo;

}
