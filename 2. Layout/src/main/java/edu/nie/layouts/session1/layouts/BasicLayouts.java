package edu.nie.layouts.session1.layouts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import edu.nie.layouts.R;
import edu.nie.layouts.demo.Demo;

public class BasicLayouts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_layouts);
    }

    public void showLinearHorizontalLayout(View view) {
        Demo.start(this, R.layout.linear_layout_example);
    }

    public void showTableLayout(View view) {
        Demo.start(this, R.layout.table_layout);
    }

    public void showRelativeLayout(View view) {
        Demo.start(this, R.layout.relative_layout);
    }

    public void showScrollLayout(View view) {
        Intent intent = new Intent(this, ScrollViewDemo.class);
        startActivity(intent);
    }

    public void showFrameLayout(View view) {
        Demo.start(this, R.layout.frame_layout);
    }
}
