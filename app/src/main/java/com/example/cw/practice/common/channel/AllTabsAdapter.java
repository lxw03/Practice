package com.example.cw.practice.common.channel;

import android.content.Context;
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

public class AllTabsAdapter extends RecyclerView.Adapter {

    public interface onAllTabsListener{
        void allTabsItemClick(View view, int position);
    }

    private Context context;
    private LayoutInflater layoutInflater;
    private onAllTabsListener listener;

    public void setListener(onAllTabsListener listener){
        this.listener = listener;
    }

    public AllTabsAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AllTabsViewHolder(layoutInflater.inflate(R.layout.channel_item_tab, null, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final AllTabsViewHolder viewHolder = (AllTabsViewHolder) holder;
        viewHolder.txt.setText(ChannelActivity.allTabs.get(position));
        viewHolder.txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.allTabsItemClick(viewHolder.itemView, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ChannelActivity.allTabs.size();
    }

    public class AllTabsViewHolder extends RecyclerView.ViewHolder {
        private TextView txt;

        public AllTabsViewHolder(View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.tabs_txt);
        }
    }
}
