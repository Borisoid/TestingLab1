package Framework.utils;

public class cartPageElementLocatorResolver {
    private static String removeItemButtonLocatorTemplate =
        "(//*[contains(@class,'cart-popup')]" + 
        "//*[@class='cart-list'])[1]" + 
        "//*[@class='ico-delete trash'][%d]";

    private cartPageElementLocatorResolver() {}

    public static String getRemoveItemButtonLocator(int itemOrder) {
        return String.format(
            removeItemButtonLocatorTemplate, 
            itemOrder
        );
    }
}
