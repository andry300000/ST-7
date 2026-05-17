import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Task2 {
    public static void printClientIp(WebDriver webDriver) {
        webDriver.get("https://api.ipify.org/?format=json");
        try {
            System.out.println(webDriver.getPageSource());
            WebElement elem = webDriver.findElement(By.tagName("pre"));

            String jsonStr = elem.getText();
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonStr);

            System.out.println("IPv4-адрес клиента: " + obj.get("ip"));
        } catch (Exception e) {
            System.out.println("Error in Task2");
            System.out.println(e.toString());
        }
    }
}
