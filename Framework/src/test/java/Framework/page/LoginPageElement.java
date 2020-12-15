package Framework.page;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Keys;

import Framework.model.User;

public class LoginPageElement extends AbstractPage {

    @FindBy(xpath = "(//*[@class='username'])[1]")
    private WebElement loginLink;

    @FindBy(xpath = "//*[@class='popup-lk-in popup-dv']")
    private WebElement loginPopup;

    @FindBy(xpath = "(//input[@type='email'])[1]")
    private WebElement emailInputField;

    @FindBy(xpath = "(//input[@type='password'])[1]")
    private WebElement passwodrInputField;

    public LoginPageElement(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LoginPageElement openPage() {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        wait.until(ExpectedConditions.visibilityOf(loginLink)).click();
        wait.until(ExpectedConditions.visibilityOf(loginPopup));

        return this;
    }

    public void closePage() {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        wait.until(ExpectedConditions.visibilityOf(loginLink)).click();
        wait.until(ExpectedConditions.invisibilityOf(loginPopup));
    }

    public LoginPageElement login(User user) {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        wait.until(ExpectedConditions.visibilityOf(emailInputField)).sendKeys(user.getEmail());
        wait.until(ExpectedConditions.visibilityOf(passwodrInputField)).sendKeys(user.getPassword());
        wait.until(ExpectedConditions.visibilityOf(passwodrInputField)).sendKeys(Keys.ENTER);

        return this;
    }
    
    public String getLoggedInUserName() {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        return wait.until(ExpectedConditions.visibilityOf(loginLink)).getText();
    }
}
