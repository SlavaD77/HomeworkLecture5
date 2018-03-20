package myproject.automation.hwlecture5.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasicPage {
    protected WebDriver driver;

    protected int milisecExpicitWait = 5000;

    public void waitForElementPresence(By locator){
        (new WebDriverWait(driver,milisecExpicitWait)).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void scrollPageDown(){
        JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
        jsExecutor.executeScript("window.scrollTo(0,document.body.scrollHeight);");
    }

    public void scrollToElementIntoView(WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void clickElementWithJavaScript(WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}
