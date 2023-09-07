import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Hello world!
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        Timer timer = new Timer();

        final WebDriver driverAuthorization = new ChromeDriver();

        driverAuthorization.get("https://digital.etu.ru/attendance/student%22");

        Thread.sleep(1000);

        driverAuthorization.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div/div/div[2]/p/div/button")).click();
        driverAuthorization.findElement(By.xpath("/html/body/div/div[1]/div/div[2]/div/form/div[1]/div/div/input")).sendKeys("");
        driverAuthorization.findElement(By.xpath("/html/body/div/div[1]/div/div[2]/div/form/div[2]/div/div/input")).sendKeys("");
        driverAuthorization.findElement(By.xpath("/html/body/div/div[1]/div/div[2]/div/form/div[4]/div[2]/div/button")).click();
        driverAuthorization.findElement(By.xpath("/html/body/div/div[1]/div/div[2]/div/div[4]/div/div[2]/form/button")).click();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Cookie cookie1 = driverAuthorization.manage().getCookieNamed("connect.digital-attendance");
                String cookieString = cookie1.toString();
                System.out.println(cookie1);
                System.out.println(cookieString.substring(27, cookieString.indexOf(";")));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                List<WebElement> elements = driverAuthorization.findElements(By.tagName("button"));

                for (WebElement element: elements) {
                    if (element.getText().equals("Посетить")){
                        element.click();
                    }
                }
            }
        }, 0, 5000);

    }

}