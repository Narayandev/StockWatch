package com.example.naray.stockwatch;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by naray on 3/18/2017.
 */

public class StockAdapter extends RecyclerView.Adapter<StockViewHolder> {

    private static final String TAG = "StockAdapter";
    private List<Stock> stockList;
    private MainActivity mainAct;

    public StockAdapter(List<Stock> stocksList, MainActivity ma) {
        this.stockList = stocksList;
        mainAct = ma;
    }

    @Override
    public StockViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: MAKING NEW");
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stock_row, parent, false);

        itemView.setOnClickListener(mainAct);
        itemView.setOnLongClickListener(mainAct);

        return new StockViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StockViewHolder holder, int position) {
        Stock stock = stockList.get(position);
        if (Float.parseFloat(stock.getStock_pricechange()) >= 0) {
            holder.stock_symbol.setText(stock.getStock_symbol());
            holder.stock_data.setText(stock.getStock_lasttradePrice()+"    " + " \u25B2" + stock.getStock_pricechangeAmount().replace("+", "") + "(" + stock.getStock_pricechange() + "%)");
            holder.company.setText(stock.getCompany());
            holder.stock_data.setTextColor(Color.GREEN);
            holder.stock_symbol.setTextColor(Color.GREEN);
            holder.company.setTextColor(Color.GREEN);

        } else {
            holder.stock_symbol.setText(stock.getStock_symbol());
            holder.stock_data.setText(stock.getStock_lasttradePrice()+"   " + " \u25BC" + stock.getStock_pricechangeAmount() + "(" + stock.getStock_pricechange() + "%)");
            holder.company.setText(stock.getCompany());
            holder.stock_data.setTextColor(Color.RED);
            holder.stock_symbol.setTextColor(Color.RED);
            holder.company.setTextColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }





}
