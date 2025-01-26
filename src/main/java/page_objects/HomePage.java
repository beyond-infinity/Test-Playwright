package page_objects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import utils.PropertyReader;
import utils.DateTimeUtil;
import java.io.IOException;
import java.util.Date;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HomePage {
    Page page;
    PropertyReader reader = new PropertyReader("properties/systemProperties.properties");
    PropertyReader data = new PropertyReader("testdata/RegisterUser.properties");
    public HomePage(Page p) throws IOException {
        page = p;
    }
    private Locator registorLink(Page page)
    {
        return page.getByText("Register");
    }

    private Locator submitButton()
    {
        return page.locator("//input[@value='Register']");
    }

    private Locator firstName()
    {
        return page.locator("//input[@name='customer.firstName']");
    }
    private Locator lastName()
    {
        return page.locator("//input[@name='customer.lastName']");
    }
    private Locator street()
    {
        return page.locator("//input[@name='customer.address.street']");
    }
    private Locator city()
    {
        return page.locator("//input[@name='customer.address.city']");
    }
    private Locator state()
    {
        return page.locator("//input[@name='customer.address.state']");
    }
    private Locator zipCode()
    {
        return page.locator("//input[@name='customer.address.zipCode']");
    }
    private Locator phoneNumber()
    {
        return page.locator("//input[@name='customer.phoneNumber']");
    }
    private Locator ssn()
    {
        return page.locator("//input[@name='customer.ssn']");
    }
    private Locator username()
    {
        return page.locator("//input[@name='customer.username']");
    }
    private Locator password()
    {
        return page.locator("//input[@name='customer.password']");
    }
    private Locator confirmPassword()
    {
        return page.locator("//input[@name='repeatedPassword']");
    }
    private Locator welcomeMessage()
    {
        return page.locator("//h1[@class='title']");
    }
    private Locator welcomeName()
    {
        return page.locator("//p[@class='smallText']");
    }
    public void navigateToURL()
    {
        page.navigate(reader.getProperty("HomePageURL"));
    }
    public void clickOnRegisterLink(Page p)
    {
        registorLink(p).click();
    }

    public void setFirstName(String firstName)
    {
        firstName().fill(firstName);
    }
    public void setLastName(String firstName)
    {
        lastName().fill(firstName);
    }
    public void setAddressStreet(String firstName)
    {
        street().fill(firstName);
    }
    public void setCity(String firstName)
    {
        city().fill(firstName);
    }
    public void setState(String firstName)
    {
        state().fill(firstName);
    }
    public void setZipCode(String firstName)
    {
        zipCode().fill(firstName);
    }
    public void setPhoneNumber(String firstName)
    {
        phoneNumber().fill(firstName);
    }
    public void setSSN(String firstName)
    {
        ssn().fill(firstName);
    }
    public void setUsername(String firstName)
    {
        username().fill(firstName);
    }
    public void setPassword(String firstName)
    {
        password().fill(firstName);
    }
    public void setConfirmPassword(String firstName)
    {
        confirmPassword().fill(firstName);
    }
    public void clickOnSubmit()
    {
        submitButton().click();
    }
    public String generateUsername()
    {
        return "User" + DateTimeUtil.getDateTime();
    }
    public void verifyWelcomeText(String username)
    {
        assertThat(welcomeMessage()).containsText("Welcome " + username);
    }
    public void verifyNameText(String name , String lastName)
    {
        assertThat(welcomeName()).containsText("Welcome " + name + " " + lastName);
    }
    public String nameWelcomeText()
    {
        return welcomeName().innerText();
    }
    
    public String registerNewUser()
    {
        setFirstName(data.getProperty("firstName"));
        setLastName(data.getProperty("lastName"));
        setAddressStreet(data.getProperty("street"));
        setCity(data.getProperty("city"));
        setState(data.getProperty("state"));
        setZipCode(data.getProperty("zipCode"));
        setPhoneNumber(data.getProperty("phoneNumber"));
        setSSN(data.getProperty("ssn"));
        String username = generateUsername();
        setUsername(username);
        setPassword(data.getProperty("password"));
        setConfirmPassword(data.getProperty("repeatedPassword"));
        return username;
    }
}
