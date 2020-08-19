package Test;

import Browsers.Browsers;
import Browsers.LaunchBrowser;
import Page.GenerateCardPage;
import Page.PaymentProcessPage;
import Page.ProductPage;
import Utils.BrowserOps;
import Utils.UtilMethods;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.TreeMap;

public class Test_PaymentProcessPage extends Base {

    public WebDriver driver;
    TreeMap<String, Object> paymentMap;

    @BeforeClass
    public void creditCardSetup() throws InterruptedException {
        launchBrowser = new LaunchBrowser();
        driver = launchBrowser.getSiteDeskTop(Browsers.FIREFOX);
        utilMethods = new UtilMethods(driver);
        utilMethods.generateCard();
        generateCardPage = new GenerateCardPage(driver);
        paymentMap = generateCardPage.getPaymentDataMap(); // ToDo: Null Pointer without this reference
    }

    @BeforeMethod
    public void setup() throws InterruptedException {
        launchBrowser = new LaunchBrowser();
        driver = launchBrowser.getSiteDeskTop(Browsers.FIREFOX);
        productPage = new ProductPage(driver);
        driver.get(productPage.getHomePage());
        productPage = new ProductPage(driver);
        productPage.getProdInfoMap("3");
        productPage.clickToProceed();
    }

    @Test
    public void  verifyTotalAmount() throws InterruptedException {
        paymentProcessPage = new PaymentProcessPage(driver);
        int expectedTotal=paymentProcessPage.paymentPageTotal();
        Assert.assertEquals(expectedTotal,60);
    }

    @Test
    public void  successfulPaymentTransaction() throws InterruptedException {
        paymentProcessPage = new PaymentProcessPage(driver);
        paymentProcessPage.getCompletePaymentFields(paymentMap);

        Thread.sleep(6000);
    }

    @AfterMethod
    public void tearDown() {
        browserOps = new BrowserOps(driver);
        browserOps.closeBrowser();
    }

    @AfterClass
    public void tearDownAll() {
        browserOps = new BrowserOps(driver);
        browserOps.closeBrowser();
    }
}
