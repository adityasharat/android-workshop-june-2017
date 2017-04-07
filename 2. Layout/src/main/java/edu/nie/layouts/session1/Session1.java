package edu.nie.layouts.session1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import edu.nie.layouts.R;
import edu.nie.layouts.demo.Demo;
import edu.nie.layouts.session1.basic_layouts.BasicLayouts;

public class Session1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.session1);
    }

    public void dPSpAndPixel(View view) {
        Intent intent = new Intent(this, DpSpAndPixel.class);
        startActivity(intent);
    }

    public void viewAndViewGroup(View view) {
        Demo.start(this, R.layout.view_viewgroup);
    }

    public void basicLayouts(View view) {
        Intent intent = new Intent(this, BasicLayouts.class);
        startActivity(intent);
    }

    public void paddingVsMargin(View view) {
        Intent intent = new Intent(this, PaddingVsMargin.class);
        startActivity(intent);
    }

    public void gravityAndLayoutGravity(View view) {
        Demo.start(this, R.layout.gravity_and_layout_gravity);
    }

    public void layoutWeight(View view) {
        Demo.start(this, R.layout.layout_weight);
    }
}
