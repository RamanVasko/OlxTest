package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage {
    private WebDriver driver;

    private By akceptujeButton = By.xpath("//button[@id='onetrust-accept-btn-handler']");
    private By searchField = By.xpath("//input[@id='headerSearch']");
    private By firstElementName = By.xpath("(//div[@class='css-1sw7q4x'])[1]//h6");
    private By firstElementPrice = By.xpath("(//div[@class='css-1sw7q4x'])[1]//p[@data-testid=\"ad-price\"]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void closeCookiesWindow() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 1);
            wait.until(ExpectedConditions.visibilityOf(
                    driver.findElement(akceptujeButton)));
            driver.findElement(akceptujeButton).click();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to click on [Akceptuję] button" + e.getMessage());
        }
    }

    public void searchParameter(String parameter) {
        try {
            if (driver.findElement(searchField).isDisplayed()) {
                driver.findElement(searchField).clear();
                driver.findElement(searchField).sendKeys(parameter);
                driver.findElement(searchField).sendKeys(Keys.ENTER);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to search parameter" + e.getMessage());
        }
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void printFirstElementName() {
        scrollToWebElementJS(driver.findElement(firstElementName));
        System.out.println("firstElementName: " + driver.findElement(firstElementName).getText());
    }

    public void printFirstElementPrice() {
        System.out.println("firstElementPrice: " + driver.findElement(firstElementPrice).getText());
    }

    public WebElement scrollToWebElementJS(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }

    public boolean isPriceHigher500() {
        int price = Integer.parseInt(driver.findElement(firstElementPrice).getText().replaceAll("[^0-9]", ""));
        System.out.println("price: " + price);
        if (price < 500)
            return false;
        else
            return true;
    }
}
