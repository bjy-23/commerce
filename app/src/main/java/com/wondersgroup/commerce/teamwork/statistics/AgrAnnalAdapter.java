package com.wondersgroup.commerce.teamwork.statistics;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.commerce.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by root on 7/12/17.
 */

public class AgrAnnalAdapter extends RecyclerView.Adapter<AgrAnnalAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> type = new ArrayList<>();

    public AgrAnnalAdapter(Context context) {
        this.context = context;
    }

    public void setType(ArrayList<String> type) {
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_agr_annals, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.nbType.setText(type.get(position));
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.nb_type)
        TextView nbType;//年报类型
        @BindView(R.id.y_nb)
        TextView ynb;//应年报
        @BindView(R.id.w_nb)
        TextView wnb;//未年报
        @BindView(R.id.yi_nb)
        TextView yinb;//已年报
        @BindView(R.id.l_nb)
        TextView nbl;//年报率

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
