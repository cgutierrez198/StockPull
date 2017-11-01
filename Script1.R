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

res <- gtrends(c( "WMT","XOM","MCK","UNH"), location, time = "all")

head(res)
names(res)[1]
n<- 0


for (df in res){
  write.table(df, file="Script3.csv", col.names=TRUE, sep=",", append=TRUE)
}

plot(res)

