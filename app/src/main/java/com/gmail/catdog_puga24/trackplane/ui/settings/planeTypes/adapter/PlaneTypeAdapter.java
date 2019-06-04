package com.gmail.catdog_puga24.trackplane.ui.settings.planeTypes.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.catdog_puga24.trackplane.R;
import com.gmail.catdog_puga24.trackplane.data.database.entity.TypePlane;
import com.gmail.catdog_puga24.trackplane.ui.settings.planeTypes.PlaneTypeFragment;

import java.util.List;

public class PlaneTypeAdapter extends RecyclerView.Adapter<PlaneTypeAdapter.ViewHolder> {

    private final List<TypePlane> listPlaneTypes;
    private final PlaneTypeFragment.OnPlaneTypeListFragmentInteractionListener listener;

    public PlaneTypeAdapter(List<TypePlane> items, PlaneTypeFragment.OnPlaneTypeListFragmentInteractionListener listener) {
        listPlaneTypes = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_plane_types, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.planeType = listPlaneTypes.get(position).getName(); //FIXME Проверить необходимость инициализации переменной
        holder.typeName.setText(listPlaneTypes.get(position).getName());
        holder.mView.setOnClickListener(v -> {
            if (null != listener) listener.onListFragmentInteraction(holder.planeType);
        });
    }

    @Override
    public int getItemCount() {
        if (listPlaneTypes == null) return 0;
        else return listPlaneTypes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final TextView typeName;
        private String planeType;

        private ViewHolder(final View view) {
            super(view);
            mView = view;
            typeName = view.findViewById(R.id.content);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + typeName.getText() + "'";
        }
    }
}
