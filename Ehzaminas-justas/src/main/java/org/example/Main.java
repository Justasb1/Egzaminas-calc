package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Main {
    public static WebDriver Browser;

    public static final int SECONDS_WAIT_TIME_FOR_ELEMENT = 5;

    public static By createNewAccountLink = By.cssSelector("[href^='/registruoti']");

    public static By registrationNameInput = By.id("username");

    public static By logInPassword = By.cssSelector("input[placeholder=\"Slaptažodis\"]");

    public static By registrationPasswordReconfirm = By.id("passwordConfirm");

    public static By logInNameInput = By.cssSelector("input[placeholder=\"Prisijungimo vardas\"]");

    public static By firstNumberInput = By.id("sk1");

    public static By secondNumberInput = By.id("sk2");

    public static By operationSignChoose = By.cssSelector("select[name=\"zenklas\"]");

    public static By clickCountbutton = By.xpath("//input[@value=\"skaičiuoti\"]");

    public static By registrationNameField = By.id("username");

    public static By registrationButtonSumbit = By.cssSelector("button[type=\"submit\"]");

    public static By loginNameField = By.cssSelector("input[placeholder=\"Prisijungimo vardas\"]");

    public static By titleTextForLogin = By.cssSelector(".form-heading");

    public static By checkLogInTitle = By.cssSelector("body h3");

    public static By logInFailureMessage = By.cssSelector("body > div:nth-child(5) > form:nth-child(1) > div:nth-child(2) > span:nth-child(4)");

    public static By signInMessage = By.cssSelector(".form-signin-heading");



    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-search-engine-choice-screen");
        chromeOptions.addArguments("--ignore-certificate-errors");


        Browser = new ChromeDriver(chromeOptions);
        Browser.get("http://localhost:8080/skaiciuotuvas");
    }

    public static void wait(int timeMiliseconds){
        try {
            Thread.sleep(timeMiliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

//    public static void clickElem(WebElement elem) {
//        wait(500);
//        Actions act = new Actions(Browser);
//        act.click(elem);
//        act.build().perform();
//    }

    public static void clickButton(By locator){;
        Browser.findElement(locator).click();
    }

    public static void sendKeysElem(WebElement elem, String keyword) {
        Actions act = new Actions(Browser);
        elem.clear();
        act.click(elem);
        act.sendKeys(keyword);
        act.build().perform();
    }

    public static WebElement waitForElem(By selector) {
        WebDriverWait wait = new WebDriverWait(Browser, Duration.ofSeconds(SECONDS_WAIT_TIME_FOR_ELEMENT));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }



    public static void createNewUser() {
        WebElement createNewUser = waitForElem(createNewAccountLink);
        clickButton(createNewAccountLink);
    }


//    public static void sendKeysName(String keyword){
//        WebElement sendKeysName = waitForElem(By.cssSelector("By.id(\"username\");"));
//        sendKeysElem(keyword);
//    }


    public static void sendKeysName(String keyword) {
        WebElement searchinput = waitForElem(registrationNameInput);
        searchinput.sendKeys(keyword);
    }

    public static void sendKeysPassword(String keyword) {
        WebElement searchinput = waitForElem(logInPassword);
        searchinput.sendKeys(keyword);
    }

    public static void sendKeysPasswordConfirm(String keyword) {
        WebElement searchinput = waitForElem(registrationPasswordReconfirm);
        searchinput.sendKeys(keyword);
    }


    public static void fillingRegistrationForm(){
       // Main.createNewUser();
        Main.waitForElem(registrationNameField);
        Main.sendKeysName("Julijus1234");
        Main.sendKeysPassword("zirafa123");
        Main.sendKeysPasswordConfirm("zirafa123");
        Main.clickButton(registrationButtonSumbit);
    }

    public static void fillingRegistrationFormNegative(){
       // Main.createNewUser();
        Main.waitForElem(registrationNameField);
        Main.sendKeysName("pl");
        Main.sendKeysPassword("sa");
        Main.sendKeysPasswordConfirm("sa");
        Main.clickButton(registrationButtonSumbit);
    }

    public static void sendKeysLoginName(String keyword) {
        WebElement searchinput = waitForElem(logInNameInput);
        searchinput.sendKeys(keyword);
    }

    public static void sendKeyLoginPassword(String keyword) {
        WebElement searchinput = waitForElem(logInPassword);
        searchinput.sendKeys(keyword);
    }

    public static void fillLoginForm(){
        Main.waitForElem(loginNameField);
        Main.sendKeysLoginName("Lilas");
        Main.sendKeyLoginPassword("zirafa123");
        Main.clickButton(registrationButtonSumbit);
    }

    public static void fillLoginFormNegative(){
        Main.waitForElem(loginNameField);
        Main.sendKeysLoginName("Lilas9854");
        Main.sendKeyLoginPassword("zirafa123");
        Main.clickButton(registrationButtonSumbit);
    }

    public static String textForAssertionNewUserLogin(By locator){
        WebElement element = Browser.findElement(locator);
        return element.getText();
    }


    public static void chooseSign(By locator){
        WebElement dropdownElement = Browser.findElement(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText("Daugyba");
    }

    public static void putInInputNumbersAndCount(String numberOne, String numberTwo, String result){
        WebElement firstNumber = Browser.findElement(firstNumberInput);
        firstNumber.clear();
        firstNumber.sendKeys(numberOne);
        WebElement secondNumber = Browser.findElement(secondNumberInput);
        secondNumber.clear();
        secondNumber.sendKeys(numberTwo);
        chooseSign(operationSignChoose);
        clickButton(clickCountbutton);
    }


    public static void closeBrowser(){
        Browser.close();
    }

    public static void main(String[] args) {}

}