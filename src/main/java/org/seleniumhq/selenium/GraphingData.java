package org.seleniumhq.selenium;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;

import com.csvreader.*;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

    public class GraphingData extends JPanel implements KeyListener {

        ArrayList<String> date = new ArrayList<String>();
        ArrayList<Integer> hist_data = new ArrayList<Integer>();
        ArrayList<String> ticker = new ArrayList<String>();
        ArrayList<Integer> data = new ArrayList<Integer>();

        ArrayList<String> tickerName = new ArrayList<String>();
	boolean setNames=false;

        ArrayList<Integer> trendsHistorical_data = new ArrayList<Integer>();
        ArrayList<String> trends_date = new ArrayList<String>();

	ArrayList<dataSlot> List1 = new ArrayList<dataSlot>();
	ArrayList<dataSlot> List2 = new ArrayList<dataSlot>();

	ArrayList<dataSlot> gSearches =new ArrayList<dataSlot>();
	ArrayList<dataSlot> yStocks = new ArrayList<dataSlot>();
	String correlcationNumber = "";
        boolean auto_start = false;
        int auto_select=0;
        int select =0;
        boolean plotSwitch= false;
	private static String search ="";

        final int PAD = 10;
        int count=0;
        int tickerindex=0;
        int arraylength;
        public GraphingData() {
        
        }

        public void keyPressed(KeyEvent e) {
                 
            int keycode = e.getKeyCode();
		if(keycode>=65 && keycode <=90 || 
		   keycode >=97 && keycode <=122 || keycode==32)
		    search += e.getKeyChar();
            switch (keycode){
                case KeyEvent.VK_RIGHT:
                    if(tickerindex>=arraylength){

                        tickerindex=0;
                    }
                    else {
                        tickerindex++;
                    }
                    data.clear();
		    List1.clear();
		    List2.clear();
		    gSearches.clear();
		    yStocks.clear();
                    hist_data.clear();
                    //date.clear();
                    trendsHistorical_data.clear();
                    trends_date.clear();
                    count=0;
                    break;
                case KeyEvent.VK_LEFT:
                    if(tickerindex<0){
                        tickerindex = arraylength;

                    }
                    else{
                        tickerindex--;
                    }
                    data.clear();
                    hist_data.clear();
                    //date.clear();
		    List1.clear();
		    List2.clear();

		    gSearches.clear();
		    yStocks.clear();
                    trendsHistorical_data.clear();
                    trends_date.clear();
                    count=0;
                    break;

                case KeyEvent.VK_SPACE:

                    auto_select++;
                    if(auto_select >1) auto_select =0;
                    if(tickerindex>=arraylength){

                        tickerindex=1;
                    }
                    if(auto_select ==1) {
                        auto_start=true;
                    }
                    else{
                        auto_start=false;
                    }
                    data.clear();
                    hist_data.clear();
                    //date.clear();

		    gSearches.clear();
		    yStocks.clear();
		    List1.clear();
		    List2.clear();
                    trendsHistorical_data.clear();
                    trends_date.clear();
                    count=0;
                    break;
                case KeyEvent.VK_BACK_SPACE:
			search = search.substring(0,search.length()-1);
			break;
                case KeyEvent.VK_ENTER:
			setIndex(search);
			search="";	
                        data.clear();
		        List1.clear();
		        List2.clear();
		        gSearches.clear();
		        yStocks.clear();
                        hist_data.clear();
                        //date.clear();
                        trendsHistorical_data.clear();
                        trends_date.clear();
                        count=0;
			break;
                case KeyEvent.VK_SHIFT:
                    if(select >1) select=0;
                    select++;
                    if(select ==1){
                        plotSwitch=true;

                    }
                    else{
                        plotSwitch=false;

                    }
            }


        }

        public void keyReleased(KeyEvent e) {

        }

        public void keyTyped(KeyEvent e) {

        }

        protected void paintComponent(Graphics g)  {

          if(count >= 160&& auto_start==true){
              data.clear();
              hist_data.clear();
             // date.clear();
              trendsHistorical_data.clear();
              trends_date.clear();
	      gSearches.clear();
	      yStocks.clear();

	      List1.clear();
	      List2.clear();
              tickerindex++;
              count=0;

          }
            if(count ==0) {
                try {
                    readFile();

                } catch (IOException e) {
                    System.out.println("error: " + e.getMessage());
                }
                data.addAll(hist_data);
            }

             count++;
            super.repaint();
            super.paintComponent(g);
            this.setBackground(Color.gray);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getWidth();
            int h = getHeight()-140;
            // Draw ordinate.
            g2.draw(new Line2D.Double(PAD, PAD, PAD, h - PAD));
            // Draw abcissa.
            g2.draw(new Line2D.Double(PAD, h - PAD, w - PAD, h - PAD));
            // Draw labels.
            Font font = g2.getFont();
            FontRenderContext frc = g2.getFontRenderContext();
            LineMetrics lm = font.getLineMetrics("0", frc);
            float sh = lm.getAscent() + lm.getDescent();
            // Ordinate label.
            String s = "data";
            float sy = PAD + ((h - 2 * PAD) - s.length() * sh) / 2 + lm.getAscent();
            for (int i = 0; i < s.length(); i++) {
                String letter = String.valueOf(s.charAt(i));
                float sw = (float) font.getStringBounds(letter, frc).getWidth();
                float sx = (PAD - sw) / 2;
                g2.drawString(letter, sx, sy);
                sy += sh;
            }
            // Abcissa label.
            s = "x axis";
            sy = h - PAD + (PAD - sh) / 2 + lm.getAscent();
            float sw = (float) font.getStringBounds(s, frc).getWidth();
            float sx = (w - sw) / 2;
            g2.drawString(s, sx, sy);
            // Draw lines.

            double xInc2 = (double) (w - 2 * PAD) / (trendsHistorical_data.size() );
            double scale2 = (double) (h - 2 * PAD) / getMax(trendsHistorical_data);
            double xInc = (double) (w - 2 * PAD) / (data.size() );
            double scale = (double) (h - 2 * PAD) / getMax(data);
            g2.setPaint(Color.BLACK);


            // Mark data points.

            g2.setFont(new Font("TimesRoman", Font.PLAIN, 24));
            String temp = ticker.get(tickerindex)+" "+tickerName.get(tickerindex);
            g2.drawString(temp, sx-600, sy+70);
            if(auto_start)
                g2.setColor(Color.magenta);
            else
                g2.setColor(Color.GRAY);
                g2.drawString("auto",sx-620,sy+130);

            g2.setFont(new Font("TimesRoman", Font.PLAIN, 14));
            g2.setPaint(Color.red);
            for (int i = 0; i < data.size(); i++) {
                double x = PAD + i * xInc;
                double y = h - PAD - scale * data.get(i);
                g2.fill(new Ellipse2D.Double(x - 2, y - 2, 3,3));

                if(i%85==0 ) {
                    g2.drawString(trends_date.get(i), (int) x - 2, super.getHeight() -120);
                }
            }
            if(plotSwitch) {
                for (int i = 0; i < data.size() - 1; i++) {
                    double x1 = PAD + i * xInc;
                    double y1 = h - PAD - scale * data.get(i);
                    double x2 = PAD + (i + 1) * xInc;
                    double y2 = h - PAD - scale * (data.get(i + 1));
                    g2.draw(new Line2D.Double(x1, y1, x2, y2));
                }
            }

            g2.setFont(new Font("TimesRoman", Font.PLAIN, 24));
            g2.drawString("Stock Data -", sx+350, sy+70);

            g2.setPaint(Color.BLUE);
            for (int i = 0; i < trendsHistorical_data.size(); i++) {
                double x2= PAD + i * xInc2;
                double y2= h - PAD - scale2 *trendsHistorical_data.get(i);
                g2.fill(new Ellipse2D.Double(x2- 2, y2- 2, 3,3));

            }
            if(plotSwitch) {
                for (int i = 0; i < trendsHistorical_data.size() - 1; i++) {
                    double x1T = PAD + i * xInc2;
                    double y1T = h - PAD - scale2 * trendsHistorical_data.get(i);
                    double x2T = PAD + (i + 1) * xInc2;
                    double y2T = h - PAD - scale2 * (trendsHistorical_data.get(i + 1));
                    g2.draw(new Line2D.Double(x1T, y1T, x2T, y2T));
                }
            }
            g2.setFont(new Font("TimesRoman", Font.PLAIN, 24));
            g2.drawString("GoogleTrends Data -", sx+90, sy+70);

            g2.setPaint(Color.BLACK);

            g2.setFont(new Font("TimesRoman", Font.PLAIN, 24));
	    g2.drawString("Correlation : "+correlcationNumber, sx+90, sy+100);
            g2.setPaint(Color.WHITE);
            g2.setFont(new Font("TimesRoman", Font.PLAIN, 24));
	    g2.drawString("Search : "+search, sx+90, sy+130);
	    
        }

        private int getMax(ArrayList<Integer> data) {
            int max = -Integer.MAX_VALUE;
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i) > max)
                    max = data.get(i);
            }
            return max;
        }

//Reads csv Data for stock file 2017-11-14_Stocks.csv
//Puts data into data structure for plotting
        public  void readFile() throws IOException {
            int data;
            String csvFile = "2017-11-14_Stocks.csv";
            FileReader file = new FileReader(csvFile);
            String line = "";
            String cvsSplitBy = ",";
	    String temp0,  tempDate1;
            BufferedReader br = new BufferedReader(file);

            int index =0;
	    if(!setNames){
            try  {
                while ((line = br.readLine()) != null) {

                    // use comma as separator
                    String[] csvData= line.split(cvsSplitBy);
		    
		    if(index<0) index=ticker.size()-1;
		    if(index>0){
				ticker.add(csvData[0]);
			    tickerName.add(csvData[1]);
                        arraylength = ticker.size();
                    // use comma as separator
                }
		index++;
	    }
            } catch (IOException e) {
	    }
	    }

	    setNames=true;
           String  stockFile = "stocks.csv";
            FileReader file1 = new FileReader(stockFile);
            BufferedReader br1 = new BufferedReader(file1);
            index  =0;
            String temp1;
	    int day1,month1,year1;

            try  {

                while ((line = br1.readLine()) != null) {

                    // use comma as separator
		    
                    String[] csvData= line.split(cvsSplitBy);
		    if(tickerindex<0) tickerindex=ticker.size()-1;
                    if(tickerindex>ticker.size()-1) tickerindex=0;
                                      
		    if(index>0){
                    // use comma as separator
		    temp1=csvData[0];
		    
		    if(temp1.compareTo(ticker.get(tickerindex))==0){
		//	try{
			    data= (int) Double.parseDouble(csvData[5]);
			    day1 = Integer.valueOf(csvData[2]);
			    month1 = Integer.valueOf(csvData[3]);
			    year1 = Integer.valueOf(csvData[4]);
			    
			    yStocks.add(new dataSlot(day1,month1,year1,data,temp1));
			    
			    hist_data.add(data);



		  //}catch(NumberFormatException e){
		  //    continue;
		  //}
		}
                }
		index++;
	    }
            } catch (IOException e) {
                e.printStackTrace();
            }


           String  trendsFile = "trends.csv";
            FileReader file2 = new FileReader(trendsFile);
            BufferedReader br2 = new BufferedReader(file2);
            index  =0;
            String temp;
            try  {

                while ((line = br2.readLine()) != null) {
                    // use comma as separator
                    String[] csvData2= line.split(cvsSplitBy);
                    if(index > 0 ) {

                        if(tickerindex>ticker.size()-1) tickerindex=0;
                        //Ticker information read here
                        if(tickerindex<0) tickerindex=ticker.size()-1;
                        temp=csvData2[9].replace("\"","");

                        if(temp.compareTo(ticker.get(tickerindex))==0) {
			String tempDate , day,month,year;;
                            try {
                                    data = Integer.parseInt(csvData2[4]);
                                    trendsHistorical_data.add(data);
                                                 
				    year = csvData2[1];
				    month = csvData2[2];
				    day = csvData2[3];
				    day1 = Integer.valueOf(csvData2[3]);
				    month1 = Integer.valueOf(csvData2[2]);
				    year1 = Integer.valueOf(csvData2[1]);
				    tempDate = year+"-"+month+"-"+day;
                                    trends_date.add(tempDate);
				    gSearches.add(new dataSlot(day1,month1,year1,data,temp));

				    



                            } catch (NumberFormatException e) {
                                continue;
                            }
                        }
                    }

                    index++;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
	dataSlot yahooData;
	dataSlot gData;
        for(int i=0; i<yStocks.size();i++){
            yahooData = yStocks.get(i);
	    for(int n=0; n<gSearches.size();n++){
	    gData = gSearches.get(n);
	    if(gData.compareDate(yahooData)>0){
	        if(n-1==-1){
		    break;
	        }
	        else {
	        List1.add(gSearches.get(n-1));
	        List2.add(yStocks.get(i));
		break; 
		}
	     }
	    }
    }
	
	    double r,nr=0,dr_1=0,dr_2=0,dr_3=0,dr=0;
    double xx[],xy[],yy[];
    int correlationSize = List1.size();

    xx =new double[correlationSize];
    xy =new double[correlationSize];
    yy =new double[correlationSize];

    double sum_y=0,sum_yy=0,sum_xy=0,sum_x=0,sum_xx=0;
    int i;
    for(i=0;i<correlationSize;i++)
    {
    xx[i]=List1.get(i).getData()*List1.get(i).getData();
    yy[i]=List2.get(i).getData()*List2.get(i).getData();
    }
    for(i=0;i<correlationSize;i++)
    {
    sum_x+=List1.get(i).getData();
    sum_y+=List2.get(i).getData();
    sum_xx+= xx[i];
    sum_yy+=yy[i];
    sum_xy+= List1.get(i).getData()*List2.get(i).getData();
    }
    nr=(correlationSize*sum_xy)-(sum_x*sum_y);
    double sum_x2=sum_x*sum_x;
    double sum_y2=sum_y*sum_y;
    dr_1=(correlationSize*sum_xx)-sum_x2;
    dr_2=(correlationSize*sum_yy)-sum_y2;
    dr_3=dr_1*dr_2;
    dr=Math.sqrt(dr_3);
    r=(nr/dr);
    String s = String.format("%.2f",r);
    r = Double.parseDouble(s);
    correlcationNumber = r+" ";
    //System.out.println("Total Numbers:"+correlationSize+"\nCorrelation Coefficient:"+r);
	//writeData(ticker.get(tickerindex),tickerName.get(tickerindex),r);


        }

    private  void writeData(String ticker, String coName, double correlation) throws IOException{
    CsvWriter csvOutput;
    File file = new File("outputs/"+coName+".csv");
    csvOutput = new CsvWriter(new FileWriter(file), ',');

    if (!file.exists()){
        file.createNewFile();
            csvOutput.write("ticker",true);
            csvOutput.write("name",true);
            csvOutput.write("Data",true);
            csvOutput.endRecord();
    }
                    
    csvOutput.write(coName,true);
    csvOutput.write(ticker,true);
    csvOutput.write(String.valueOf(correlation),true);
    csvOutput.endRecord();
    



    csvOutput.flush();
    csvOutput.close();

    }
        public void setIndex(String temp){
	temp = temp.toLowerCase();
	temp = temp.trim();
        int index = tickerindex;
        for(int i=0; i<ticker.size(); i++){
		String target = ticker.get(i);
		target = target.toLowerCase();
        	if(temp.compareTo( target)==0){
        		index = i;
        		break;
        	}

        }
        tickerindex = index;
        


        }

    }
