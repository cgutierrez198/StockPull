package org.seleniumhq.selenium;

public class stock_LabelHandle {

    private String[] label;



    public void setLabels(String ... labels){
    label = new String[labels.length];

    for ( int i=0; i<labels.length ; i++){

        label[i]=labels[i];
    }


    }

    public String getLabels(int index){
        return label[index];
    }

}
