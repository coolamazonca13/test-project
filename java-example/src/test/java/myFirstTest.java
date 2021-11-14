import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class myFirstTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Test
    public void testOpenWindows() {
        driver= new ChromeDriver();
        wait= new WebDriverWait(driver, 10);
        driver.get("https://www.metro-cc.ru/");
        driver.quit();
        driver= null;
    }
}
