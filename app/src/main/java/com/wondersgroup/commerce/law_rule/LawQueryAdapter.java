package com.wondersgroup.commerce.law_rule;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.law_rule.bean.LawBean;
import com.wondersgroup.commerce.law_rule.bean.LawDetailsBean;
import com.wondersgroup.commerce.law_rule.bean.LawTypeBean;
import com.wondersgroup.commerce.service.Result;
import com.wondersgroup.commerce.utils.DWZH;

import java.util.List;

/**
 * Created by bjy on 2017/5/15.
 */

public class LawQueryAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<LawBean> mData;
    private LayoutInflater inflater;
    private Drawable drawableRight;

    public LawQueryAdapter(Context mContext, List<LawBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        inflater = LayoutInflater.from(mContext);
        drawableRight = mContext.getResources().getDrawable(R.mipmap.app_right);
        drawableRight.setBounds(0,0,drawableRight.getIntrinsicWidth(),drawableRight.getIntrinsicHeight());
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case 0:
            case 1:
                view = inflater.inflate(R.layout.item_law_query_type, parent, false);
                return new LawQueryViewHolder_1(view);
            case 2:
                view = inflater.inflate(R.layout.item_law_details,parent,false);
                return new LawQueryViewHolder_2(view);
            case 3:
                view = inflater.inflate(R.layout.item_law_head,parent,false);
                return new LawQueryViewHolde_3(view);
            case 4://footView
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.setMargins(DWZH.dp(30),DWZH.dp(20),0,0);
                TextView textView = new TextView(mContext);
                textView.setLayoutParams(lp);
                textView.setTextColor(mContext.getResources().getColor(R.color.red));
                textView.setText("注：以上法律法规和行政执法系统一致。");
                return new FootViewHolder(textView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (mData.get(position).getType()){
            case 0:
                LawQueryViewHolder_1 viewHolder0 = (LawQueryViewHolder_1) holder;
                viewHolder0.textType.setText(((LawTypeBean)mData.get(position)).getTypeName());
                viewHolder0.textType.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext,LawQueryActivity.class);
                        intent.putExtra(Constants.TYPE,Constants.LAW);
                        intent.putExtra(Constants.BEAN,(LawTypeBean)mData.get(position));
                        mContext.startActivity(intent);
                    }
                });
                break;
            case 1:
                LawQueryViewHolder_1 viewHolder1 = (LawQueryViewHolder_1) holder;
                viewHolder1.textType.setText(((LawTypeBean)mData.get(position)).getTypeName());
                viewHolder1.textType.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext,LawQueryActivity.class);
                        intent.putExtra(Constants.TYPE,Constants.T);
                        intent.putExtra(Constants.BEAN,(LawTypeBean)mData.get(position));
                        mContext.startActivity(intent);
                    }
                });
                break;
            case 2:
                LawQueryViewHolder_2 viewHolder2 = (LawQueryViewHolder_2) holder;
                final LawDetailsBean lawDetailsBean = (LawDetailsBean)mData.get(position);
                viewHolder2.tvIndex.setText(lawDetailsBean.getShowTitle());
                viewHolder2.tvContent.setText(lawDetailsBean.getDetailContent());
                if ("1".equals(lawDetailsBean.getChildFlg())){
                    viewHolder2.tvContent.setCompoundDrawables(null,null,drawableRight,null);
                    viewHolder2.layoutCard.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext,LawQueryActivity.class);
                            intent.putExtra(Constants.BEAN,lawDetailsBean);
                            intent.putExtra(Constants.TYPE,Constants.K);
                            mContext.startActivity(intent);
                        }
                    });
                }
                break;
            case 3:
                LawQueryViewHolde_3 viewHolde3 = (LawQueryViewHolde_3) holder;
                viewHolde3.tvName.setText(((LawTypeBean)mData.get(position)).getTypeName());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addFootView(){
        LawBean lawBean = new LawBean();
        lawBean.setType(4);
        mData.add(lawBean);
        notifyDataSetChanged();
    }

    class LawQueryViewHolder_1 extends RecyclerView.ViewHolder{
        private TextView textType;

        public LawQueryViewHolder_1(View itemView) {
            super(itemView);
            textType = (TextView) itemView.findViewById(R.id.tv_type);
        }
    }

    class LawQueryViewHolder_2 extends RecyclerView.ViewHolder{
        private TextView tvIndex;
        private TextView tvContent;
        private CardView layoutCard;

        public LawQueryViewHolder_2(View itemView) {
            super(itemView);
            tvIndex = (TextView) itemView.findViewById(R.id.tv_index);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            layoutCard = (CardView) itemView.findViewById(R.id.layout_card);
        }
    }

    class LawQueryViewHolde_3 extends RecyclerView.ViewHolder{
        private TextView tvName;
        public LawQueryViewHolde_3(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    class FootViewHolder extends RecyclerView.ViewHolder{
        public FootViewHolder(View itemView) {
            super(itemView);
        }
    }
}
