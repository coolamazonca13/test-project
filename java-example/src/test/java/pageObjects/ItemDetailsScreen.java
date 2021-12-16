package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ItemDetailsScreen {
    private WebDriver driver;
    private WebDriverWait wait;

    public ItemDetailsScreen(WebDriver webDriver) {
        driver = webDriver;
        wait = new WebDriverWait(driver, 10);
    }

    public void addDuckToBasket() {
        if (isElementPresent(By.xpath("//select"))) {
            selectFromDropDownItem("options[Size]", "Small");
        }
        driver.findElement(By.name("add_cart_product")).click();
    }

    public void checkBasketValuesIsChanged(int count){
        System.out.println( isElementPresent(By.xpath("//span[@class='quantity' and text()='" + count + "']")));
    }

    public void clickButtonBackToHome(){
        driver.findElement(By.xpath("//a[text()='Home']")).click();
    }

    private void selectFromDropDownItem(String selectName, String optionName) {
        Select dropDown = new Select(driver.findElement(By.name(selectName)));
        dropDown.selectByVisibleText(optionName);
    }

    boolean isElementPresent(By locator) {
        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return driver.findElements(locator).size() > 0;
        } finally {
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        }
    }
}
