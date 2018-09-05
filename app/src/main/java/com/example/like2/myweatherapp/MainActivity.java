package com.example.like2.myweatherapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements WeatherRecyclerAdapter.ListitemclickListner, LoaderManager.LoaderCallbacks<List<WeatherDataModel>>, SharedPreferences.OnSharedPreferenceChangeListener {

    ProgressBar progressBar;
    WeatherRecyclerAdapter adapter;
    RecyclerView recyclerView;
    private static final int loader_id = 1;
    SharedPreferences preferences;
    TextView textView;
    String adress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        progressBar = findViewById(R.id.loading_indicator);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.rec1);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager ln = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(ln);
        adapter = new WeatherRecyclerAdapter(new ArrayList<WeatherDataModel>(), this);
        recyclerView.setAdapter(adapter);
        getSupportLoaderManager().initLoader(loader_id, null, this);
        textView = findViewById(R.id.tv_test);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.registerOnSharedPreferenceChangeListener(this);
        textView.setText(String.valueOf(preferences.getBoolean("show_bass", true)));
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
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.refresh_weatherdata) {
            progressBar.setVisibility(View.VISIBLE);
            LoaderManager loaderManager = getSupportLoaderManager();
            Loader<List<WeatherDataModel>> loader = loaderManager.getLoader(loader_id);
            /*if (loader == null) {
                loaderManager.initLoader(loader_id, null, this);
            } else {
                //invalidateData();
                loaderManager.restartLoader(loader_id, null, this);
            }*/
            if (loader != null) {
                //invalidateData();
                loaderManager.restartLoader(loader_id, null, this);
            }
            return true;
        }
        if (id == R.id.maplocation) {
            openLocationinMap(adress);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListitemclick(int index) {
        // Toast.makeText(this, "item # " + index, Toast.LENGTH_SHORT).show();

        WeatherDataModel data = adapter.dataModels.get(index);
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("weather", data.getWeather());
        startActivity(intent);


    }

    @NonNull
    @Override
    public Loader<List<WeatherDataModel>> onCreateLoader(int id, @Nullable Bundle args) {
        Log.d("LOADERRR", "onLoaderCreate");
        return new MainLoader(this);

    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<WeatherDataModel>> loader, List<WeatherDataModel> data) {

        progressBar.setVisibility(View.INVISIBLE);
        if (data != null && !data.isEmpty()) {
            adapter.setData(data);
        }
        Log.d("LOADERRR", "onLoaderfinshed");

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<WeatherDataModel>> loader) {
       /*adapter.dataModels.clear();
       adapter.notifyDataSetChanged();*/
        Log.d("LOADERRR", "onLoaderReset");

    }

    private void invalidateData() {
        adapter.dataModels.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("show_bass")) {
            textView.setText(String.valueOf(sharedPreferences.getBoolean("show_bass", true)));

        }
        if (key.equals("location")) {
            adress = sharedPreferences.getString("location", "");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }

    private void openLocationinMap(String adress) {
        String adr = "1600 Ampitheatre Parkway, CA";
        Uri uri = Uri.parse("geo:0,0?q=" + adress);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    /* class networktask extends AsyncTask<Void, Void, List<WeatherDataModel>> {

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
    }*/

}

