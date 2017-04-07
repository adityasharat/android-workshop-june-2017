package edu.nie.layouts.session2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import edu.nie.layouts.R;

public class ActivityTransition extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
    }

    public void transition(View view) {
        startActivity(new Intent(this, DummyActivity.class));

        overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit);
    }
}