package edu.temple.stockapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StockListAdapter extends BaseAdapter {

    Context context;
    File file;
    String internalFilename = "stock_file";
    ArrayList<String> stockList = new ArrayList<>();

    public StockListAdapter(Context context) throws FileNotFoundException {
        this.context = context;
        Scanner s = new Scanner(new File(context.getFilesDir(), internalFilename));
        while (s.hasNext()){
            stockList.add(s.next());
        }
        s.close();
    }

    @Override
    public int getCount() {
        return stockList.size();
    }

    @Override
    public Object getItem(int position) {
        return stockList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setText(stockList.get(position));
        return textView;
    }
}
