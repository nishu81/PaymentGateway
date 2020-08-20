package Test;

import Browsers.Browsers;
import Browsers.LaunchBrowser;
import Page.GenerateCardPage;
import Page.OrderConfirmationPage;
import Page.PaymentProcessPage;
import Page.ProductPage;
import Utils.BrowserOps;
import Utils.UtilMethods;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.TreeMap;

public class Test_OrderConfirmationPage extends Base {

    public WebDriver driver;

    TreeMap<String, Object> paymentMap;

   /* //@BeforeClass
    public void creditCardSetup() throws InterruptedException {
        launchBrowser = new LaunchBrowser();
        driver = launchBrowser.getSiteDeskTop(Browsers.FIREFOX);
        utilMethods = new UtilMethods(driver);
        utilMethods.generateCard();
        generateCardPage = new GenerateCardPage(driver);
        paymentMap = generateCardPage.getPaymentDataMap(); // ToDo: Null Pointer without this reference
    }*/

    @BeforeMethod
    public void setup() throws InterruptedException {
        launchBrowser = new LaunchBrowser();
        driver = launchBrowser.getSiteDeskTop(Browsers.FIREFOX);
        utilMethods = new UtilMethods(driver);
        utilMethods.generateCard();
        generateCardPage = new GenerateCardPage(driver);
        paymentMap = generateCardPage.getPaymentDataMap();
        //  launchBrowser = new LaunchBrowser();
        driver = launchBrowser.getSiteDeskTop(Browsers.FIREFOX);
        productPage = new ProductPage(driver);
        driver.get(productPage.getHomePage());
        productPage = new ProductPage(driver);
        productPage.getProdInfoMap("3");
        productPage.clickToProceed();
    }

    @Test
    public void  successfulPaymentTransaction() throws InterruptedException {
        paymentProcessPage = new PaymentProcessPage(driver);
        paymentProcessPage.getCompletePaymentFields(paymentMap);
        orderConfirmationPage = new OrderConfirmationPage(driver);
        orderConfirmationPage.getOrderId();
    }

    @AfterMethod
    public void tearDown() {
        browserOps = new BrowserOps(driver);
        browserOps.closeBrowser();
    }
}
