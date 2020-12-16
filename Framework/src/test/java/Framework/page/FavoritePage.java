package Framework.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import Framework.utils.FavoritePageLocatorResolver;
import Framework.wait.WaitJQueryAJAXCompleted;

public class FavoritePage extends ItemContainerAbstractPage {
    private final String BASE_URL = "https://store.sony.ru/personal_cabinet/favorite/";

    

    public FavoritePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public FavoritePage openPage() {
        driver.get(BASE_URL);

        return this;
    }

    public FavoritePage removeItem (String id) {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                FavoritePageLocatorResolver.getRemoveItemButtonLocator(id)
            )
        ).click();

        wait.until(WaitJQueryAJAXCompleted.jQueryAJAXCompleted());

        return this;
    }

    public FavoritePage removeItem() {
        return removeItem("");
    }
}
