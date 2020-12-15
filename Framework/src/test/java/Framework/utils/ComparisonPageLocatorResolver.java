package Framework.utils;

import org.openqa.selenium.By;

public class ComparisonPageLocatorResolver {
    private final static String COMPARED_PARAMETER_LOCATOR_TEMPLATE = 
        "(//*[contains(@class,'item %s')])[%s]";

    public static By getComparedParameterLocator(String itemId, String rowNumber) {
        return By.xpath(String.format(COMPARED_PARAMETER_LOCATOR_TEMPLATE, itemId, rowNumber));
    }
}
