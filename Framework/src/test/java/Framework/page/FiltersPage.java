package Framework.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import Framework.utils.FiltersPageLocatorResolver;
import Framework.wait.WaitJQueryAJAXCompleted;

public class FiltersPage extends ItemContainerAbstractPage {
    private final String BASE_URL = "https://store.sony.ru";

    private String pageUrl;

    public FiltersPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public FiltersPage setPage(String pageUrl) {
        this.pageUrl = pageUrl;

        logger.info("Filters page URL set to [" + pageUrl + "]");

        return this;
    }

    public FiltersPage openPage() {
        try{
            driver.get(BASE_URL + pageUrl);

            logger.info("Opened FiltersPage");

            return this;

        } catch(Exception e) {
            logger.error("Could not open FiltersPage" , e);

            throw e;
        }
    }

    @Override
    public FiltersPage closeCookiePopup() {
        super.closeCookiePopup();

        return this;
    }

    public FiltersPage clickCheckBox(String label) {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        try{
            wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    FiltersPageLocatorResolver.getFillterCheckboxLocator(label)
                )
            ).click();
            wait.until(WaitJQueryAJAXCompleted.jQueryAJAXCompleted());

            logger.info("Clicked checkbox labeled [" + label + "] on Filters page");
            
            return this;

        } catch(Exception e) {
            logger.error("Could not click checkbox labeled [" + label + "]" , e);

            throw e;
        }
    }
}
