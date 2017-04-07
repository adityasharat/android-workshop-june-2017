package edu.nie.layouts.session1.layouts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import edu.nie.layouts.R;

public class ScrollViewDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_layout);
    }

    public void scrollViewWithListView(View view) {
        final Intent intent = new Intent(this, ScrollViewWithListView.class);
        startActivity(intent);
    }
}
