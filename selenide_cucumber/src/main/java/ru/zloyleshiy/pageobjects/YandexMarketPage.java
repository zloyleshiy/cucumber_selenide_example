package ru.zloyleshiy.pageobjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class YandexMarketPage extends AbstractPage {

    @NameOfElement("Market Logo")
    @FindBy(xpath = "//a[@class='logo logo_type_link logo_part_market']")
    public SelenideElement marketLogo;


    @NameOfElement("Subtitle")
    @FindBy(xpath = "//h1[@class='title title_size_32 i-bem title_changeable_yes title_js_inited']")
    public SelenideElement subtitle;


    @NameOfElement("Minimum Price")
    @FindBy(name = "Цена от")
    public SelenideElement minimumPrice;

    @NameOfElement("Maximum Price")
    @FindBy(name = "Цена до")
    public SelenideElement maximumPrice;

    @NameOfElement("Filter block")
    @FindBy(xpath = "//div[@class='n-filter-block_pos_left i-bem']")
    public SelenideElement filterBlock;

    @NameOfElement("List of prices")
    @FindBys({@FindBy(xpath = "//div/a/div[@class='price']")})
    public List<SelenideElement> listOfPrices;

    @NameOfElement("List of names")
    @FindBys({@FindBy(xpath = "//div[@class='n-snippet-card2__title']/a")})
    public List<SelenideElement> listOfNames;

    @NameOfElement("Last footer")
    @FindBy(xpath = "//div[@class='footer-market__common footer-market__copyright']")
    public SelenideElement lastFooter;


    public ArrayList getListOfPriceIntValue() {
        ArrayList<Integer> listOfPriceIntValue = new ArrayList<Integer>();
        assertTrue("Laptop not found", listOfPrices.size() > 0);
        for (SelenideElement priceElement : listOfPrices) {
            String currentPriceStr = priceElement.getText();
            currentPriceStr = currentPriceStr.replace("от ", "");
            // \u20BD this is code of '₽' symbol
            currentPriceStr = currentPriceStr.replace(" \u20BD", "");
            currentPriceStr = currentPriceStr.replace(" ", "");
            listOfPriceIntValue.add(Integer.parseInt(currentPriceStr));
        }
        return listOfPriceIntValue;
    }

    public ArrayList getListOfNamesStrValue() {
        ArrayList<String> listOfNamesStrValue = new ArrayList<String>();
        assertTrue("Laptop not found", listOfNames.size() != 0);
        for (SelenideElement nameElement : listOfNames) {
            String currentNameStr = nameElement.getText();
            listOfNamesStrValue.add(currentNameStr);
        }
        return listOfNamesStrValue;
    }

}
