package edu.nie.interactions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // another way to set the content view
        //ViewGroup container = (ViewGroup) getLayoutInflater().inflate(R.layout.activity_main, null, false);
        //setContentView(container);

        // create a variable named `btnSample` which is of type View. Assign
        // the value of `btnSample` to whatever is returned by the method `findViewById`.
        //
        // Just like
        //          int i = someMethod();
        // in C++
        Button btnSample = (Button) findViewById(R.id.btnSample);

        // `btnSample` which is a `View` has a method `setOnClickListener`
        // like public methods in C++/JAVA classes. This method takes in
        // 1 argument, an object of type `View.OnClickListener`. So whenever
        // the `btnSample` view is clicked by the user, The Android OS will call
        // the `onClick` method of this object.
        // It is a callback, take whatever action you want inside the `onClick`
        // method.
        btnSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // `v` is the same as `btnSample` or basically the
                // view that was click. This can enable you to reuse the OnClickListener
                // object between multiple views
                System.out.println("button was clicked");
            }
        });

        //renderItems(container);
    }

    void renderItems(ViewGroup container) {
        TextView tv = new TextView(container.getContext());
        tv.setText("Some Text in a Text View");
        container.addView(tv);

        CheckBox cb = new CheckBox(container.getContext());
        cb.setText("A Check Box");
        container.addView(cb);

        Button btn = new Button(container.getContext());
        btn.setText("This is a Button");
        container.addView(btn);


    }
}
