package edu.nie.interactions;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout1) ;

        ListView listView = (ListView) findViewById(R.id.listview) ;

        ArrayList <String> listActivities = new ArrayList<>() ;

        listActivities.add("Text View activity") ;
        listActivities.add("Edit View activity") ;
        listActivities.add("Button View activity") ;
        listActivities.add("Toggle Button activity") ;
        listActivities.add("Check Box activity") ;
        listActivities.add("Radio Button activity") ;

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listActivities) ;

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0 :
                        startActivity(new Intent(getApplicationContext(),TextViewActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getApplicationContext(),EditAcitivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getApplicationContext(),ButtonActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getApplicationContext(),ToggleButtonAcitivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(getApplicationContext(),CheckBoxActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(getApplicationContext(),RadioActivity.class));
                        break;
                }
            }
        });

    }
}
