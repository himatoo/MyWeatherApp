package com.example.like2.myweatherapp;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    Intent intent;
    TextView textView;
    String details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        textView = findViewById(R.id.tv_details);
        intent = getIntent();
        details = intent.getStringExtra("weather");
        textView.setText(details);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.share_details) {
            String mimtype = "text/plain";
            String title = "weather details";
            Intent shareintent = ShareCompat.IntentBuilder.from(this)
                    .setChooserTitle(title)
                    .setType(mimtype)
                    .setText(details)
                    .getIntent();
            if (shareintent.resolveActivity(getPackageManager()) != null) {
                startActivity(shareintent);
            }
        }
        return true;
    }
}
