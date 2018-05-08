import basicStaff.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import robotaUA.GeneralPage;
import robotaUA.SearchResaultPage;

import java.util.ArrayList;
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
        kievVacantion.forEach(System.out::println);
       /* dneprVaCnation = searchForJobs("Днепр");
        lvivVaCantion = searchForJobs("Львов");

        System.out.println("------------");
        dneprVaCnation.forEach(System.out::println);
        System.out.println("------------");
        lvivVaCantion.forEach(System.out::println);*/

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
        int allVacantions = openedSearchPage.vacations().getElements("//table[@id=\"content_vacancyList_gridList\"]/tbody/tr").size() - 1;
        log("readied vancies : "+allVacantions);
        for (int i = 1; i <= j; i++) {
            System.out.println(i);
            if (i <= allVacantions ) {
                if(checkTimeOfVacation(i)) {
                    System.out.println(checkTimeOfVacation(i));
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
                }else j++;
            } else break;
        }
        return arrForThisMethod;
    }

   /* private void recurs(int z){
           if(checkTimeOfVacation(z)) {
                    System.out.println(checkTimeOfVacation(z));
                    companyName = openedSearchPage.vacations().getCompanyName(z).getText();
                    System.out.println(companyName);
                    if (companyName.contains("Automation") || companyName.contains("Test")) {
                        arrForThisMethod.add("city of vacantion : "
                                + city + " name of company : "
                                + companyName
                                + " name of vacantion : "
                                + openedSearchPage.vacations().getVacantionName(z).getText()
                                + " date of publication is : "
                                + getDataOfPublication(z));

                    } else j++;
                }else j++;
    }*/


    private String getDataOfPublication(int i) {
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
       String fullDayOfPublication = getDataOfPublication(numberOfVacation);
       String dayOfPublication = fullDayOfPublication.charAt(0)+ "" + fullDayOfPublication.charAt(1)+"";
        String curentDay = getCurentDay();
        boolean b = (Integer.parseInt(dayOfPublication) + 3) <= Integer.parseInt(getCurentDay());
        System.out.println(b);
        log("dayOfPublication" + dayOfPublication + "CurentDay " + curentDay + "boolean resalt is : " + b);
        return b;
    }


  //  @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
