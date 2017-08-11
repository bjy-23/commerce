package com.wondersgroup.commerce.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.model.MsgItem;
import com.wondersgroup.commerce.widget.SwipeMenuViewHolder;

import java.util.List;

/**
 * Created by 薛定猫 on 2015/11/26.
 */
public class RecyclerAdapter extends RecyclerView.Adapter {
    private List<MsgItem> msgItems;
    private Context context;
    private LayoutInflater layoutInflater;
    private OnItemTouchListener onItemTouchListener;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View swipeMenuView = layoutInflater.inflate(R.layout.swipe_menu_view, parent, false);
        View swipeMenuView=LayoutInflater.from(parent.getContext()).inflate(R.layout.swipe_menu_view,parent,false);
        LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120, context.getResources().getDisplayMetrics()));
        l.setMargins(0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, context.getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, context.getResources().getDisplayMetrics()), 0);
        swipeMenuView.setLayoutParams(l);
        //View captureView = layoutInflater.inflate(R.layout.view_recycler_item, parent, false);
        View captureView=LayoutInflater.from(parent.getContext()).inflate(R.layout.view_recycler_item,parent,false);

        return new SwipeViewHolder(parent.getContext(), (ViewGroup) swipeMenuView, (ViewGroup) captureView, SwipeMenuViewHolder.EDGE_RIGHT).getDragViewHolder();

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final SwipeViewHolder swipeHolder = (SwipeViewHolder) SwipeMenuViewHolder.getHolder(holder);
        MsgItem data = msgItems.get(position);
        swipeHolder.appName.setText(data.getAppName());
        swipeHolder.flowName.setText(data.getFlowName());
        swipeHolder.title.setText(data.getTitle());
        swipeHolder.user.setText(data.getUser());
        swipeHolder.date.setText(data.getDate());
       /* swipeHolder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msgItems.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, getItemCount());
            }
        });*/
    }

    public RecyclerAdapter(Context context, List<MsgItem> msgItems) {
        this.context = context;
        this.msgItems = msgItems;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return msgItems.size();
    }

    class SwipeViewHolder extends SwipeMenuViewHolder implements View.OnClickListener {
        TextView appName;
        TextView flowName;
        TextView title;
        TextView user;
        TextView date;
        private TextView tvDelete;

        public SwipeViewHolder(Context context, ViewGroup bgView, ViewGroup topView, int mTrackingEdges) {
            super(context, bgView, topView, mTrackingEdges);
            bgView.setOnClickListener(this);
        }

        @Override
        public void initView(View itemView) {
            appName=(TextView)itemView.findViewById(R.id.recycler_item_appName);
            flowName=(TextView)itemView.findViewById(R.id.recycler_item_flowName);
            title=(TextView)itemView.findViewById(R.id.recycler_item_title);
            user=(TextView)itemView.findViewById(R.id.recycler_item_user);
            date=(TextView)itemView.findViewById(R.id.recycler_item_date);
            tvDelete = (TextView) itemView.findViewById(R.id.tv_delete);
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

    public interface OnItemTouchListener {
        void onItemTouchListener(int position, View view);
    }
}
