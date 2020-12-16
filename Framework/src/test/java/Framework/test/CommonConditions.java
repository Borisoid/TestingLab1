package Framework.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;

import Framework.utils.TestListener;
import Framework.driver.DriverSingleton;

@Listeners({TestListener.class})
public class CommonConditions {
    protected WebDriver driver;

    @BeforeMethod
    public void initialize() {
        driver = DriverSingleton.getDriver();
    }

    @AfterMethod
    public void cleanup() {
        DriverSingleton.closeDriver();
    }
}
