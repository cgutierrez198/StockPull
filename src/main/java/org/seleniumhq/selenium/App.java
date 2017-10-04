package org.seleniumhq.selenium;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;


public class App {
    public static void main(String[] args) throws IOException {

        Calendar from = Calendar.getInstance();
        Calendar to= Calendar.getInstance();

        Calendar from2 = Calendar.getInstance();
        Calendar to2= Calendar.getInstance();
        from .add(Calendar.YEAR,-12);

        from2.add(Calendar.YEAR,-5);
        Stock apple = YahooFinance.get("f", true);
        System.out.println(apple.getHistory(from,to));

        BufferedReader reader = new BufferedReader(new FileReader("test.csv"));
       // List<String> lines = new ArrayList<String>();
        String line = null;
        while ((line = reader.readLine()) != null) {
         //   lines.add(line);
            System.out.println(line);
            Stock line1= YahooFinance.get(line, true);
            System.out.println(line1.getHistory(from,to));
        }

    }

}