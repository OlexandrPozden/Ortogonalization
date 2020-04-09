import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FirstTest {
    WebDriver driver;

    @BeforeClass
    public void beforeMet() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Nazar/.m2/repository/webdriver/chromedriver/win32/80.0.3987.106/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.phptravels.net/home");

        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
    }


    @Test
    public void test1() {
        //Arrange
        String name = "Bob",
                lastName = "Marley";


        //Act
        driver.findElement(By.cssSelector(".dropdown-login")).click();
        driver.findElement(By.xpath("//div[contains(@class, 'dropdown-login')]//a[contains (text(), 'Sign Up')]")).click();
        driver.findElement(By.xpath("//input[@name = 'firstname']")).sendKeys(name);
        driver.findElement(By.xpath("//input[@name = 'lastname']")).sendKeys(lastName);
        driver.findElement(By.xpath("//input[@name = 'phone']")).sendKeys("5656565656");
        driver.findElement(By.xpath("//input[@name = 'email']")).sendKeys("bobmarley4@gmail.com");
        driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys("123456789");
        driver.findElement(By.xpath("//input[@name = 'confirmpassword']")).sendKeys("123456789");
        driver.findElement(By.cssSelector("button.signupbtn")).click();
        String actual = driver.findElement(By.cssSelector(".text-align-left")).getText();

        //Assert
        Assert.assertEquals(actual, "Hi, " + name + " " + lastName);
    }

    @Test
    public void test2() {

        driver.findElement(By.cssSelector(".dropdown-login")).click();
        driver.findElement(By.xpath("//div[contains(@class, 'dropdown-login')]//a[contains (text(), 'Sign Up')]")).click();
        driver.findElement(By.xpath("//input[@name = 'firstname']")).sendKeys("Bob");
        driver.findElement(By.xpath("//input[@name = 'lastname']")).sendKeys("Marley");
        driver.findElement(By.xpath("//input[@name = 'phone']")).sendKeys("5656565656");
        driver.findElement(By.xpath("//input[@name = 'email']")).sendKeys("bobmarley4@gmail.com");
        driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys("123456789");
        driver.findElement(By.xpath("//input[@name = 'confirmpassword']")).sendKeys("123456789");

        driver.findElement(By.cssSelector("button.signupbtn")).click();

        String actual = driver.findElement(By.cssSelector(".text-align-left")).getText();

        Assert.assertEquals(actual, "Hi, Bob Marley");
    }

    @Test
    public void test3() {

        driver.findElement(By.cssSelector(".dropdown-login")).click();
        driver.findElement(By.xpath("//div[contains(@class, 'dropdown-login')]//a[contains (text(), 'Sign Up')]")).click();
        driver.findElement(By.xpath("//input[@name = 'firstname']")).sendKeys("Bob");
        driver.findElement(By.xpath("//input[@name = 'lastname']")).sendKeys("Marley");
        driver.findElement(By.xpath("//input[@name = 'phone']")).sendKeys("5656565656");
        driver.findElement(By.xpath("//input[@name = 'email']")).sendKeys("bobmarley4@gmail.com");
        driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys("123456789");
        driver.findElement(By.xpath("//input[@name = 'confirmpassword']")).sendKeys("123456789");

        driver.findElement(By.cssSelector("button.signupbtn")).click();

        String actual = driver.findElement(By.cssSelector(".text-align-left")).getText();

        Assert.assertEquals(actual, "Hi, Bob Marley");
    }

    @BeforeClass
    public void close() {
        driver.quit();
    }
}
