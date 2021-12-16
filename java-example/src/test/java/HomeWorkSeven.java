import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomeWorkSeven {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @Test
    public void testCheckAllTabAdminWindow() {
        driver= new ChromeDriver();
        wait= new WebDriverWait(driver, 10);
        driver.get(" http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        int count = driver.findElements(By.xpath(".//li[@id='app-']")).size();
        for (int i=0; i<count; i++){
            List<WebElement> lists = driver.findElements(By.xpath(".//li[@id='app-']"));
            lists.get(i).click();
            int chieldCount = driver.findElements(By.xpath(".//ul[@class='docs']/li/a")).size();
            for (int j=0; j<chieldCount; j++){
                List<WebElement> chieldList = driver.findElements(By.xpath(".//ul[@class='docs']/li/a"));
                chieldList.get(j).click();
                assert isElementPresent(driver, By.xpath(".//h1"));
            }
        }
    }

    boolean isElementPresent(WebDriver driver, By locator){
        try {
            driver.findElement(locator);
            return true;
        }catch (NoSuchElementException ex){
            return false;
        }
    }

    @After
    public void stopDriver(){
        if (driver != null) {
            System.out.println("End");
            driver.quit();
            driver = null;
        }
    }
}
