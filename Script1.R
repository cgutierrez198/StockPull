if (!require("devtools"))install.packages("devtools")
#devtools::install_github("hadley/devtools")
library(devtools)
#install_github("PMassicotte/gtrendsR")
#devtools::install_github("dvanclev/GTrendsR")
#devtools::install_github("trinker/gtrend")
library(gtrendsR)
library(gtrend)
#library(`gtrend-master`)
library("tidyr")
library("readr")
#library(RJSONIO)
startDt  <- as.Date("2002-01-01") 
#datetime::datetime(year=2002, month=1, day=1)

location = "US-TX" # 
#stockList = ["WMT","XOM","MCK","UNH","CVS"]

#res <- gtrends(c( "WMT","XOM","MCK","UNH"), location, time = "all")
res <-read.csv(file="Stocks.csv", header= TRUE ,sep=",")
i = res[4,]
print(i)
head(res)
f= sprintf('%s',i)
print (f)
names(res)[1]
n<- -1
temp=0
for(n in 1:441){

i = res[n,]
print(i)
f= sprintf('%s',i)
print (f)
stocks <- gtrends(f,location,time ="all")

}
for (df in stocks){
  write.table(df, file="Script3.csv", col.names=TRUE, sep=",", append=TRUE)
}

plot(stocks)

