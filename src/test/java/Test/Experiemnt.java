package Test;

import Browsers.Browsers;
import Browsers.LaunchBrowser;
import Page.GenerateCardPage;
import Utils.BrowserOps;
import Utils.UtilMethods;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Experiemnt extends Base {

    public WebDriver driver;

    @BeforeMethod
    public void setup() throws InterruptedException {
        launchBrowser = new LaunchBrowser();
        driver = launchBrowser.getSiteDeskTop(Browsers.FIREFOX);
    }

    @Test
    public void getNewCardNumber() throws InterruptedException {
       utilMethods = new UtilMethods(driver);
       utilMethods.generateCard();
       Thread.sleep(6000);
    }

    @AfterMethod
    public void tearDown() {
        browserOps = new BrowserOps(driver);
        browserOps.closeBrowser();
    }
}
