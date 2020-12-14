package Framework.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends AbstractPage {
    private final String BASE_URL = "https://store.sony.ru/basket/orders";

    @FindBy(xpath = "//*[@class='cart-item']//*[@class='price']")
    private WebElement itemsPrise;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public CartPage openPage() {
        driver.get(BASE_URL);

        return this;
    }

    public int getItemsPrise() {
        return Integer.parseInt(itemsPrise.getText().replaceAll("[^\\d]", ""));
    }
}
