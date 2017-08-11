package com.wondersgroup.commerce.teamwork.casedeal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.model.NoteRecordEnquire;
import com.wondersgroup.commerce.model.NoteRecordSpot;
import com.wondersgroup.commerce.widget.SwipeMenuViewHolder;

import java.util.List;

/**
 * Created by 1229 on 2016/3/21.
 */
public class CaseDetialXccxAdapter extends RecyclerView.Adapter{
    private List<NoteRecordSpot> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private OnItemTouchListener onItemTouchListener;

    public CaseDetialXccxAdapter(Context context, List<NoteRecordSpot> memos){
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
        View captureView = layoutInflater.inflate(R.layout.list_line_item_xccx, parent, false);
        return new SwipeViewHolder(context, (ViewGroup) swipeMenuView, (ViewGroup) captureView, SwipeMenuViewHolder.EDGE_RIGHT).getDragViewHolder();

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final SwipeViewHolder swipeHolder = (SwipeViewHolder) SwipeMenuViewHolder.getHolder(holder);
        swipeHolder.tv_InspectedPerson.setText(list.get(position).getLitigantName());
        swipeHolder.tv_InspectPerson.setText(list.get(position).getInspector());
        swipeHolder.tv_InspectSpot.setText(list.get(position).getInsspot());
        swipeHolder.tv_InspectStartDate.setText(list.get(position).getInsfromStr());
        swipeHolder.tv_InspectEndDate.setText(list.get(position).getInstoStr());
        swipeHolder.delete.setText("删除");
    }

    class SwipeViewHolder extends SwipeMenuViewHolder implements View.OnClickListener {
        private TextView tv_InspectedPerson;        //被检查人
        private TextView tv_InspectPerson;          //检查人
        private TextView tv_InspectSpot;            //检查地点
        private TextView tv_InspectStartDate;       //检查起始时间
        private TextView tv_InspectEndDate;         //检查结束时间
        private TextView delete;

        @Override
        public void initView(View view) {
            tv_InspectedPerson = (TextView) view.findViewById(R.id.inspected_person_textview);
            tv_InspectPerson = (TextView) view.findViewById(R.id.inspect_person_name_textview);
            tv_InspectSpot = (TextView) view.findViewById(R.id.inspected_spot_textview);
            tv_InspectStartDate = (TextView) view.findViewById(R.id.inspected_date_start_textview);
            tv_InspectEndDate = (TextView) view.findViewById(R.id.inspected_date_end_textview);
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

