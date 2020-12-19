package Framework.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import Framework.model.Item;
import Framework.page.CartPage;
import Framework.page.CartPageElement;
import Framework.page.ProductPage;
import Framework.service.ItemCreator;
import Framework.service.TestDataReader;

public class CartTest extends CommonConditions {
    @Test
    public void severalItemsInCartTotalPriceTest() {
        logger.info("severalItemsInCartTotalPriceTest");

        Item firstItem = ItemCreator.constructFromProperties(1);
        Item secondItem = ItemCreator.constructFromProperties(2);

        new ProductPage(driver)
            .setProduct(firstItem.getUrl())
            .openPage()
            .buy()
            .closeModalPopupIfPresent(firstItem.getGiftModalPopupExpected())
            .setProduct(secondItem.getUrl())
            .openPage()
            .buy()
            .closeModalPopupIfPresent(secondItem.getGiftModalPopupExpected());

        Assert.assertEquals(
            new CartPage(driver).openPage().getItemsPrise(), 
            Integer.parseInt(
                TestDataReader.getTestData(
                    "Framework.test.cartTest.severalItemsInCartTotalPriceTest.expectedTotalPrice"
                )
            )
        );
    }

    @Test
    public void itemsInCartAreSameAsWhatWereAddedTest() {
        logger.info("itemsInCartAreSameAsWhatWereAddedTest");

        Item firstExpectedItem = ItemCreator.constructFromProperties(1);
        Item secondExpectedItem = ItemCreator.constructFromProperties(2);

        CartPageElement cartPageElement = 
            new ProductPage(driver)
                .setProduct(firstExpectedItem.getUrl())
                .openPage()
                .buy()
                .closeModalPopupIfPresent(firstExpectedItem.getGiftModalPopupExpected())
                .setProduct(secondExpectedItem.getUrl())
                .openPage()
                .buy()
                .closeModalPopupIfPresent(secondExpectedItem.getGiftModalPopupExpected())
                .getCartPageElement()
                .openPage();

        Item firstItemInCart = cartPageElement.getItem(1);
        Item secondItemInCart = cartPageElement.getItem(2);

        Assert.assertEquals(firstExpectedItem, firstItemInCart);
        Assert.assertEquals(secondExpectedItem, secondItemInCart);
    }

    @Test
    public void incartAddTest() {
        logger.info("incartAddTest");

        Item item = ItemCreator.constructFromProperties(2);

        new ProductPage(driver)
            .setProduct(item.getUrl())
            .openPage()
            .buy()
            .getCartPageElement()
            .openPage()
            .itemCountIncrease(1)
            .closePage();

        Assert.assertEquals(
            new CartPage(driver).openPage().getItemsPrise(), 
            Integer.parseInt(
                TestDataReader.getTestData(
                    "Framework.test.cartTest.incartAddTest.expectedTotalPrice"
                )
            )
        );
    }

    @Test
    public void cartItemDeletionTest() {
        logger.info("cartItemDeletionTest");

        Item firstItem = ItemCreator.constructFromProperties(1);
        Item secondItem = ItemCreator.constructFromProperties(2);

        boolean isCartEmptyExpected = true;

        boolean isCartEmptyActual = 
            new ProductPage(driver)
                .setProduct(firstItem.getUrl())
                .openPage()
                .buy()
                .closeModalPopupIfPresent(firstItem.getGiftModalPopupExpected())
                .setProduct(secondItem.getUrl())
                .openPage()
                .buy()
                .closeModalPopupIfPresent(secondItem.getGiftModalPopupExpected())
                .getCartPageElement()
                .openPage()
                .removeItem()
                .removeItem()
                .isEmpty();

        Assert.assertEquals(isCartEmptyExpected, isCartEmptyActual);
    }
}
