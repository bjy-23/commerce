package com.wondersgroup.commerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wondersgroup.commerce.R;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yclli on 2015/11/26.
 */
public class InfoItemListAdapter extends BaseAdapter {

    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private int type = 0;
    private Context context;

    public InfoItemListAdapter(Context context, List<Map<String, Object>> data){
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }


    public InfoItemListAdapter(Context context, int type, List<Map<String, Object>> data){
        this.context = context;
        this.type = type;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public String getTitle(int position){
        return (String)data.get(position).get("name");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = layoutInflater.inflate(R.layout.list_info_item, null);
        ViewHolder holder = new ViewHolder(v);
        holder.name.setTextSize(14);
        v.setTag(holder);
        if("1".equals(data.get(position).get("isUrgency"))){
            holder.name.setTextColor(context.getResources().getColor(R.color.red));
            holder.name.setText("【急】 "+(String) data.get(position).get("name"));
        }else
            holder.name.setText((String) data.get(position).get("name"));
        holder.date.setText((String)data.get(position).get("date"));
        holder.rangeImg.setBackgroundResource((int)data.get(position).get("rangeImg"));
        holder.rangeName.setText((String) data.get(position).get("rangeName"));
        return v;
    }

    static class ViewHolder {
        @Bind(R.id.text1) TextView name;
        @Bind(R.id.text2) TextView date;
        @Bind(R.id.img) ImageView rangeImg;
        @Bind(R.id.text3) TextView rangeName;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
