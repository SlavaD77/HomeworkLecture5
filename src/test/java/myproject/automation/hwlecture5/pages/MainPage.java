package myproject.automation.hwlecture5.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasicPage {

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    private By threeLineMenuLocator = By.cssSelector("*.material-icons.d-inline");
    public By getThreeLineMenuLocator(){return threeLineMenuLocator;}

    private By allProductsLinkLocator = By.cssSelector("a.all-product-link.pull-xs-left.pull-md-right.h4");  //all-product-link pull-xs-left pull-md-right h4
    public By getAllProductsLinkLocator(){
        return allProductsLinkLocator;
    }

    private By productNameLocator = By.cssSelector("h1[itemprop='name']");
    public By getProductNameLocator(){
        return productNameLocator;
    }

    private By productPriceLocator = By.cssSelector("span[itemprop='price']");
    public By getProductPriceLocator(){
        return productPriceLocator;
    }


    private By productQuantityLocator = By.cssSelector("#product-details > div.product-quantities > span");
    public By getProductQuantityLocator(){
        return productQuantityLocator;
    }

    public void clickAllProductsLink(){
        (driver.findElement(allProductsLinkLocator)).click();
    }
}
