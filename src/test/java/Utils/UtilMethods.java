package Utils;

import Browsers.Browsers;
import Page.GenerateCardPage;
import Page.HomePage;
import Page.PaymentProcessPage;
import Page.ProductPage;
import Test.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.TreeMap;

public class UtilMethods extends Base {

    WebDriver driver;
   public static TreeMap<String, Object> paymentMap;  // Null because was not static

    public UtilMethods(WebDriver driver) throws InterruptedException {
        this.driver = driver;
        waits = new Waits(driver);
        generateCardPage = new GenerateCardPage(driver);
        PageFactory.initElements(driver, this);
    }

    public void generateCard() throws InterruptedException {
        generateCardPage = new GenerateCardPage(driver);
        driver.get(generateCardPage.getGenerateCardPage());
        generateCardPage = new GenerateCardPage(driver);
        generateCardPage.getPaymentDataMap();
        generateCardPage.getCreditCardNumber(generateCardPage.getPaymentDataMap());
        generateCardPage.getCVVNumber(generateCardPage.getPaymentDataMap());
        generateCardPage.getMonthYear(generateCardPage.getPaymentDataMap());
        generateCardPage = new GenerateCardPage(driver);
        paymentMap = generateCardPage.getPaymentDataMap();
    }

    public void placeOrder() throws InterruptedException {
        productPage = new ProductPage(driver);
        driver.get(productPage.getHomePage());
        productPage = new ProductPage(driver);
        productPage.getProdInfoMap("3");
        productPage.clickToProceed();
        paymentProcessPage = new PaymentProcessPage(driver);
        paymentProcessPage.getCompletePaymentFields(paymentMap);
    }
}
