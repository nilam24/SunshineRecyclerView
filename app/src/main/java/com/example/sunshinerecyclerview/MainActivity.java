package com.example.sunshinerecyclerview;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sunshinerecyclerview.data.Sunshinepreferences;
import com.example.sunshinerecyclerview.utilities.NetworkUtils;
import com.example.sunshinerecyclerview.utilities.OpenWeatherJsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity  implements ForecastAdapter.ForecastAdapterOnClickHandler{
     private RecyclerView mRecyclerView;
    ProgressBar progressBar;
    TextView textViewError;
    private ForecastAdapter mForecastAdapter;
    


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        int number=1,i=0,j=0;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        mRecyclerView=(RecyclerView)findViewById(R.id.recyclerview_forecast);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        textViewError=(TextView)findViewById(R.id.textViewError);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mForecastAdapter=new ForecastAdapter(this);
        mRecyclerView.setAdapter(mForecastAdapter);




//        for(i=0;i<6;i++)
//        {
//            System.out.println(" ");
//            for(j=0;j<=i;j++){
//
//                System.out.print(number+" ");
//                number++;
//            }
//        }






    }

    private void loadWeatherData(){
        showWeatherData();
        String location= Sunshinepreferences.getPreferredWeatherLocation(this);
           new FetchWeatherTask().execute(location);


    }

    private void  showErrorMessage(){
        textViewError.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);




    }
    private void  showWeatherData(){
        mRecyclerView.setVisibility(View.VISIBLE);
        textViewError.setVisibility(View.INVISIBLE);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.forecast1,menu);


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_refresh)
        {
            mForecastAdapter.setmWeatherData(null);
            loadWeatherData();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(String weatherForDay) {
        Context context=getApplicationContext();
        Toast.makeText(this,"welcome to item click "+weatherForDay,Toast.LENGTH_LONG).show();

    }

    class FetchWeatherTask extends AsyncTask<String, Void,String[]>
    {

        FetchWeatherTask(){
            
            super();

        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);


        }

        @Override
        protected String[] doInBackground(String... params) {

            String[] simpleJsonWeatherData=null;
            if (params.length == 0) {
                return null;
            }
            String location = params[0];
            URL url = NetworkUtils.buildUrl(location);
            try {
                String jsonWeatherResponse = NetworkUtils.getResponseFromHttpUrl(url);

                 simpleJsonWeatherData = OpenWeatherJsonUtils.getSimpleWeatherStringsFromJson(MainActivity.this, jsonWeatherResponse);




            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
            return simpleJsonWeatherData;

        }

        @Override
        protected void onPostExecute(String [] weatherData) {
            super.onPostExecute(weatherData);

            progressBar.setVisibility(View.INVISIBLE);
            if(weatherData!=null)
            {
                showWeatherData();

                 mForecastAdapter.setmWeatherData(weatherData);


                }
            else {
                showErrorMessage();
            }



        }
    }
}
