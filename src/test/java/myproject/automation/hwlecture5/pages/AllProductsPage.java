package myproject.automation.hwlecture5.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AllProductsPage extends BasicPage {
    public AllProductsPage(WebDriver driver){
        this.driver = driver;
    }

    private By linkProductNameLocator = By.cssSelector("div.product-description>h1>a");  //up to 12 items per desktop page
    public By getLinkProductNameLocator(){return linkProductNameLocator;}

    private By nextProductsLinkEnabledLocator = By.cssSelector("a.next.js-search-link"); //next js-search-link
    public By getNextProductsLinkEnabledLocator(){ return nextProductsLinkEnabledLocator;}

    private By nextProductsLinkDisabledLocator = By.cssSelector("a.next.disabled.js-search-link"); //next disabled js-search-link
    public By getNextProductsLinkDisabledLocator(){
        return nextProductsLinkDisabledLocator;
    }

    public List<WebElement> getAllLinksToProducts(){
        return driver.findElements(getLinkProductNameLocator());
    }

    public boolean isNextProductsEnabled(){
        return !(driver.findElements(this.getNextProductsLinkEnabledLocator()).isEmpty());
    }

}
