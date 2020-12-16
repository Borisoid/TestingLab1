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

        return this;
    }

    public FiltersPage openPage() {
        driver.get(BASE_URL + pageUrl);

        return this;
    }

    @Override
    public FiltersPage closeCookiePopup() {
        super.closeCookiePopup();

        return this;
    }

    public FiltersPage clickCheckBox(String label) {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                FiltersPageLocatorResolver.getFillterCheckboxLocator(label)
            )
        ).click();

        wait.until(WaitJQueryAJAXCompleted.jQueryAJAXCompleted());

        return this;
    }
}
