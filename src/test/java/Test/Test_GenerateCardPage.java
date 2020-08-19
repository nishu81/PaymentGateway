package Test;

import Browsers.Browsers;
import Browsers.LaunchBrowser;
import Page.GenerateCardPage;
import Utils.BrowserOps;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Test_GenerateCardPage extends Base {

    public WebDriver driver;

    @BeforeMethod
    public void setup() throws InterruptedException {
        launchBrowser = new LaunchBrowser();
        driver = launchBrowser.getSiteDeskTop(Browsers.FIREFOX);
        generateCardPage = new GenerateCardPage(driver);
        driver.get(generateCardPage.getGenerateCardPage());
    }

    @Test
    public void getNewCardNumber() throws InterruptedException {
        generateCardPage = new GenerateCardPage(driver);
       // generateCardPage.getPaymentDataMap();
        generateCardPage.getCreditCardNumber(generateCardPage.getPaymentDataMap());
        generateCardPage.getCVVNumber(generateCardPage.getPaymentDataMap());
        generateCardPage.getMonthYear(generateCardPage.getPaymentDataMap());
    }

    @AfterMethod
    public void tearDown() {
        browserOps = new BrowserOps(driver);
        browserOps.closeBrowser();
    }
}
