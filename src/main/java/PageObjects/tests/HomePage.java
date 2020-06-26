package PageObjects.tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    WebDriver driver;

    @FindBy(css="a.home")
    WebElement home;

    @FindBy(className="product_img_link")
    private WebElement productItem;

    @FindBy(className="exclusive")
    private WebElement addToCart;

    @FindBy(className="cross")
    private WebElement closePopUp;

    @FindBy(className="cross")
    private WebElement closePopMsg;

   @FindBy(css = "div.shopping_cart>a")
   WebElement shoppingCart;

    @FindBy(className="cart-images")
    private WebElement productNameCart;

    @FindBy(className ="sf-with-ul")
    private WebElement homeValidate;

    @FindBy(className = "ajax_add_to_cart_button")
    private WebElement hoverAddCart;

    @FindBy(className="button-medium")
    private WebElement proceedToCheckout;

    public HomePage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String goToHome(){
        WebDriverWait wait= new WebDriverWait(driver,120);
        wait.until(ExpectedConditions.visibilityOf(home));
        home.click();
        wait.until(ExpectedConditions.visibilityOf(homeValidate));
        return homeValidate.getText();
    }

    public String shoppingFromHomePage(){
        WebDriverWait wait= new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOf(productItem));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productItem);
        String imageDetails = null;
        try {
            Thread.sleep(500);
            Actions builder = new Actions(driver);
            builder.moveToElement(productItem).build().perform();
            Thread.sleep(10000);
            hoverAddCart.click();
            Thread.sleep(3000);
            proceedToCheckout.click();
            Thread.sleep(3000);
            shoppingCart.click();
            Thread.sleep(3000);
            imageDetails = productNameCart.getAttribute("title");
            System.out.println(imageDetails);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return imageDetails;
    }
}
