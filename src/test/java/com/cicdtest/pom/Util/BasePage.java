package com.cicdtest.pom.Util;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class BasePage {
    protected Page page;

    // Constructor
    public BasePage(Page page) {
        this.page = page;
    }

    // Common helper methods

    public void click(Locator locator) {
        locator.click();
    }

    public void fill(Locator locator, String text) {
        locator.fill(text);
    }

    public void waitForVisibility(Locator locator, int timeoutMillis) {
        locator.waitFor(new Locator.WaitForOptions().setTimeout(timeoutMillis));
    }

    public boolean isElementVisible(Locator locator) {
        try {
            locator.waitFor(new Locator.WaitForOptions().setTimeout(5000));
            return locator.isVisible();
        } catch (Exception e) {
            return false;
        }
    }

    public String getText(Locator locator) {
        try {
            return locator.innerText();
        } catch (Exception e) {
            return "";
        }
    }

}
