package com.example.user.newstoday;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class detail extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();

        String stuff = bundle.getString("detail1");

        Log.d("akash",stuff);

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(stuff));
        startActivity(browserIntent);
    }
}
