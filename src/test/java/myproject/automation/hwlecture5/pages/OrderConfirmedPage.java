package myproject.automation.hwlecture5.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderConfirmedPage extends BasicPage {
    public OrderConfirmedPage(WebDriver driver){
        this.driver = driver;
    }

    //ВАШ ЗАКАЗ ПОДТВЕРЖДЁН    //*.h1.card-title
    private By confirmTextLocator = By.cssSelector("*.h1.card-title");
    public By getConfirmTextLocator(){return confirmTextLocator;}

    private By productNameLocator = By.cssSelector("div.col-sm-4.col-xs-9.details>span");
    public By getProductNameLocator(){return productNameLocator;}
    public String getProductName(){
        return driver.findElement(productNameLocator).getText();
    }

    private By productPriceLocator = By.cssSelector("div.col-xs-5.text-sm-right.text-xs-left");
    public By getProductPriceLocator(){return productPriceLocator;}
    public String getProductPrice(){
        return driver.findElement(productPriceLocator).getText();
    }

}
