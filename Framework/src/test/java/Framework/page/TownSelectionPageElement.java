package Framework.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import Framework.wait.WaitJQueryAJAXCompleted;

import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.FindBy;

public class TownSelectionPageElement extends AbstractPage {
    @FindBy(id = "city-select-current-name")
    private WebElement townSelectionLink;

    @FindBy(id = "city-select-input")
    private WebElement townSelectionInput;

    @FindBy(id = "city-list")
    private WebElement townHints;

    public TownSelectionPageElement(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public TownSelectionPageElement openPage() {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        wait.until(ExpectedConditions.visibilityOf(townSelectionLink)).click();
        wait.until(ExpectedConditions.visibilityOf(townSelectionInput));

        return this;
    }

    public TownSelectionPageElement selectTown(String town) {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        wait.until(ExpectedConditions.visibilityOf(townSelectionInput)).sendKeys(town);
        wait.until(ExpectedConditions.visibilityOf(townHints));
        wait.until(ExpectedConditions.visibilityOf(townSelectionInput)).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.invisibilityOf(townSelectionInput));
        wait.until(WaitJQueryAJAXCompleted.jQueryAJAXCompleted());

        return this;
    }

    public String getSelectedTown() {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

        return wait.until(ExpectedConditions.visibilityOf(townSelectionLink)).getText(); 
    }
}
