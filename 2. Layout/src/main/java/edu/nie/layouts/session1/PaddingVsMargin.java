package edu.nie.layouts.session1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import edu.nie.layouts.R;
import edu.nie.layouts.demo.Demo;

public class PaddingVsMargin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.padding_vs_margin);
    }

    public void padding(View view) {
        Demo.start(this, R.layout.padding);
    }

    public void margin(View view) {
        Demo.start(this, R.layout.margin);
    }
}
