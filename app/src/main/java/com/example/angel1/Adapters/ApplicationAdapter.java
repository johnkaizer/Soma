package com.example.angel1.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angel1.Model.ApplicationModel;
import com.example.angel1.R;

import java.util.ArrayList;

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.ViewHolder> {

    public ApplicationAdapter(Context context, ArrayList<ApplicationModel> list) {
        this.context = context;
        this.list = list;
    }

    Context context;
    ArrayList<ApplicationModel>list;

    @NonNull
    @Override
    public ApplicationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.application_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ApplicationAdapter.ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getAppTitle());
        holder.deadline.setText(list.get(position).getAppDeadline());
        holder.name.setText(list.get(position).getStdName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,deadline,name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.textView13);
            deadline = itemView.findViewById(R.id.textView15);
            name = itemView.findViewById(R.id.textView17);
        }
    }
}
