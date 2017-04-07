package edu.nie.layouts.session1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import edu.nie.layouts.R;
import edu.nie.layouts.demo.Demo;

public class DpSpAndPixel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dp_sp_and_pixel);
    }

    public void whatIsScreenSize(View view) {
        Demo.start(this, R.layout.screen_size);
    }

    public void dpi(View view) {
        Demo.start(this, R.layout.dpi);
    }

    public void sp(View view) {
        Demo.start(this, R.layout.sp);
    }
}
