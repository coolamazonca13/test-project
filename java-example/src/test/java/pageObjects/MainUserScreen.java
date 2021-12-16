package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainUserScreen {
    private WebDriver driver;
    private WebDriverWait wait;

    public MainUserScreen(WebDriver webDriver){
        driver=webDriver;
        wait = new WebDriverWait(driver, 10);
    }
    public void clickFirstDuck(){
        driver.findElements(By.xpath("//ul[@class='listing-wrapper products']/li")).get(0).click();
    }
    public void clickButtonCheckout(){
        driver.findElement(By.xpath("//a[contains(text(),'Checkout')]")).click();
    }
}
