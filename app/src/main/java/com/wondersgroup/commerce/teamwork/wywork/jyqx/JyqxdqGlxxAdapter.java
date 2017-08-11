package com.wondersgroup.commerce.teamwork.wywork.jyqx;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wondersgroup.commerce.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/4/15.
 */
public class JyqxdqGlxxAdapter extends BaseAdapter{
    private ArrayList<ArrayList<String>> datas;
    private Context mContext;

    public JyqxdqGlxxAdapter(ArrayList<ArrayList<String>> map,Context  context){
        super();
        datas   =   map;
        mContext    =   context;
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LinearLayout linearLayout;
            linearLayout = (LinearLayout) View.inflate(mContext, R.layout.jyqxdq_qyxx_list_item, null);
            for (int i=0;i<datas.get(position).size()-1;i++){
            View view =   View.inflate(mContext, R.layout.jyqxdq_qyxx_list_item_child,null);
            LinearLayout ll =   (LinearLayout)view.findViewById(R.id.jyqxdq_qyxx_list_ll_ll);
            TextView    tvName  =   (TextView)view.findViewById(R.id.jyqxdq_qyxx_list_ll_name);
            TextView    tvValue  =   (TextView)view.findViewById(R.id.jyqxdq_qyxx_list_ll_value);
            String[] strDatas   =   datas.get(position).get(i).split("##");
            if(strDatas.length>1) {
                tvValue.setText(strDatas[1]);
            }
            tvName.setText(strDatas[0]);

            if(datas.get(position).get(datas.get(position).size()-1).length()==18&&i==0){
                tvValue.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,4));
                TextView textView = new TextView(mContext);
                textView.setText("详情");
                textView.setTextColor(Color.rgb(33, 150, 243));
                textView.setTextSize(15);
                textView.setPadding(10, 10, 10, 10);
                textView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,1));
                ll.addView(textView);
            }
            linearLayout.addView(view);
        }
        return linearLayout;
    }
}
