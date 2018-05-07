package robotaUA;

import basicStaff.PageBase;
import org.openqa.selenium.WebDriver;

public class GeneralPage extends PageBase {
    public GeneralPage(WebDriver driver) {
        super(driver);
    }

    public SearchBlock searchBlock(){
        return new SearchBlock(getElement("//div[@id='content_vacSearch_pnlsearch']"));
    }

    @Override
    public String getExpectedUrl() {
        return super.getExpectedUrl();
    }
}
