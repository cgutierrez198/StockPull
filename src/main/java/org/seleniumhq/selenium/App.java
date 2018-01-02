package org.seleniumhq.selenium;
import java.awt.*;
import java.io.IOException;

import javax.swing.*;


public class App  {
    public static void main(String[] args) throws IOException {
/*
      JFrame f = new JFrame();
      final JPanel panel = new JPanel();
      final GraphingData graph =new GraphingData();

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
*/

        StockWrapper stocks = new StockWrapper();
        stocks.pullStock();
        stocks.writeFile();
    }

    }

