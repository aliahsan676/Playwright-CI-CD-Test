package com.cicdtest.pom.Util;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.Arrays;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    protected ExtentTest test;

    @BeforeSuite
    public void beforeSuite() {
        ExtentManager.initReport();
    }

    @BeforeMethod
    public void setUp(Method method) {
        playwright = Playwright.create();

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(true)
                        .setArgs(Arrays.asList(new String[]{
                                "--no-sandbox",
                                "--disable-dev-shm-usage"
                        }))
        );

        // Set screen size to 1920x1080 when creating context
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
                .setViewportSize(1540, 724);
        context = browser.newContext(contextOptions);

        page = context.newPage();

        // Start a new test in Extent report
        test = ExtentManager.startTest(method.getName());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test skipped: " + result.getThrowable());
        }

        if (playwright != null) {
            playwright.close();
        }
    }

    @AfterSuite
    public void afterSuite() {
        ExtentManager.flushReport();
    }

    public Page getPage() {
        return page;
    }
}
