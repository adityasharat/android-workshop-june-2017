package edu.nie.layouts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import edu.nie.layouts.session1.Session1;
import edu.nie.layouts.session2.Session2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSession1(View view) {
        Intent intent = new Intent(this, Session1.class);
        startActivity(intent);
    }

    public void startSession2(View view) {
        Intent intent = new Intent(this, Session2.class);
        startActivity(intent);
    }
}
