package edu.nie.interactions;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CheckBoxActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout1) ;

        final CheckBox check = new CheckBox(getApplicationContext()) ;

        check.setText("Do you Agree for T & C??");
        check.setTextColor(Color.parseColor("#0000FF"));
        check.setChecked(false);

        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (check.isChecked())
                    Toast.makeText(getApplicationContext(), "You have agreed for the T & C", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), "You have declined the T & C", Toast.LENGTH_LONG).show();
            }
        });

        linearLayout.addView(check);
    }
}
