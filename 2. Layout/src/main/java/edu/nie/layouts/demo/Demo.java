package edu.nie.layouts.demo;

import android.content.Context;
import android.content.Intent;

public class Demo {
    public static void start(Context context, int layout) {
        Intent intent = new Intent(context, DemoActivity.class);
        intent.putExtra(DemoActivity.VIEW_TO_LOAD, layout);
        context.startActivity(intent);
    }
}
