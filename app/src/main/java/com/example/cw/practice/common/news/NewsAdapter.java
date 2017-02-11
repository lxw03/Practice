package com.example.cw.practice.common.news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cw.practice.R;

import java.util.ArrayList;

/**
 * Created by cw on 2017/2/11.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private ArrayList<String> mArrayList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public NewsAdapter(ArrayList<String> arrayList, Context context) {
        this.mArrayList = arrayList;
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mLayoutInflater.inflate(R.layout.fragment_news_item, null, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txt.setText(mArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt;
        public MyViewHolder(View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.fragment_news_text);
        }
    }
}
