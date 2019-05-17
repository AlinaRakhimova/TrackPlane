package com.gmail.catdog_puga24.trackplane.ui.newFlight;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.catdog_puga24.trackplane.R;
import com.gmail.catdog_puga24.trackplane.data.database.entity.TypeFlight;
import com.gmail.catdog_puga24.trackplane.ui.newFlight.presenter.IRecyclerTypeFlightPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TypeFlightAdapter extends RecyclerView.Adapter<TypeFlightAdapter.ViewHolder> {

    private IRecyclerTypeFlightPresenter presenter;

    public TypeFlightAdapter(IRecyclerTypeFlightPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_flight_types, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.position = position;
        presenter.bindView(holder);
        holder.typeFlightName.setOnClickListener(v -> presenter.onSelectedItem(holder));
    }

    @Override
    public int getItemCount() {
        return presenter.getItemCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements IViewHolder {

        @BindView(R.id.flightType)
        TextView typeFlightName;

        private int position = 0;

        private ViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + typeFlightName.getText() + "'";
        }

        @Override
        public void setTypeFlight(TypeFlight typeFlight) {
            typeFlightName.setText(typeFlight.getName());
        }

        @Override
        public int getPos() {
            return position;
        }
    }
}

