package org.seleniumhq.selenium;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.csvreader.CsvWriter;
import java.io.IOException;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;



public class App {
    public static void main(String[] args) throws IOException {

        Calendar from = Calendar.getInstance();
        Calendar to= Calendar.getInstance();
        from.add(Calendar.YEAR, -12);
        Calendar from2 = Calendar.getInstance();

        BufferedReader reader = new BufferedReader(new FileReader("test.csv"));
        List<String> ticker = new ArrayList<String>();
        List<HistoricalQuote> hist_data = new ArrayList<HistoricalQuote>();

        String line = null;
        Stock line1;
        int index=0;
        while ((line = reader.readLine()) !=null ) {
            try{
            //line1 = YahooFinance.get(line ,from,to);

                line1 = YahooFinance.get(line,from,to,Interval.DAILY);

            }
            catch(IOException e){
               continue;

            }
            catch(StringIndexOutOfBoundsException e){
                break;
            }
                ticker.add(line1.getName());
                hist_data.addAll(line1.getHistory(from,to, Interval.DAILY));
            System.out.println("this is the size of the input:"+ticker.size());
            System.out.println(ticker.get(index));
            System.out.println(index);
            index++;

        }


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
        File file = new File(  df.format(new Date()) +"_Stocks.csv");
        if ( !file.exists() )
            file.createNewFile();

        // Use FileWriter constructor that specifies open for appending
        CsvWriter csvOutput = new CsvWriter(new FileWriter(file, true), ',');
       for(int i =0;i<ticker.size();i++){
           csvOutput.write(ticker.get(i));

           for(int x=0 ;x<hist_data.size();x++)
           csvOutput.write(hist_data.get(x).getClose()+"");

        csvOutput.endRecord();
       }

        csvOutput.flush();
        csvOutput.close();

    }

}