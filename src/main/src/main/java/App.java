package main.java;

import main.java.exception.CookieException;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class App {

    public static void main(String[] args) {

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    WebDriver driver = new ChromeDriver();

                    driver.get("https://digital.etu.ru/attendance/student");

                    Cookie cookie = new Cookie("connect.digital-attendance", "s%3A_9jF-K4WgSiOM6T-kAbnshqpGSQOlfka.3pWlkDrJACGoWcs%2Biqi2Ucsy2vQnSQXBTy02suK2FCg");
                    driver.manage().addCookie(cookie);

                    driver.get("https://digital.etu.ru/attendance/student");

                    Thread.sleep(10000);

                    List<WebElement> elements = driver.findElements(By.tagName("button"));

                    if (elements.size() == 0) throw new CookieException("Cookies was killed!");
                    for (WebElement element :
                            elements) {
                        if (element.getText().equals("Посетить")) {
                            element.click();
                            break;
                        }
                    }
                } catch (CookieException | InterruptedException e) {
                    //getCookies();
                    e.printStackTrace();
                }
            }
        }, 0, 20000);
    }

}
