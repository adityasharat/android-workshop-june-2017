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

    public static final String KEY_RESULT_NUMBER = "resultNum";
    public static final String KEY_RESULT_WAS_EDIT = "resultWasEdit";

    private static final String KEY_SAVED_NUMBERS = "savedNumbers";
    private static final String KEY_SAVED_POSITION = "savedPos";

    private ViewGroup container;

    private List<Integer> numbers = new ArrayList<>();
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (ViewGroup) findViewById(R.id.container);

        View go = findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNumberPickerActivity(-1);
            }
        });

        if (savedInstanceState != null) {
            numbers = savedInstanceState.getIntegerArrayList(KEY_SAVED_NUMBERS);
            currentIndex = savedInstanceState.getInt(KEY_SAVED_POSITION);
            if (numbers == null) {
                numbers = new ArrayList<>();
            }
        }

        int index = 0;
        for (int i : numbers) {
            addView(i, index);
            index++;
        }
    }

    private void openNumberPickerActivity(int number) {
        Intent intent = new Intent(MainActivity.this, NumberPickerActivity.class);
        intent.putExtra(NumberPickerActivity.KEY_NUMBER, number);
        startActivityForResult(intent, 1);
    }

    private void addView(int number, int index) {
        TextView view = (TextView) getLayoutInflater().inflate(R.layout.number, container, false);
        view.setTag(index);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (int) v.getTag();
                openNumberPickerActivity(Integer.parseInt(((TextView) v).getText().toString()));
            }
        });
        view.setText(String.valueOf(number));
        container.addView(view);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            int number = data.getIntExtra(KEY_RESULT_NUMBER, 0);
            boolean wasEdit = data.getBooleanExtra(KEY_RESULT_WAS_EDIT, false);
            if (wasEdit) {
                numbers.set(currentIndex, number);
                TextView tv = (TextView) container.getChildAt(currentIndex);
                tv.setText(String.valueOf(number));
            } else {
                numbers.add(number);
                addView(number, numbers.size() - 1);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putIntegerArrayList(KEY_SAVED_NUMBERS, (ArrayList<Integer>) numbers);
        super.onSaveInstanceState(outState);
    }
}
