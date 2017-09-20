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

public class stock_pullHandle {


    private static String url = "http://www.marketwatch.com/investing/stock/";
    private String tickerUrl;
    private String completeUrl="";
    private Document doc;
    /*
    String TickerSymbol = doc.select("meta[name=tickerSymbol]").first().attr("content");

    String quote = doc.select("meta[name=price]").first().attr("content");

    String priceChange = doc.select("meta[name=priceChange]").first().attr("content");
*/

    private void init_Grab(){

        try

        {
             doc = Jsoup.connect(completeUrl).get();
        }
        catch(IOException e){
            System.out.println("exception thrown "+ e);
        }

    }

    public String getQuote(String symbol){
        String temp;
        completeUrl= url+symbol;
        init_Grab();
        temp= doc.select("meta[name=price]").first().attr("content");
        completeUrl="";
        return temp;
    }
    public String getPriceChange(String symbol){
        String temp;
        completeURL= url+symbol;
        init_Grab();
        temp = doc.select("meta[name=priceChange]").first().attr("content");
        completeUrl="";
        return temp;
    }



}
