package Framework.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import Framework.wait.WaitJQueryAJAXCompleted;

public class ProductPage extends AbstractPage {
    private final String BASE_URL = "https://store.sony.ru";

    private String productUrl;

    @FindBy(xpath = "//*[contains(@class, 'product-top__cont')]//*[contains(@class, 'button-buy')]")
    private WebElement mainBuyButton;

    @FindBy(className = "add_compare")
    private WebElement compareLink;

    @FindBy(className = "to-fav")
    private WebElement toFavoriteLink;
    
    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public ProductPage openPage() {
        try{
            driver.get(BASE_URL + productUrl);

            logger.info("Opened ProductPage with " + productUrl);

            return this;

        } catch(Exception e) {
            logger.error("Could not open ProductPage\n", e);

            throw e;
        }
    }

    public ProductPage setProduct(String productUrl) {
        this.productUrl = productUrl;

        return this;
    }

    public ProductPage buy() {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        try{
            wait.until(ExpectedConditions.elementToBeClickable(mainBuyButton)).click();
            wait.until(WaitJQueryAJAXCompleted.jQueryAJAXCompleted());

            logger.info("Bought item at page " + productUrl);

            return this;

        } catch(Exception e) {
            logger.error("Could not click buy button at " + productUrl + " page\n", e);

            throw e;
        }
    }

    public ProductPage addToComparison() {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        try{
            wait.until(ExpectedConditions.elementToBeClickable(compareLink)).click();
            wait.until(WaitJQueryAJAXCompleted.jQueryAJAXCompleted());

            logger.info("Added item to comparison at " + productUrl);

            return this;

        } catch(Exception e) {
            logger.error("Could not add item to comparison at " + productUrl + "\n", e);

            throw e;
        }
    }

    public ProductPage addToFavorite() {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        try{
            wait.until(ExpectedConditions.visibilityOf(toFavoriteLink)).click();
            wait.until(WaitJQueryAJAXCompleted.jQueryAJAXCompleted());

            logger.info("Added item to favorite at " + productUrl);

            return this;

        } catch(Exception e) {
            logger.error("Could not add item to favorite at " + productUrl, e);

            throw e;
        }
    }

    @Override
    public ProductPage closeModalPopupIfPresent(boolean present) {
        super.closeModalPopupIfPresent(present);

        return this;
    }

    @Override
    public ProductPage closeCookiePopup() {
        super.closeCookiePopup();

        return this;
    }
}
