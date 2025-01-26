package base;

import com.microsoft.playwright.*;
import utils.PropertyReader;

import java.io.IOException;

public class Base {
    Playwright playwright;
    Page page;
    public static String authCookie;
    public static String previousAccountNumber;
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
}
