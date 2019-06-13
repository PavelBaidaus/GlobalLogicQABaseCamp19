import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.ChromeDriverManager


public class NotePadInitialTests {

    WebDriver driver;
    WebDriverWait wait;


    @Before
    public void openBrowser()
    {

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 5);
    }

    @Test
    public void calculatorTest()
    {
        driver.get("https://anotepad.com/");

        driver.findElement(By.id("edit_title")).sendKeys("My First Note");
        driver.findElement(By.id("btnSaveNote")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".alert.alert-warning"), "You have saved your note as a"));

        Assert.assertEquals("You have saved your note as a", driver.findElement(By.cssSelector(".alert.alert-warning")).getText());

        driver.findElement(By.cssSelector(".delete")).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();
        alert.accept();

        Assert.assertEquals("No note here.", driver.findElement(By.cssSelector(".saved_notes div")).getText());


    }


    @After
    public void closeBrowser() {driver.quit();}

}
