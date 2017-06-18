package edu.nie.interactions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // another way to set the content view
        //ViewGroup container = (ViewGroup) getLayoutInflater().inflate(R.layout.activity_main, null, false);
        //setContentView(container);

        // simple example
        Button btnSample = (Button) findViewById(R.id.btnSample);

        // react to click of the button
        btnSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("button was clicked");
            }
        });

        //renderItems(container);
    }

    void renderItems(ViewGroup container) {
        TextView tv = new TextView(container.getContext());
        tv.setText("Some Text in a Text View");
        container.addView(tv);

        CheckBox cb = new CheckBox(container.getContext());
        cb.setText("A Check Box");
        container.addView(cb);

        Button btn = new Button(container.getContext());
        btn.setText("This is a Button");
        container.addView(btn);


    }
}
