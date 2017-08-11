package com.wondersgroup.commerce.teamwork.statistics;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wondersgroup.commerce.R;

/**
 * Created by chan on 7/13/17.
 */

public class InOutAdapter extends RecyclerView.Adapter<InOutAdapter.ViewHolder> {
    private Context context;

    public InOutAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InOutAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_inout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
