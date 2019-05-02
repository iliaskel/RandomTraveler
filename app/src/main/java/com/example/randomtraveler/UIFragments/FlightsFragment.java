package com.example.randomtraveler.UIFragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.randomtraveler.Database.Flight;
import com.example.randomtraveler.RecyclerViewAdapters.FlightsAdapter;
import com.example.randomtraveler.Repositories.FlightsRepository;
import com.example.randomtraveler.R;
import com.example.randomtraveler.ViewModels.FlightViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FlightsFragment extends Fragment{

    private String TAG = FlightsFragment.class.getSimpleName();

    private FlightViewModel flightViewModel;
    private RecyclerView mFlightsRecyclerView;
    private FlightsRepository flightsRepository;
    private String startingPoint;
    private String days;
    private String budget;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.flights_results_fragment,container,false);

        mFlightsRecyclerView = v.findViewById(R.id.flights_results_rv);
        mFlightsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        mFlightsRecyclerView.setHasFixedSize(true);

        final FlightsAdapter flightsAdapter = new FlightsAdapter(getContext());
        mFlightsRecyclerView.setAdapter(flightsAdapter);

        flightViewModel = ViewModelProviders.of(this).get(FlightViewModel.class);
        flightViewModel.getAllFlights().observe(this, new Observer<List<Flight>>() {
           @Override
           public void onChanged(List<Flight> flights) {
              flightsAdapter.setAllFlights(flights);
           }
       });
        return  v;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onSomAttach: ATTACHEDD");
        flightsRepository = new FlightsRepository(getActivity().getApplication());
        flightsRepository.fetchFlights(startingPoint,days,budget);
    }

    @Override
    public void onStop() {
        super.onStop();
        flightsRepository.deleteAllFlights();
    }

    public void setSearchParams(String startingPoint, String days, String budget){
        this.startingPoint = startingPoint;
        this.days = days;
        this.budget = budget;
    }


}
