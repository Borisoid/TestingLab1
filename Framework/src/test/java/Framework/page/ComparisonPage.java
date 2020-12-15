package Framework.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import Framework.utils.ComparisonPageElementLocatorResolver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class ComparisonPage extends AbstractPage {
    private final String BASE_URL = "https://store.sony.ru/compare/";

    public ComparisonPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public ComparisonPage openPage() {
        driver.get(BASE_URL);

        return this;
    }

    public String getComparedParemeter(String itemId, String rowNumber) {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        return 
            wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    ComparisonPageElementLocatorResolver
                        .getComparedParameterLocator(itemId, rowNumber)
                )
            ).getText();
    }
}
