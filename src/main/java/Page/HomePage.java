package Page;

import Utils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;
    Waits waits;

    public HomePage(WebDriver driver){
        this.driver = driver;
        waits = new Waits(driver);
        PageFactory.initElements(driver,this);
    }

    public String getHomePage(){
        String homeUrl="http://demo.guru99.com/payment-gateway/";
        return homeUrl;
    }

}
