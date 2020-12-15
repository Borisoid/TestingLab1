package Framework.test;

import org.junit.Assert;
import org.junit.Test;

import Framework.model.Item;
import Framework.page.CartPage;
import Framework.page.CartPageElement;
import Framework.page.ProductPage;
import Framework.service.ItemCreator;

public class CartTest extends CommonConditions {
    @Test
    public void severalItemsInCartTotalPriceTest() {
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
            firstItem.getPrice() + secondItem.getPrice()
        );
    }

    @Test
    public void incartAddTest() {
        Item item = ItemCreator.constructFromProperties(2);

        new ProductPage(driver)
            .setProduct(item.getUrl())
            .openPage()
            .buy();

        new CartPageElement(driver)
            .openPage()
            .itemCountIncrease(1)
            .closePage();

        Assert.assertEquals(
            new CartPage(driver).openPage().getItemsPrise(), 
            item.getPrice() * 2
        );
    }

    @Test
    public void cartItemDeletionTest() {
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

        Assert.assertTrue(
            new CartPageElement(driver)
                .openPage()
                .removeItem()
                .removeItem()
                .isEmpty()
        );
    }
}
