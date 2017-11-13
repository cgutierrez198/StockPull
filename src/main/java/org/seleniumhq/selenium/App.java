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

        File tick_file ;

        if ( !file.exists() )
            file.createNewFile();
        BufferedReader reader = new BufferedReader(new FileReader("Stocks.csv"));


        List<HistoricalQuote> date = new ArrayList<HistoricalQuote>();
        List<HistoricalQuote> hist_data = new ArrayList<HistoricalQuote>();
//        List<HistoricalQuote> hist_data;
        List<String> ticker = new ArrayList<String>();



        CsvWriter update_ticks;
        CsvWriter csvOutput = new CsvWriter(new FileWriter(file), ',');
        String line = null;
        Stock line1;
        int index=0;

        String temp;

        //Read The text file and save values to the data structure for further use
        //section also identifies if the symbol is a valid symbol
        while ((line = reader.readLine()) !=null ) {
            try {
                line1 = YahooFinance.get(line,true );

            }
            catch(IOException e){
               continue;

            }
            catch(StringIndexOutOfBoundsException e){
                break;
            }

            ticker.add(line1.getSymbol());

            System.out.println(line1.getSymbol());

//            csvOutput.endRecord();

          // Retrieve Historical stock data from the source and output to file
          hist_data = new ArrayList<HistoricalQuote>(line1.getHistory(from,to));
          if(index<1){
              csvOutput.write("");
              csvOutput.write("");
           for(int i =0;i<hist_data.size();i++){
              temp = (hist_data.get(i).getDate().getTime().getMonth()+1) +"/"+ (hist_data.get(i).getDate().getTime().getYear()-100);
              csvOutput.write(temp);
           }

           csvOutput.endRecord();
          }

          // Get the name of the stock and a formatted date
          csvOutput.write(line1.getSymbol());
          temp =line1.getName();
          csvOutput.write(temp.replace(';',' '));
          for(int i=0;i< hist_data.size();i++)
          csvOutput.write(hist_data.get(i).getClose()+ "");
          csvOutput.endRecord();
            index++;
        }


        csvOutput.flush();
        csvOutput.close();


//   Update the read file and close
        tick_file = new File("Stocks.csv");


         update_ticks = new CsvWriter(new FileWriter(tick_file),',');

         for (int i=0;i<ticker.size();i++){
            update_ticks.write(ticker.get(i));
            update_ticks.endRecord();
         }
         update_ticks.flush();
         update_ticks.close();


    }
}