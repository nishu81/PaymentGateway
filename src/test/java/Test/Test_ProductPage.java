package Test;

import Browsers.Browsers;
import Browsers.LaunchBrowser;
import Page.GenerateCardPage;
import Page.PaymentProcessPage;
import Page.ProductPage;
import Utils.BrowserOps;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Test_ProductPage extends Base {

    public WebDriver driver;

    @BeforeMethod
    public void setup() throws InterruptedException {
        launchBrowser = new LaunchBrowser();
        driver = launchBrowser.getSiteDeskTop(Browsers.FIREFOX);
        productPage = new ProductPage(driver);
        driver.get(productPage.getHomePage());
    }

    @Test
    public void increaseQtyAndCheckout() throws InterruptedException {
        productPage = new ProductPage(driver);
        /*productPage.getItemPrice();
        productPage.selectItemQuantity("3");*/
        productPage.getProdInfoMap("3");
        productPage.clickToProceed();
        /*Thread.sleep(7000);
        paymentProcessPage = new PaymentProcessPage(driver);
        paymentProcessPage.paymentPageTotal();*/
    }

    @AfterMethod
    public void tearDown() {
        browserOps = new BrowserOps(driver);
        browserOps.closeBrowser();
    }
}

