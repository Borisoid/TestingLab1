package Framework.utils;

import org.openqa.selenium.By;

public class CartPageElementLocatorResolver {
    private static String removeItemButtonLocatorTemplate =
        "(//*[contains(@class,'cart-popup')]" + 
        "//*[@class='cart-list'])[1]" + 
        "//*[@class='ico-delete trash'][%d]";

    private static String cartItemUrlLocatorTemplate =
        "(((//*[contains(@class,'cart-popup')]" + 
        "//*[@class='cart-list'])[1]" + 
        "//*[contains(@class,'cart-item ')])[%d]" + 
        "//a[contains(@href,'/product/')])[2]";

    private static String cartItemPriceLocatorTemplate =
        "((//*[contains(@class,'cart-popup')]" + 
        "//*[@class='cart-list'])[1]" + 
        "//*[contains(@class,'cart-item ')])[%d]" + 
        "//span[contains(@class,'price')]";

    private static String itemCountIncreaseButtonLocatorTemplate =
        "(((//*[contains(@class,'cart-popup')]" + 
        "//*[@class='cart-list'])[1]" + 
        "//*[contains(@class,'cart-item ')])[%d])" + 
        "//*[contains(@class,'ico-count-inc')]";

    private CartPageElementLocatorResolver() {}

    public static By getRemoveItemButtonLocator(int itemOrder) {
        return By.xpath(
                String.format(
                    removeItemButtonLocatorTemplate, 
                    itemOrder
            )
        );
    }

    public static By getItemUrlLocator(int itemOrder) {
        return By.xpath(
                String.format(
                    cartItemUrlLocatorTemplate, 
                    itemOrder
            )
        );
    }

    public static By getItemPriceLocator(int itemOrder) {
        return By.xpath(
                String.format(
                    cartItemPriceLocatorTemplate, 
                    itemOrder
            )
        );
    }

    public static By getItemCountIncreaseButtonLocator(int itemOrder) {
        return By.xpath(
                String.format(
                    itemCountIncreaseButtonLocatorTemplate, 
                    itemOrder
            )
        );
    }
}
