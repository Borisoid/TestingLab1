package Framework.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import Framework.page.FiltersPage;
import Framework.service.ItemCreator;
import Framework.service.TestDataReader;
import Framework.model.Item;

public class FiltersTest extends CommonConditions {
    @Test
    public void filtersTest() {
        Item expectedItem1 = ItemCreator.constructFromProperties(5);
        Item expectedItem2 = ItemCreator.constructFromProperties(6);

        FiltersPage filtersPage = 
            new FiltersPage(driver)
                .setPage(
                    TestDataReader.getTestData(
                        "Framework.test.filtersTest.filtersTest.PageUrl"
                    )
                )
                .openPage()
                .closeCookiePopup()
                .clickCheckBox(
                    TestDataReader.getTestData(
                        "Framework.test.filtersTest.filtersTest.checkboxLabel"
                    )
                );
        
        Assert.assertEquals(expectedItem1, filtersPage.getItem(expectedItem1.getId()));
        Assert.assertEquals(expectedItem2, filtersPage.getItem(expectedItem2.getId()));
    }
}
