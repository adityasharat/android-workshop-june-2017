package edu.nie.layouts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by vinays on 01/04/17.
 */

public class ListViewSample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView listview = (ListView) findViewById(R.id.listview) ;

        ArrayList <String> listOfDepartments = new ArrayList<>() ;
        listOfDepartments.add("Computer Science & Engineering") ;
        listOfDepartments.add("Information Science & Engineering") ;
        listOfDepartments.add("Electronics & Communication Engineering") ;
        listOfDepartments.add("Electrical & Electronics Engineering") ;
        listOfDepartments.add("Civil Engineering") ;
        listOfDepartments.add("Mechanical Engineering") ;
        listOfDepartments.add("Industrial & Production Engineering") ;
        listOfDepartments.add("MCA") ;
        listOfDepartments.add("Basic Sciences") ;
        listOfDepartments.add("Computer Science & Engineering") ;
        listOfDepartments.add("Information Science & Engineering") ;
        listOfDepartments.add("Electronics & Communication Engineering") ;
        listOfDepartments.add("Electrical & Electronics Engineering") ;
        listOfDepartments.add("Civil Engineering") ;
        listOfDepartments.add("Mechanical Engineering") ;
        listOfDepartments.add("Industrial & Production Engineering") ;
        listOfDepartments.add("MCA") ;
        listOfDepartments.add("Basic Sciences") ;

        final ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, listOfDepartments);
        listview.setAdapter(adapter);
    }
}
