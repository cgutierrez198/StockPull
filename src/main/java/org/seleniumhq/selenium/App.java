package org.seleniumhq.selenium;
import java.io.IOException;
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


public class App {
    public static void main(String[] args) throws IOException {
/*
        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        WebDriver driver;
        System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");
        driver = new FirefoxDriver();
        // And now use this to visit Google
//        driver.navigate().to("http://thiagomarzagao.com/2013/11/12/webscraping-with-selenium-part-1/");
//        driver.navigate().to("https://finance.yahoo.com/quote/MCD?p=MCD");
        driver.navigate().to("http://www.marketwatch.com/investing/stock/AAPL");\
        //Close the browser
        driver.quit();
    }

    */
//Jsoup webscrape Strategy
        String url = "http://www.marketwatch.com/investing/stock/AAPL";
        Document doc = Jsoup.connect(url).get();
        String TickerSymbol = doc.select("meta[name=tickerSymbol]").first().attr("content");

        String quote = doc.select("meta[name=price]").first().attr("content");

        String priceChange = doc.select("meta[name=priceChange]").first().attr("content");
        System.out.println("STOCK SYMBOL: " + TickerSymbol );
        System.out.println("STOCK PRICE: "+quote +" CHANGE: "+ priceChange);

        


    }

}