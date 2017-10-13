package com.wondersgroup.commerce.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Created by bjy on 2017/10/11.
 */

public abstract class ViewModel {
    protected CommonAdapter.OnItemClick onItemClick;
    protected int position;

    public abstract int type();

    public abstract RecyclerView.ViewHolder viewHolder(View view);

    public abstract void dataBind(RecyclerView.ViewHolder viewHolder);

    public void onItemClick(CommonAdapter.OnItemClick onItemClick, int position){
        this.onItemClick = onItemClick;
        this.position = position;
    }
}
