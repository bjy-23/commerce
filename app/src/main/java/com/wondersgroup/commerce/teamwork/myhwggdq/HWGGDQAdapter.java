package com.wondersgroup.commerce.teamwork.myhwggdq;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wondersgroup.commerce.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jiajiangyi on 2016/4/26 0026.
 */
public class HWGGDQAdapter extends BaseAdapter {

    private HwggdqBean data;
    private Context mContext;

    public HwggdqBean getData() {
        return data;
    }

    public void setData(HwggdqBean data) {
        this.data = data;
    }

    public HWGGDQAdapter(HwggdqBean data, Context context) {
        super();
        this.data = data;
        mContext = context;
    }

    @Override
    public int getCount() {
        return data.getValues().getResult().size();
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        if (arg1 == null) {
            arg1 = View.inflate(mContext, R.layout.item_hwggdq, null);
            ViewHolder holder = new ViewHolder(arg1);
            arg1.setTag(holder);
        }

        ViewHolder holder = (ViewHolder) arg1.getTag();

        holder.getTvAdName().setText(data.getValues().getResult().get(arg0).getAdName());
        holder.getTvReleaseOrganization().setText(data.getValues().getResult().get(arg0).getUnitName());
        holder.getTvReleaseTime().setText(data.getValues().getResult().get(arg0).getEndDate());


        return arg1;
    }


    static class ViewHolder {
        @BindView(R.id.tv_ad_name)
        TextView tvAdName;
        @BindView(R.id.tv_release_organization)
        TextView tvReleaseOrganization;
        @BindView(R.id.tv_release_time)
        TextView tvReleaseTime;

        public TextView getTvAdName() {
            return tvAdName;
        }

        public void setTvAdName(TextView tvAdName) {
            this.tvAdName = tvAdName;
        }

        public TextView getTvReleaseOrganization() {
            return tvReleaseOrganization;
        }

        public void setTvReleaseOrganization(TextView tvReleaseOrganization) {
            this.tvReleaseOrganization = tvReleaseOrganization;
        }

        public TextView getTvReleaseTime() {
            return tvReleaseTime;
        }

        public void setTvReleaseTime(TextView tvReleaseTime) {
            this.tvReleaseTime = tvReleaseTime;
        }

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
