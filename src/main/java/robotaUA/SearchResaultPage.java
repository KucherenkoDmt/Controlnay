package robotaUA;

import basicStaff.PageBase;
import org.openqa.selenium.WebDriver;

public class SearchResaultPage extends PageBase {

    public SearchResaultPage(WebDriver driver) {
        super(driver);
    }

    public Vacations vacations(){
        return new Vacations(getElement("//table[@id=\"content_vacancyList_gridList\"]/tbody"));
    }

}
