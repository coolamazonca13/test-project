import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class myFirstTest {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @Test
    public void testOpenWindows() {
        driver= new ChromeDriver();
        wait= new WebDriverWait(driver, 10);
        driver.get("https://www.metro-cc.ru/");

    }
    @AfterAll
    public static void stopDriver(){
        System.out.println("End");
        driver.quit();
        driver= null;
    }
}
