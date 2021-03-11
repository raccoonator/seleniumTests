import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void start() {
        System.setProperty("webdriver.chrome.driver", "src/test/resource/bin/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
    }

    @Test
    void test() {
        driver.navigate().to("https://mail.ru");
        driver.findElement(By.xpath("//*[@id=\"mailbox\"]/form[1]/div[1]/div[2]/input")).sendKeys("jsjsh@ty");
        driver.findElement(By.xpath("//*[@id=\"mailbox\"]/form[1]/button[1]")).click();
        WebElement pass = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mailbox\"]/form[1]/div[2]/input")));
        pass.sendKeys("sdffvg");
        driver.findElement(By.xpath("//*[@id=\"mailbox\"]/form[1]/button[2]")).click();
    }

    @AfterEach
    public void stop(){
        driver.quit();
        driver = null;
    }
}
