package PageObjects.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomerServicePage {
    WebDriver driver;

    @FindBy(css="div[id=contact-link]")
    WebElement contactUs;

    @FindBy(css="select[id=id_contact]")
    WebElement subHeading;

    @FindBy(css="input[id=email]")
    WebElement email;

    @FindBy(css="input[id=id_order]")
    WebElement orderRef;

    @FindBy(css="textarea.form-control")
    WebElement message;

    @FindBy(css="button[name=submitMessage]")
    WebElement sendBtn;

    @FindBy(css="p.alert-success")
    WebElement alertMessage;

    public CustomerServicePage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public String sendMsg(String emailVal,String orderMsg,String msg) {
        WebDriverWait wait= new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOf(contactUs));
        contactUs.click();
        Select oSelect = new Select(driver.findElement(By.cssSelector("select[id=id_contact]")));
        oSelect.selectByIndex(1);
        wait.until(ExpectedConditions.visibilityOf(email));
        email.clear();
        email.sendKeys(emailVal);
        message.sendKeys(msg);
        sendBtn.click();
        System.out.println(alertMessage.getText());
       return alertMessage.getText();
    }
}
