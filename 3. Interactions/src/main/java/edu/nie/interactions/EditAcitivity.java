package edu.nie.interactions;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

public class EditAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout1);

        EditText editText = new EditText(getApplicationContext());
        editText.setHint(getResources().getString(R.string.entername));
        editText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        editText.setTextColor(Color.parseColor("#3333cc"));
        editText.setTextSize(25);
        editText.setTypeface(Typeface.SANS_SERIF);
        editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        linearLayout.addView(editText);
    }
}
