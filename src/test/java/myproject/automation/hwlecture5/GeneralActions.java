package myproject.automation.hwlecture5;


import myproject.automation.hwlecture5.model.ProductData;
import myproject.automation.hwlecture5.pages.AllProductsPage;
import myproject.automation.hwlecture5.pages.ProductPage;
import myproject.automation.hwlecture5.utils.DataConverter;
import myproject.automation.hwlecture5.utils.logging.CustomReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


        import java.util.List;
import java.util.Random;

/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions {
    private WebDriver driver;
    private WebDriverWait wait;

    public GeneralActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    /**
     *
     * @return text of the link to product
     */
    public String openRandomProduct() {
        // TODO implement logic to open random product before purchase -- DONE
        AllProductsPage page = new AllProductsPage(driver);
        List<WebElement> allProducts = page.getAllLinksToProducts();
        Random rand = new Random();
        WebElement randomProduct = allProducts.get(rand.nextInt(allProducts.size()));
        String linkText= randomProduct.getText();
        randomProduct.click();
        return linkText;
        //throw new UnsupportedOperationException();
    }

    /**
     * Extracts product information from opened product details page.
     *
     * @return
     */
    public ProductData getOpenedProductInfo() {
        CustomReporter.logAction("Get information about currently opened product");
        // TODO extract data from opened page --- DONE

        ProductPage productPage = new ProductPage(driver);

        String name = driver.findElement(productPage.getProductNameLocator()).getText();
        String strPrice = driver.findElement(productPage.getProductPriceLocator()).getText();
        String strQty = driver.findElement(productPage.getProductQuantityLocator()).getAttribute("innerHTML");
         //      strQty = driver.findElement(By.cssSelector("div.product-quantities")).getText();

        CustomReporter.log("ExtractedInfo: " + name + " " + " " + strPrice + " " + strQty);

        float price = DataConverter.parsePriceValue(strPrice);
        int stockValue = DataConverter.parseStockValue(strQty);

        ProductData productData = new ProductData(name, stockValue, price);
        return productData;
    }

    /**
     * Generate unique email based on current time in miliseconds
     * @return email string
     */
    public static String generateEmail(){
        return "Email" + System.currentTimeMillis() + "@test.com";
    }

    /**
     * Waits until page loader disappears from the page
     */
    public void waitForContentLoad() {
        // DONE implement generic method to wait until page content is loaded
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(expectation);
    }
}
