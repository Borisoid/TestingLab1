package Framework.test;

import org.junit.Assert;
import org.junit.Test;

import Framework.page.LoginPageElement;
import Framework.page.MainPage;
import Framework.service.UserCreator;
import Framework.model.User;

public class LoginTest extends CommonConditions {
    @Test
    public void loginTest() {
        User user = UserCreator.constructFromProperties(1);

        new MainPage(driver).openPage();

        String loggedInUserName = 
            new LoginPageElement(driver)
                .openPage()
                .login(user)
                .getLoggedInUserName();

        Assert.assertEquals(
            user.getUsername(), 
            loggedInUserName
        );
    }
}
