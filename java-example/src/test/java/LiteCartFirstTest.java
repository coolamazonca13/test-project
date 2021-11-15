import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LiteCartFirstTest {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @Test
    public void testOpenAdminWindows() {
        driver= new ChromeDriver();
        wait= new WebDriverWait(driver, 10);
        driver.get(" http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

    }
    @AfterAll
    public static void stopDriver(){
        System.out.println("End");
        driver.quit();
        driver= null;
    }
}
