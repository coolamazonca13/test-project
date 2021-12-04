import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.awt.windows.ThemeReader;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomeWorkThirteen {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @Test
    public void testRegisterNewSale() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get(" http://localhost/litecart/en/");

        driver.findElement(By.xpath("//a[@title='Purple Duck']")).click();
        driver.findElement(By.name("add_cart_product")).click();
        assert isElementPresent(By.xpath("//span[@class='quantity' and text()='1']"));
        driver.findElement(By.xpath("//a[text()='Home']")).click();
        driver.findElement(By.xpath("//a[@title='Blue Duck']")).click();
        driver.findElement(By.name("add_cart_product")).click();
        assert isElementPresent(By.xpath("//span[@class='quantity' and text()='2']"));
        driver.findElement(By.xpath("//a[text()='Home']")).click();
        driver.findElement(By.xpath("//a[@title='Green Duck']")).click();
        driver.findElement(By.name("add_cart_product")).click();
        assert isElementPresent(By.xpath("//span[@class='quantity' and text()='3']"));
        driver.findElement(By.xpath("//a[contains(text(),'Checkout')]")).click();
        driver.findElements(By.xpath("//button[text()='Remove']")).get(0).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//td[@class='item']"),2));
        driver.findElements(By.xpath("//button[text()='Remove']")).get(0).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//td[@class='item']"),1));
        driver.findElements(By.xpath("//button[text()='Remove']")).get(0).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//td[@class='item']"),0));
    }

    boolean isElementPresent(By locator) {
        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return driver.findElements(locator).size() > 0;
        } finally {
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        }
    }

    private int getCountFromUi(By locator) {
        return Integer.parseInt(driver.findElement(locator).getText());
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
