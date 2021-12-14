package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public void clickDeleteItemAndWaitUpdate(int expectedCount){
        driver.findElements(By.xpath("//button[text()='Remove']")).get(0).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//td[@class='item']"), expectedCount));
    }
}
