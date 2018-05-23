package org.seleniumhq.selenium;

import java.util.Date;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
public class dataSlot {
    private int day;
    private int month;
    private int year;
    private Date date;
    private Calendar cal;
    private String ticker;
    private double  stockData;
    public dataSlot(int day, int month, int year,double input,String ticker){
	this.day=day;
	if(year<2000){
	    this.year=year+2000;
	}
	else
	this.year = year;
	this.month = month;
	this.stockData = input;
	this.ticker = ticker;
	cal = new GregorianCalendar(year , month , day);
    }
    public int getDay(){return day;}
    public int getMonth(){return month;}
    public int getYear(){return year;}
    public double getData(){return stockData;}
    public String getTicker(){return ticker;}
    
    public Calendar getDate(){return cal;}
    
    public int compareDate(dataSlot T){
	Integer date1 = intString(this);
	Integer date2 = intString(T);
	return date1.compareTo(date2); 
    }

    public Integer intString(dataSlot T){
	String day = (T.getDay() <10)?"0"+String.valueOf(T.getDay()):String.valueOf(T.getDay());
	String month = (T.getMonth() < 10)? "0"+String.valueOf(T.getMonth()):String.valueOf(T.getMonth());
	String year = String.valueOf(T.getYear());
	String date2 =year+month+day;
	Integer date2I = Integer.valueOf(date2); 

	return date2I;
    }

    
    


}
