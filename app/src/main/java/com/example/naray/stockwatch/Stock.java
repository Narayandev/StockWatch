package com.example.naray.stockwatch;

/**
 * Created by naray on 3/18/2017.
 */

public class Stock implements Comparable<Stock> {

    private String stock_symbol;
    private String last_trade_price;
    private String price_change_amount;
    private String price_change;
    private String company;

    private static int ctr = 1;

    public Stock() {
        this.stock_symbol = "StockSymbol ";
        this.company = "Company ";
        this.last_trade_price = "LastTradePrice";
        this.price_change_amount = "PriceChangeAmount";
        this.price_change = "PriceChange";
    }

    public Stock(String symbol, String company) {
        this.stock_symbol = symbol;
        this.company = company;
    }

    public Stock(String stocksymbol, String tradePrice, String changeamount, String change, String company) {
        this.stock_symbol = stocksymbol;
        this.last_trade_price = tradePrice;
        this.price_change_amount = changeamount;
        this.price_change = change;
        this.company = company;
    }

    public String getStock_symbol() {
        return stock_symbol;
    }

    public String getStock_lasttradePrice() {
        return last_trade_price;
    }

    public String getStock_pricechangeAmount() {
        return price_change_amount;
    }

    public String getStock_pricechange() {
        return price_change;
    }

    public String getCompany() {
        return company;
    }

    @Override
    public String toString() {
        return stock_symbol + " (" + last_trade_price + price_change_amount + price_change + "), " + company;
    }

    @Override
    public int compareTo(Stock o) {
        if (this.equals(o)) {
            return 0;
        }
        int result = stock_symbol.compareTo(o.stock_symbol);
        if (result > 0)
            return 1;
        else
            return -1;
    }
}



