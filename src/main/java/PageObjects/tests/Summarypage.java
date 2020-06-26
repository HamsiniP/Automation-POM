package PageObjects.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;

public class Summarypage {
    WebDriver driver;
    @FindBy(className="div.shopping_cart>a")
    private WebElement cart;

    @FindBy(className="div.row>div>ul.step")
    private WebElement summary;

    public Summarypage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ArrayList validateSummary() throws InterruptedException {
        WebDriverWait wait= new WebDriverWait(driver,20);
        Thread.sleep(5000);
        ArrayList<String> summaryList = new ArrayList();
        List<WebElement> links = driver.findElements(By.xpath("//*[@id='order_step']/li/span"));
        for (int i = 0; i < links.size(); i++)
        {
            System.out.println(links.get(i).getText());
            summaryList.add(links.get(i).getText());
        }
      return  summaryList;
    }
}
