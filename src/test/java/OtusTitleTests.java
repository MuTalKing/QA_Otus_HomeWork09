import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.ByteArrayInputStream;
import java.util.concurrent.TimeUnit;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OtusTitleTests {

    protected static WebDriver driver;

    @BeforeAll
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void checkUrl(){
        driver.get("https://otus.ru/");
        String actualUrl = driver.getCurrentUrl();
        AssertionError assertionError = null;

        try{
            Assertions.assertEquals("https://otus.ru/", actualUrl);
        }
        catch(AssertionError aEr){
            assertionError = aEr;
        }
        finally{
            if(assertionError == null){
            }else{
                throw assertionError;
            }
        }
    }

    @Test
    public void checkTitle(){
        driver.get("https://otus.ru/");
        String actualTitle = driver.getTitle();

            Assertions.assertEquals(actualTitle, "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям");

    }

    @AfterAll
    public void setDown(){
        if (driver != null){
            driver.quit();
        }
    }
}
