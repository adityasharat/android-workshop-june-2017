package edu.nie.interactions;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TextViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout1) ;

        TextView textView = new TextView(getApplicationContext());
//                (TextView) findViewById(R.id.textView);
        textView.setText("Hi Nie");
        textView.setAllCaps(true);
        textView.setTextColor(Color.parseColor("#3333cc"));
        textView.setTextSize(25);
        textView.setTypeface(Typeface.MONOSPACE);
        textView.setBackgroundColor(Color.parseColor("#CECECE"));
        linearLayout.addView(textView);
    }
}
