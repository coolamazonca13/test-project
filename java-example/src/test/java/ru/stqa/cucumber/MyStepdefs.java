package ru.stqa.cucumber;

import io.cucumber.java.AfterAll;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.CheckoutScreen;
import pageObjects.ItemDetailsScreen;
import pageObjects.MainUserScreen;

public class MyStepdefs {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @When("user open main form magazine")
    public void userOpenMainFormMagazine() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get(" http://localhost/litecart/en/");
    }

    @Then("user add 3 duck")
    public void userAddDuck() {
        ItemDetailsScreen itemDetailsScreen = new ItemDetailsScreen(driver);
        for (int i = 1; i < 4; i++) {
            new MainUserScreen(driver).clickFirstDuck();
            itemDetailsScreen.addDuckToBasket();
            itemDetailsScreen.checkBasketValuesIsChanged(i);
            itemDetailsScreen.clickButtonBackToHome();
        }
    }

    @Then("user del 3 duck")
    public void userDelDuck() {
        CheckoutScreen checkoutScreen = new CheckoutScreen(driver, wait);
        new MainUserScreen(driver).clickButtonCheckout();
        int count = checkoutScreen.getItemCount();
        while(!checkoutScreen.checkTheCheckoutIsEmpty()){
            checkoutScreen.clickDeleteItemAndWaitUpdate(count);
            count--;
        }
    }
    @AfterAll
    public static void stopDriver() {
        if (driver != null) {
            System.out.println("End");
            driver.quit();
            driver = null;
        }
    }
}
