package Page;

import Utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.TreeMap;

public class PaymentProcessPage {

    WebDriver driver;
    Waits waits;
    HomePage homePage;
    GenerateCardPage generateCardPage;


    public PaymentProcessPage(WebDriver driver) throws InterruptedException {
        this.driver = driver;
        waits = new Waits(driver);
        generateCardPage = new GenerateCardPage(driver);
        PageFactory.initElements(driver, this);
    }

 //ToDo: Common NullPointer causes
// Approach 1: TreeMap<String,Object> paymentMap = new TreeMap<String,Object>();
// Approach 2: TreeMap<String,Object> paymentMap = generateCardPage.getPaymentDataMap();
// Approach 3: TreeMap<String,Object> paymentMap;

    public String getPaymentProcessPage() {
        String paymentProcessUrl = homePage.getHomePage().concat("process_purchasetoy.php");
        return paymentProcessUrl;
    }

    @FindBy(xpath = "//font[text()='Pay Ammount']")
    WebElement paymentAmountText;

    @FindBy(xpath = "//input[@id='card_nmuber']")
    WebElement cardNumberField;

    @FindBy(xpath = "//input[@id='cvv_code']")
    WebElement cvvField;

    @FindBy(xpath = "//input[@name='submit']")
    WebElement buttonSubmit;

    //Credit Card
    public String getCardNumber(TreeMap<String,Object> paymentMap)  {
        String ccNum = generateCardPage.getCreditCardNumber(paymentMap);
        return ccNum;
    }

    //Select Month
    public void selectMonth(TreeMap<String,Object> paymentMap) throws InterruptedException {
        //String month = generateCardPage.getMonthYear(paymentMap).get(0); // Null Pointer
        String month = generateCardPage.getMonthYear(paymentMap).get(0);
        Select ccMonth = new Select(driver.findElement(By.xpath("//select[@id='month']")));
       ccMonth.selectByVisibleText(month.trim());
    }

    //Select Year
    public void selectYear(TreeMap<String,Object> paymentMap) {
        String year = generateCardPage.getMonthYear(paymentMap).get(1);
        Select ccYear = new Select(driver.findElement(By.xpath("//select[@id='year']")));
        ccYear.selectByValue(year);
    }

    //CVV number
    public String getCVVNumber(TreeMap<String,Object> paymentMap)  {
        String cvvNum = generateCardPage.getCVVNumber(paymentMap);
        return cvvNum;
    }

    public Integer paymentPageTotal() {
        waits.waitIsPresent(paymentAmountText);
        String amount = driver.findElement(By.xpath("//font[@color='red']")).getText().replace("$", "");
        Double totalAmount = Double.parseDouble(amount);

        int roundValTotal = (int) Math.round(totalAmount);
        return roundValTotal;
    }

    //This method will handle completion of Payment fields with valid data and check button amount
    public void getCompletePaymentFields(TreeMap<String,Object> paymentMap) throws InterruptedException {
        waits.waitIsPresent(paymentAmountText);
       // getCardNumber(paymentMap);
        cardNumberField.sendKeys(getCardNumber(paymentMap));
        selectMonth(paymentMap);
        selectYear(paymentMap);
        cvvField.sendKeys(getCVVNumber(paymentMap));

        String value = driver.findElement(By.xpath("//input[@name='submit']")).getAttribute("value").replace("Pay $", "");
        Double totalValue = Double.parseDouble(value);
        int roundValTotal = (int) Math.round(totalValue);
        if(roundValTotal==paymentPageTotal()){
            waits.waitIsClickable(buttonSubmit);
            buttonSubmit.click();
        }

    }

}
