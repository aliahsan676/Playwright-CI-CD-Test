package com.cicdtest.pom.Pages;

import com.cicdtest.pom.Util.General;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;

public class LoginPage {
    private final Page page;

    private final String emailInput = "//input[@data-qa='login-email']";
    private final String passwordInput = "//input[@placeholder='Password']";
    private final String loginButton = "//button[normalize-space()='Login']";
    private final String dashboardElement = "//a[normalize-space()='Logout']";
    private final String errorMessage = "//p[normalize-space()='Your email or password is incorrect!']";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void navigateToLogin(String url) {
        page.navigate(url);
        new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED);
    }

    public void login(String email, String password) {
        page.fill(emailInput, email);
        page.fill(passwordInput, password);
        page.click(loginButton);
        General.waitForDomStable();
    }

    public boolean isDashboardVisible() {
        try {
            Locator dashboard = page.locator(dashboardElement);
            dashboard.waitFor(new Locator.WaitForOptions().setTimeout(5000));
            return dashboard.isVisible();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorMessageVisible() {
        try {
            Locator error = page.locator(errorMessage);
            error.waitFor(new Locator.WaitForOptions().setTimeout(5000));
            return error.isVisible();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessageText() {
        return page.locator(errorMessage).innerText();
    }
}
