package Framework.test;

import org.junit.Assert;
import org.junit.Test;

import Framework.page.CartPage;
import Framework.page.CartPageElement;
import Framework.page.ProductPage;

public class TestTest extends AbstractTest {

    @Test
    public void severalItemsInCartTotalPriceTest() {
        new ProductPage(driver)
            .setProduct("smartfon-xperia-1-ii-317261")
            .openPage()
            .buy()
            .closeModalPopupIfPresent(true)
            .setProduct("smartfon-xperia-1-316506")
            .openPage()
            .buy()
            .closeModalPopupIfPresent(false);

        Assert.assertEquals(
            new CartPage(driver).openPage().getItemsPrise(), 
            139980
        );
    }

    @Test
    public void cartItemDeletionTest() {
        // new MainPage(driver)
        //     .openPage();

        new ProductPage(driver)
            .setProduct("smartfon-xperia-1-ii-317261")
            .openPage()
            .buy()
            .closeModalPopupIfPresent(true)
            .setProduct("smartfon-xperia-1-316506")
            .openPage()
            .buy()
            .closeModalPopupIfPresent(false);

        Assert.assertTrue(
            new CartPageElement(driver)
                .openPage()
                .removeItem()
                .removeItem()
                .isEmpty()
        );
    }
}
