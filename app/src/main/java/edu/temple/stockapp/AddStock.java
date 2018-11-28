package edu.temple.stockapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;

public class AddStock extends AppCompatActivity {

    Button buttonAdd, buttonCancel;
    EditText editText;
    File file;
    String internalFilename = "stock_file";
    String stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonCancel = findViewById(R.id.buttonCancel);
        editText = findViewById(R.id.editTextStock);

        file = new File(getFilesDir(), internalFilename);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                stock = s.toString();
                stock = stock + "\n";
            }
        });


        final Intent sendBack = new Intent();

        //Cancel Button
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED, sendBack);
                finish();
            }
        });

        //Add Button
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream outputStream  = new FileOutputStream(file, true);
                    outputStream.write(stock.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                setResult(RESULT_OK, sendBack);
                finish();
            }
        });
    }
}
