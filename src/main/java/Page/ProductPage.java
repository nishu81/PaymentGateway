package Page;

import Utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.Map;

public class ProductPage {

    WebDriver driver;
    Waits waits;

    private int calculatedItemPrice;

    public ProductPage(WebDriver driver){
        this.driver = driver;
        waits = new Waits(driver);
        PageFactory.initElements(driver,this);
    }

    public String getHomePage(){
        String homeUrl="http://demo.guru99.com/payment-gateway/";
        return homeUrl;
    }

    @FindBy(xpath = "//h3[contains(text(),'Price')]")
    WebElement productPrice;

    @FindBy(xpath = "//input[@value='Buy Now']")
    WebElement buttonBuyNow;

    //ToDo :  Do I need these 2 items below? Seems useless
    public int getItemPrice(){
        String price = productPrice.getText().replace("Price: $","");
        return Integer.parseInt(price);
    }

    public int selectItemQuantityAndCheckOut(String qty){
        Select prodQty = new Select(driver.findElement(By.xpath("//Select[@name='quantity']")));
        prodQty.selectByValue(qty);
        return Integer.parseInt(qty);
    }

    public void clickToProceed(){
        buttonBuyNow.click();
    }

    //This method will capture item Quantity and Price and calculate total price
    public HashMap<String,Integer> getProdInfoMap(String qty){
        int sum=1;
        Select prodQty = new Select(driver.findElement(By.xpath("//Select[@name='quantity']")));
        prodQty.selectByValue(qty);
        HashMap<String,Integer> prodMap = new HashMap<String,Integer>();
        prodMap.put("Price",Integer.parseInt(productPrice.getText().replace("Price: $","")));
        prodMap.put("Quantity", Integer.parseInt(qty));

        for (Map.Entry<String, Integer> mm : prodMap.entrySet()) {
            System.out.println(mm.getKey() + " " + mm.getValue());
            sum = sum * mm.getValue();
        }

        calculatedItemPrice=sum;
        System.out.println("SUM " + sum);

        return prodMap;
    }
}
