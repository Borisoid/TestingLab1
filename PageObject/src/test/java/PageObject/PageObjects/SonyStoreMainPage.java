package PageObject.PageObjects;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SonyStoreMainPage extends AbstractPage {
    private String pageUrl = "https://store.sony.ru";

    private final String itemsForSalePricesLocator =
        "//*[contains(@class, 'item-button') and " + 
        "contains(@class, 'sale_button') and " + 
        "not(contains(@class, 'hide'))]" + 
        "/parent::div[contains(@class, 'pricebox')]" + 
        "//div[@class='item-price']" + 
        "//span[@itemprop='price']";

    //item may have sale button, preorder button or sold out button (has calss hide)
    private final String buyButtonsLocator = 
        "//*[contains(@class, 'item-button') and " + 
        "contains(@class, 'sale_button') and " + 
        "not(contains(@class, 'hide'))]";

    private final String cartTotalPriceLocator = 
        "//div[contains(@class, 'cart-popup-block')]" + 
        "//div[@class='cart-footer']" + 
        "//span[contains(@class, 'price')]";


    public SonyStoreMainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public SonyStoreMainPage openPage() {
        driver.get(pageUrl);
        
        return this;
    }

    public int getTotalPriceOfNFirstItemsForSale(int numberOfItems) {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        wait.until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath(itemsForSalePricesLocator)
            )
        );

        List<WebElement> priceSpan = driver.findElements(
            By.xpath(itemsForSalePricesLocator)
        );

        int totalPrice = 0;
        for(int i = 0; i < numberOfItems; i++) {
            totalPrice += Integer.parseInt(priceSpan.get(i).getText().replace('"', ' ').trim() );
        }
        
        return totalPrice;
    }

    public SonyStoreMainPage buyNFirstItemsForSale(int numberOfItemsToBuy) {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(buyButtonsLocator)));

        List<WebElement> buyButtons = driver.findElements(By.xpath(buyButtonsLocator));
       
        for(int i = 0; i < numberOfItemsToBuy; i++) {
            WebElement currentButton = buyButtons.get(i);

            Actions scrollAction = new Actions(driver);
            scrollAction.moveToElement(currentButton);
            scrollAction.perform();

            wait.until(ExpectedConditions.visibilityOf(currentButton));
            
            currentButton.click();
        }

        return this;
    }

    public int getTotalCartItemsPrice() {
        WebElement cart = driver.findElement(By.className("cart-link"));
        cart.click();

        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cartTotalPriceLocator)));

        return Integer.parseInt(
            driver.findElement(By.xpath(cartTotalPriceLocator))
            .getText()
            .replace('p', ' ')
        );
    }
}
