package edu.nie.interactions;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ButtonActivity extends AppCompatActivity {

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout1);

        final TextView textView = new TextView(getApplicationContext());
        textView.setText(String.valueOf(count));
        textView.setTextColor(Color.parseColor("#00FFFF"));
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setTextScaleX(3.0f);
        textView.setMinHeight(50);
        linearLayout.addView(textView);
        Button button = new Button(getApplicationContext());

        button.setText("Click to add 1");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                textView.setText(String.valueOf(count));
            }
        });

        linearLayout.addView(button);

    }
}
