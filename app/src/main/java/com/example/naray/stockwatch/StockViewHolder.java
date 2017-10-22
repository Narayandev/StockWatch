package com.example.naray.stockwatch;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by naray on 3/18/2017.
 */

public class StockViewHolder extends RecyclerView.ViewHolder {

    public TextView stock_symbol;
    public TextView stock_data;
    public TextView company;

    public StockViewHolder(View view) {
        super(view);
        stock_symbol = (TextView) view.findViewById(R.id.stockSymbol);
        stock_data = (TextView) view.findViewById(R.id.stockCmpny);
        company = (TextView) view.findViewById(R.id.Company);
    }
}
