package AnnotationTests;

import AnnotationBase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        driver.navigate().to("https://practicetestautomation.com/");
    }

    @Test
    public void verifyThatUserCanLogIn(){

        homePage.clickOnPracticeButton();
        practicePage.clickOnTestLoginPageButton();

        loginPage.inputUsername("student");
        loginPage.inputPassword("Password123");
        loginPage.clickOnSubmitButton();

        Assert.assertTrue(profilePage.logOutButton.isDisplayed());
        Assert.assertTrue(profilePage.message.isDisplayed());
    }

    @Test
    public void verifyThatUserCannotLoginWithInvalidUsername(){

        homePage.clickOnPracticeButton();
        practicePage.clickOnTestLoginPageButton();

        loginPage.inputUsername("incorrectUser");
        loginPage.inputPassword("Password123");
        loginPage.clickOnSubmitButton();

        String expectedErrorMessage = "Your username is invalid!";
        String actualErrorMessage = loginPage.errorText();

        // Error message check
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

        // url check
        String expectedURL = "https://practicetestautomation.com/practice-test-login/";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
    }
}
