package com.juangm.retrofit_weather_demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.juangm.retrofit_weather_demo.models.WeatherAPIResult;
import com.juangm.retrofit_weather_demo.rest.RestClient;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final TextView textView = (TextView) findViewById(R.id.resulttext);

        //FAB onClick
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Sending request...", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                //Send get weather request
                RestClient.WeatherApiInterface service = RestClient.getClient();
                //We need to pass our city ID and our openweathermap APPID
                Call<WeatherAPIResult> call = service.getWeather(2519240, "476f28ed531b9477e89ddb6ab463dbd5");
                call.enqueue(new Callback<WeatherAPIResult>() {
                    @Override
                    public void onResponse(Response<WeatherAPIResult> response, Retrofit retrofit) {
                        if(response.isSuccess()) {
                            //Handle the received weather data here
                            WeatherAPIResult result = response.body();
                            Gson gson = new GsonBuilder().setPrettyPrinting().create();
                            textView.setText(gson.toJson(result));
                        } else {
                            Log.e("MainActivity", "Response received but request not successful. Response: " + response.raw());
                            textView.setText("Response received but request not successful. Response: " + response.raw());
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.e("MainActivity", "Request error!");
                    }
                });
            }
        });
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
