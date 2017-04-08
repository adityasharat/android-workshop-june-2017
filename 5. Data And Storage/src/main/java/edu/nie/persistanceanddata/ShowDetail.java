package edu.nie.persistanceanddata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ShowDetail extends AppCompatActivity {

    TextView name;
    private boolean isFromFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        name = (TextView) findViewById(R.id.name);
        isFromFile = getIntent().getExtras().getBoolean(MainActivity.IS_FROM_FILE);
        if (isFromFile) {
            readFromFile();
        } else {
            readFromPref();
        }
    }

    private void readFromFile() {

        String saveName = "";

        try {
            InputStream inputStream = ShowDetail.this.openFileInput("test_file.txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                saveName = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        display(saveName);
    }

    private void readFromPref() {
        String savedName = getSharedPreferences("test", MODE_PRIVATE).getString("name", "");
        display(savedName);
    }

    private void display(String savedName) {
        name.setText(savedName);
    }
}
