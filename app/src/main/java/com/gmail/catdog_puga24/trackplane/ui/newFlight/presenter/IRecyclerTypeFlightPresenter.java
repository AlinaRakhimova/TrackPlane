package com.gmail.catdog_puga24.trackplane.ui.newFlight.presenter;

import com.gmail.catdog_puga24.trackplane.ui.newFlight.IViewHolder;

public interface IRecyclerTypeFlightPresenter {

    void bindView(IViewHolder iViewHolder);

    int getItemCount();

    void onSelectedItem(IViewHolder holder);
}


