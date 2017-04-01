package edu.nie.layouts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by vinays on 01/04/17.
 */

public class LinearLayoutVertical extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        LinearLayout layout1 = (LinearLayout) findViewById(R.id.layout1) ;

        for (int i = 0 ; i < 15 ; i++) {
            Button button = new Button(getApplicationContext()) ;
            button.setText("Button "+i);
            layout1.addView(button) ;
        }
    }
}
