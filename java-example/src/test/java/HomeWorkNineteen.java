import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.CheckoutScreen;
import pageObjects.ItemDetailsScreen;
import pageObjects.MainUserScreen;

public class HomeWorkNineteen {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @Test
    public void testRegisterNewSale() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get(" http://localhost/litecart/en/");
        MainUserScreen mainUserScreen = new MainUserScreen(driver);
        ItemDetailsScreen itemDetailsScreen = new ItemDetailsScreen(driver);
        CheckoutScreen checkoutScreen = new CheckoutScreen(driver, wait);

        for (int i = 1; i < 4; i++) {
            mainUserScreen.clickFirstDuck();
            itemDetailsScreen.addDuckToBasket();
            itemDetailsScreen.checkBasketValuesIsChanged(i);
            itemDetailsScreen.clickButtonBackToHome();
        }
        mainUserScreen.clickButtonCheckout();

        int count = checkoutScreen.getItemCount();
        for (int i = count; i > 0; i--) {
            checkoutScreen.clickDeleteItemAndWaitUpdate(i);
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
