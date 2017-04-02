package edu.nie.interactions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class RadioActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);

//        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout1) ;

        RadioGroup group = (RadioGroup) findViewById(R.id.radiogroup) ;

        group.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.yes) {
                    Toast.makeText(getApplicationContext(), "choice: Yes",
                            Toast.LENGTH_SHORT).show();
                } else if(checkedId == R.id.no) {
                    Toast.makeText(getApplicationContext(), "choice: No",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "choice: May be",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });

        RadioButton yes = (RadioButton) findViewById(R.id.yes);
        RadioButton no = (RadioButton) findViewById(R.id.no);
        RadioButton maybe = (RadioButton) findViewById(R.id.maybe);
        TextView textView = (TextView) findViewById(R.id.textView2);

        int selectedId = group.getCheckedRadioButtonId();

        // find which radioButton is checked by id
        if(selectedId == yes.getId()) {
            textView.setText("You chose 'Yes' option");
        } else if(selectedId == no.getId()) {
            textView.setText("You chose 'No' option");
        } else {
            textView.setText("You chose 'May Be' option");
        }
    }
}
