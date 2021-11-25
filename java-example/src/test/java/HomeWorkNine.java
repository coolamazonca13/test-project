import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeWorkNine {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @Test
    public void testCheckZoneState() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get(" http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        List<String> currentList = new ArrayList<String>();
        String mathElement = null;
        List<WebElement> lists = driver.findElements(By.xpath("//tr[@class='row']"));
        for (int element = 0; element < lists.size(); element++) {
            mathElement = lists.get(element).findElement(By.xpath(".//td[5]")).getText();
            currentList.add(mathElement);
            if ((Integer.parseInt(lists.get(element).findElement(By.xpath(".//td[6]")).getText())) > 0) {
                lists.get(element).findElement(By.xpath(".//td[5]/a")).click();
                String mathChieldElement = null;
                WebElement rootZone = driver.findElement(By.xpath("//table[@id='table-zones']"));
                List<WebElement> chieldZone = rootZone.findElements(By.xpath(".//tbody/tr"));
                List<String> currentChieldList = new ArrayList<String>();
                for (int i = 1; i < chieldZone.size() - 1; i++) {
                    mathChieldElement = chieldZone.get(i).findElement(By.xpath(".//td[3]")).getText();
                    currentChieldList.add(mathChieldElement);
                    System.out.println("1-> " + currentChieldList);
                    Collections.sort(currentChieldList);
                    System.out.println("2-> " + currentChieldList);
                    assert mathChieldElement != null & currentChieldList.get(currentChieldList.size() - 1).equals(mathChieldElement);
                }
                driver.findElement(By.name("cancel")).click();
                lists = driver.findElements(By.xpath("//tr[@class='row']"));
            }
            Collections.sort(currentList);
            assert mathElement != null & currentList.get(currentList.size() - 1).equals(mathElement);
        }
        stopDriver();
    }


    @Test
    public void testCheckGeoZone() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        List<WebElement> lists = driver.findElements(By.xpath("//tr[@class='row']"));
        for (int element = 0; element < lists.size(); element++) {
            lists.get(element).findElement(By.xpath(".//td[3]/a")).click();
            String mathChieldElement = null;
            WebElement rootZone = driver.findElement(By.xpath("//table[@id='table-zones']"));
            List<WebElement> chieldZone = rootZone.findElements(By.xpath(".//tbody/tr"));
            List<String> currentChieldList = new ArrayList<String>();
            for (int i = 1; i < chieldZone.size() - 1; i++) {
                mathChieldElement = chieldZone.get(i).findElement(By.xpath(".//td[3]/select/option[@selected=\"selected\"]")).getText();
                currentChieldList.add(mathChieldElement);
                System.out.println("1-> " + currentChieldList);
                Collections.sort(currentChieldList);
                System.out.println("2-> " + currentChieldList);
                assert mathChieldElement != null & currentChieldList.get(currentChieldList.size() - 1).equals(mathChieldElement);
            }
            driver.findElement(By.name("cancel")).click();
            lists = driver.findElements(By.xpath("//tr[@class='row']"));
        }
        stopDriver();
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
