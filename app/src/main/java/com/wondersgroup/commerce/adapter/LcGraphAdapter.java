package com.wondersgroup.commerce.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.model.LcGraphItem;
import com.wondersgroup.commerce.model.TextWpicItem;
import com.wondersgroup.commerce.utils.DWZH;
import com.wondersgroup.commerce.widget.TableRow;
import com.wondersgroup.commerce.widget.TableRowView;

import java.util.List;

/**
 * Created by yclli on 2015/3/5.
 */
public class LcGraphAdapter extends RecyclerView.Adapter<LcGraphAdapter.ViewHolder>{

    private Context context;
    private int lineNum;
    private List<LcGraphItem> items;


    public LcGraphAdapter(List<LcGraphItem> items, int lineNum) {
        this.items = items;
        this.lineNum = lineNum;
    }

    @Override
    public LcGraphAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_lcgraph_item,parent,false);
        ViewHolder holder=new ViewHolder(view, lineNum);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(LcGraphAdapter.ViewHolder holder, int position) {
        LcGraphItem item=items.get(position);
        if(position==0){
            holder.topLine.setVisibility(View.INVISIBLE);
            holder.point.setImageResource(R.mipmap.circle_s);
            holder.bottomLine.setVisibility(View.VISIBLE);
        }else if(position==(items.size()-1)){
            holder.topLine.setVisibility(View.VISIBLE);
            holder.bottomLine.setVisibility(View.INVISIBLE);
        }else{
            holder.topLine.setVisibility(View.VISIBLE);
            holder.point.setImageResource(R.mipmap.circle);
            holder.bottomLine.setVisibility(View.VISIBLE);
        }
        for(int i=0; i<lineNum; i++){
            TableRow view = (TableRow)holder.backgroud.getChildAt(i);
            view.setTitle(item.getTitleList().get(i));
            view.getContentView().setText(item.getContentList().get(i));
            if(i!=0){
                if(position==0){
                    view.getTitleView().setTextColor(ContextCompat.getColor(context, R.color.deep_gray));
                    view.getContentView().setTextColor(ContextCompat.getColor(context, R.color.deep_gray));
                }else{
                    view.getTitleView().setTextColor(ContextCompat.getColor(context, R.color.white_gray));
                    view.getContentView().setTextColor(ContextCompat.getColor(context, R.color.white_gray));
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{

        View topLine;
        ImageView point;
        View bottomLine;
        LinearLayout backgroud;

        public ViewHolder(View itemView, int num) {
            super(itemView);
            topLine = itemView.findViewById(R.id.topline);
            point = (ImageView) itemView.findViewById(R.id.point);
            bottomLine = itemView.findViewById(R.id.bottomline);
            backgroud = (LinearLayout) itemView.findViewById(R.id.contentBlock);
            backgroud.addView(new TableRow.Builder(backgroud.getContext())
                    .marginH(15)
                    .marginV(0)
                    .titleW(76)
                    .textSize(12)
                    .titleColor(R.color.blue)
                    .textColor(R.color.blue)
                    .title("")
                    .content("")
                    .hideBtmLine()
                    .build());
            for(int i=1; i<num-1; i++){
                backgroud.addView(new TableRow.Builder(backgroud.getContext())
                    .marginH(15)
                    .marginV(8)
                    .titleW(76)
                    .textSize(11)
                    .textColor(R.color.deep_gray)
                    .title("")
                    .content("")
                    .hideBtmLine()
                    .build());
            }
            backgroud.addView(new TableRow.Builder(backgroud.getContext())
                    .marginH(15)
                    .marginV(8)
                    .titleW(76)
                    .textSize(11)
                    .textColor(R.color.deep_gray)
                    .title("")
                    .content("")
                    .hideBtmLine()
                    .build());
        }
    }
}
