package com.example.naray.stockwatch;

/**
 * Created by naray on 3/18/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHandler";

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    // DB Name
    private static final String DATABASE_NAME = "StockAppDB";
    // DB Table Name
    private static final String TABLE_NAME = "StockWatchTable";
    ///DB Columns
    private static final String SYMBOL = "StockSymbol";
    private static final String COMPANY = "CompanyName";
    // DB Table Create Code
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    SYMBOL + " TEXT not null unique," +
                    COMPANY + " TEXT not null)";

    private SQLiteDatabase database;

    /*private static final String DATABASE_ALTER_TABLE_FOR_V2 = "ALTER TABLE "
            + TABLE_NAME + " ADD COLUMN " + AREA + " int not null default 0;";*/

    /*private static final String DATABASE_ALTER_TABLE_FOR_V3 = "ALTER TABLE "
            + TABLE_NAME + " ADD COLUMN " + NUMBORDERS + " int not null default 0;";*/

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = getWritableDatabase();
        Log.d(TAG, "DatabaseHandler: C'tor DONE");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // onCreate is only called is the DB does not exist
        Log.d(TAG, "onCreate: STOCK New DB");
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        /*if (newVersion == 2) {
            db.execSQL(DATABASE_ALTER_TABLE_FOR_V2);
        }
        if (newVersion == 3) {
            db.execSQL(DATABASE_ALTER_TABLE_FOR_V3);
        }*/

    }

   /* public ArrayList<Country> loadCountries() {

        Log.d(TAG, "loadCountries: LOADING COUNTRY DATA FROM DB");
        ArrayList<Country> countries = new ArrayList<>();
        Cursor cursor = database.query(
                TABLE_NAME,  // The table to query
                new String[]{COUNTRY, REGION, SUBREGION, CAPITAL, POPULATION}, // The columns to return
                null, // The columns for the WHERE clause
                null, // The values for the WHERE clause
                null, // don't group the rows
                null, // don't filter by row groups
                null); // The sort order
        if (cursor != null) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {
                String country = cursor.getString(0);
                String region = cursor.getString(1);
                String subRegion = cursor.getString(2);
                String capital = cursor.getString(3);
                int population = cursor.getInt(4);
                countries.add(new Country(country, capital, population, region, subRegion));
                cursor.moveToNext();
            }
            cursor.close();
        }
        Log.d(TAG, "loadCountries: DONE LOADING COUNTRY DATA FROM DB");

        return countries;
    }*/


    public void addStock(Stock stock) {
        Log.d(TAG, "addStock: Adding " + stock.getStock_symbol());
        ContentValues values = new ContentValues();
        values.put(SYMBOL, stock.getStock_symbol());
        values.put(COMPANY, stock.getCompany());
        database.insert(TABLE_NAME, null, values);
        Log.d(TAG, ("addStock: Add Complete"));
    }


    public void deleteStock(String symbol) {
        Log.d(TAG, "deleteStock: Deleting Stock " + symbol);
        int cnt = database.delete(TABLE_NAME, SYMBOL + "= ?", new String[]{symbol});
        Log.d(TAG, "deleteStock: " + cnt);
    }


    public ArrayList<Stock> loadStocks() {
        Log.d(TAG, " loadStocks: Load all symbol-company entries from DB");
        ArrayList<Stock> stocks = new ArrayList<Stock>();
        Cursor cursor = database.query(
                TABLE_NAME, // The table to query
                new String[]{ SYMBOL, COMPANY }, // The columns to return
                null, // The columns for the WHERE clause, null means “*”
                null, // The values for the WHERE clause, null means “*”
                null, // don't group the rows
                null, // don't filter by row groups
                null); // The sort order
        if (cursor != null) { // Only proceed if cursor is not null
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                String symbol = cursor.getString(0); //
                String company = cursor.getString(1); //
                stocks.add(new Stock(symbol, company));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return stocks;
    }


    /*public void updateCountry(Country country) {
        ContentValues values = new ContentValues();
        values.put(COUNTRY, country.getName());
        values.put(REGION, country.getRegion());
        values.put(SUBREGION, country.getSubRegion());
        values.put(CAPITAL, country.getCapital());
        values.put(POPULATION, country.getPopulation());

        long key = database.update(
                TABLE_NAME, values, COUNTRY + " = ?", new String[]{country.getName()});

        Log.d(TAG, "updateCountry: " + key);
    }*/

    /*public void deleteCountry(String name) {
        Log.d(TAG, "deleteCountry: " + name);
        int cnt = database.delete(TABLE_NAME, COUNTRY + " = ?", new String[]{name});
        Log.d(TAG, "deleteCountry: " + cnt);
    }

    public void dumpLog() {
        Cursor cursor = database.rawQuery("select * from " + TABLE_NAME, null);
        if (cursor != null) {
            cursor.moveToFirst();

            Log.d(TAG, "dumpLog: vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
            for (int i = 0; i < cursor.getCount(); i++) {
                String country = cursor.getString(0);
                String region = cursor.getString(1);
                String subRegion = cursor.getString(2);
                String capital = cursor.getString(3);
                int population = cursor.getInt(4);
                Log.d(TAG, "dumpLog: " +
                        String.format("%s %-18s", COUNTRY + ":", country) +
                        String.format("%s %-18s", REGION + ":", region) +
                        String.format("%s %-18s", SUBREGION + ":", subRegion) +
                        String.format("%s %-18s", CAPITAL + ":", capital) +
                        String.format("%s %-18s", POPULATION + ":", population));
                cursor.moveToNext();
            }
            cursor.close();
        }

        Log.d(TAG, "dumpLog: ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
    }*/
}

