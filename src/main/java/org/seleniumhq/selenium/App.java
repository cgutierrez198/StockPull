package org.seleniumhq.selenium;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

import java.awt.*;
import javax.swing.*;
         

public class App  {
    public static void main(String[] args) throws IOException {
  
      JFrame f = new JFrame();
      JButton refresh = new JButton("refresh");
      JTextField ticker = new JTextField("Ticker");
      final JPanel panel = new JPanel();
       GraphingData graph =new GraphingData();
	JPanel mPane = new JPanel();
	mPane.setLayout(new GridBagLayout());
      GridBagConstraints holder_c = new GridBagConstraints();
      GridBagConstraints mainPane = new GridBagConstraints();
	JPanel holder = new JPanel();
	holder.add(refresh);
	holder.add(ticker);
	mainPane.fill = GridBagConstraints.HORIZONTAL;	
//	mainPane.fill = GridBagConstraints.VERTICAL-1;
	mainPane.ipady = 650;
	mainPane.gridwidth = 1;
	mainPane.gridheight = 2;
	mainPane.weighty = .8;
	mainPane.weightx = 1;
	mainPane.gridx=0;
	mainPane.gridy=0;
	

////////ticker.setMaximumSize(new Dimension(100,20));
////////refresh.setMaximumSize(new Dimension(100,20));
////////holder.setMaximumSize(new Dimension(200,20));
////////panel.setMaximumSize(new Dimension(1280, 600));
        panel.setFocusable(true);
        panel.setRequestFocusEnabled(true);
	
	
	
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1280, 800);
        f.setPreferredSize(new Dimension(1280, 400));
        f.addKeyListener(graph);
	
        mPane.add(graph,mainPane);
	holder_c.fill = GridBagConstraints.VERTICAL;
	holder_c.gridwidth = 1;
	holder_c.gridheight = 1;
	holder_c.weightx = 0.2;
	holder_c.weighty = 1;
	holder_c.gridx=0;
	holder_c.gridy=2;
//	mPane.add(holder,holder_c);
	f.add(mPane);
        f.setLocation(200, 200);
        f.setVisible(true);

/*
        StockWrapper stocks = new StockWrapper();
        stocks.pullStock();
        stocks.writeFile();*/
    }

    }

