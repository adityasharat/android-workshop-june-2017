package edu.nie.navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NumberPickerActivity extends AppCompatActivity {

    public static final String KEY_NUMBER = "number";

    private int number = 0;
    private EditText picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_picker);

        this.number = getIntent().getIntExtra(KEY_NUMBER, -1);

        picker = (EditText) findViewById(R.id.number);
        if (this.number != -1) {
            picker.setText(String.valueOf(this.number));
        }

        View done = findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = picker.getText().toString().trim();
                if (string.length() == 0) {
                    Toast.makeText(NumberPickerActivity.this, "please enter a number", Toast.LENGTH_SHORT).show();
                    return;
                }
                number = Integer.parseInt(string);
                returnResult();
            }
        });
    }

    private void returnResult() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(MainActivity.KEY_RESULT, number);
        setResult(AppCompatActivity.RESULT_OK, resultIntent);
        finish();
    }
}
