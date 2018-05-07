package basicStaff;

import elementBase.interfasesForElementBase.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class PageBase implements Getter {
    private WebDriver driver;

    private String url = "https://rabota.ua/";

    public PageBase(WebDriver driver){
        this.driver=driver;
    }

    public String getExpectedUrl() {
        return url;
    }

    @Override
    public List<WebElement> getElements(String xpath) {
        return driver.findElements(By.xpath(xpath));
    }

    @Override
    public WebElement getElement(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

}
