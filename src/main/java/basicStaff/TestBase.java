package basicStaff;

import log.ConsoleLogger;
import log.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestBase {
    protected WebDriver driver;



    protected void log(String stringToLog) {
        Logger logger = new ConsoleLogger();
        logger.log(stringToLog);
    }
    protected void waitFor(int sec) {
        try {
            log("wait :" + sec*1000);
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    protected String chooseActiveElement(String xpath1, String xpath2) {
        if (isElementPresent(xpath1)) {
            log("xpath1 works");
            return xpath1;
        }
        if (isElementPresent(xpath2)) {
            log("xpath2 works");
            return xpath2;
        }
        log("both xpath doesn't works");
        return "";
    }

    protected Boolean isElementPresent(String xpath) {
        return driver.findElements(By.xpath(xpath)).size() != 0;
    }
}
