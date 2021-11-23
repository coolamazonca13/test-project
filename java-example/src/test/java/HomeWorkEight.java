import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomeWorkEight {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @Test
    public void testCheckStickers() {
        driver= new ChromeDriver();
        wait= new WebDriverWait(driver, 10);
        driver.get(" http://localhost/litecart/en/");
        List<WebElement> lists = driver.findElements(By.cssSelector("li.product.column"));
        for (WebElement element:lists) {
            assert checkCountStickers(element, By.cssSelector("div.sticker"));
        }
    }

    boolean checkCountStickers(WebElement element, By locator){
        try {
            return element.findElements(locator).size() == 1;
        }catch (NoSuchElementException ex){
            return false;
        }
    }

    @AfterAll
    public static void stopDriver(){
        if (driver != null) {
            System.out.println("End");
            driver.quit();
            driver = null;
        }
    }
}
