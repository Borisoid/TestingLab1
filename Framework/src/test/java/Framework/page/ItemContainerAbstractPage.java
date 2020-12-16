package Framework.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Framework.service.TestDataReader;
import Framework.utils.ItemElementLocatorResolver;
import Framework.model.Item;

public abstract class ItemContainerAbstractPage extends AbstractPage {
    public ItemContainerAbstractPage(WebDriver driver) {
        super(driver);
    }

    public Item getItem(String productId) {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        String url =
            wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    ItemElementLocatorResolver.getElementContainingUrlLocator(productId)
                )
            ).getAttribute("href")
            .replace(TestDataReader.getTestData("Framework.test.site.prefix"), "");

        String price =
            wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    ItemElementLocatorResolver.getItemPriceLocator(productId)
                )
            ).getText().replaceAll("[^\\d]", "");

        return new Item(url, Integer.parseInt(price), false);
    }
}
