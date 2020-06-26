package tests;

import PageObjects.tests.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class tests {

    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    CustomerServicePage customerServicePage;
    Summarypage summarypage;
    MyAccountPage myAccountPage;
   ProductsPage productsPage;

    @BeforeTest

    public void setupdriver(){

        System.setProperty("webdriver.chrome.driver","path\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();

    }
    @Test(priority=0)
    public void verifylogin() throws InterruptedException
    {
        loginPage=new LoginPage(driver);
        myAccountPage=new MyAccountPage(driver);
        loginPage.enterLoginDetails("ahed123@gmail.com","Demo@123");
        String user=myAccountPage.verifyLoggedInUserName();
        Assert.assertEquals(user, "My account");
        System.out.println("User successfully logged In");
    }

    @Test(priority=1)
    public void verifyWelcomeMessage() throws InterruptedException
    {
        loginPage=new LoginPage(driver);
        myAccountPage=new MyAccountPage(driver);
        String message=myAccountPage.verifyWelcomeMessage();
        Assert.assertTrue(message.contains("Welcome to your account"));
        System.out.println("Welcome Message Displayed to user");
    }

    @Test(priority=2)
    public void verifyAccountTiles() throws InterruptedException
    {
        loginPage=new LoginPage(driver);
        myAccountPage=new MyAccountPage(driver);
        ArrayList<String> accounttiles_expected= new ArrayList<String>();
        ArrayList<String> accounttiles_actual= new ArrayList<String>();
        accounttiles_expected.add("ORDER HISTORY AND DETAILS");
        accounttiles_expected.add("MY CREDIT SLIPS");
        accounttiles_expected.add("MY ADDRESSES");
        accounttiles_expected.add("MY PERSONAL INFORMATION");
        accounttiles_expected.add("MY WISHLISTS");
        accounttiles_actual=myAccountPage.verifyAccountTiles();
        Assert.assertTrue(accounttiles_actual.containsAll(accounttiles_expected));
        System.out.println("Account Tiles are expected ones");
    }

    @Test(priority=3)
    public void verifyAddingProductIntoCart() throws InterruptedException
    {
        productsPage=new ProductsPage(driver);
        String value=productsPage.addProductToCart();
        Assert.assertTrue(value.equals("1"));
        System.out.println("1 product added to cart");
    }

    @Test(priority=4)
    public void verifyRemoveProduct() throws InterruptedException
    {
        productsPage=new ProductsPage(driver);
        String cartVal=productsPage.removeProductsFromCart();
        System.out.println(cartVal);
        Assert.assertTrue(cartVal.equals("(empty)"));
    }

    @Test(priority=5)
    public void verifyContactUs() {
        customerServicePage = new CustomerServicePage(driver);
        String issueMsg = null;
        issueMsg = customerServicePage.sendMsg("ahed123@gmail.com","Issue","Issue");
        Assert.assertEquals(issueMsg, "Your message has been successfully sent to our team.");
        System.out.println("Your message has been successfully sent to our team.");
    }

    @Test(priority =6)
    public void goToHome(){
        homePage = new HomePage(driver);
        String homeValidation = null;
        homeValidation = homePage.goToHome();
        Assert.assertEquals(homeValidation, "WOMEN");
        System.out.println("WOMEN");
    }
    @Test(priority=7)
    public void addItemfromHome(){
        homePage = new HomePage(driver);
       String image = null;
        image = homePage.shoppingFromHomePage();
        Assert.assertTrue(image.contains("T-shirts"));
        System.out.println("Product is displayed in the cart");
    }

    @Test(priority=8)
    public void verifyTilesInSummary() throws InterruptedException {
        summarypage = new Summarypage(driver);
        ArrayList<String> summaryList = new ArrayList(Arrays.asList("01. Summary", "02. Sign in", "03. Address","04. Shipping","05. Payment"));
        ArrayList<String> toValidateSummaryPage = new ArrayList();
        toValidateSummaryPage = summarypage.validateSummary();
       Assert.assertTrue(summaryList.containsAll(toValidateSummaryPage));
    }

    @Test(priority=9)
    public void signOut(){
        loginPage = new LoginPage(driver);
        String signOutMsg = null;
        signOutMsg = loginPage.logOff();
        Assert.assertTrue(signOutMsg.contains("Sign in"));
        System.out.println("user signed out");
        }
    }
