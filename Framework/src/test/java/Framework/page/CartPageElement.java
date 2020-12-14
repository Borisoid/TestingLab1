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

import static Framework.utils.cartPageElementLocatorResolver.getRemoveItemButtonLocator;

public class CartPageElement extends AbstractPage {
    private final String cartListChildrenLocator = "//*[@class='cart-list']//*";
    
    @FindBy(xpath = "//*[contains(@class,'cart-link')]")
    private WebElement cartLink;

    public CartPageElement(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CartPageElement openPage() {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        wait.until(ExpectedConditions.visibilityOf(cartLink)).click();

        return this;
    }

    public boolean isEmpty() {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        try {
            wait.until(
                ExpectedConditions.invisibilityOfElementLocated(By.xpath(cartListChildrenLocator))
            );
            return true;
        } catch(TimeoutException e) {
            return false;
        }
    }

    public CartPageElement removeItem(int itemOrder) {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        WebElement removeItemButton = 
            wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(getRemoveItemButtonLocator(itemOrder))
                )
            );
        removeItemButton.click();
        wait.until(ExpectedConditions.invisibilityOf(removeItemButton));

        return this;
    }

    public CartPageElement removeItem() {
        return removeItem(1);
    }
}
