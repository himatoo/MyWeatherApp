package com.example.like2.myweatherapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements WeatherRecyclerAdapter.ListitemclickListner {


    ProgressBar progressBar;
    WeatherRecyclerAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        progressBar = findViewById(R.id.loading_indicator);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.rec1);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager ln = new LinearLayoutManager(this);
        ln.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(ln);
        adapter = new WeatherRecyclerAdapter(new ArrayList<WeatherDataModel>(), this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            new networktask().execute();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListitemclick(int index) {
        Toast.makeText(this, "item # " + index, Toast.LENGTH_SHORT).show();

    }

    class networktask extends AsyncTask<Void, Void, List<WeatherDataModel>> {

        @Override
        protected List<WeatherDataModel> doInBackground(Void... voids) {
            return NetworkUtils.Fetchdata();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(List<WeatherDataModel> weatherDataModels) {
            super.onPostExecute(weatherDataModels);
            progressBar.setVisibility(View.INVISIBLE);
            adapter.dataModels.clear();
            if (weatherDataModels != null && !weatherDataModels.isEmpty()) {
                adapter.dataModels = weatherDataModels;
                adapter.notifyDataSetChanged();
            }
        }
    }

}

