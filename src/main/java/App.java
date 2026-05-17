import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class App 
{
    public static void main(String[] args)
    {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver-win64/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get("https://www.calculator.net/password-generator.html");
            System.out.println(webDriver.getPageSource());

            Thread.sleep(300);

            WebElement elem = webDriver.findElement(By.cssSelector("#resultid .verybigtext b"));
            String password = elem.getText();

            System.out.println("Сгенерированный пароль: " + password);
            System.out.println();

            Task2.printClientIp(webDriver);
            System.out.println();

            Task3.printForecast(webDriver);
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        } finally {
            webDriver.quit();
        }
    }
}
