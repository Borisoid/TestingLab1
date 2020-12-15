package Framework.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import Framework.model.Item;
import Framework.utils.SearchPageLocatorResolver;
import Framework.service.TestDataReader;

public class SearchPage extends AbstractPage{
    private final String BASE_URL = "https://store.sony.ru/search/";

    @FindBy(xpath = "//div[@class='search_result_form']//input[@class='form-control']")
    private WebElement searchInputField;

    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public SearchPage openPage() {
        driver.get(BASE_URL);

        return this;
    }

    public SearchPage search(String searchRequest) {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        wait.until(ExpectedConditions.visibilityOf(searchInputField)).sendKeys(searchRequest, Keys.ENTER);

        return this;
    }

    public Item getItem(String productId) {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        String url =
            wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    SearchPageLocatorResolver.getElementContainingUrlLocator(productId)
                )
            ).getAttribute("href")
            .replace(TestDataReader.getTestData("Framework.test.site.prefix"), "");

        String price =
            wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    SearchPageLocatorResolver.getItemPriceLocator(productId)
                )
            ).getText().replaceAll("[^\\d]", "");

        return new Item(url, Integer.parseInt(price), false);
    }
}
