package Framework.test;

import org.junit.Assert;
import org.junit.Test;

import Framework.model.Item;
import Framework.page.ComparisonPage;
import Framework.page.ProductPage;
import Framework.service.ItemCreator;
import Framework.service.TestDataReader;

public class ComparisonTest extends CommonConditions {
    @Test
    public void compareItems() {
        Item firstItem = ItemCreator.constructFromProperties(1);
        Item secondItem = ItemCreator.constructFromProperties(2);

        new ProductPage(driver)
            .setProduct(secondItem.getUrl())
            .openPage()
            .addToComparison()
            .setProduct(firstItem.getUrl())
            .openPage()
            .addToComparison();
        
        ComparisonPage comparisonPage = new ComparisonPage(driver).openPage();

        Assert.assertEquals(
            TestDataReader.getTestData(
                "Framework.test.comparisonTest.compareItems.row.1.item.1.expectedValue"
            ), 
            comparisonPage.getComparedParemeter(
                firstItem.getId(), 
                TestDataReader.getTestData("Framework.test.comparisonTest.compareItems.row")
            )
        );
        Assert.assertEquals(
            TestDataReader.getTestData(
                "Framework.test.comparisonTest.compareItems.row.1.item.2.expectedValue"
            ), 
            comparisonPage.getComparedParemeter(
                secondItem.getId(), 
                TestDataReader.getTestData("Framework.test.comparisonTest.compareItems.row")
            )
        );
    }
}
