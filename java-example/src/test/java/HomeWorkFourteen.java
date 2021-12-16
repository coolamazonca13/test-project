import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class HomeWorkFourteen {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @Test
    public void testOpenCloseWindows() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.findElements(By.xpath("//tr[@class='row']")).get(0).findElement(By.xpath(".//td[5]/a")).click();
        for (WebElement item: driver.findElements(By.xpath("//a[./i[@class='fa fa-external-link']]"))) {
            String originalWindows = driver.getWindowHandle();
            Set<String> existingWindows = driver.getWindowHandles();
            item.click();
            String newWindows = wait.until(anyWindowsOtherThen(existingWindows));
            driver.switchTo().window(newWindows);
            driver.close();
            driver.switchTo().window(originalWindows);
        }
        driver.quit();


    }

    public ExpectedCondition<String> anyWindowsOtherThen(Set<String> oldWindows){
        return new ExpectedCondition<String>() {
            @Override
            public String apply(WebDriver driver) {
                Set<String> handles=driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size()>0?handles.iterator().next():null;
            }
        };
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
