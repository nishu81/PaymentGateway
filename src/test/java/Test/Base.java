package Test;

import Browsers.LaunchBrowser;
import Page.*;
import Utils.BrowserOps;
import Utils.UtilMethods;
import Utils.Waits;

public class Base {

    protected HomePage homePage;
    protected Waits waits;
    protected BrowserOps browserOps;
    protected UtilMethods utilMethods;
    protected LaunchBrowser launchBrowser;
    protected GenerateCardPage generateCardPage;
    protected CheckCreditLimitPage checkCreditLimitPage;
    protected ProductPage productPage;
    protected PaymentProcessPage paymentProcessPage;

}
