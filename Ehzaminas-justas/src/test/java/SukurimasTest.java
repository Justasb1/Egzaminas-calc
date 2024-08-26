import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.example.Main;
import org.testng.annotations.Test;

public class SukurimasTest {


    @BeforeMethod
    public static void setUp() {
        Main.setup();
    }

    @AfterMethod
    public static void closeBrowser(ITestResult result){

        Main.closeBrowser();
    }


    @Test(priority = 1)
    public void createNewRecordPositive() {
        Main.fillLoginForm();
        Main.putInInputNumbersAndCount("10","3","-");
        String actualResult = Main.textForAssertionNewUserLogin(By.xpath("/html/body/h4"));
        Main.waitForElem(By.xpath("/html/body/h4"));
        String expectedResult = "10 * 3 = 30";
        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test(priority = 2)
    public void createNewRecordNegative() {
        Main.fillLoginForm();
        Main.putInInputNumbersAndCount("-10","3","-");
        String actualResult = Main.textForAssertionNewUserLogin(By.cssSelector(".error"));
        String expectedResult = "Validacijos klaida: skaičius negali būti neigiamas";
        Assert.assertEquals(actualResult, expectedResult);
    }



}
