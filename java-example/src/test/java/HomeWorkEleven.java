import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.sql.Timestamp;

public class HomeWorkEleven {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @Test
    public void testRegisterNewUser() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        String email = null;
        driver.get(" http://localhost/litecart/en/");
        driver.findElement(By.xpath("//a[contains(text(), 'New custom')]")).click();
        driver.findElement(By.name("firstname")).sendKeys("Anastasia");
        driver.findElement(By.name("lastname")).sendKeys("Leonova");
        driver.findElement(By.name("address1")).sendKeys("Lenina street 15");
        driver.findElement(By.name("postcode")).sendKeys("10002");
        driver.findElement(By.name("city")).sendKeys("New York");
        driver.findElement(By.cssSelector("span.select2-selection")).click();
        driver.findElement(By.cssSelector("input.select2-search__field")).sendKeys("United States", Keys.ENTER);
        email = timestamp.getTime() +"@testmail.com";
        System.out.println("Generated email: "+email);
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("phone")).sendKeys("+123456789");
        driver.findElement(By.name("password")).sendKeys("pass");
        driver.findElement(By.name("confirmed_password")).sendKeys("pass");
        driver.findElement(By.name("create_account")).click();
        driver.findElement(By.xpath("//a[contains(text(), 'Logout')]")).click();
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("pass");
        driver.findElement(By.name("login")).click();
        driver.findElement(By.xpath("//a[contains(text(), 'Logout')]")).click();
        stopDriver();
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
