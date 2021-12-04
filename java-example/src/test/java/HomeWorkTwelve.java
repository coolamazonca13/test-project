import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.corba.Bridge;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;

public class HomeWorkTwelve {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @Test
    public void testRegisterNewItem() throws InterruptedException, IOException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        String duckName = "";
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.findElement(By.xpath("//a[text()=' Add New Product']")).click();
        driver.findElement(By.xpath("//label[text()=' Enabled']")).click();
        duckName= "Duck " + timestamp.getTime();
        driver.findElement(By.name("name[en]")).sendKeys(duckName);
        driver.findElement(By.name("code")).sendKeys("123");
        driver.findElement(By.xpath("//input[@data-name='Rubber Ducks']")).click();
        driver.findElement(By.xpath("//input[@value='1-2']")).click();
        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys("10.99");
        driver.findElement(By.name("new_images[]")).sendKeys(getFullPathToFile("pic/cats.jpg"));
        driver.findElement(By.name("date_valid_from")).sendKeys("01.12.2021");
        driver.findElement(By.name("date_valid_to")).sendKeys("30.12.2021");
        openTab("Information");
        selectFromDropDownItem("manufacturer_id","ACME Corp.");
        driver.findElement(By.name("keywords")).sendKeys("test key words");
        driver.findElement(By.name("short_description[en]")).sendKeys("Shorts duck");
        driver.findElement(By.className("trumbowyg-editor")).sendKeys("Test description for duck");
        driver.findElement(By.name("head_title[en]")).sendKeys("head_title duck");
        driver.findElement(By.name("meta_description[en]")).sendKeys("meta_description duck");
        openTab("Prices");
        driver.findElement(By.name("purchase_price")).clear();
        driver.findElement(By.name("purchase_price")).sendKeys("9.45");
        selectFromDropDownItem("purchase_price_currency_code","US Dollars");
        driver.findElement(By.name("prices[USD]")).sendKeys("10.25");
        driver.findElement(By.name("prices[EUR]")).sendKeys("11.35");
        driver.findElement(By.name("save")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[text()='"+duckName+"']")).click();
        Thread.sleep(2000);
        stopDriver();
    }

    private void openTab(String tabName) throws InterruptedException {
        driver.findElement(By.xpath("//a[text()='"+tabName+"']")).click();
        Thread.sleep(2000);
    }

    private void selectFromDropDownItem(String selectName, String optionName){
        Select dropDown = new Select(driver.findElement(By.name(selectName)));
        dropDown.selectByVisibleText(optionName);
    }

    private String getFullPathToFile(String fileName) throws IOException {
        URL url = this.getClass()
                .getClassLoader()
                .getResource(fileName);
        assert url != null;
        File f = new File(url.getFile());
        System.out.println(f.getCanonicalFile());
        return String.valueOf(f.getCanonicalFile());
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
