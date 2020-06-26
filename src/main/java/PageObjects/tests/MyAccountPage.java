package PageObjects.tests;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountPage {

    WebDriver driver;
    @FindBy(className="navigation_page")
    WebElement myaccount_Header;

    @FindBy(id="passwd")
    WebElement passwordField;

    @FindBy(id="SubmitLogin")
    WebElement submit_button;

    @FindBy(className="login")
    WebElement signIn_link;

    @FindBy(xpath="//div[@id='center_column']/p")
    WebElement welcome_text;

    @FindBy(xpath="//ul[@class='myaccount-link-list']/li//span")
    List<WebElement> accountTiles;

    public MyAccountPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String verifyLoggedInUserName() throws InterruptedException
    {
        WebDriverWait wait= new WebDriverWait(driver,120);
        wait.until(ExpectedConditions.visibilityOf(myaccount_Header));
        String user=myaccount_Header.getText();
        return user;
    }

    public String verifyWelcomeMessage() throws InterruptedException
    {
        WebDriverWait wait= new WebDriverWait(driver,120);
        wait.until(ExpectedConditions.visibilityOf(myaccount_Header));
        String message=welcome_text.getText();
        return message;
    }

    public ArrayList<String> verifyAccountTiles() throws InterruptedException
    {
        ArrayList<String> accounttiles= new ArrayList<String>();
        WebDriverWait wait= new WebDriverWait(driver,120);
        for(WebElement ele:accountTiles)
        {
            String tiles=ele.getText();
            accounttiles.add(tiles);
        }
        System.out.println("Account tiles from application"+accounttiles);
        return accounttiles;
    }






}

