package Framework.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import Framework.utils.ComparisonPageLocatorResolver;

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
        try{
            driver.get(BASE_URL);

            logger.info("Opened ComparisonPage");

            return this;
        } catch(Exception e) {
            logger.error("Could not open ComparisonPage" , e);

            throw e;
        }
    }

    public String getComparedParameter(String itemId, String rowNumber) {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        try{
            String comparedParameter =
                wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                        ComparisonPageLocatorResolver
                            .getComparedParameterLocator(itemId, rowNumber)
                    )
                ).getText();

            logger.info("Got item[" + itemId + "] "
                + "comparison parameter at row [" + rowNumber + "] : " 
                + comparedParameter);

            return comparedParameter;

        } catch(Exception e) {
            logger.error("Could not get item[" + itemId + "] " 
                + "comparison parameter at row [" + rowNumber + "]",
                e
            );

            throw e;
        }
    }
}
