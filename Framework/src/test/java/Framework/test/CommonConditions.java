package Framework.test;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import Framework.driver.DriverSingleton;

public class CommonConditions {
    protected WebDriver driver;

    @Before
    public void initialize() {
        driver = DriverSingleton.getDriver();
    }

    @After
    public void cleanup() {
        DriverSingleton.closeDriver();
    }
}
