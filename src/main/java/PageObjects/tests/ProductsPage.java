package PageObjects.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {

    WebDriver driver;

    @FindBy(xpath="//a[@title='View my shopping cart']")
    WebElement viewCart;

    @FindBy(xpath="//span[@class='remove_link']//a[@rel='nofollow']")
    WebElement removeProduct;

    @FindBy(xpath="//span[@class='ajax_cart_no_product']")
    WebElement cartValue;

    @FindBy(xpath="//a[@title='Women']")
    WebElement women_link;

    @FindBy(linkText="T-shirts")
    WebElement Tshirts_link;

    @FindBy(className="page-heading product-listing")
    WebElement items;

    @FindBy(xpath="//div[@class='product-image-container']")
    WebElement productImage;

    @FindBy(xpath="//span[contains(text(),'Add to cart')]")
    WebElement AddToCartButton;

    @FindBy(xpath="//span[@class='cross']")
    WebElement cancelbutton;

    @FindBy(xpath="//div[@class='shopping_cart']//a[@title='View my shopping cart']//span")
    WebElement cartValueField;

    public ProductsPage(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String addProductToCart()
    {
        WebDriverWait wait= new WebDriverWait(driver,60);
        Actions act= new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(women_link));
        act.moveToElement(women_link).build().perform();
        wait.until(ExpectedConditions.visibilityOf(Tshirts_link));
        Tshirts_link.click();
        wait.until(ExpectedConditions.visibilityOf(productImage));
        act.moveToElement(productImage).build().perform();
        wait.until(ExpectedConditions.visibilityOf(AddToCartButton));
        AddToCartButton.click();
        wait.until(ExpectedConditions.visibilityOf(cancelbutton));
        cancelbutton.click();
        String value=cartValueField.getText();
        return value;
    }

    public String removeProductsFromCart() throws InterruptedException
    {
        Actions act= new Actions(driver);
        WebDriverWait wait= new WebDriverWait(driver,60);
        wait.until(ExpectedConditions.visibilityOf(viewCart));
        act.moveToElement(viewCart).build().perform();
        wait.until(ExpectedConditions.visibilityOf(removeProduct));
        removeProduct.click();
        wait.until(ExpectedConditions.visibilityOf(cartValue));
        String cartVal=cartValue.getText();
        return cartVal;
    }
}
