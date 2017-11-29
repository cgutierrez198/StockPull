package org.seleniumhq.selenium;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import yahoofinance.histquotes.Interval;

import javax.swing.*;


public class App  {
    public static int index = 0;
    public static void main(String[] args) throws IOException {

        //    BufferedReader reader;
        //    reader = new BufferedReader(new FileReader("Stocks.csv"));
        //    String line=null;
        //    while((line=reader.readLine())!=null){
        //        tickers.add(line);
        //    }
        //    for (int i=0; i<tickers.size();i++){
        //        System.out.println(tickers.get(i));
        //    }
        JFrame f = new JFrame();
      final JPanel panel = new JPanel();
      final GraphingData graph =new GraphingData();
        ArrayList<String> date = new ArrayList<String>();
        ArrayList<Integer> hist_data = new ArrayList<Integer>();
        ArrayList<String> ticker = new ArrayList<String>();

        panel.setPreferredSize(new Dimension(1280, 100));
        panel.setFocusable(true);
        panel.setRequestFocusEnabled(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1280, 800);
        f.setPreferredSize(new Dimension(1280, 400));
        f.addKeyListener(graph);
        f.add(graph);
        f.setLocation(200, 200);
        f.setVisible(true);
        JButton northEast = new JButton("Move to North East");
        northEast.setBounds(240, 710, 190, 20);

        JButton refresh = new JButton("North East");
        refresh.setBounds(110, 700, 190, 20);

//            f.add(northEast);
//            f.add(refresh);

        // StockWrapper stocks = new StockWrapper();
        // stocks.pullStock();

        // stocks.writeFile();
    }

    }

