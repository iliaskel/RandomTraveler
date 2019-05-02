package com.example.randomtraveler.RecyclerViewAdapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.randomtraveler.Database.Flight;
import com.example.randomtraveler.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FlightsAdapter extends RecyclerView.Adapter<FlightsAdapter.FlightsViewHolder>{

    private final String TAG = FlightsAdapter.class.getSimpleName();
    private List<Flight> flightsList = new ArrayList<>();
    private Context context;


    public FlightsAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public FlightsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater =LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.flight_result_list_item,parent,false);
        return new FlightsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightsViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return flightsList.size();
    }

    public void setAllFlights(List<Flight> flightList){
        this.flightsList = flightList;
        notifyDataSetChanged();
    }


    class FlightsViewHolder extends RecyclerView.ViewHolder{

        private TextView going_city_from,going_city_to,going_time_departure,
                going_time_arrival,going_duration,return_city_from,return_city_to,
                return_time_departure,return_time_arrival,return_duration,price;

        private ImageView going_airline_logo,return_airline_logo;

        FlightsViewHolder(@NonNull View itemView) {
            super(itemView);
            going_city_from = itemView.findViewById(R.id.flights_results_list_item_city_departure_going_tv);
            going_city_to = itemView.findViewById(R.id.flights_results_list_item_city_arrival_going_tv);
            going_time_departure = itemView.findViewById(R.id.flights_results_list_item_departure_hour_going_tv);
            going_time_arrival = itemView.findViewById(R.id.flights_results_list_item_arrival_hour_going_tv);
            going_duration = itemView.findViewById(R.id.flights_results_list_item_duration_going_tv);
            return_city_from = itemView.findViewById(R.id.flights_results_list_item_city_departure_returning_tv);
            return_city_to = itemView.findViewById(R.id.flights_results_list_item_city_arrival_returning_tv);
            return_time_departure = itemView.findViewById(R.id.flights_results_list_item_departure_hour_returning_tv);
            return_time_arrival = itemView.findViewById(R.id.flights_results_list_item_arrival_hour_returning_tv);
            return_duration = itemView.findViewById(R.id.flights_results_list_item_duration_returning_tv);
            price = itemView.findViewById(R.id.flights_results_list_item_price_tv);
            going_airline_logo = itemView.findViewById(R.id.flights_results_list_item_airline_logo_going_iv);
            return_airline_logo = itemView.findViewById(R.id.flights_results_list_item_airline_logo_coming_iv);
        }

        void bind(int position) {
            Flight flight = flightsList.get(position);
            if(flight==null)
                return;

            going_city_from.setText(flight.getGoing_city_from());
            Log.d(TAG, "bind: going_city_from " + flight.getGoing_city_from());
            going_city_to.setText(flight.getGoing_city_to());
            Log.d(TAG, "bind:going_city_to " + flight.getGoing_city_to());
            going_time_departure.setText(flight.getGoing_time_departure());
            Log.d(TAG, "bind:  going_time_departure " + flight.getGoing_time_departure());
            going_time_arrival.setText(flight.getGoing_time_arrival());
            Log.d(TAG, "bind: going_time_arrival " + flight.getGoing_time_arrival());
            going_duration.setText(flight.getGoing_duration());
            Log.d(TAG, "bind: going_duration " + flight.getGoing_duration());
            return_city_from.setText(flight.getReturn_city_from());
            Log.d(TAG, "bind: return_city_from " + flight.getReturn_city_from());
            return_city_to.setText(flight.getReturn_city_to());
            Log.d(TAG, "bind: return_city_to " + flight.getReturn_city_to());
            return_time_departure.setText(flight.getReturn_time_departure());
            Log.d(TAG, "bind: return_time_departure " + flight.getReturn_time_departure());
            return_time_arrival.setText(flight.getReturn_time_arrival());
            Log.d(TAG, "bind: return_time_arrival " + flight.getReturn_time_arrival());
            return_duration.setText(flight.getReturn_duration());
            Log.d(TAG, "bind: return_duration " + flight.getReturn_duration());
            price.setText(String.valueOf(flight.getPrice()));
            Log.d(TAG, "bind: price " + flight.getPrice());


            Log.d(TAG, "bind: going airline " + flight.getGoing_airline());
            Log.d(TAG, "bind: return airline " + flight.getReturn_airline());

            Glide.with(context)
                    .load("https://images.kiwi.com/airlines/64/" + flight.getGoing_airline() + ".png")
                    .into(going_airline_logo);

            Glide.with(context)
                    .load("https://images.kiwi.com/airlines/64/" + flight.getReturn_airline()+ ".png")
                    .into(return_airline_logo);
        }
    }
}
