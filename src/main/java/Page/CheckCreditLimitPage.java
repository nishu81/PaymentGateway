package Page;

import Utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;
import java.util.TreeMap;

public class CheckCreditLimitPage {

    WebDriver driver;
    Waits waits;
    HomePage homePage;

    public CheckCreditLimitPage(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        homePage = new HomePage(driver);
        PageFactory.initElements(driver, this);
    }

    public String getCheckCreditLimitPage() {
        String cardUrl = homePage.getHomePage().concat("check_credit_balance.php");
        return cardUrl;
    }

    @FindBy(xpath = "//input[@id='card_nmuber']")
    WebElement cardNumberTextArea;

    @FindBy(xpath = "//input[@value='submit']")
    WebElement buttonSubmit;

    @FindBy(xpath = "//h4[text()='Credit Card Balance ']")
    WebElement cardBalanceText;

    @FindBy(xpath = "//tr//th[text()='Card Number']/following::tbody/tr/td[1]")
    WebElement cardNumber;

    @FindBy(xpath = "//tr//th[text()='Amount']/following::tbody/tr/td[2]")
    WebElement spentAmount;

    @FindBy(xpath = "//tr//th[text()='Order Id']/following::tbody/tr/td[6]")
    WebElement orderID;


    //Gets credit card payment data information in a map
    public TreeMap<String, Object> getCreditLimitInfoMap() {
        TreeMap<String, Object> pMap = new TreeMap<String, Object>();
        if(driver.findElements(By.xpath("//h4[text()='Credit Card Balance ']")).size()==0) {

            pMap.put("Card Number", cardNumber.getText());
            pMap.put("Spent Amount", Double.parseDouble(spentAmount.getText()));
            pMap.put("Order Id", orderID.getText());

        }
        for (Map.Entry<String, Object> mm : pMap.entrySet()) {
            System.out.println(mm.getKey() + " " + mm.getValue());
        }
        return pMap;
    }
}
