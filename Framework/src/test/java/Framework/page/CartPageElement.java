package Framework.page;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Framework.utils.CartPageElementLocatorResolver;
import Framework.wait.WaitJQueryAJAXCompleted;
import Framework.model.Item;
import Framework.service.TestDataReader;

public class CartPageElement extends AbstractPage {
    private final String cartListChildrenLocator = "//*[@class='cart-list']//*";
    
    @FindBy(xpath = "//*[contains(@class,'cart-link')]")
    private WebElement cartLink;

    @FindBy(xpath = "(//*[contains(@class,'cart-popup')])[3]")
    private WebElement cartPopup;

    public CartPageElement(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CartPageElement openPage() {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        try{
            if(!driver.getCurrentUrl().contains(
                TestDataReader.getTestData("Framework.test.site.prefix")
                )
            ) {
                new MainPage(driver).openPage();
            }

            wait.until(ExpectedConditions.visibilityOf(cartLink)).click();
            wait.until(ExpectedConditions.visibilityOf(cartPopup));

            logger.info("Opened CartPageElement");

            return this;

        } catch(Exception e) {
            logger.error("Could not open CartPageElement" , e);
            throw e;
        }
    }

    public void closePage() {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        try{
            wait.until(ExpectedConditions.visibilityOf(cartLink)).click();
            wait.until(ExpectedConditions.invisibilityOf(cartPopup));

            logger.info("CartPageElement closed");

        } catch(Exception e) {
            logger.error("Could not close CartPageElement" , e);
            throw e;
        }
    }

    public boolean isEmpty() {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        try {
            wait.until(
                ExpectedConditions.invisibilityOfElementLocated(By.xpath(cartListChildrenLocator))
            );

            logger.info("Cart is empty");

            return true;

        } catch(TimeoutException e) {
            logger.info("Cart is not empty");

            return false;
        }
    }

    public CartPageElement removeItem(int itemOrder) {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        try {
            WebElement removeItemButton = 
                wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                        CartPageElementLocatorResolver.getRemoveItemButtonLocator(itemOrder)
                    )
                );
            removeItemButton.click();
            wait.until(ExpectedConditions.invisibilityOf(removeItemButton));

            logger.info("Item [" + itemOrder + "] removed from cart");

            return this;

        } catch (Exception e) {
            logger.error("Could not remove item [" + itemOrder + "] from cart" , e);
            throw e;
        }
    }

    public CartPageElement removeItem() {
        return removeItem(1);
    }

    public CartPageElement itemCountIncrease(int itemOrder) {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        try {
            wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    CartPageElementLocatorResolver.getItemCountIncreaseButtonLocator(itemOrder)
                )
            ).click();
            wait.until(WaitJQueryAJAXCompleted.jQueryAJAXCompleted());

            logger.info("Count of item [" + itemOrder + "] increased");

            return this;

        } catch(Exception e) {
            logger.error("Could not increase count of item [" + itemOrder + "]" , e);
            throw e;
        }
    }

    public Item getItem(int itemOrder) {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        try{
            String url =
                wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                        CartPageElementLocatorResolver.getItemUrlLocator(itemOrder)
                    )
                ).getAttribute("href")
                .replace(TestDataReader.getTestData("Framework.test.site.prefix"), "");

            String price =
                wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                        CartPageElementLocatorResolver.getItemPriceLocator(itemOrder)
                    )
                ).getText().replaceAll("[^\\d]", "");

            logger.info("Got item URL: " + url + " Price: " + price);

            return new Item(url, Integer.parseInt(price));

        } catch(Exception e) {
            logger.error("Could not get item [" + itemOrder + "]");
            throw e;
        }       
    }
}
