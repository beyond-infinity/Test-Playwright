package base;

import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import utils.PropertyReader;

import java.io.IOException;

public class Base {
    Playwright playwright;
    Page page;
    public static String authCookie;
    public static String previousAccountNumber;
    AllureLifecycle lifecycle;
    public Browser getDriver()
    {
        Browser browser = null;
        try
        {
            playwright = Playwright.create();
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return browser;
    }

    public void setName(String name)
    {
        lifecycle = Allure.getLifecycle();
        lifecycle.updateTestCase(testResult -> testResult.setName(name));
    }
}
