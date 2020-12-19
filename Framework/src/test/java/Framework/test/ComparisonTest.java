package Framework.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import Framework.model.Item;
import Framework.page.ComparisonPage;
import Framework.page.ProductPage;
import Framework.service.ItemCreator;
import Framework.service.TestDataReader;

public class ComparisonTest extends CommonConditions {
    @Test
    public void compareItemsTest() {
        logger.info("compareItemsTest");

        String comparisonRow = 
            TestDataReader.getTestData("Framework.test.comparisonTest.compareItems.row");
        
        String firstItemExpectedComparedParameter = 
            TestDataReader.getTestData(
                "Framework.test.comparisonTest.compareItems.row.1.item.1.expectedValue"
            );
        String secondItemExpectedComparedParameter = 
            TestDataReader.getTestData(
                "Framework.test.comparisonTest.compareItems.row.1.item.2.expectedValue"
            );

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
            firstItemExpectedComparedParameter, 
            comparisonPage.getComparedParameter(firstItem.getId(), comparisonRow)
        );
        Assert.assertEquals(
            secondItemExpectedComparedParameter, 
            comparisonPage.getComparedParameter(secondItem.getId(), comparisonRow)
        );
    }
}
