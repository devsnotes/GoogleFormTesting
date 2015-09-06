import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class MainTest {
    @Test
    public void testGoogleFormCompleteDeprecated() throws Exception {
        Selenium selenium = new DefaultSelenium("localhost", 4444, "chrome", "https://docs.google.com/forms/d/1J0G7EpjpXjKolkHedBziu-bJWAR8vgyCdMhKlEgoutw/viewform");
        selenium.start();
        selenium.open("");
        selenium.windowMaximize();
        selenium.focus("entry.869633803");
        selenium.type("css=input[name=\"entry.869633803\"]", "10");
        selenium.click("group_779788489_1");
        selenium.click("entry.676519541");
        selenium.click("group_676519541_4");

        selenium.select("entry_855085148", "index=1");
        selenium.type("entry_676519541_other_option_response", "20");

        selenium.click("ss-submit");

        Thread.sleep(2000);
        assertEquals(selenium.getText("//div[@class='ss-resp-message']"), "Your response has been recorded.");

        selenium.close();
    }

    @Test
    public void testGoogleFormComplete() throws Exception {
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("https://docs.google.com/forms/d/1J0G7EpjpXjKolkHedBziu-bJWAR8vgyCdMhKlEgoutw/viewform");
        //driver.manage().window().maximize();
        driver.findElement(By.name("entry.869633803")).sendKeys("30");
        driver.findElement(By.id("group_779788489_1")).click();
        driver.findElement(By.name("entry.676519541")).click();
        driver.findElement(By.id("group_676519541_4")).click();
        driver.findElement(By.name("entry.676519541.other_option_response")).sendKeys("40");

        Select select = new Select(driver.findElement(By.id("entry_855085148")));
        select.selectByIndex(1);

        driver.findElement(By.id("ss-submit")).click();

        Thread.sleep(2000);
        assertEquals(driver.findElement(By.xpath("//div[@class='ss-resp-message']")).getText(), "Your response has been recorded.");

        driver.close();
    }
}