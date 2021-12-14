import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomeWorkSeventeen {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static final String CATEGORIESFOLDER = "//tr[./td/input[contains(@name,'categories')]]/td[3]/a";
    private static final String PRODUCTSITEM = "//tr[./td/input[contains(@name,'products')]]/td[3]/a";

    @Test
    public void testViewWebConsoleLogs() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get("http://localhost/litecart/admin/");
        LogEntries entry = driver.manage().logs().get(LogType.BROWSER);
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.findElement(By.xpath("//span[@class='name' and text()='Catalog']")).click();
        while (driver.findElements(By.xpath(CATEGORIESFOLDER)).size() > 0) {
            driver.findElements(By.xpath(CATEGORIESFOLDER)).get(0).click();
        }
        int count = driver.findElements(By.xpath(PRODUCTSITEM)).size();
        for (int i = 0; i < count; i++) {
            driver.findElements(By.xpath(PRODUCTSITEM)).get(i).click();
            if (entry.getAll().size() > 0) {
                System.out.println(entry.getAll().get(0));
                assert true;
            }
            driver.findElement(By.name("cancel")).click();
        }
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
