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

    TreeMap<String, Object> paymentMap = new  TreeMap<String, Object>();


    public PaymentProcessPage(WebDriver driver) throws InterruptedException {
        this.driver = driver;
        waits = new Waits(driver);
        generateCardPage = new GenerateCardPage(driver);
        PageFactory.initElements(driver, this);
    }



    public String getPaymentProcessPage() {
        String paymentProcessUrl = homePage.getHomePage().concat("process_purchasetoy.php");
        return paymentProcessUrl;
    }

    @FindBy(xpath = "//font[text()='Pay Ammount']")
    WebElement paymentAmountText;

    @FindBy(xpath = "//input[@id='card_nmuber']")
    WebElement cardNumberField;

    //Select Month
    public void selectMonth() throws InterruptedException {
        String month = generateCardPage.getMonthYear(paymentMap).get(0);
        Select ccMonth = new Select(driver.findElement(By.xpath("//select[@id='month']")));
        ccMonth.selectByValue(month);
    }

    //Select Year
    public void selectYear() {
        String year = generateCardPage.getMonthYear(paymentMap).get(1);
        Select ccYear = new Select(driver.findElement(By.xpath("//select[@id='year']")));
        ccYear.selectByValue(year);
    }

    public Integer paymentPageTotal() {
        waits.waitIsPresent(paymentAmountText);
        String amount = driver.findElement(By.xpath("//font[@color='red']")).getText().replace("$", "");
        Double totalAmount = Double.parseDouble(amount);

        int roundValTotal = (int) Math.round(totalAmount);
        return roundValTotal;
    }

    //This method will handle completion of Payment fields with valid data
    public void getCompletePaymentFields() throws InterruptedException {
        waits.waitIsPresent(paymentAmountText);
        selectMonth();
    }
}
