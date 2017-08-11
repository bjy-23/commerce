package com.wondersgroup.commerce.teamwork.casedeal;

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
import com.wondersgroup.commerce.model.NoteRecordEnquire;
import com.wondersgroup.commerce.widget.SwipeMenuViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1229 on 2016/3/21.
 */
public class CaseDetialXwblAdapter extends RecyclerView.Adapter{
    private List<NoteRecordEnquire> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private OnItemTouchListener onItemTouchListener;

    public CaseDetialXwblAdapter(Context context, List<NoteRecordEnquire> memos){
        this.context = context;
        this.list = memos;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View swipeMenuView = layoutInflater.inflate(R.layout.swipe_menu_view, parent, false);
        LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 133, context.getResources().getDisplayMetrics()));
        l.setMargins(0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, context.getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, context.getResources().getDisplayMetrics()), 0);
        swipeMenuView.setLayoutParams(l);
        View captureView = layoutInflater.inflate(R.layout.list_line_item_xwbl, parent, false);
        return new SwipeViewHolder(context, (ViewGroup) swipeMenuView, (ViewGroup) captureView, SwipeMenuViewHolder.EDGE_RIGHT).getDragViewHolder();

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final SwipeViewHolder swipeHolder = (SwipeViewHolder) SwipeMenuViewHolder.getHolder(holder);
        swipeHolder.tv_enquiredPerson.setText(list.get(position).getInquiredname());
        swipeHolder.tv_enquireTimes.setText(list.get(position).getEnqordNo());
        swipeHolder.tv_enquireStartTime.setText(list.get(position).getEnqTimeStart());
        swipeHolder.tv_enquireEndTime.setText(list.get(position).getEnqTimeStart());
        swipeHolder.delete.setText("删除");
    }

    class SwipeViewHolder extends SwipeMenuViewHolder implements View.OnClickListener {
        private TextView tv_enquiredPerson;
        private TextView tv_enquireTimes;
        private TextView tv_enquireStartTime;
        private TextView tv_enquireEndTime;
        private TextView delete;

        @Override
        public void initView(View view) {
            tv_enquiredPerson = (TextView) view.findViewById(R.id.enquired_person_textview);
            tv_enquireTimes = (TextView) view.findViewById(R.id.enquire_times_textview);
            tv_enquireStartTime = (TextView) view.findViewById(R.id.enquire_start_date_textview);
            tv_enquireEndTime = (TextView) view.findViewById(R.id.enquire_end_date_textview);
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
        if(list == null){
            return 0;
        }

        return list.size();
    }


    public interface OnItemTouchListener {
        void onItemTouchListener(int position, View view);
    }
}

