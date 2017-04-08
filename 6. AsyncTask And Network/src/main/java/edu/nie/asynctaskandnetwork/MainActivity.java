package edu.nie.asynctaskandnetwork;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View startTaskView = findViewById(R.id.start_task);
        final TextView statusView = (TextView) findViewById(R.id.run_status);
        final CheckBox runAsyncView = (CheckBox) findViewById(R.id.run_async);

        TextView incrementView = (TextView) findViewById(R.id.increment);
        final TextView counterView = (TextView) findViewById(R.id.counter);

        startTaskView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statusView.setText("Running...");

                if (runAsyncView.isChecked()) {

                    v.setEnabled(false);

                    Log.d("order", "1");
                    startSuperComplexTaskInBackground(v, statusView);
                    Log.d("order", "3");

                } else {

                    v.setEnabled(false);

                    Log.d("order", "1");
                    startSuperComplexTask();
                    Log.d("order", "3");

                    v.setEnabled(true);
                    statusView.setText("Completed !");
                }
            }
        });

        incrementView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                counterView.setText(String.valueOf(counter));
            }
        });

        View nextButton = findViewById(R.id.next_section);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ServerActivity.class);
                startActivity(intent);
            }
        });
    }

    private void startSuperComplexTask() {
        Log.d("order", "2");
        for (int i = 0; i < 100000; i++) {
            isPrime(i);
        }
    }

    private boolean isPrime(int x) {
        for (int f = 2; f < x; f++) {
            if (x % f == 0) {
                return false;
            }
        }
        return true;
    }

    private void startSuperComplexTaskInBackground(final View button, final TextView statusView) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                startSuperComplexTask();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                button.setEnabled(true);
                statusView.setText("Completed !");
            }
        }.execute();
    }
}
