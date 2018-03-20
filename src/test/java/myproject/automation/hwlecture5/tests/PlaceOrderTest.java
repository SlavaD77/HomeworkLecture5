package myproject.automation.hwlecture5.tests;

import myproject.automation.hwlecture5.BaseTest;
import myproject.automation.hwlecture5.model.ProductData;
import myproject.automation.hwlecture5.pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaceOrderTest extends BaseTest {

    @Test
    public void checkSiteVersion() {
        // TODO open main page and validate website version  --

        driver.navigate().to("http://prestashop-automation.qatestlab.com.ua/");
        MainPage mainPage = new MainPage(driver);

        Boolean isThreeLineDisplayed = driver.findElement(mainPage.getThreeLineMenuLocator()).isDisplayed();

        Assert.assertEquals((Boolean)isThreeLineDisplayed, (Boolean)isMobileTest, "Website version do not match the type of test");  //"Website version do not match the type of test"
    }

    @Test
    public void createNewOrder() {
        // TODO implement order creation test

        // open random product

        // save product parameters

        // add product to Cart and validate product information in the Cart

        // proceed to order creation, fill required information

        // place new order and validate order summary

        // check updated In Stock value
    }

}
