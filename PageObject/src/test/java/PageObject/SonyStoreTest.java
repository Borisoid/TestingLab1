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
    public void severalItemsInCartTotalPriceTest() {
        int numberOfItemsToBuy = 2;

        SonyStoreMainPage page = new SonyStoreMainPage(driver);
        page.openPage()
            .findTotalPriceOfNFirstItemsForSale(numberOfItemsToBuy)
            .buyNFirstItemsForSale(numberOfItemsToBuy)
            .findTotalCartItemsPrice();

        Assert.assertEquals(
            page.getTotalPriceOfNFirstItemsForSale(), 
            page.getTotalCartItemsPrice()
        );
    }

    @Test
    public void zeroItemsInCartTest() {
        SonyStoreMainPage page = new SonyStoreMainPage(driver);
        page.openPage();

        Assert.assertTrue(page.isCartEmptyPromptShown());
        Assert.assertFalse(page.isOrderButtonAvailable());
    }
}
