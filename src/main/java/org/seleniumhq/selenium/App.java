package org.seleniumhq.selenium;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.Exception;

import org.jsoup.jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class App {
    public static void main(String[] args) throws InterruptedException{
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
        try{
            Document doc = jsoup.connect("http://www.marketwatch.com/investing/stock/AAPL").get();
            String title = doc.title();
            System.out.println("title: "+ title);
            Element links = doc




        }

}