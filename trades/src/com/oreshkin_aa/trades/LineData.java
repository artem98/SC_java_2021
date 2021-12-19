package com.oreshkin_aa.trades;

public class LineData {

    public final static String SPLIT_REGEX = "\t";

    public String tradeNo;
    public int tradeTime;
    public String secBoard;
    public String secCode;
    public double price;
    public int volume;
    public double accruedint;
    public double yield;
    public double value;

    private String originalString;

    public LineData(String tradeNo,
                    int tradeTime,
                    String secBoard,
                    String secCode,
                    double price,
                    int volume,
                    double accruedint,
                    double yield,
                    double value,
                    String originalString) {
        this.tradeNo = tradeNo;
        this.tradeTime = tradeTime;
        this.secBoard = secBoard;
        this.secCode = secCode;
        this.price = price;
        this.volume = volume;
        this.accruedint = accruedint;
        this.yield = yield;
        this.value = value;
        this.originalString = originalString;
    }

    public static LineData makeLineData(String fileLine) {
        String[] items = fileLine.split(SPLIT_REGEX);
        try {
            String newTradeNo = items[0];
            int newTradeTime = Integer.parseInt(items[1]);
            String  newSecBoard = items[2];
            String  newSecCode = items[3];
            double newPrice = Double.parseDouble(items[4]);
            int newVolume = Integer.parseInt(items[5]);
            double newAccruedint = Double.parseDouble(items[6]);
            double newYield = Double.parseDouble(items[7]);
            double newValue = Double.parseDouble(items[8]);
            return new LineData(newTradeNo, newTradeTime, newSecBoard, newSecCode, newPrice,
                    newVolume, newAccruedint, newYield, newValue, fileLine);
        }
        catch (NumberFormatException | NullPointerException e) {
            return null;
        }
    }

    public String getSecCode() {
        return secCode;
    }

    @Override
    public String toString() {
        return originalString; //"" + tradeNo + " " + tradeTime + " " + secBoard;//
    }
}
