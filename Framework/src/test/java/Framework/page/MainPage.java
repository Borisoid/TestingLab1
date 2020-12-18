package Framework.page;

import org.openqa.selenium.WebDriver;

public class MainPage extends AbstractPage{
    private final String BASE_URL = "https://store.sony.ru";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MainPage openPage() {
        try{
            driver.get(BASE_URL);

            logger.info("Opened MainPage");

            return this;
            
        } catch(Exception e) {
            logger.error("Could not open MainPage" , e);
            
            throw e;
        }
    }
}
