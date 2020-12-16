package Framework.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import Framework.page.LoginPageElement;
import Framework.page.MainPage;
import Framework.page.TownSelectionPageElement;
import Framework.service.TestDataReader;
import Framework.service.UserCreator;
import Framework.model.User;

public class UserDataTest extends CommonConditions {
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

    @Test
    public void setTownTest() {
        String inputTownName = 
            TestDataReader.getTestData("Framework.test.userDataTest.setTownTest.inputValue");
        String expectedTownName =
            TestDataReader.getTestData("Framework.test.userDataTest.setTownTest.expectedValue");
        
        new MainPage(driver).openPage();

        Assert.assertEquals(
            expectedTownName, 
            new TownSelectionPageElement(driver)
                .openPage()
                .selectTown(inputTownName)
                .getSelectedTown()
        );
    }
}
