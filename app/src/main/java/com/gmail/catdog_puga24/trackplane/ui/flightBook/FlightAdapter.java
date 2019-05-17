package com.gmail.catdog_puga24.trackplane.ui.flightBook;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.catdog_puga24.trackplane.R;
import com.gmail.catdog_puga24.trackplane.data.database.entity.Flight;
import com.gmail.catdog_puga24.trackplane.ui.flightBook.view.FlightBookFragment;

import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.ViewHolder> {

    private final List<Flight> flights;
    private final FlightBookFragment.OnListFragmentInteractionListener listener;

    public FlightAdapter(List<Flight> flights, FlightBookFragment.OnListFragmentInteractionListener listener) {
        this.flights = flights;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_flight, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.flight = flights.get(position);
        holder.date.setText(flights.get(position).getDate());

        holder.mView.setOnClickListener(v -> {
            if (null != listener) listener.onListFragmentInteraction(holder.flight);
        });
    }

    @Override
    public int getItemCount() {
        return flights.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final TextView date;
        private final TextView quantity;
        private final TextView meteo;
        private Flight flight;

        private ViewHolder(final View view) {
            super(view);
            mView = view;
            date = view.findViewById(R.id.etDate);
            quantity = view.findViewById(R.id.etQuantity);
            meteo = view.findViewById(R.id.etMeteo);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + date.getText() + "'";
        }
    }
}

