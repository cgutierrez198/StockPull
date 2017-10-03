package org.seleniumhq.selenium;
import java.io.IOException;
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
        from .add(Calendar.YEAR,-12);
        Stock tesla = YahooFinance.get("AAPL", true);
         String text;
        System.out.println(tesla.getHistory(from,to));





    }

}