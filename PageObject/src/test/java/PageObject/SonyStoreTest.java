package PageObject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import PageObject.PageObjects.SonyStoreMainPage;

public class SonyStoreTest {
    private WebDriver driver;

    @Before
    public void initialize() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-infobars");
        options.addArguments("start-maximized");
        options.addArguments("--disable-extensions");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
    }

    @After
    public void driverQuit() {
        driver.quit();
        driver = null;
    }

    @Test
    public void twoItemsInCartTest() {
        int itemsToBuy = 2;

        SonyStoreMainPage page = new SonyStoreMainPage(driver);
        page.openPage()
            .findTotalPriceOfNFirstItemsForSale(itemsToBuy)
            .buyNFirstItemsForSale(itemsToBuy)
            .findTotalCartItemsPrice();

        Assert.assertEquals(
            page.getTotalPriceOfNFirstItemsForSale(), 
            page.getTotalCartItemsPrice()
        );
    }
}
