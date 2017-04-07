package edu.nie.interactions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

public class ToggleButtonAcitivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout1);

        final ToggleButton button = new ToggleButton(getApplicationContext());
        button.setChecked(true);

        button.setTextOff("I am OFF");
        button.setTextOn("I am ON");
        linearLayout.addView(button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Toggle Button", (button.isChecked() ? "I am ON" : "I am OFF"));
            }
        });

    }
}
