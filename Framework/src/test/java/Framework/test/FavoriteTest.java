package Framework.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import Framework.page.FavoritePage;
import Framework.service.ItemCreator;
import Framework.model.Item;
import Framework.model.User;
import Framework.service.UserCreator;
import Framework.page.LoginPageElement;
import Framework.page.MainPage;
import Framework.page.ProductPage;

public class FavoriteTest extends CommonConditions {
    @Test
    public void FaforiteTest() {
        User user = UserCreator.constructFromProperties(1);
        Item expectedItem = ItemCreator.constructFromProperties(4);

        new MainPage(driver).openPage();

        new LoginPageElement(driver)
            .openPage()
            .login(user);

        new ProductPage(driver)
            .setProduct(expectedItem.getUrl())
            .openPage()
            .addToFavorite();

        FavoritePage favoritePage = new FavoritePage(driver).openPage();

        Item actualItem = favoritePage.getItem(expectedItem.getId());

        // fav items are saved on server, so i need to remove already added item
        // for test to keep working
        favoritePage.removeItem(expectedItem.getId());

        Assert.assertEquals(expectedItem, actualItem);
    }
}
