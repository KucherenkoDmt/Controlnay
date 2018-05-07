package robotaUA;

import elementBase.elaementBaseClasses.ElementBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Vacations extends ElementBase {

   public Vacations(WebElement element) {
        super(element);
    }

    public ElementBase getCompanyName(int numberOfVacantion){
        return new ElementBase(getElement("//tbody/tr[" + numberOfVacantion + "]/descendant::h3"));
    }
    public ElementBase getVacantionName(int numberOfVacantion){
        return new ElementBase(getElement("//tbody/tr[" + numberOfVacantion + "]/descendant::a[@class='f-text-dark-bluegray f-visited-enable']"));
    }
    public ElementBase getTimeValue(int numberOfVacantion){
        return new ElementBase(getElement("//tbody/tr[" + numberOfVacantion + "]/descendant::p[@class='f-vacancylist-agotime f-text-light-gray fd-craftsmen']"));
    }
    public ElementBase getLinkByCompanyName(int numberOfVacantion){
        return new ElementBase(getElement("//tbody/tr[" + numberOfVacantion + "]/descendant::h3/a"));
    }

    @Override
    public List<WebElement> getElements(String xpath) {
        return super.getElements(xpath);
    }
}
