import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class HomeWorkThirteen {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @Test
    public void testRegisterNewSale()  {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get(" http://localhost/litecart/en/");

        for (int i = 1; i < 4; i++) {
            driver.findElements(By.xpath("//ul[@class='listing-wrapper products']/li")).get(0).click();
            if (isElementPresent(By.xpath("//select"),1)) {
                selectFromDropDownItem("options[Size]", "Small");
            }
            driver.findElement(By.name("add_cart_product")).click();
            assert isElementPresent(By.xpath("//span[@class='quantity' and text()='" + i + "']"),10);
            driver.findElement(By.xpath("//a[text()='Home']")).click();
        }
        driver.findElement(By.xpath("//a[contains(text(),'Checkout')]")).click();
        int count = driver.findElements(By.xpath("//td[@class='item']")).size();
        while(!isElementPresent(By.xpath("//em[text()='There are no items in your cart.']"),1)){
            driver.findElements(By.xpath("//button[text()='Remove']")).get(0).click();
            wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//td[@class='item']"), count));
            count--;
        }
    }

    private void selectFromDropDownItem(String selectName, String optionName) {
        Select dropDown = new Select(driver.findElement(By.name(selectName)));
        dropDown.selectByVisibleText(optionName);
    }

    boolean isElementPresent(By locator, int waitTime) {
        try {
            driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
            return driver.findElements(locator).size() > 0;
        } finally {
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        }
    }

    @After
    public void stopDriver() {
        if (driver != null) {
            System.out.println("End");
            driver.quit();
            driver = null;
        }
    }
}