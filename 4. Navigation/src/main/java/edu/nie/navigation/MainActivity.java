package edu.nie.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_RESULT = "number";

    private ViewGroup container;

    private List<Integer> numbers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (ViewGroup) findViewById(R.id.container);

        View go = findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(-1);
            }
        });

        for (int i : numbers) {
            addView(i);
        }
    }

    private void start(int number) {
        Intent intent = new Intent(MainActivity.this, NumberPickerActivity.class);
        intent.putExtra(NumberPickerActivity.KEY_NUMBER, number);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int number = data.getIntExtra(KEY_RESULT, 0);
        numbers.add(number);
        addView(number);
    }

    private void addView(int number) {
        TextView view = (TextView) getLayoutInflater().inflate(R.layout.number, container, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(Integer.parseInt(((TextView) v).getText().toString()));
            }
        });
        view.setText(String.valueOf(number));
        container.addView(view);
    }
}
