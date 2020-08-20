package Page;

import Utils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmationPage {

        WebDriver driver;
        Waits waits;

        public OrderConfirmationPage(WebDriver driver) throws InterruptedException {
            this.driver = driver;
            waits = new Waits(driver);
            PageFactory.initElements(driver, this);
        }


        @FindBy(xpath = "//h2[text()='Payment successfull!']")
        WebElement paymentSuccessfullText;

        @FindBy(xpath = "(//h3/strong)[2]")
        WebElement orderID;

        public String getOrderId(){
            String oid = orderID.getText();
            System.out.println(" ORDER ID " + oid);
            return oid;
        }
    }
