import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OtusTitleTests {

    protected static WebDriver driver;

    @BeforeAll
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
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
        AssertionError assertionError = null;

        try{
            Assertions.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям", actualTitle);
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

    @AfterAll
    public void setDown(){
        if (driver != null){
            driver.quit();
        }
    }
}
