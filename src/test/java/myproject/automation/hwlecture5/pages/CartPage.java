package myproject.automation.hwlecture5.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasicPage {
    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    private By liCartItemLocator = By.cssSelector("li.cart-item");
    public By getLiCartItemLocator(){return liCartItemLocator;}

    private By imgProductLocator = By.cssSelector("span.product-image");
    public By getImgProductLocator() {return imgProductLocator;}

    private By linkNameProductLocator = By.cssSelector("div.product-line-info>a.label");
    public By getLinkNameProductLocator(){return linkNameProductLocator;}

    private By productPriceLocator = By.cssSelector("span.product-price");
    public By getProductPriceLocator() {return productPriceLocator;}

    private By btnProceedToOrderLocator = By.cssSelector("div.checkout a");  //div.checkout.cart-detailed-actions.card-block a.btn.btn-primary
    public  By getBtnProceedToOrderLocator(){return btnProceedToOrderLocator;}

    public void clickBtnProceedToOrder(){
        driver.findElement(getBtnProceedToOrderLocator()).click();
    }

}
