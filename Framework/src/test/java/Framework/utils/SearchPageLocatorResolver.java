package Framework.utils;

import org.openqa.selenium.By;

public class SearchPageLocatorResolver {
    public static By getElementContainingUrlLocator(String id) {
        return By.className("product_url_" + id);
    }
    public static By getItemPriceLocator(String id) {
        return 
            By.xpath(
                String.format(
                    "//div[@rel='%s']//div[@class='item-price']//span[@itemprop='price']", 
                    id
                )
            );
    }
    //div[@rel='317401']//div[@class='item-price']//span[@itemprop='price']
}
