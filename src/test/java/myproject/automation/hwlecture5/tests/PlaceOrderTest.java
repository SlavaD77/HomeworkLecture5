package myproject.automation.hwlecture5.tests;

import com.beust.jcommander.Parameter;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import myproject.automation.hwlecture5.BaseTest;
import myproject.automation.hwlecture5.GeneralActions;
import myproject.automation.hwlecture5.model.ProductData;
import myproject.automation.hwlecture5.pages.*;
import myproject.automation.hwlecture5.utils.DataConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaceOrderTest extends BaseTest {

    @Parameters({"storeAddress"})
    @Test
    public void checkSiteVersion(String storeAddress) {
        // TODO open main page and validate website version  -- DONE

        driver.navigate().to(storeAddress);
        MainPage mainPage = new MainPage(driver);

        Boolean isThreeLineDisplayed = driver.findElement(mainPage.getThreeLineMenuLocator()).isDisplayed();

        Assert.assertEquals((Boolean)isThreeLineDisplayed, (Boolean)isMobileTest, "Website version do not match the type of test");  //"Website version do not match the type of test"
    }
    @Parameters({"storeAddress", "orderConfirmedText_RU", "orderConfirmedText_UA"})
    @Test
    public void createNewOrder(String storeAddress, String orderConfirmedText_RU, String orderConfirmedText_UA) {
        // TODO implement order creation test

        driver.navigate().to(storeAddress);

        MainPage mainPage = new MainPage(driver);

        mainPage.clickAllProductsLink();

        String linkText = actions.openRandomProduct();

        ProductPage productPage = new ProductPage(driver);

        productPage.clickBtnProductDetails();

        ProductData savedProduct = actions.getOpenedProductInfo();

        savedProduct.setLinkText(linkText);

        productPage.clickBtnAddToCart();

        //productPage.clickLinkProceedToOrder();
        productPage.clickElementWithJavaScript(driver.findElement(productPage.getLinkProceedToOrderLocator()));

        CartPage cartPage= new CartPage(driver);

        List<WebElement> items = driver.findElements(cartPage.getLiCartItemLocator());

        Assert.assertEquals(1, items.size(),"Number of items in cart differs from 1");

        String name = driver.findElement(cartPage.getLinkNameProductLocator()).getText();
        float price = DataConverter.parsePriceValue(driver.findElement(cartPage.getProductPriceLocator()).getText() );

        Assert.assertEquals(savedProduct.getName().toUpperCase(), name.toUpperCase(), "Product name in cart doesn't match to saved prod");
        Assert.assertEquals(savedProduct.getPrice(), price);

        cartPage.clickBtnProceedToOrder();

        OrderPage orderPage = new OrderPage(driver);

        orderPage.fillFirstName("John");
        orderPage.fillLastName("Dou");
        orderPage.fillEmail(GeneralActions.generateEmail());

        orderPage.clickBtnContinue();

        orderPage.fillAddress1("Test address");
        orderPage.fillPostcode("12345");
        orderPage.fillCity("MyCity");

        orderPage.clickBtnConfirmAddresses();

        orderPage.clickBtnConfirmDeliveryOpt();

        orderPage.clickRadioPaymentByChek();

        orderPage.clickCheckboxApprove();

        orderPage.clickBtnSubmit();

        OrderConfirmedPage orderConfirmedPage = new OrderConfirmedPage(driver);

        //Check for confirmation text presense on the page.
       // Assert.assertTrue(driver.getPageSource().contains(orderConfirmedText_RU)||driver.getPageSource().contains((orderConfirmedText_UA))
        //        , "Confirmation text not found:" + orderConfirmedText_RU);
        String confirmationText = driver.findElement(orderConfirmedPage.getConfirmTextLocator()).getText();
        Assert.assertTrue(confirmationText.contains(orderConfirmedText_RU)||confirmationText.contains(orderConfirmedText_UA),"Confirmation text not fourd");

        List<WebElement> productRows = driver.findElements(By.cssSelector("div.order-line.row"));

        Assert.assertEquals(productRows.size(),1,"Qty of product rows in Order confirmation differs from 1");

        String nameOrder = driver.findElement(orderConfirmedPage.getProductNameLocator()).getText();

        Assert.assertTrue(nameOrder.toUpperCase().contains(savedProduct.getName().toUpperCase()),"Product name not found in order");

        String priceOrder = driver.findElement(orderConfirmedPage.getProductPriceLocator()).getText();

        price = DataConverter.parsePriceValue(priceOrder);

        Assert.assertEquals(savedProduct.getPrice(),price, "Price in the order confirmation doesn't corresponds to the price of product");

        //Check that quantity of selected product in storage decreased  by 1
        driver.navigate().to(storeAddress);

        mainPage.clickAllProductsLink();

        AllProductsPage allProductsPage = new AllProductsPage(driver);

        actions.waitForContentLoad();

        List<WebElement> nextProductLinkEnabled = driver.findElements(allProductsPage.getNextProductsLinkEnabledLocator());

        List<WebElement> listProductLinks = driver.findElements(By.partialLinkText(savedProduct.getLinkText()));

        //switch pages 1,2 .... with product views
        while (!nextProductLinkEnabled.isEmpty())
        {
            if(!listProductLinks.isEmpty()) break;
            nextProductLinkEnabled.get(0).click();
            driver.findElement(allProductsPage.getNextProductsLinkEnabledLocator()).click();
            actions.waitForContentLoad();
            listProductLinks = driver.findElements(By.partialLinkText(savedProduct.getLinkText()));
            nextProductLinkEnabled = driver.findElements(allProductsPage.getNextProductsLinkEnabledLocator());  //
        }

        Assert.assertTrue(!listProductLinks.isEmpty(), "Name of purchased product was not found in store (search by partial link text");

        listProductLinks.get(0).click();

        actions.waitForContentLoad();

        int qty = DataConverter.parseStockValue(driver.findElement(productPage.getProductQuantityLocator()).getAttribute("innerHTML"));

        Assert.assertEquals(qty, savedProduct.getQty() - 1, "Quantity of products in storage wrong");
    }

}
