package ru.zloyleshiy.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import static com.codeborne.selenide.Selenide.*;

@RunWith(Cucumber.class)

@CucumberOptions(
        plugin = {"html:target/cucumber-report/smoketest", "json:target/cucumber.json"},
        features = "src/test/java/ru/zloyleshiy/features",
        glue = "ru/zloyleshiy/steps",
        tags = "@smoketest")

public class SmokeTest {


    @BeforeClass
    static public void startBrowser() {
        open("");
    }
}
