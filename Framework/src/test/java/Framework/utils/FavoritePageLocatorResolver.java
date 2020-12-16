package Framework.utils;

import org.openqa.selenium.By;

public class FavoritePageLocatorResolver {
    private final static String ITEM_REMOVE_BUTTON_LOCATOR_TEMPLATE = 
        "//*[contains(@class,'row')]//*[contains(@rel,'%s')]//*[contains(@class,'close')]";

    public static By getRemoveItemButtonLocator(String id) {
        return 
            By.xpath(
                String.format(
                    ITEM_REMOVE_BUTTON_LOCATOR_TEMPLATE, 
                    id
                )
            );
    }

    public static By getRemoveItemButtonLocator() {
        return getRemoveItemButtonLocator("");
    }
}
