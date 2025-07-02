package com.cicdtest.pom.Test;

import com.cicdtest.pom.Util.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.cicdtest.pom.Pages.LoginPage;
import com.cicdtest.pom.Util.ConfigReader;


public class LoginTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void setupPage() {
        loginPage = new LoginPage(getPage());
        loginPage.navigateToLogin(ConfigReader.getProperty("url"));
    }

    @Test(priority = 1)
    public void testValidLogin() {
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
        boolean dashboardVisible = loginPage.isDashboardVisible();

        if (dashboardVisible) {
            test.pass("Dashboard is visible. Login successful.");
        } else {
            test.fail("Dashboard not visible. Login failed.");
        }

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(dashboardVisible, "Dashboard should be visible after login");
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void testInvalidEmail() {
        loginPage.login("invalid@example.com", ConfigReader.getProperty("password"));
        boolean errorVisible = loginPage.isErrorMessageVisible();

        test.info("Testing login with invalid email");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errorVisible, "Error message should be visible for invalid email");
        softAssert.assertEquals(loginPage.getErrorMessageText(), "Your email or password is incorrect!", "Incorrect error message text");
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void testInvalidPassword() {
        loginPage.login(ConfigReader.getProperty("username"), "wrongpassword");
        boolean errorVisible = loginPage.isErrorMessageVisible();

        test.info("Testing login with invalid password");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errorVisible, "Error message should be visible for invalid password");
        softAssert.assertEquals(loginPage.getErrorMessageText(), "Your email or password is incorrect!", "Incorrect error message text");
        softAssert.assertAll();
    }
}
