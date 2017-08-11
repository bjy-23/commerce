package com.wondersgroup.commerce.adapter;

/**
 * Created by Lee on 2016/3/15.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.model.GgDetails;

import java.util.ArrayList;

//附件下载
public class FJXZAdapter extends BaseAdapter {

    private ArrayList<GgDetails.DocAttachVoList> docAttachVoList;
    private Context context;

    public FJXZAdapter(Context context, ArrayList docAttachVoList){
        this.context = context;
        this.docAttachVoList = docAttachVoList;
    }

    @Override
    public int getCount() {
        docAttachVoList = docAttachVoList;
        return (docAttachVoList ==null)?0:docAttachVoList.size();
    }

    @Override
    public Object getItem(int position) {
        return docAttachVoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public class ViewHolder{
        TextView attname;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.view_info_select_bar,null);
            viewHolder = new ViewHolder();
            viewHolder.attname = (TextView) convertView.findViewById(R.id.info_bar_text);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.attname.setText(docAttachVoList.get(position).getAttachName());

        final ViewHolder finalViewHolder = viewHolder;
        return convertView;
    }
}