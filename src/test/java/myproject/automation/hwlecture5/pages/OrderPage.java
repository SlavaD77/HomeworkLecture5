package myproject.automation.hwlecture5.pages;

import org.openqa.selenium.By;

public class OrderPage extends BasicPage {
    public OrderPage(){
        this.driver = driver;
    }

    private By firstNameInputLocator = By.cssSelector("input[name='firstname']");
    public By getFirstNameInputLocator(){return firstNameInputLocator;}

    private By lastNameInputLocator = By.cssSelector("input[name='lastname']");
    public By getLastNameInputLocator(){return lastNameInputLocator;}

    private By emailInputLocator = By.cssSelector("input[name='email']");
    public By getEmailInputLocator(){return emailInputLocator;}

    private By btnContinueRegNewCustomerLocator = By.cssSelector("button[data-link-action='register-new-customer']");
    public By getBtnContinueRegNewCustomerLocator(){return btnContinueRegNewCustomerLocator;}

    private By address1InputLocator = By.cssSelector("input[name='address1']");
    public By getAddress1InputLocator(){return address1InputLocator;}

    private By postcodeInputLocator = By.cssSelector("input[name='postcode']");
    public By getPostcodeInputLocator(){return postcodeInputLocator;}

    private By cityInputLocator = By.cssSelector("input[name='city']");
    public By getCityInputLocator(){return cityInputLocator;}

    private By btnConfirmAddressesLocator = By.cssSelector("button[name='confirm-addresses']");
    public By getBtnConfirmAddressesLocator(){return btnConfirmAddressesLocator;}

    private By btnConfirmDeliveryOptionsLocator = By.cssSelector("button[name='confirmDeliveryOption']");
    public By getBtnConfirmDeliveryOptionsLocator(){return btnConfirmDeliveryOptionsLocator;}

    private By radioPaymentByCheckLocator = By.cssSelector("input#payment-option-1");
    public By getRadioPaymentByCheckLocator(){return radioPaymentByCheckLocator;}

    private By radioPaymentByBankTransferLocator = By.cssSelector("input#payment-option-2");
    public By getRadioPaymentByBankTransferLocator(){return radioPaymentByBankTransferLocator;}

    private By checkboxApproveConditionsLocator = By.cssSelector("input[id='conditions_to_approve[terms-and-conditions]']");
    public By getCheckboxApproveConditionsLocator(){return checkboxApproveConditionsLocator;}

    private By btnSubmitLocator = By.cssSelector("div#payment-confirmation button");
    public By getBtnSubmitLocator(){return btnSubmitLocator;}

}
