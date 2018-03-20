package myproject.automation.hwlecture5.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasicPage {
    public ProductPage(WebDriver driver){
        this.driver = driver;
    }

    private By productNameLocator = By.cssSelector("h1[itemprop='name']");
    public By getProductNameLocator(){
        return productNameLocator;
    }

    private By productPriceLocator = By.cssSelector("span[itemprop='price']");
    public By getProductPriceLocator(){
        return productPriceLocator;
    }

    private By productQuantityLocator = By.cssSelector("div.product-quantities > span");
    public By getProductQuantityLocator(){return productQuantityLocator; }

    private By btnAddToCartLocator =  By.cssSelector("button.add-to-cart");  //button.btn.btn-primary.add-to-cart
    public By getBtnAddToCartLocator() {return btnAddToCartLocator;}

    private By linkProceedToOrderLocator = By.cssSelector("a.btn.btn-primary");
    public By getLinkProceedToOrderLocator() {return linkProceedToOrderLocator;}

    public void clickBtnAddToCart(){
        driver.findElement(getBtnAddToCartLocator()).click();
    }

    public void clickLinkProceedToOrder(){
        driver.findElement(getLinkProceedToOrderLocator()).click();
    }
}
