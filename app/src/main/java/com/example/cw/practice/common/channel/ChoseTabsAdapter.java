package com.example.cw.practice.common.channel;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cw.practice.R;
import com.example.cw.practice.ui.activity.ChannelActivity;

/**
 * Created by chenwei on 17/2/7.
 */

public class ChoseTabsAdapter extends RecyclerView.Adapter{

    public interface OnChoseTabsListener{
        void choseTabsItemClick(View view, int position);
    }

    private Context context;
    private LayoutInflater layoutInflater;
    private OnChoseTabsListener listener;

    public ChoseTabsAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setListener(OnChoseTabsListener onChoseTabsListener){
        this.listener = onChoseTabsListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChoseTabsViewHolder(layoutInflater.inflate(R.layout.channel_item_tab, null, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ChoseTabsViewHolder viewHolder = (ChoseTabsViewHolder) holder;
        String text = ChannelActivity.choseTabs.get(position);
        viewHolder.txt.setText(text);
        if (text == "头条"){
            viewHolder.txt.setTextColor(Color.WHITE);
        }
        viewHolder.txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.choseTabsItemClick(viewHolder.itemView, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ChannelActivity.choseTabs.size();
    }

    public class ChoseTabsViewHolder extends RecyclerView.ViewHolder {

        private TextView txt;

        public ChoseTabsViewHolder(View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.tabs_txt);
        }
    }
}
