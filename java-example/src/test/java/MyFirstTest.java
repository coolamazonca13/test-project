import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyFirstTest {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @Test
    public void testOpenWindows() {
        driver= new ChromeDriver();
        wait= new WebDriverWait(driver, 10);
        driver.get("https://www.metro-cc.ru/");

    }
    @After
    public void stopDriver(){
        System.out.println("End");
        driver.quit();
        driver= null;
    }
}
