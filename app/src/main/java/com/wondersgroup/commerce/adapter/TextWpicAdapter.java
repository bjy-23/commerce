package com.wondersgroup.commerce.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.model.TextWpicItem;

import java.util.List;

/**
 * Created by yclli on 2015/3/5.
 */
public class TextWpicAdapter extends RecyclerView.Adapter<TextWpicAdapter.ViewHolder>{

    private Context context;
    private List<TextWpicItem> items;

    public interface OnItemClickListener{
        void OnItemClick(View view, int position);
    }

    private static OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }

    public TextWpicAdapter(List<TextWpicItem> items) {
        this.items = items;
    }

    @Override
    public TextWpicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_textwpic_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(TextWpicAdapter.ViewHolder holder, int position) {
        TextWpicItem item=items.get(position);
        if("1".equals(item.getIsUrgency())){
            holder.title.setTextColor(context.getResources().getColor(R.color.red));
            holder.title.setText("【急】 "+item.getTitle());
        }else{
            holder.title.setTextColor(context.getResources().getColor(R.color.text_color));
            holder.title.setText(item.getTitle());
        }
        holder.rowTwoText.setText(item.getRowTwoText());
        holder.picView.setImageResource(item.getPicId());
        holder.picText.setText(item.getPicText());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        TextView rowTwoText;
        ImageView picView;
        TextView picText;

        public ViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.text1);
            rowTwoText = (TextView) itemView.findViewById(R.id.text2);
            picView = (ImageView) itemView.findViewById(R.id.img);
            picText = (TextView) itemView.findViewById(R.id.text3);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listener!=null){
                listener.OnItemClick(v,getLayoutPosition());
            }
        }
    }
}
