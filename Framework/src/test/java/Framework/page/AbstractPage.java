package Framework.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
	protected WebDriver driver;

	protected abstract AbstractPage openPage();
    protected final int WAIT_TIMEOUT_SECONDS = 30;
    
    @FindBy(xpath = "//*[@id='_evh-ric-c']")
    private WebElement cookiePopupCloseButton;

    @FindBy(xpath = "//*[@id='myModal-317261-1384']//*[@class='modal-content']")
    private WebElement modalPopup;

    @FindBy(xpath = 
        "//*[@id='myModal-317261-1384']" + 
        "//*[@class='modal-content']" + 
        "//*[contains(@class, 'gift-select-button')][1]"
    )
    private WebElement modalPopupGiftButton;

    @FindBy(tagName = "body")
    private WebElement body;

	protected AbstractPage(WebDriver driver) {
		this.driver = driver;
    }

    // it needs to be closed since it gets in the way 
    // when selenium tries to press something in the very bottom of the window
    public AbstractPage closeCookiePopup() {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        try {
            wait.until(ExpectedConditions.visibilityOf(cookiePopupCloseButton)).click();
        } catch(TimeoutException e) {
            //no cookie popup - no action
        }
        wait.until(
            ExpectedConditions.not(ExpectedConditions.visibilityOf(cookiePopupCloseButton))
        );

        return this;
    }

    // gets in the way as well
    public AbstractPage closeModalPopup() {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        wait.until(ExpectedConditions.visibilityOf(modalPopup));
        modalPopupGiftButton.click();
        wait.until(ExpectedConditions.invisibilityOf(modalPopup));

        return this;
    }
}