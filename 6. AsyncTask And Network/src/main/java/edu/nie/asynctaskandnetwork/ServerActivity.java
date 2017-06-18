package edu.nie.asynctaskandnetwork;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class ServerActivity extends AppCompatActivity {

    private Server server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setup();
        setContentView(R.layout.activity_server);

        View get = findViewById(R.id.get);


        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {
                String url = ((EditText) findViewById(R.id.input)).getText().toString();

                // check if the user has entered something
                // by simply checking if the length of the
                // text is zero or not
                if (url.length() == 0) {
                    // if zero, show a toast to user
                    Toast.makeText(button.getContext(), "Enter a url", Toast.LENGTH_SHORT).show();
                    // and return without doing anything
                    return;
                }

                // Now find the output text view where
                // you will dump the data from the server
                TextView outputTextView = (TextView) findViewById(R.id.output);

                // while the data is being loaded
                // set it's text to `Loading..`
                outputTextView.setText("Loading...");

                // Also disable the button that was clicked so that the user
                // can press it again and again before we are done
                button.setEnabled(false);

                // Now call the getDataFromUrl method
                // we are passing the url the user entered
                // the output text view where we will dump the result
                // the button, so that we can enabled it back after we are done
                getDataFromUrl(url, outputTextView, button);
            }
        });
    }

    private void getDataFromUrl(String url, final TextView outputView, final View button) {

        // create a new async task
        // the 1st type here is `String` which is like the input type
        // the 2nd type is Void, just ignore it for now
        // the 3rd type is String, which is like the result type.
        new AsyncTask<String, Void, String>() {

            // This is the actual stuff that is running in the
            // background. Please do not modify any views here
            // if you do that, the app will crash.
            @Override
            protected String doInBackground(String... url) {

                try { // ignore this

                    // url is an array of Strings
                    // the input type (1st argument)
                    // which you passed int the execute method

                    // this method makes the call
                    // and get the data as a string
                    // and then returns it
                    return getFromServer(url[0]);


                } catch (IOException e) { // ignore this
                    e.printStackTrace(); // ignore this
                }

                // if something went wrong just return null or nothing.
                return null;
            }

            // This method is call after the `doInBackground` is done
            // This is called on the main thread. So you can modify the views here
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                // remember if we returned null, means something went wrong
                // so let's do tell that to the user
                if (result == null) {
                    result = "some error occurred !";
                }

                // now enable the button again
                // you users can use it again
                button.setEnabled(true);

                // Now set the result you got from the
                // server to the output text view.
                outputView.setText(result);
            }
        }.execute(url);  // actually starts the task you defined above.
    }

    /**
     * SETUP CODE RELATED TO NETWORK COMMUNICATION
     */
    private String getFromServer(String url) throws IOException {
        return server.get(url).execute().body().string();
    }

    private void setup() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .build();
        server = retrofit.create(Server.class);
    }

    public interface Server {
        @GET
        Call<ResponseBody> get(@Url String url);
    }
}
