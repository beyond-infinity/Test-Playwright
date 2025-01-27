package ui_test;

import api_helper.FindTransactionHelper;
import api_test.FindTransactionTest;
import base.Base;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.Cookie;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page_objects.AccountServicePage;
import page_objects.HomePage;
import utils.PropertyReader;

import java.io.IOException;
import java.util.List;

public class AccountServiceTest extends Base {
    AccountServicePage accountPage;
    HomePage homePage;
    Page page;
    Browser browser;

    PropertyReader reader;
    String newAccountNumber;

    @BeforeClass
    public void initBrowser() throws IOException {
        browser = getDriver();
        page = browser.newPage();
        accountPage = new AccountServicePage(page);
        homePage = new HomePage(page);
        reader = new PropertyReader("testdata/RegisterUser.properties");
    }

    @Description("Create a new account with some balance.")
    @Test
    public void createNewAccount()
    {
        Allure.label("subSuite", "Account Service Test");
        setName("Create a new account test");
        homePage.navigateToURL();
        homePage.clickOnRegisterLink(page);
        page.waitForTimeout(500);
        String username = homePage.registerNewUser();
        homePage.clickOnSubmit();
        page.waitForTimeout(500);
        accountPage.clickOnOpenAccount();
        page.waitForTimeout(500);
        BrowserContext context = browser.contexts().get(0);
        List<Cookie> cookies = context.cookies();
        authCookie = cookies.get(0).value;
        accountPage.selectAccountType(0);
        accountPage.clickOnOpenAccountButton();
        page.waitForTimeout(500);
        accountPage.verifyNewAccountMessage();
        previousAccountNumber = accountPage.getnewAccountNumber();

    }

    @Description("Verify the balance of newly created bank account using the Account Overview Page.")
    @Test(dependsOnMethods = {"createNewAccount"})
    public void verifyNewAccountBalance()
    {
        Allure.label("subSuite", "Account Service Test");
        setName("Verify new account's balance");
        accountPage.clickOnAccountOverview();
        accountPage.verifyAccountExist(previousAccountNumber);
        accountPage.verifyAccountBalance("$100.00");
    }

    @Description("Transfer money from the newly created account to old account.")
    @Test(dependsOnMethods = {"verifyNewAccountBalance"})
    public void transferMoneyFromNewAccount()
    {
        Allure.label("subSuite", "Account Service Test");
        setName("Transfer money from new account");
        accountPage.clickOnTransferFunds();
        accountPage.enterAmount("5");
        accountPage.setFromAccount();
        accountPage.clickOnTransferButton();
        accountPage.verifyTransfer();
    }

    @Description("Pay a bill from the newly created account.")
    @Test(dependsOnMethods = {"transferMoneyFromNewAccount"})
    public void billPaymentFromNewAccount()
    {
        Allure.label("subSuite", "Account Service Test");
        setName("Pay bill from new account");
        page.navigate("https://parabank.parasoft.com/parabank/billpay.htm");
        accountPage.fillBillDetails(previousAccountNumber);
        accountPage.clickOnSubmitButton();
        accountPage.verifyBillPayment(previousAccountNumber);
    }

    @AfterClass
    public void closePage()
    {
        page.close();
    }

}
