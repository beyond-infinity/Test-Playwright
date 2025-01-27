package ui_test;

import base.Base;
import com.microsoft.playwright.Page;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page_objects.HomePage;
import page_objects.LoginPage;
import utils.PropertyReader;
import java.io.IOException;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class RegisterTest extends Base {
    HomePage homePage;
    Page page;
    PropertyReader reader;

    public RegisterTest() throws IOException {
    }

    @BeforeClass
    public void initBrowser() throws IOException {
        page = getDriver().newPage();
        homePage = new HomePage(page);
        reader = new PropertyReader("testdata/RegisterUser.properties");
    }

    @Description("Register a new User with Parasoft Bank.")
    @Test
    public void createNewUser()
    {
        homePage.navigateToURL();
        homePage.clickOnRegisterLink(page);
        String username = homePage.registerNewUser();
        homePage.clickOnSubmit();
        homePage.verifyWelcomeText(username);
        homePage.verifyNameText(reader.getProperty("firstName") , reader.getProperty("lastName"));

    }

    @Description("Given new user has been created.Verify the global menu on the Home Page.")
    @Test(dependsOnMethods = {"createNewUser"})
    public void verifyGlobalMenu()
    {
        LoginPage loginPage = new LoginPage(page);
        loginPage.homebuttonClick();
        loginPage.verifyHomePage();
        loginPage.aboutButtonClick();
        loginPage.verifyAboutPage();
        loginPage.contactbuttonClick();
        loginPage.verifyContactPage();
    }

    @AfterClass
    public void closebrowser()
    {
        page.close();
    }
}
