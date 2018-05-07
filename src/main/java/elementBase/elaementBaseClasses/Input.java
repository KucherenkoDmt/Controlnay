package elementBase.elaementBaseClasses;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class Input extends ElementBase implements elementBase.interfasesForElementBase.Input {
    public Input(WebElement element) {
        super(element);
    }

    @Override
    public void typeText(String text) {
        clean();
        getWebElement().sendKeys(text);
    }
    public void pressEnter() {
        getWebElement().sendKeys(Keys.ENTER);
    }

    @Override
    public void addText(String text) {
        getWebElement().sendKeys(text);
    }

    @Override
    public void clean() {
        getWebElement().clear();
    }

    @Override
    public void submitText(String text) {
    }
}
