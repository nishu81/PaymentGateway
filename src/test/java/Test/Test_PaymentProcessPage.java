package Test;

import Browsers.Browsers;
import Browsers.LaunchBrowser;
import Page.PaymentProcessPage;
import Page.ProductPage;
import Utils.BrowserOps;
import Utils.UtilMethods;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Test_PaymentProcessPage extends Base {

    public WebDriver driver;

    @BeforeClass
    public void creditCardSetup() throws InterruptedException {
        launchBrowser = new LaunchBrowser();
        driver = launchBrowser.getSiteDeskTop(Browsers.FIREFOX);
        utilMethods = new UtilMethods(driver);
        utilMethods.generateCard();
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
        /*Test_GenerateCardPage  test_generateCardPage = new Test_GenerateCardPage();
        test_generateCardPage.getNewCardNumber();*/
        paymentProcessPage = new PaymentProcessPage(driver);
        //paymentProcessPage.getCompletePaymentFields();
        paymentProcessPage.selectMonth();
    }

    @AfterMethod
    public void tearDown() {
        browserOps = new BrowserOps(driver);
        browserOps.closeBrowser();
    }
}
