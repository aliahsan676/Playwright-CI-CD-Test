package com.cicdtest.pom.Test;

import com.aventstack.extentreports.Status;
import com.cicdtest.pom.Pages.AddProductPage;
import com.cicdtest.pom.Util.BaseTest;
import com.cicdtest.pom.Util.ConfigReader;
import com.cicdtest.pom.Util.ExtentManager;
import com.cicdtest.pom.Util.General;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.cicdtest.pom.Pages.LoginPage;

public class AddProductTest extends BaseTest {
    private LoginPage loginPage;

    private AddProductPage addProductPage;

    @BeforeMethod
    public void setupPage() {
        loginPage = new LoginPage(getPage());
        addProductPage = new AddProductPage(getPage());
        ExtentManager.initReport();
    }

    @Test
    public void testAddProductSuccessfully() {
        test = ExtentManager.startTest("Add Product Successfully in Cart");
        SoftAssert softAssert = new SoftAssert();

        loginPage.navigateToLogin(ConfigReader.getProperty("url"));
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
        test.log(Status.INFO, "Logged into the system");

        addProductPage.navigateToProductsSection();
        test.log(Status.INFO, "Navigated to Products section");

        addProductPage.searchProduct();
        test.log(Status.INFO, "Search a specific product");

        General.waitForDivStable();

        addProductPage.viewProduct();
        test.log(Status.INFO, "View the details of the product");

        addProductPage.addToCart();
        test.log(Status.INFO, "Add the product to cart");

        addProductPage.navigateToCart();
        test.log(Status.INFO, "Navigate to cart Page");

        General.waitForDomStable();

        softAssert.assertTrue(addProductPage.isProductVisibleInCart(), "Added product should be visible");
        test.log(Status.PASS, "Added product is present in Cart page");

        softAssert.assertAll();
    }

}
