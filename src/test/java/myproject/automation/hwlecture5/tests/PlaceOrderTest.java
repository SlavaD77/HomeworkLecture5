package myproject.automation.hwlecture5.tests;

import com.beust.jcommander.Parameter;
import myproject.automation.hwlecture5.BaseTest;
import myproject.automation.hwlecture5.GeneralActions;
import myproject.automation.hwlecture5.model.ProductData;
import myproject.automation.hwlecture5.pages.*;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PlaceOrderTest extends BaseTest {

    @Test
    public void checkSiteVersion() {
        // TODO open main page and validate website version  -- DONE

        driver.navigate().to("http://prestashop-automation.qatestlab.com.ua/");
        MainPage mainPage = new MainPage(driver);

        Boolean isThreeLineDisplayed = driver.findElement(mainPage.getThreeLineMenuLocator()).isDisplayed();

        Assert.assertEquals((Boolean)isThreeLineDisplayed, (Boolean)isMobileTest, "Website version do not match the type of test");  //"Website version do not match the type of test"
    }
    @Parameters("storeAddress")
    @Test
    public void createNewOrder(String storeAddress) {
        // TODO implement order creation test

        driver.navigate().to(storeAddress);

        MainPage mainPage = new MainPage(driver);

        mainPage.clickAllProductsLink();

        actions.openRandomProduct();

        ProductPage productPage = new ProductPage(driver);

        ProductData savedProduct = actions.getOpenedProductInfo();

        productPage.clickBtnAddToCart();

        productPage.clickLinkProceedToOrder();

        CartPage cartPage= new CartPage(driver);

        cartPage.clickBtnProceedToOrder();

        OrderPage orderPage = new OrderPage(driver);

        orderPage.fillFirstName("John");
        orderPage.fillLastName("Dou");
        orderPage.fillEmail(GeneralActions.generateEmail());
        // open random product

        // save product parameters

        // add product to Cart and validate product information in the Cart

        // proceed to order creation, fill required information

        // place new order and validate order summary

        // check updated In Stock value
    }

}
