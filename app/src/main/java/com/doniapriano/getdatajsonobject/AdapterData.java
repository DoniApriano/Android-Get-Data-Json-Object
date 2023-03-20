package com.doniapriano.getdatajsonobject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {

    LayoutInflater inflater;
    List<DataModel> dataModels;

    public AdapterData(Context context, List<DataModel> dataModels) {
        this.inflater = LayoutInflater.from(context);
        this.dataModels = dataModels;
    }

    @NonNull
    @Override
    public AdapterData.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_view, parent, false);
        return new HolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterData.HolderData holder, int position) {
        holder.tvName.setText(dataModels.get(position).getName());
        holder.tvType.setText(dataModels.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {

        TextView tvName,tvType;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvType = itemView.findViewById(R.id.tv_type);
        }
    }
}
