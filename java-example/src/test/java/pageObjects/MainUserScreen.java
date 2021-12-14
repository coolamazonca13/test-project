package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainUserScreen {
    private WebDriver driver;
    public MainUserScreen(WebDriver webDriver){
        driver=webDriver;
    }
    public void clickFirstDuck(){
        driver.findElements(By.xpath("//ul[@class='listing-wrapper products']/li")).get(0).click();
    }
    public void clickButtonCheckout(){
        driver.findElement(By.xpath("//a[contains(text(),'Checkout')]")).click();
    }
}
