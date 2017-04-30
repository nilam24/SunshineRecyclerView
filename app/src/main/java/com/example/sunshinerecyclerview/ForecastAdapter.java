package com.example.sunshinerecyclerview;

import android.content.Context;
import android.os.storage.StorageManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Dell1 on 28/04/2017.
 */

  public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder> {

    private String [] mWeatherData;

    private  final ForecastAdapterOnClickHandler mClickHandler;

    public interface ForecastAdapterOnClickHandler {
        void onClick(String weatherForDay);
    }

    public ForecastAdapter(ForecastAdapterOnClickHandler clickHandler){
        mClickHandler=clickHandler;

    }

    @Override
    public ForecastAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        Context context=parent.getContext();
        int id=R.layout.forecast_list_item;
        LayoutInflater inflater=LayoutInflater.from(context);
        boolean attachParentImediately=false;
        View view=inflater.inflate(id,parent,attachParentImediately);




        return new ForecastAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastAdapterViewHolder holder, int position) {
        String weatherForThisDay=mWeatherData[position];
        holder.mWeatherTextView.setText(weatherForThisDay);


    }

    @Override
    public int getItemCount() {

       if(mWeatherData==null) {
           return 0;
       }

       return mWeatherData.length;

    }


    public void setmWeatherData(String[] weatherData)
    {
        mWeatherData=weatherData;
        notifyDataSetChanged();
    }


     class ForecastAdapterViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        public final TextView mWeatherTextView;


         public ForecastAdapterViewHolder(View itemView) {
             super(itemView);

             mWeatherTextView=(TextView)itemView.findViewById(R.id.tv_weather_data);
             itemView.setOnClickListener(this);

         }




         @Override
         public void onClick(View view) {

             int adapterPosition=getAdapterPosition();
            String weatherForDay=mWeatherData[adapterPosition];
             mClickHandler.onClick(weatherForDay);
         }
     }

}
