import log.ConsoleLogger;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class VacanciesQaEngieneir {
    WebDriver driver;
    ArrayList<String> kievVacantion;
    ArrayList<String> dneprVaCnation;
    ArrayList<String> lvivVaCantion;
    ArrayList<String> cityesForTest;



    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //  driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        kievVacantion = new ArrayList<>();
        dneprVaCnation = new ArrayList<>();
        cityesForTest = new ArrayList<>();
        lvivVaCantion = new ArrayList<>();
    }

    @Test
    public void vacantionTest() throws IOException {
        dneprVaCnation = findValues("Днепр", 5);
        kievVacantion = findValues("Киев", 5);
        lvivVaCantion = findValues("Львов", 5);
        for(String dnepr:dneprVaCnation){
            System.out.println(dnepr);
        }
        System.out.println("------------");
        for(String kiev:kievVacantion){
            System.out.println(kiev);
        }
        System.out.println("------------");
        for(String lviv:lvivVaCantion){
            System.out.println(lviv);
        }
    }

 /*   @Test
    public void sampleTest() {
        findValues("Днепр", 5);
    }*/

    public ArrayList<String> findValues(String city, int counterOfRegistration) {
        ArrayList<String> arrForThisMethod = new ArrayList<>();
        log("Open page");
        driver.get("https://rabota.ua");
        waitFor(2);
        log("Type QA Automation");
        type("//input[@id=\"content_vacSearch_Keyword\"]", "QA Automation");
        log("Choose a lokaile");
        type("//div[@class='f-input-block city-search']/input", city);
        waitFor(2);
        log("click submit");
        click("//a[@id=\"content_vacSearch_lnkSearch\"]");
        String naneOFvacantion;
        String nameOfTheCompany;
        for (int i = 1; i < counterOfRegistration+1; i++) {
            naneOFvacantion =  getText("//tbody/tr["+i+"]/td/article/div[1]/div[1]/h3");
            nameOfTheCompany = getText("//tbody/tr["+i+"]//article/div[1]/div[1]/p[1]");
            System.out.println(nameOfTheCompany+" + "+naneOFvacantion);
            arrForThisMethod.add("city of vacantion : " + city +" name of company : "+ nameOfTheCompany +" name of vacantion : "+ naneOFvacantion);
        }
        return arrForThisMethod;
    }


    protected void log(String stringToLog){
        ConsoleLogger logger = new ConsoleLogger();
        logger.log(stringToLog);
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void click(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }

    public void type(String xpath, String text) {
        driver.findElement(By.xpath(xpath)).clear();
        driver.findElement(By.xpath(xpath)).sendKeys(text);
    }
    public String getText(String xpath){
        return driver.findElement(By.xpath(xpath)).getText();
    }

    public void waitFor(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}