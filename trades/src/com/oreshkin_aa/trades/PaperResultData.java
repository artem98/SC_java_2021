package com.oreshkin_aa.trades;

import java.util.Comparator;

public class PaperResultData implements Comparable {

    public double priceOpen;
    public int timeOpen;
    public double priceClose;
    public int timeClose;

    public String secCode;

    public double totalTradeVolume;

    public PaperResultData(String secCode) {
        this.secCode = secCode;
        this.priceOpen = 0;
        this.priceClose = 0;
        this.totalTradeVolume = 0;
        timeClose = -1;
        timeOpen = -1;
    }

    public PaperResultData(double priceOpen, double priceClose, double totalTradeVolume) {
        this.priceOpen = priceOpen;
        this.priceClose = priceClose;
        this.totalTradeVolume = totalTradeVolume;
        timeClose = -1;
        timeOpen = -1;
    }

    public PaperResultData(double priceOpen, double priceClose) {
        this.priceOpen = priceOpen;
        this.priceClose = priceClose;
        totalTradeVolume = 0;
        timeClose = -1;
        timeOpen = -1;
    }

    @Override
    public String toString() {
        return "  " + secCode + ": " + "delta: " + getDayPriceDelta() + ", relative delta: " +
                (getDayPriceRelativeDelta() * 100) +
                "%, total volume: " + totalTradeVolume;
    }

    public void consumeLineData(LineData data) {
        totalTradeVolume += data.volume;

        if(timeClose == -1) {
            timeClose = timeOpen = data.tradeTime;
            priceOpen = priceClose = data.price;
        }
        else {
            if(timeOpen > data.tradeTime) {
                timeOpen = data.tradeTime;
                priceOpen = data.price;
            }

            if(timeClose < data.tradeTime) {
                timeClose = data.tradeTime;
                priceClose = data.price;
            }
        }
    }


    public double getDayPriceDelta() {
        return priceClose - priceOpen;
    }

    public double getDayPriceRelativeDelta() {
        return getDayPriceDelta() / priceOpen;
    }


    @Override
    public int compareTo(Object o) {
        PaperResultData d = (PaperResultData) o;
        double diff = this.getDayPriceDelta() - d.getDayPriceDelta();
        if(diff < 0)
            return 1;
        if(diff > 0)
            return -1;
        return 0;
    }
}
