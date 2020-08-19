package Page;

import Utils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class GenerateCardPage {

    WebDriver driver;
    Waits waits;
    HomePage homePage;

    public GenerateCardPage(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        homePage = new HomePage(driver);
        PageFactory.initElements(driver, this);
    }

    public String getGenerateCardPage() {
        String cardUrl = homePage.getHomePage().concat("cardnumber.php");
        return cardUrl;
    }

    @FindBy(xpath = "//h4[contains(text(),'Card Number:- ')]")
    WebElement cardNumber;

    @FindBy(xpath = "//h4[contains(text(),'CVV:')]")
    WebElement cardNumberCode;

    @FindBy(xpath = "//h4[contains(text(),'Exp:')]")
    WebElement cardNumberExpDate;

    @FindBy(xpath = "//h4/span")
    WebElement cardLimit;

    //Gets credit card data information in a map
    public TreeMap<String, Object> getPaymentDataMap() throws InterruptedException {
        TreeMap<String, Object> pMap = new TreeMap<String, Object>();
        Thread.sleep(4000);
        pMap.put("Card Number", cardNumber.getText().split("-")[1]);
        pMap.put("CVV", cardNumberCode.getText().split("-")[1]);
        pMap.put("Exp Date", cardNumberExpDate.getText().split("-")[1]);
        pMap.put("Limit", Double.parseDouble(cardLimit.getText().replace("$", "")));

       /* for (Map.Entry<String, Object> mm : pMap.entrySet()) {
            System.out.println(mm.getKey() + " " + mm.getValue());
        }*/
        return pMap;
    }

    public String getCreditCardNumber(TreeMap<String, Object> paymentMap) {
        String ccNum = paymentMap.get("Card Number").toString();
        System.out.println(ccNum);
        return ccNum;
    }

    public String getCVVNumber(TreeMap<String, Object> paymentMap) {
        String cvvNum = paymentMap.get("CVV").toString();
        System.out.println(cvvNum);
        return cvvNum;
    }

    public  ArrayList<String> getMonthYear(TreeMap<String, Object> paymentMap) {
        ArrayList<String> date = new ArrayList<String>();
        String[] dateYear = paymentMap.get("Exp Date").toString().split("/");
        date.add(dateYear[0]);
        date.add(dateYear[1]);
        System.out.println("Month " +  date.get(0) + " Year " + date.get(1));
        return date;
    }
}
