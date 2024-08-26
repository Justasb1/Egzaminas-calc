import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.example.Main;
import org.testng.annotations.Test;

public class PrisijungimasTest {


    @BeforeMethod
    public static void setUp() {
        Main.setup();
    }

    @AfterMethod
    public static void closeBrowser(ITestResult result){

        Main.closeBrowser();
    }


    @Test(priority = 1)
    public void existingUserLoginPositive(){
        String actualTitle = Main.textForAssertionNewUserLogin(Main.titleTextForLogin);
        String expectedTitle = "Prisijungimas";
        Assert.assertEquals(actualTitle, expectedTitle);
        Main.fillLoginForm();
        String actualUser = Main.textForAssertionNewUserLogin(Main.checkLogInTitle);
        String expectedUser = "Skaičiuotuvas jautrus neigiamiems skaičiams ;)";
        Assert.assertEquals(actualUser, expectedUser);
    }

    @Test(priority = 2)
    public void existingUserLoginNegative(){
        String actualTitle = Main.textForAssertionNewUserLogin(Main.titleTextForLogin   ) ;
        String expectedTitle = "Prisijungimas";
        Assert.assertEquals(actualTitle, expectedTitle);
        Main.fillLoginFormNegative();
        String actualErrorMessage = Main.textForAssertionNewUserLogin(Main.logInFailureMessage);
        String expectedErrorMessage1 = "Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage1);
    }



}
