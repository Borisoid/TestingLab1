package Framework.test;

import org.junit.Assert;
import org.junit.Test;

import Framework.page.SearchPage;
import Framework.service.ItemCreator;
import Framework.service.TestDataReader;
import Framework.model.Item;

public class SearchTest extends CommonConditions{
    @Test
    public void searchTest() {
        Item expectedItem = ItemCreator.constructFromProperties(3);
        Item actualItem = 
            new SearchPage(driver)
                .openPage()
                .search(TestDataReader.getTestData("Framework.test.searchTest.searchRequest"))
                .getItem(TestDataReader.getTestData("Framework.test.searchTest.expectedItemId"));

        Assert.assertEquals(expectedItem, actualItem);
    }
}
