package Framework.page;

import org.openqa.selenium.WebDriver;

public class MainPage extends AbstractPage{
    private final String BASE_URL = "https://store.sony.ru";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MainPage openPage() {
        driver.get(BASE_URL);

        return this;
    }

    
}
