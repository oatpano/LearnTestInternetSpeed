package com.example.anirutteerabut.learntestspeed;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<SpeedModel> dataList = new ArrayList<>();

    public void setDataList(int position, SpeedModel data) {
        if (dataList.size() <= position) {
            dataList.add(data);

        } else {
            dataList.set(position, data);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        if (dataList == null)
            return 0;

        return dataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final AppCompatTextView tvProgress;
        private final AppCompatTextView tvDownload;
        private final AppCompatTextView tvUpload;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvProgress = itemView.findViewById(R.id.tvProgress);
            tvDownload = itemView.findViewById(R.id.tvDownload);
            tvUpload = itemView.findViewById(R.id.tvUpload);
        }

        void bind(SpeedModel speedModel) {
            tvProgress.setText("" + speedModel.progress);
            tvDownload.setText("" + speedModel.downloadSpeed);
            tvUpload.setText("" + speedModel.uploadSpeed);
        }
    }
}
