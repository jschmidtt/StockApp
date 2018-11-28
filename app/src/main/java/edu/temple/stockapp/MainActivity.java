package edu.temple.stockapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    Button deleteButton;
    FragmentManager fm = getSupportFragmentManager();
    StockListFragment stockFrag = new StockListFragment();

    File stockFile;

    final private int REQUEST_CODE_MESSAGE = 934;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stockFile = new File(getFilesDir(),"stock_file");

        floatingActionButton = findViewById(R.id.floatingActionButton);
        deleteButton = findViewById(R.id.buttonFileDelete);

        //Floating action button for adding new stocks
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddStock.class);
                startActivityForResult(intent, REQUEST_CODE_MESSAGE);
            }
        });

        //Delete stock file for debugging/testing
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stockFile.exists()){
                    stockFile.delete();
                }
            }
        });

        fm.beginTransaction().replace(R.id.container_1, stockFrag).commit();
    }

    //Result update for when new stock is added
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_MESSAGE && resultCode == RESULT_OK){
            try {
                stockFrag.stockUpdate();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
