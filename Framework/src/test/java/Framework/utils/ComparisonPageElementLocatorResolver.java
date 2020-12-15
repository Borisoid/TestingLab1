package Framework.utils;

import org.openqa.selenium.By;

public class ComparisonPageElementLocatorResolver {
    private final static String comparedParameterLocatorTemplate = 
        "(//*[contains(@class,'item %s')])[%s]";

    public static By getComparedParameterLocator(String itemId, String rowNumber) {
        return By.xpath(String.format(comparedParameterLocatorTemplate, itemId, rowNumber));
    }
}
