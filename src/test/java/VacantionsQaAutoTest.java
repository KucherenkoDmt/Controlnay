import basicStaff.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import robotaUA.GeneralPage;
import robotaUA.SearchResaultPage;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class VacantionsQaAutoTest extends TestBase {

    private String jobsForSearch;
    private ArrayList<String> kievVacantion;
    private ArrayList<String> dneprVaCnation;
    private ArrayList<String> lvivVaCantion;
    private int counterOfVacantion = 5;
    SearchResaultPage openedSearchPage;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        jobsForSearch = "QA Automations";
        kievVacantion = new ArrayList<>();
        dneprVaCnation = new ArrayList<>();
        lvivVaCantion = new ArrayList<>();
        openedSearchPage = new SearchResaultPage(driver);
    }

    @Test
    public void vacantionsTest() {
        kievVacantion = searchForJobs("Киев");
        dneprVaCnation = searchForJobs("Днепр");
        lvivVaCantion = searchForJobs("Львов");
        kievVacantion.forEach(System.out::println);
        System.out.println("------------");
        dneprVaCnation.forEach(System.out::println);
        System.out.println("------------");
        lvivVaCantion.forEach(System.out::println);

    }

    private ArrayList<String> searchForJobs(String city) {
        ArrayList<String> arrForThisMethod = new ArrayList<>();
        GeneralPage generalPage = new GeneralPage(driver);

        log("open page " + generalPage.getExpectedUrl());
        driver.get(generalPage.getExpectedUrl());
        waitFor(2);
        log("type looking job");
        generalPage.searchBlock().nameOfVacantionFields().typeText(jobsForSearch);
        log("type city for search job");
        generalPage.searchBlock().cityOfVacantion().typeText(city);
        waitFor(1);
        generalPage.searchBlock().cityOfVacantion().pressEnter();
        waitFor(1);
        int j = counterOfVacantion;
        String companyName;
        int allVacantions = openedSearchPage.vacations().getElements("//table[@id=\"content_vacancyList_gridList\"]/tbody/tr").size()-1;
        System.out.println(allVacantions);
        for (int i = 1; i <= j; i++) {
            if (i <= allVacantions) {
                companyName = openedSearchPage.vacations().getCompanyName(i).getText();
                System.out.println(companyName);
                if (companyName.contains("Automation") || companyName.contains("Test")) {
                    arrForThisMethod.add("city of vacantion : "
                            + city + " name of company : "
                            + companyName
                            + " name of vacantion : "
                            + openedSearchPage.vacations().getVacantionName(i).getText()
                            + " date of publication is : "
                            + getDataOfPublication(i));

                } else j++;
            } else break;
        }
        return arrForThisMethod;
    }


    public String getDataOfPublication(int i) {
        String time;
        log("get data click");
        openedSearchPage.vacations().getLinkByCompanyName(i).click();
        log("get data");
        time = driver.findElement(By.xpath(chooseActiveElement("//li[@id=\"d-date\"]/span[2]", "//div[@class='fd-f-left']/span[2]"))).getText();
        waitFor(1);
        driver.navigate().back();
        return time;
    }


    private boolean checkTimeOfVacation(int numberOfVacation) {
        log("checkTimeOfVacation");
        String timeValue;
        try {
            timeValue = openedSearchPage.vacations().getTimeValue(numberOfVacation).getText();
            System.out.println(timeValue);
        } catch (Exception e) {
            e.getStackTrace();
            log("Not a time of public vacation on page");
            return false;
        }
        return true;
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
