package com.example.angel1.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angel1.Model.DetailsModel;
import com.example.angel1.R;

import java.util.ArrayList;

public class MoreDetailsAdapter extends RecyclerView.Adapter<MoreDetailsAdapter.ViewHolder> {

    Context context;
    ArrayList<DetailsModel>list;
    public MoreDetailsAdapter(Context context, ArrayList<DetailsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MoreDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.more_details, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull MoreDetailsAdapter.ViewHolder holder, int position) {
        holder.address.setText(list.get(position).getuAddress());
        holder.home.setText(list.get(position).getuHome());
        holder.name.setText(list.get(position).getuName());
        holder.county.setText(list.get(position).getuCounty());
        holder.gender.setText(list.get(position).getuGender());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView address, name,home, county,gender;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            address= itemView.findViewById(R.id.address_txt);
            name= itemView.findViewById(R.id.textView);
            home= itemView.findViewById(R.id.home_txt);
            county= itemView.findViewById(R.id.county_txt);
            gender= itemView.findViewById(R.id.gender_txt);
        }
    }
}
