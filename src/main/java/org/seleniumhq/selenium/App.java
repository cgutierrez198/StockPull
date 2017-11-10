package org.seleniumhq.selenium;
import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.csvreader.*;
import java.io.IOException;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;



public class App {
    public static void main(String[] args) throws IOException {

        Calendar from = Calendar.getInstance();
        Calendar to= Calendar.getInstance();
        from.add(Calendar.YEAR, -2);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        File file = new File(  df.format(new Date()) +"_Stocks.csv");

        File tick_file = new File(  df.format("Stocks"));
        if ( !file.exists() )
            file.createNewFile();
        BufferedReader reader = new BufferedReader(new FileReader("test.csv"));
        List<HistoricalQuote> date = new ArrayList<HistoricalQuote>();
//        List<HistoricalQuote> hist_data = new ArrayList<HistoricalQuote>();
        List<HistoricalQuote> hist_data;
        List<String> ticker = new ArrayList<String>();

        CsvWriter update_ticks = new CsvWriter(new FileWriter(tick_file),',');
        CsvWriter csvOutput = new CsvWriter(new FileWriter(file, true), ',');
        String line = null;
        Stock line1;
        int index=0;

        while ((line = reader.readLine()) !=null ) {
            try {
                line1 = YahooFinance.get(line, from, to);

            }
            catch(IOException e){
               continue;

            }
            catch(StringIndexOutOfBoundsException e){
                break;
            }

            ticker.add(line1.getSymbol());


//            csvOutput.endRecord();

        //  // Use FileWriter constructor that specifies open for appending
        //  hist_data = new ArrayList<HistoricalQuote>(line1.getHistory(from,to));
        //  if(index<1){
        //      csvOutput.write("");
        //   for(int i =0;i<hist_data.size();i++){
        //       csvOutput.write(hist_data.get(i).getDate().getTime()+"");
        //   }
        //   csvOutput.endRecord();
        //  }

        //  csvOutput.write(line1.getName());
        //  for(int i=0;i< hist_data.size();i++)
        //  csvOutput.write(hist_data.get(i).getClose()+ "");
        //  csvOutput.endRecord();
        //     /*
        //  System.out.println("this is the size of the input:"+ticker.size());
        //  System.out.println(ticker.get(index));
        //  System.out.println(index);
        //  index++;
        //  */
            index++;
        }



    }
}