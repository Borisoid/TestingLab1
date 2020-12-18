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
import Framework.service.TestDataReader;

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

        try{
            if(!driver.getCurrentUrl().contains(
                TestDataReader.getTestData("Framework.test.site.prefix")
                )
            ) {
                new MainPage(driver).openPage();
            }

            wait.until(ExpectedConditions.visibilityOf(loginLink)).click();
            wait.until(ExpectedConditions.visibilityOf(loginPopup));

            logger.info("Opened LoginPageElement");

            return this;

        } catch(Exception e) {
            logger.error("Could not open LoginPageElement" , e);

            throw e;
        }
    }

    public void closePage() {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        try{
            wait.until(ExpectedConditions.visibilityOf(loginLink)).click();
            wait.until(ExpectedConditions.invisibilityOf(loginPopup));

            logger.info("Closed LoginPageElement");

        } catch(Exception e) {
            logger.error("Could not close LoginPageElement" , e);
            
            throw e;
        }
    }

    public LoginPageElement login(User user) {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        try{
            wait.until(ExpectedConditions.visibilityOf(emailInputField)).sendKeys(user.getEmail());
            wait.until(ExpectedConditions.visibilityOf(passwodrInputField)).sendKeys(user.getPassword());
            wait.until(ExpectedConditions.visibilityOf(passwodrInputField)).sendKeys(Keys.ENTER);

            logger.info("Entered user credentials.  " 
                + "Email: " + user.getEmail() 
                + " Password: " + user.getPassword()
            );

            return this;

        } catch(Exception e) {
            logger.error("Could not enter user cradentials" , e);

            throw e;
        }
    }
    
    public String getLoggedInUserName() {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        try{
            String loggedInUserName 
                = wait.until(ExpectedConditions.visibilityOf(loginLink)).getText();

            logger.info("Got logged in username: " + loggedInUserName);

            return loggedInUserName;

        } catch(Exception e) {
            logger.error("Could not get logged in username" , e);

            throw e;
        }
    }
}
