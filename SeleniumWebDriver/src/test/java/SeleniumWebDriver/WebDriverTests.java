package SeleniumWebDriver;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverTests {
    private WebDriver driver;

    @Before
    public void initialize() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-infobars");
        options.addArguments("start-maximized");
        options.addArguments("--disable-extensions");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
    }

    @After
    public void driverQuit() {
        driver.quit();
        driver = null;
    }

    @Test
    public void noItemsInCartTest() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://store.sony.ru");

        WebElement cart = driver.findElement(By.className("cart-link"));
        cart.click();

        WebElement cartSum = driver.findElement(By.className("cart-sum"));
        Assert.assertTrue(
            "Passes if cart is empty", 
            cartSum.getText().equals("Корзина пуста")
        );

        boolean couldPressOrdersButton;
        try {
            WebElement cartBuyButton = 
                driver.findElement(By.xpath(
                    "//form[@class='cart-buttons']//a[text()='Оформить заказ']"
                    )
                );
            cartBuyButton.click();
            couldPressOrdersButton = true;
        }
        catch(ElementNotInteractableException e) {
            couldPressOrdersButton = false;
        }

        Assert.assertFalse(
            "Passes if orders button couldn't be pressed", 
            couldPressOrdersButton
        );
    }
}
