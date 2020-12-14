package PageObject.PageObjects;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
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

    //item has buy button, preorder button, sold out button and in-cart button
    //at the same time. 
    //all of them but currently active have class .hide
    private final String buyButtonsLocator = 
        "//*[contains(@class, 'item-button') and " + 
        "contains(@class, 'sale_button') and " + 
        "not(contains(@class, 'hide'))]" + 
        "/a[contains(@class, 'button-buy')]";

    private final String inCartButtonsLocator =
        "//*[contains(@class, 'item-button') and " + 
        "contains(@class, 'sale_button') and " + 
        "not(contains(@class, 'hide'))]" + 
        "//*[contains(@class, 'button-in_cart') and " + 
        "not(contains(@class, 'hide'))]";

    private final String cartTotalPriceLocator = 
        "//div[contains(@class, 'cart-popup-block')]" + 
        "//div[@class='cart-footer']" + 
        "//span[contains(@class, 'price')]";

    private final String cookiePopupCloseButtonLocator = 
        "//*[@id='_evh-ric-c']";

    private int _totalPriceOfNFirstItemsForSale;
    public int getTotalPriceOfNFirstItemsForSale() {
        return _totalPriceOfNFirstItemsForSale;
    }

    private int _totalCartItemsPrice;
    public int getTotalCartItemsPrice() {
        return _totalCartItemsPrice;
    }

    public SonyStoreMainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public SonyStoreMainPage openPage() {
        driver.get(pageUrl);
        
        closeCookiePopup();

        return this;
    }

    public SonyStoreMainPage findTotalPriceOfNFirstItemsForSale(int numberOfItems) {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        List<WebElement> priceSpans = 
            wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath(itemsForSalePricesLocator)
                )
            );

        int totalPrice = 0;
        for(int i = 0; i < numberOfItems; i++) {
            totalPrice += Integer.parseInt(priceSpans.get(i).getText().replace('"', ' ').trim() );
        }
        
        _totalPriceOfNFirstItemsForSale = totalPrice;

        return this;
    }

    public SonyStoreMainPage buyNFirstItemsForSale(int numberOfItemsToBuy) {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        List<WebElement> buyButtons = 
            wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath(buyButtonsLocator)
                )
            );
       
        for(int i = 0; i < numberOfItemsToBuy; i++) {
            WebElement currentButton = buyButtons.get(i);

            wait.until(ExpectedConditions.visibilityOf(currentButton)).click();

            wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.xpath(inCartButtonsLocator)
                )
            );
        }

        return this;
    }

    public SonyStoreMainPage findTotalCartItemsPrice() {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.className("cart-link")
            )
        ).click();

        _totalCartItemsPrice = Integer.parseInt(
            wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(cartTotalPriceLocator)
                )
            )
            .getText()
            .replace('p', ' ')
            .trim()
        );

        return this;
    }

    public boolean isCartEmptyPromptShown() {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.className("cart-link")
            )
        ).click();

        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.className("cart-sum")
            )
        )
        .getText()
        .equals("Корзина пуста");
    }

    public boolean isOrderButtonAvailable() {
        try {
            driver.findElement(
                By.xpath("//form[@class='cart-buttons']//a[text()='Оформить заказ']")
            ).click();

            return true;
        }
        catch(ElementNotInteractableException e) {
            return false;
        }
    }

    // it needs to be closed since it gets in the way 
    // when selenium tries to press buy buttons
    private void closeCookiePopup() {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath(cookiePopupCloseButtonLocator)
            )
        ).click();
        wait.until(
            ExpectedConditions.not(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(cookiePopupCloseButtonLocator)
                )
            )
        );
    }
}
