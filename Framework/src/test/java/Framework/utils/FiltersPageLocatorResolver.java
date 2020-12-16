package Framework.utils;

import org.openqa.selenium.By;

public class FiltersPageLocatorResolver {
    private static final String FILTER_CHECKBOX_LOCATOR_TEMPLATE = 
        "//span[@class='text' and text()='%s']";

    public static By getFillterCheckboxLocator(String label) {
        return 
            By.xpath(String.format(FILTER_CHECKBOX_LOCATOR_TEMPLATE, label));
    }
}
