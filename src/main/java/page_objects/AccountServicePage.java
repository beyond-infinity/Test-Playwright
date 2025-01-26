package page_objects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utils.PropertyReader;

import java.io.IOException;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.AssertJUnit.assertEquals;

public class AccountServicePage {
    Page page;
    PropertyReader data = new PropertyReader("testdata/RegisterUser.properties");
    public AccountServicePage(Page page) throws IOException {
        this.page = page;
    }


    private Locator openNewAccountLink()
    {
        return page.getByText("Open New Account");
    }
    private Locator accountTypeDropdown()
    {
        return page.locator("//select[@id='type']");
    }
    private Locator accountNumberDropdown()
    {
        return page.locator("//input[@id='fromAccountId']");
    }
    private Locator checkingAccount()
    {
        return page.locator("//option[@value='0']");
    }
    private Locator savingAccount()
    {
        return page.locator("//option[@value='1']");
    }
    private Locator newAccountId()
    {
        return page.locator("//a[@id='newAccountId']");
    }
    private Locator openNewAccountButton()
    {
        return page.locator("//input[@value='Open New Account']");
    }
    private Locator newAccountMessage()
    {
        return page.locator("//div[@id='openAccountResult']/h1[@class='title']");
    }
    private Locator accountOverviewLink()
    {
        return page.locator("//a[@href='overview.htm']");
    }
    private Locator accountNumber(String accountNumber)
    {
        return page.getByText(accountNumber);
    }
    private Locator balance()
    {
        return page.locator("//table[@id='accountTable']/tbody/tr/td").all().get(4);
    }
    private Locator transferFundsLink()
    {
        return page.locator("//a[@href='transfer.htm']");
    }
    private Locator transferAmountTextbox()
    {
        return page.locator("//input[@id='amount']");
    }
    private Locator fromAccount()
    {
        return page.locator("//select[@id='fromAccountId']");
    }
    private Locator toAccount()
    {
        return page.locator("//select[@id='toAccountId']");
    }
    private Locator submitTransferInfo()
    {
        return page.locator("//input[@type='submit']");
    }
    private Locator transferMessage()
    {
        return page.locator("//div[@id='showResult']/h1[@class='title']");
    }
    private Locator name()
    {
        return page.locator("//input[@name='payee.name']");
    }

    private Locator street()
    {
        return page.locator("//input[@name='payee.address.street']");
    }
    private Locator city()
    {
        return page.locator("//input[@name='payee.address.city']");
    }
    private Locator state()
    {
        return page.locator("//input[@name='payee.address.state']");
    }
    private Locator zipCode()
    {
        return page.locator("//input[@name='payee.address.zipCode']");
    }
    private Locator phoneNumber()
    {
        return page.locator("//input[@name='payee.phoneNumber']");
    }
    private Locator accountNumber()
    {
        return page.locator("//input[@name='payee.accountNumber']");
    }
    private Locator repeatAccount()
    {
        return page.locator("//input[@name='verifyAccount']");
    }
    private Locator billAmount()
    {
        return page.locator("//input[@name='amount']");
    }
    private Locator sendButton()
    {
        return page.locator("//input[@value='Send Payment']");
    }
    private Locator billPaymentCompleteMessage()
    {
        return page.locator("//div[@id='billpayResult']/h1");
    }
    private Locator billPayMessage()
    {
        return page.locator("//div[@id='billpayResult']/p").nth(0);
    }
    private Locator billPayLink()
    {
        return page.locator("//a[href='billpay.htm']");
    }

    public void accountDropdown(String accountNumber)
    {
        page.selectOption("//select[@name='fromAccountId']", accountNumber);
    }
    public void clickOnOpenAccount()
    {
        openNewAccountLink().click();
    }
    public void clickOnBillPaymentLink()
    {
        billPayLink().click();
    }
    public void selectAccountType(int accountTypeNumber)
    {

        if(accountTypeNumber==1) {
            accountTypeDropdown().click();
            page.keyboard().press("ArrowDown");
            page.keyboard().press("Enter");
        }

    }
    public void clickOnOpenAccountButton()
    {
        openNewAccountButton().click();
    }

    public String getnewAccountNumber()
    {
        return newAccountId().textContent();
    }
    public void verifyNewAccountMessage()
    {
        assertEquals(newAccountMessage().innerText(),"Account Opened!");
    }

    public void clickOnAccountOverview()
    {
        accountOverviewLink().click();
    }
    public void verifyAccountExist(String accountNumber)
    {
        assertThat(accountNumber(accountNumber)).isVisible();
    }
    public void verifyAccountBalance(String balance)
    {
        assertEquals("New account balance does not match!" , balance().innerText() , balance );
    }
    public void clickOnTransferFunds()
    {
        transferFundsLink().click();
        page.waitForTimeout(200);
    }
    public void enterAmount(String amount)
    {
        transferAmountTextbox().fill(amount);
    }
    public void setFromAccount()
    {
        fromAccount().click();
        page.keyboard().press("ArrowDown");
        page.keyboard().press("Enter");
    }
    public void clickOnTransferButton()
    {
        submitTransferInfo().click();
    }
    public void verifyTransfer()
    {
        assertEquals(transferMessage().innerText(),"Transfer Complete!");
    }

    public void fillBillDetails(String accountNumber)
    {
        name().fill(data.getProperty("firstName"));
        street().fill(data.getProperty("street"));
        city().fill(data.getProperty("city"));
        state().fill(data.getProperty("state"));
        zipCode().fill(data.getProperty("zipCode"));
        phoneNumber().fill(data.getProperty("phoneNumber"));
        accountNumber().fill(accountNumber + "1");
        repeatAccount().fill(accountNumber + "1");
        billAmount().fill("1");
        accountDropdown(accountNumber);
    }
    public void clickOnSubmitButton()
    {
        sendButton().click();
    }

    public void verifyBillPayment(String accountNumber)
    {
        assertEquals(billPaymentCompleteMessage().innerText() , "Bill Payment Complete");
    }
}
