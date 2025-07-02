package com.cicdtest.pom.Pages;

import com.cicdtest.pom.Util.General;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AddProductPage {
    private final Page page;

    public AddProductPage(Page page) {
        this.page = page;
    }

    public void navigateToProductsSection() {
        page.locator("//a[@href='/products']").click();

    }

    public void searchProduct() {
        page.locator("//input[@id='search_product']").click();
        page.locator("//input[@id='search_product']").fill("Winter Top");
        page.locator("//button[@id='submit_search']").click();

    }

    public void viewProduct(){

        page.mouse().wheel(0, 800); // Scroll down the page
        General.waitForDivStable();

        page.locator("//a[normalize-space()='View Product']").click();
    }

    public void addToCart(){
        page.locator("//button[normalize-space()='Add to cart']").click();
        General.waitForDivStable();
        page.locator("//button[normalize-space()='Continue Shopping']").click();
    }

    public void navigateToCart(){
        page.locator("body > header:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > ul:nth-child(1) > li:nth-child(3) > a:nth-child(1)").click();
    }

    public boolean isProductVisibleInCart() {
        return page.locator("//a[normalize-space()='Winter Top']").isVisible();
    }



}
