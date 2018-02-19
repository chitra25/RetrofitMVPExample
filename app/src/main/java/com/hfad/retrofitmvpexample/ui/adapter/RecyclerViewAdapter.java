package com.hfad.retrofitmvpexample.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hfad.retrofitmvpexample.R;
import com.hfad.retrofitmvpexample.model.Notice;
import com.hfad.retrofitmvpexample.presenter.RecyclerViewItemClickListener;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {
    private ArrayList<Notice> dataList;
    private RecyclerViewItemClickListener recyclerViewItemClickListener;

    public RecyclerViewAdapter(ArrayList<Notice> dataList, RecyclerViewItemClickListener recyclerViewItemClickListener){
        this.dataList=dataList;
        this.recyclerViewItemClickListener=recyclerViewItemClickListener;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view_list_layout, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        holder.textNoticeTitle.setText(dataList.get(position).getTitle());
        holder.textNoticeBrief.setText(dataList.get(position).getBrief());
        holder.textNoticeURL.setText(dataList.get(position).getFileSource());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewItemClickListener.onItemClick(dataList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView textNoticeTitle;
        TextView textNoticeBrief;
        TextView textNoticeURL;
        public CustomViewHolder(View itemView) {
            super(itemView);
            textNoticeTitle =  itemView.findViewById(R.id.text_notice_title);
            textNoticeBrief =  itemView.findViewById(R.id.text_notice_brief);
            textNoticeURL =  itemView.findViewById(R.id.text_notice_url);
        }
    }
}
