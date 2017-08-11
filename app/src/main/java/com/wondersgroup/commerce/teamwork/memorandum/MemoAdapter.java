package com.wondersgroup.commerce.teamwork.memorandum;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.model.Memo;
import com.wondersgroup.commerce.widget.SwipeMenuViewHolder;

import java.util.ArrayList;

/**
 * Created by 1229 on 2016/3/21.
 */
public class MemoAdapter extends RecyclerView.Adapter{
    private ArrayList<Memo> memos;
    private Context context;
    private LayoutInflater layoutInflater;
    private OnItemTouchListener onItemTouchListener;

    public  MemoAdapter(Context context,ArrayList<Memo> memos){
        this.context = context;
        this.memos = memos;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View swipeMenuView = layoutInflater.inflate(R.layout.swipe_menu_view, parent, false);
        LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 93, context.getResources().getDisplayMetrics()));
        l.setMargins(0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, context.getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, context.getResources().getDisplayMetrics()), 0);
        swipeMenuView.setLayoutParams(l);
        View captureView = layoutInflater.inflate(R.layout.list_line_item, parent, false);
        return new SwipeViewHolder(context, (ViewGroup) swipeMenuView, (ViewGroup) captureView, SwipeMenuViewHolder.EDGE_RIGHT).getDragViewHolder();

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final SwipeViewHolder swipeHolder = (SwipeViewHolder) SwipeMenuViewHolder.getHolder(holder);
        swipeHolder.content.setText(memos.get(position).getMemo_content());
        swipeHolder.time1.setText(memos.get(position).getMemo_time());
        swipeHolder.time2.setText(memos.get(position).getRemind_time());
        swipeHolder.delete.setText("删除");
        if(memos.get(position).getRemind_time()==null){
            swipeHolder.clock.setImageResource(R.drawable.clock1);
        }else {
            swipeHolder.clock.setImageResource(R.drawable.clock2);
        }
    }

    class SwipeViewHolder extends SwipeMenuViewHolder implements View.OnClickListener {
        TextView content;
        TextView time1;
        TextView time2;
        ImageView clock;
        TextView delete;

        @Override
        public void initView(View view) {
            content = (TextView) view.findViewById(R.id.content);
            time1 = (TextView) view.findViewById(R.id.time1);
            time2 = (TextView)view.findViewById(R.id.time2);
            clock = (ImageView) view.findViewById(R.id.clock);
            delete = (TextView) view.findViewById(R.id.tv_delete);
        }


        public SwipeViewHolder(Context context, ViewGroup bgView, ViewGroup topView, int mTrackingEdges) {
            super(context, bgView, topView, mTrackingEdges);
            topView.setOnClickListener(this);
            topView.setTag("1");
            bgView.setOnClickListener(this);
            bgView.setTag("2");

        }

        @Override
        public void onClick(View v) {
            if (onItemTouchListener != null) {
                onItemTouchListener.onItemTouchListener(
                        ((RecyclerView.ViewHolder) getDragViewHolder()).getAdapterPosition(), v);
            }
        }



    }

    public void setOnItemTouchListener(OnItemTouchListener onItemTouchListener) {
        this.onItemTouchListener = onItemTouchListener;
    }


    @Override
    public int getItemCount() {
        if(memos == null){
            return 0;
        }

        return memos.size();
    }


    public interface OnItemTouchListener {
        void onItemTouchListener(int position, View view);
    }
}

