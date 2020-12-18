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
        try{
            driver.get(BASE_URL);

            logger.info("Opened CartPage");

            return this;

        } catch(Exception e) {
            logger.error("Could not open CartPage", e);

            throw e;
        }
    }

    public int getItemsPrise() {
        try{
            int itemsPrice = Integer.parseInt(itemsPrise.getText().replaceAll("[^\\d]", ""));

            logger.info("Got cart items price: " + itemsPrice);

            return itemsPrice;

        } catch(Exception e) {
            logger.error("Could not get items price on cart page" , e);

            throw e;
        }
    }
}
