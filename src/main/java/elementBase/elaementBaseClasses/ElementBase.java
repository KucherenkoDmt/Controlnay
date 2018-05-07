package elementBase.elaementBaseClasses;

import elementBase.interfasesForElementBase.ElementBaseInterface;
import elementBase.interfasesForElementBase.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementBase implements Getter, ElementBaseInterface {

    private WebElement element;

    private ElementBase(){}

    public ElementBase(WebElement element){
        this.element = element;
    }

    @Override
    public void click() {
        this.element.click();
    }

    @Override
    public String getText() {
        return this.element.getText();
    }

    @Override
    public boolean isDisplayed() {
        return this.element.isDisplayed();
    }

    @Override
    public List<WebElement> getElements(String xpath) {
        return this.element.findElements(By.xpath(xpath));
    }

    @Override
    public WebElement getElement(String xpath) {
        return this.element.findElement(By.xpath(xpath));
    }

    protected WebElement getWebElement(){
          return this.element;
    }
}
