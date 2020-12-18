package Framework.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractPage {
    protected final Logger logger = LogManager.getRootLogger();

	protected WebDriver driver;

	protected abstract AbstractPage openPage();
    protected final int WAIT_TIMEOUT_SECONDS = 10;
    
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
            logger.error("Expected cookie popup to appear, but it didn't.");
            throw e;
        }
        wait.until(
            ExpectedConditions.not(ExpectedConditions.visibilityOf(cookiePopupCloseButton))
        );

        return this;
    }

    // gets in the way as well
    public AbstractPage closeModalPopupIfPresent(boolean present) {
        if(!present) {
            return this;
        }

        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        try {
            wait.until(ExpectedConditions.visibilityOf(modalPopup));
            modalPopupGiftButton.click();
        } catch(TimeoutException e) {
            logger.error("Expected modal popup to appear, but it didn't.");
            throw e;
        }
        wait.until(ExpectedConditions.invisibilityOf(modalPopup));

        return this;
    }
}