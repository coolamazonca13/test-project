import com.sun.scenario.effect.impl.prism.PrImage;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeWorkTen {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private final static String MAIN_FORM_ITEM_NAME = "//div[@id='box-campaigns']/div/ul/li/a/div[@class='name']";
    private final static String MAIN_FORM_ITEM_LINK = "//div[@id='box-campaigns']/div/ul/li/a";
    private final static String MAIN_FORM_ITEM_PRICE = "//div[@id='box-campaigns']/div/ul/li/a/div[@class='price-wrapper']/S";
    private final static String MAIN_FORM_ITEM_SALE_PRICE = "//div[@id='box-campaigns']/div/ul/li/a/div[@class='price-wrapper']/strong";

    private final static String DETAIL_FORM_NAME = "//div[@id='box-product']/div/h1";
    private final static String DETAIL_FORM_PRICE = "//div[@id='box-product']/div[@class='content']/div[@class='information']/div[@class='price-wrapper']/s";
    private final static String DETAIL_FORM_SALE_PRICE = "//div[@id='box-product']/div[@class='content']/div[@class='information']/div[@class='price-wrapper']/strong";


    @Test
    public void testCheckNames()  {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get(" http://localhost/litecart/en/");
        String mainFormItemName = driver.findElement(By.xpath(MAIN_FORM_ITEM_NAME)).getText();
        driver.findElement(By.xpath(MAIN_FORM_ITEM_LINK)).click();
        String detailFormItemName = driver.findElement(By.xpath(DETAIL_FORM_NAME)).getText();
        assert detailFormItemName.equals(mainFormItemName);
        stopDriver();
    }

    @Test
    public void testCheckFullPrice()  {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get(" http://localhost/litecart/en/");
        String mainFormItemName = driver.findElement(By.xpath(MAIN_FORM_ITEM_PRICE)).getText();
        driver.findElement(By.xpath(MAIN_FORM_ITEM_LINK)).click();
        String detailFormItemName = driver.findElement(By.xpath(DETAIL_FORM_PRICE)).getText();
        assert detailFormItemName.equals(mainFormItemName);
        stopDriver();
    }

    @Test
    public void testCheckSalePrice()  {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get(" http://localhost/litecart/en/");
        String mainFormItemName = driver.findElement(By.xpath(MAIN_FORM_ITEM_SALE_PRICE)).getText();
        driver.findElement(By.xpath(MAIN_FORM_ITEM_LINK)).click();
        String detailFormItemName = driver.findElement(By.xpath(DETAIL_FORM_SALE_PRICE)).getText();
        assert detailFormItemName.equals(mainFormItemName);
        stopDriver();
    }

    @Test
    public void testCheckSalePriceByColor()  {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get(" http://localhost/litecart/en/");
        String mainFormItemColor = driver.findElement(By.xpath(MAIN_FORM_ITEM_SALE_PRICE)).getCssValue("color");
        driver.findElement(By.xpath(MAIN_FORM_ITEM_LINK)).click();
        String detailFormItemColor = driver.findElement(By.xpath(DETAIL_FORM_SALE_PRICE)).getCssValue("color");
        assert detailFormItemColor.equals(mainFormItemColor);
        assert checkRedColor(mainFormItemColor);
        assert checkRedColor(detailFormItemColor);
        stopDriver();
    }

    @Test
    public void testCheckFullPriceColorAndLine()  {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get(" http://localhost/litecart/en/");
        String mainFormItemColor = driver.findElement(By.xpath(MAIN_FORM_ITEM_PRICE)).getCssValue("color");
        String mainFormItemTextDecoration = driver.findElement(By.xpath(MAIN_FORM_ITEM_PRICE)).getCssValue("text-decoration");
        driver.findElement(By.xpath(MAIN_FORM_ITEM_LINK)).click();
        String detailFormItemColor = driver.findElement(By.xpath(DETAIL_FORM_PRICE)).getCssValue("color");
        String detailFormItemTextDecoration = driver.findElement(By.xpath(DETAIL_FORM_PRICE)).getCssValue("text-decoration");
//        assert detailFormItemColor.equals(mainFormItemColor); на детальной форме и главном экране цветовая маска не совпадает
        assert checkGrayColor(mainFormItemColor);
        assert checkGrayColor(detailFormItemColor);
        assert ((mainFormItemTextDecoration.contains("line-through")) & (detailFormItemTextDecoration.contains("line-through")));
        stopDriver();
//
    }

    @Test
    public void testCheckSalePriceSizeAndBoldMainForm()  {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get(" http://localhost/litecart/en/");
        String fullPrice = driver.findElement(By.xpath(MAIN_FORM_ITEM_PRICE)).getCssValue("font-size");
        float fPrice = Float.parseFloat(fullPrice.replace("px", ""));
        String salePrice = driver.findElement(By.xpath(MAIN_FORM_ITEM_SALE_PRICE)).getCssValue("font-size");
        String salePriceFontWeight = driver.findElement(By.xpath(MAIN_FORM_ITEM_SALE_PRICE)).getCssValue("font-weight");
        float sPrice = Float.parseFloat(salePrice.replace("px", ""));
        assert sPrice > fPrice;
        assert (salePriceFontWeight.equals("bold") || salePriceFontWeight.equals("700"));
        stopDriver();
    }

    @Test
    public void testCheckSalePriceSizeAndBoldDetailForm()  {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get(" http://localhost/litecart/en/");
        driver.findElement(By.xpath(MAIN_FORM_ITEM_LINK)).click();
        String fullPrice = driver.findElement(By.xpath(DETAIL_FORM_PRICE)).getCssValue("font-size");
        float fPrice = Float.parseFloat(fullPrice.replace("px", ""));
        String salePrice = driver.findElement(By.xpath(DETAIL_FORM_SALE_PRICE)).getCssValue("font-size");
        float sPrice = Float.parseFloat(salePrice.replace("px", ""));
        assert sPrice > fPrice;
        stopDriver();
    }


    boolean checkGrayColor(String color) {
        String[] numbers = color.replace("rgba(", "").replace(")", "").split(",");
        int r = Integer.parseInt(numbers[0].trim());
        int g = Integer.parseInt(numbers[1].trim());
        int b = Integer.parseInt(numbers[2].trim());
        if ((b == g) & (g == r)) {
            return true;
        } else {
            return false;
        }
    }

    boolean checkRedColor(String color) {
        String[] numbers = color.replace("rgba(", "").replace(")", "").split(",");
        int r = Integer.parseInt(numbers[0].trim());
        int g = Integer.parseInt(numbers[1].trim());
        int b = Integer.parseInt(numbers[2].trim());
        if ((b == 0) & (g == 0) & r != 0) {
            return true;
        } else {
            return false;
        }
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
