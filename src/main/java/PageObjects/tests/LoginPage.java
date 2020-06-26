package PageObjects.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver;
    @FindBy(id="email")
    WebElement emailField;

    @FindBy(id="passwd")
    WebElement passwordField;

    @FindBy(id="SubmitLogin")
    WebElement submit_button;

    @FindBy(className="login")
    WebElement signIn_link;

    @FindBy(linkText = "Sign out")
    WebElement signOut;

    @FindBy(linkText = "Sign in")
    WebElement signIn;

    public LoginPage(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterLoginDetails(String email,String password) throws InterruptedException
    {
        WebDriverWait wait= new WebDriverWait(driver,120);
        wait.until(ExpectedConditions.visibilityOf(signIn_link));
        signIn_link.click();
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        submit_button.click();
    }
    public String logOff(){
        WebDriverWait wait= new WebDriverWait(driver,120);
        wait.until(ExpectedConditions.visibilityOf(signOut));
        signOut.click();
        return signIn.getText();
    }

}

