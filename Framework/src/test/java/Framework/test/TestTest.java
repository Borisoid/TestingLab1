package Framework.test;

import java.lang.Thread;

import org.junit.Assert;
import org.junit.Test;

import Framework.page.CartPage;
import Framework.page.ProductPage;

public class TestTest extends AbstractTest {

    @Test
    public void Test1() throws InterruptedException {
        ProductPage productPage = new ProductPage(driver);
        productPage
            .setProduct("smartfon-xperia-1-ii-317261")
            .openPage()
            .buy()
            .closeModalPopup()
            .setProduct("smartfon-xperia-1-316506")
            .openPage()
            .buy();

        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.openPage().getItemsPrise(), 139980);
    }
}
