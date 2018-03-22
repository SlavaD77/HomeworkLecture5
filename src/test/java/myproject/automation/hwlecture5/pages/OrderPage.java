package myproject.automation.hwlecture5.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage extends BasicPage {
    public OrderPage(WebDriver driver){
        this.driver = driver;
    }

    private By firstNameInputLocator = By.cssSelector("input[name='firstname']");
    public By getFirstNameInputLocator(){return firstNameInputLocator;}
    public void fillFirstName(String name){
        driver.findElement(getFirstNameInputLocator()).sendKeys(name);
    }

    private By lastNameInputLocator = By.cssSelector("input[name='lastname']");
    public By getLastNameInputLocator(){return lastNameInputLocator;}
    public void fillLastName(String name){
        driver.findElement(getLastNameInputLocator()).sendKeys(name);
    }

    private By emailInputLocator = By.cssSelector("input[name='email']");
    public By getEmailInputLocator(){return emailInputLocator;}
    public void fillEmail(String email){
        driver.findElement(getEmailInputLocator()).sendKeys(email);
    }

    private By btnContinueRegNewCustomerLocator = By.cssSelector("button[data-link-action='register-new-customer']");
    public By getBtnContinueRegNewCustomerLocator(){return btnContinueRegNewCustomerLocator;}
    public void clickBtnContinue(){
        driver.findElement(getBtnContinueRegNewCustomerLocator()).click();
    }

    private By address1InputLocator = By.cssSelector("input[name='address1']");
    public By getAddress1InputLocator(){return address1InputLocator;}
    public void fillAddress1(String address){
        driver.findElement(getAddress1InputLocator()).sendKeys(address);
    }

    private By postcodeInputLocator = By.cssSelector("input[name='postcode']");
    public By getPostcodeInputLocator(){return postcodeInputLocator;}
    public void fillPostcode(String postcode){
        driver.findElement(getPostcodeInputLocator()).sendKeys(postcode);
    }

    private By cityInputLocator = By.cssSelector("input[name='city']");
    public By getCityInputLocator(){return cityInputLocator;}
    public void fillCity(String city){
        driver.findElement(getCityInputLocator()).sendKeys(city);
    }

    private By btnConfirmAddressesLocator = By.name("confirm-addresses"); //By.cssSelector("button[name='confirm-addresses']");
    public By getBtnConfirmAddressesLocator(){return btnConfirmAddressesLocator;}
    public void clickBtnConfirmAddresses(){
        driver.findElement(getBtnConfirmAddressesLocator()).click();
    }

    private By btnConfirmDeliveryOptionsLocator = By.name("confirmDeliveryOption");//By.cssSelector("button[name='confirmDeliveryOption']");
    public By getBtnConfirmDeliveryOptionsLocator(){return btnConfirmDeliveryOptionsLocator;}
    public void clickBtnConfirmDeliveryOpt(){
        driver.findElement(getBtnConfirmDeliveryOptionsLocator()).click();
    }

    private By radioPaymentByCheckLocator = By.cssSelector("input#payment-option-1");
    public By getRadioPaymentByCheckLocator(){return radioPaymentByCheckLocator;}
    public void clickRadioPaymentByChek(){
        driver.findElement(getRadioPaymentByCheckLocator()).click();
    }

    private By radioPaymentByBankTransferLocator = By.cssSelector("input#payment-option-2");
    public By getRadioPaymentByBankTransferLocator(){return radioPaymentByBankTransferLocator;}

    private By checkboxApproveConditionsLocator = By.cssSelector("input[id='conditions_to_approve[terms-and-conditions]']");
    public By getCheckboxApproveConditionsLocator(){return checkboxApproveConditionsLocator;}
    public void clickCheckboxApprove(){
        driver.findElement(getCheckboxApproveConditionsLocator()).click();
    }

    private By btnSubmitLocator = By.cssSelector("div#payment-confirmation button");
    public By getBtnSubmitLocator(){return btnSubmitLocator;}
    public void clickBtnSubmit(){
        driver.findElement(getBtnSubmitLocator()).click();
    }

}
