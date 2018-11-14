package edu.temple.stockapp;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class StockListFragment extends Fragment {

    View v;
    File file;
    String internalFilename = "stock_file";
    ListView listView;
    EditText textView;
    Context context;

    public StockListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_stock_list, container, false);

        textView = v.findViewById(R.id.editText);
        listView = v.findViewById(R.id.listView);

        //file = new File(context.getFilesDir(), internalFilename);

        /*
        if(file.exists()){
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                StringBuilder text = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    text.append(line);
                    text.append('\n');
                }
                br.close();
                textView.setText(text.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        */

        try {
            listView.setAdapter(new StockListAdapter(context));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return v;
    }

}
