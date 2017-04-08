package edu.nie.persistanceanddata;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    public static final String IS_FROM_FILE = "IS_FROM_FILE";
    SharedPreferences.Editor editor;
    EditText name;
    Button save, startSaveFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editor = getSharedPreferences("test", MODE_PRIVATE).edit();
        name = (EditText) findViewById(R.id.name);
        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("name", name.getText().toString()).commit();
                openNextScreen(false);
            }
        });
        startSaveFile = (Button) findViewById(R.id.start_file_save);
        startSaveFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveInFile(name.getText().toString());
                openNextScreen(true);
            }
        });
    }

    private void openNextScreen(boolean isFromFile){
        Intent intent = new Intent(this, ShowDetail.class);
        intent.putExtra(IS_FROM_FILE, isFromFile);
        startActivity(intent);
    }

    private void saveInFile(String text){
        String filename = "test_file.txt";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(text.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
