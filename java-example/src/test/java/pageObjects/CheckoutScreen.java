package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CheckoutScreen {
    WebDriver driver;
    WebDriverWait wait;

    public CheckoutScreen(WebDriver webDriver, WebDriverWait webDriverWait){
        driver=webDriver;
        wait = webDriverWait;
    }

    public int getItemCount(){
        return driver.findElements(By.xpath("//td[@class='item']")).size();
    }

    public boolean checkTheCheckoutIsEmpty(){
        return isElementPresent(By.xpath("//em[text()='There are no items in your cart.']"),1);
    }

    public void clickDeleteItemAndWaitUpdate(int expectedCount){
        driver.findElements(By.xpath("//button[text()='Remove']")).get(0).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//td[@class='item']"), expectedCount));
    }

    public boolean isElementPresent(By locator, int waitTime) {
        try {
            driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
            return driver.findElements(locator).size() > 0;
        } finally {
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        }
    }
}
