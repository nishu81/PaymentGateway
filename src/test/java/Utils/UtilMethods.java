package Utils;

import Page.GenerateCardPage;
import Page.HomePage;
import Page.PaymentProcessPage;
import Test.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class UtilMethods extends Base {

    WebDriver driver;

    public UtilMethods(WebDriver driver){
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
    }
}
