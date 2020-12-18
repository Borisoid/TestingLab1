package Framework.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import Framework.wait.WaitJQueryAJAXCompleted;

public class SearchPage extends ItemContainerAbstractPage {
    private final String BASE_URL = "https://store.sony.ru/search/";

    @FindBy(xpath = "//div[@class='search_result_form']//input[@class='form-control']")
    private WebElement searchInputField;

    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public SearchPage openPage() {
        try{
            driver.get(BASE_URL);

            logger.info("Opened SearchPage");

            return this;

        } catch(Exception e) {
            logger.error("Could not open SearchPage\n" 
                + e.getMessage() + e.getStackTrace()
            );

            throw e;
        }
    }

    public SearchPage search(String searchRequest) {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        try{
            wait.until(ExpectedConditions.visibilityOf(searchInputField)).sendKeys(searchRequest, Keys.ENTER);
            wait.until(WaitJQueryAJAXCompleted.jQueryAJAXCompleted());

            logger.info("Made search request: " + searchRequest);

            return this;

        } catch(Exception e) {
            logger.error("Could nod make search request", e);

            throw e;
        }
    }
}
