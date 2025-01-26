package page_objects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage {
    Page page;
    public LoginPage(Page page)
    {
        this.page = page;
    }

    private Locator globalHomeButton()
    {
        return page.locator("//li[@class='home']/a[@href='index.htm']");
    }
    private Locator globalAboutButton()
    {
        return page.locator("//li[@class='aboutus']/a[@href='about.htm']");
    }
    private Locator globalContactButton()
    {
        return page.locator("//li[@class='contact']/a[@href='contact.htm']");
    }
    private Locator serviceHeading()
    {
        return page.locator("//li[@class='captionone']");
    }
    private Locator serviceHeadingTwo()
    {
        return page.locator("//li[@class='captiontwo']");
    }
    private Locator aboutUsMessage()
    {
        return page.locator("//h1[@class='title']");
    }


    public void homebuttonClick()
    {
        globalHomeButton().click();
    }
    public void aboutButtonClick()
    {
        globalAboutButton().click();
    }
    public void contactbuttonClick()
    {
        globalContactButton().click();
    }

    public void verifyHomePage()
    {
        assertThat(serviceHeading()).containsText("ATM Services");
        assertThat(serviceHeadingTwo()).containsText("Online Services");
    }
    public void verifyAboutPage()
    {
        assertThat(aboutUsMessage()).containsText("ParaSoft Demo Website");
    }
    public void verifyContactPage()
    {
        assertThat(aboutUsMessage()).containsText("Customer Care");
    }
}
