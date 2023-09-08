import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    private static final Timer timer = new Timer();
    private static final HashMap<String, String> profiles = new HashMap<>();

    public static void main(String[] args) {

        profiles.put("Your email", "Your password");

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (Map.Entry<String, String> profile :
                        profiles.entrySet()) {
                    WebDriver driver = new ChromeDriver();

                    try {

                        driver.get("https://digital.etu.ru/attendance/student%22");

                        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

                        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div/div/div[2]/p/div/button")).click();
                        driver.findElement(By.xpath("/html/body/div/div[1]/div/div[2]/div/form/div[1]/div/div/input")).sendKeys(profile.getKey());
                        driver.findElement(By.xpath("/html/body/div/div[1]/div/div[2]/div/form/div[2]/div/div/input")).sendKeys(profile.getValue());
                        driver.findElement(By.xpath("/html/body/div/div[1]/div/div[2]/div/form/div[4]/div[2]/div/button")).click();
                        driver.findElement(By.xpath("/html/body/div/div[1]/div/div[2]/div/div[4]/div/div[2]/form/button")).click();

                        driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div/div[2]/div[1]/div/div/div/div[1]/div/div/button")).click();

                        driver.findElement(By.xpath("/html/body/div/div/nav/ul/li[2]/a/svg")).click();
                        driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div/footer/button[1]")).click();

                    } catch (NoSuchElementException e) {
                        System.out.println("Проблемы с интернетом или сайт сломался)");
                    } catch (ElementClickInterceptedException e) {
                        System.out.println("Вы уже отметились");
                    } finally {
                        driver.close();
                    }
                }
            }
        }, 0, 60 * 60 * 1000);
    }

}