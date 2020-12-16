package Framework.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        driver.get(BASE_URL + productUrl);

        return this;
    }

    public ProductPage setProduct(String productUrl) {
        this.productUrl = productUrl;

        return this;
    }

    public ProductPage buy() {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        wait.until(ExpectedConditions.elementToBeClickable(mainBuyButton)).click();

        return this;
    }

    public ProductPage addToComparison() {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        wait.until(ExpectedConditions.elementToBeClickable(compareLink)).click();

        return this;
    }

    public ProductPage addToFavorite() {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        wait.until(ExpectedConditions.visibilityOf(toFavoriteLink)).click();

        return this;
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
