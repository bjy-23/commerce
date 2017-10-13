package com.wondersgroup.commerce.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wondersgroup.commerce.recyclerView.ViewModel;

import java.util.List;

/**
 * Created by bjy on 2017/9/6.
 */

public class CommonAdapter  extends RecyclerView.Adapter{
    private Context context;
    private List<ViewModel> viewModels;
    private ViewModel temp;

    private LayoutInflater inflater;
    private OnItemClick onItemClick;


    public CommonAdapter(Context context, List<ViewModel> viewModels) {
        this.context = context;
        this.viewModels = viewModels;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        temp = viewModels.get(position);
        return viewModels.get(position).type();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(viewType,parent,false);
        return temp.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewModel viewModel = viewModels.get(position);
        viewModel.dataBind(holder);

        //单击
        if (onItemClick != null){
            viewModel.onItemClick(onItemClick, position);
        }
    }

    @Override
    public int getItemCount() {
        return viewModels.size();
    }

    public interface OnItemClick{
        void onClick(int position);
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
}
