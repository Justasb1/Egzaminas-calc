import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.example.Main;
import org.testng.annotations.Test;

public class RegistrcijaTest {


    @BeforeMethod
    public static void setUp() {
        Main.setup();
    }

    @AfterMethod
    public static void closeBrowser(ITestResult result){

        Main.closeBrowser();
    }


    @Test(priority = 1)
    public void registrationOfNewUserPositive(){
        Main.waitForElem(Main.createNewAccountLink);
        Main.createNewUser();
        String actualTitle = Main.textForAssertionNewUserLogin(Main.signInMessage);
        String expectedTitle = "Naujos paskyros sukūrimas";
        Assert.assertEquals(actualTitle, expectedTitle);
        Main.fillingRegistrationForm();
        String actualUser = Main.textForAssertionNewUserLogin(By.cssSelector("body h3") );
        String expectedUser = "Skaičiuotuvas jautrus neigiamiems skaičiams ;)";
        Assert.assertEquals(actualUser, expectedUser);
    }

    @Test(priority = 2)
    public void registrationOfNewUserNegative(){
        Main.waitForElem(Main.createNewAccountLink);
        Main.createNewUser();
        String actualTitle = Main.textForAssertionNewUserLogin(Main.signInMessage);
        String expectedTitle = "Naujos paskyros sukūrimas";
        Assert.assertEquals(actualTitle, expectedTitle);
        Main.fillingRegistrationFormNegative();
        String actualErrorMessage = Main.textForAssertionNewUserLogin(By.id("username.errors"));
        String expectedErrorMessage = "Privaloma įvesti nuo 3 iki 32 simbolių";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
        String actualErrorMessage1 = Main.textForAssertionNewUserLogin(By.id("password.errors"));
        String expectedErrorMessage1 = "Privaloma įvesti bent 3 simbolius";
        Assert.assertEquals(actualErrorMessage1, expectedErrorMessage1);
    }



}
