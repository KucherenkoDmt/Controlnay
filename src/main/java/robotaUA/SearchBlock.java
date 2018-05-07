package robotaUA;

import elementBase.elaementBaseClasses.ElementBase;
import elementBase.elaementBaseClasses.Input;
import org.openqa.selenium.WebElement;

public class SearchBlock  extends ElementBase {

    public SearchBlock(WebElement element) {
        super(element);
    }

    public Input nameOfVacantionFields(){
        return new Input(getElement("//input[@id=\"content_vacSearch_Keyword\"]"));
    }
    public Input cityOfVacantion(){
        return new Input(getElement("//input[@id=\"content_vacSearch_CityPickerWork_inpCity\"]"));
    }
    public ElementBase sabmitSearch(){
        return new ElementBase(getElement("//a[@id=\"content_vacSearch_lnkSearch\"]"));
    }

}
