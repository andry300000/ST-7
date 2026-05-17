import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Task3 {
    private static final String WEATHER_URL =
            "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&current=cloud_cover&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";

    public static void printForecast(WebDriver webDriver) {
        webDriver.get(WEATHER_URL);
        try {
            System.out.println(webDriver.getPageSource());
            WebElement elem = webDriver.findElement(By.tagName("pre"));

            String jsonStr = elem.getText();
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonStr);
            JSONObject hourly = (JSONObject) obj.get("hourly");

            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray temperatures = (JSONArray) hourly.get("temperature_2m");
            JSONArray rainValues = (JSONArray) hourly.get("rain");

            StringBuilder table = new StringBuilder();
            table.append(String.format("%-3s| %-16s| %-12s| %-12s%n",
                    "№", "Дата/время", "Температура", "Осадки (мм)"));
            table.append("---|-----------------|-------------|------------").append(System.lineSeparator());

            System.out.println("Прогноз погоды на сутки");
            System.out.print(table.toString());

            for (int i = 0; i < times.size(); ++i) {
                String row = String.format("%-3d| %-16s| %-12s| %-12s",
                        i + 1,
                        times.get(i),
                        temperatures.get(i),
                        rainValues.get(i));
                System.out.println(row);
                table.append(row).append(System.lineSeparator());
            }

            saveForecast(table.toString());
        } catch (Exception e) {
            System.out.println("Error in Task3");
            System.out.println(e.toString());
        }
    }

    private static void saveForecast(String content) throws IOException {
        Path resultDir = Paths.get("result");
        Files.createDirectories(resultDir);
        Files.writeString(resultDir.resolve("forecast.txt"), content, StandardCharsets.UTF_8);
    }
}
