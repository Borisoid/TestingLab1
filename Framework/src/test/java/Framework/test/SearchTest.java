package Framework.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import Framework.page.SearchPage;
import Framework.service.ItemCreator;
import Framework.service.TestDataReader;
import Framework.model.Item;

public class SearchTest extends CommonConditions{
    @Test
    public void searchTest() {
        logger.info("searchTest");

        Item expectedItem = ItemCreator.constructFromProperties(3);
        Item actualItem = 
            new SearchPage(driver)
                .openPage()
                .search(TestDataReader.getTestData("Framework.test.searchTest.searchRequest"))
                .getItem(expectedItem.getId());

        Assert.assertEquals(expectedItem, actualItem);
    }
}
