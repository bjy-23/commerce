package com.wondersgroup.commerce.ynwq.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.teamwork.wywork.AnimatedExpandableListView;
import com.wondersgroup.commerce.ynwq.bean.FiveBean;
import com.wondersgroup.commerce.ynwq.bean.ThreeBean;

import java.util.ArrayList;

/**
 * Created by 1229 on 2015/12/29.
 */
public class CommonAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {
    private Context context;
    private String[] itemsName;
    private ArrayList<Object> data;
    private int layout;
    private int countNum;

    public CommonAdapter(Context context, String[] itemsName,
                         ArrayList<Object> data, int layout) {
        this.context = context;
        this.itemsName = itemsName;
        this.data = data;
        this.layout = layout;
        countNum = itemsName.length;
    }

    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub

        if (data.size() >= 5) {
            return 5;
        } else {
            return data.size();
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        switch (countNum) {
            case 3:
                if (convertView == null) {
                    convertView = View.inflate(context, layout, null);
                    HolderThree ht = new HolderThree();


                    convertView.setTag(ht);

                    ht.oneLeft.setText(itemsName[0]);
                    ht.twoLeft.setText(itemsName[1]);
                    ht.threeLeft.setText(itemsName[2]);

                    ht.oneRight.setText(((ThreeBean) data.get(groupPosition))
                            .getOne());
                    ht.twoRight.setText(((ThreeBean) data.get(groupPosition))
                            .getTwo());
                    ht.threeRight.setText(((ThreeBean) data.get(groupPosition))
                            .getThree());
                } else {
                    HolderThree ht = (HolderThree) convertView.getTag();
                    ht.oneLeft.setText(itemsName[0]);
                    ht.twoLeft.setText(itemsName[1]);
                    ht.threeLeft.setText(itemsName[2]);

                    ht.oneRight.setText(((ThreeBean) data.get(groupPosition))
                            .getOne());
                    ht.twoRight.setText(((ThreeBean) data.get(groupPosition))
                            .getTwo());
                    ht.threeRight.setText(((ThreeBean) data.get(groupPosition))
                            .getThree());
                }

                break;

            case 5:
                if (convertView == null) {
                    convertView = View.inflate(context, layout, null);
                    HolderFive ht = new HolderFive();

                    convertView.setTag(ht);

                    ht.oneLeft.setText(itemsName[0]);
                    ht.twoLeft.setText(itemsName[1]);
                    ht.threeLeft.setText(itemsName[2]);
                    ht.fourLeft.setText(itemsName[3]);
                    ht.fiveLeft.setText(itemsName[4]);

                    ht.oneRight.setText(((FiveBean) data.get(groupPosition))
                            .getOne());
                    ht.twoRight.setText(((FiveBean) data.get(groupPosition))
                            .getTwo());
                    ht.threeRight.setText(((FiveBean) data.get(groupPosition))
                            .getThree());
                    ht.fourRight.setText(((FiveBean) data.get(groupPosition))
                            .getFour());
                    ht.fiveRight.setText(((FiveBean) data.get(groupPosition))
                            .getFive());
                } else {
                    HolderFive ht = (HolderFive) convertView.getTag();
                    ht.oneLeft.setText(itemsName[0]);
                    ht.twoLeft.setText(itemsName[1]);
                    ht.threeLeft.setText(itemsName[2]);
                    ht.fourLeft.setText(itemsName[3]);
                    ht.fiveLeft.setText(itemsName[4]);

                    ht.oneRight.setText(((FiveBean) data.get(groupPosition))
                            .getOne());
                    ht.twoRight.setText(((FiveBean) data.get(groupPosition))
                            .getTwo());
                    ht.threeRight.setText(((FiveBean) data.get(groupPosition))
                            .getThree());
                    ht.fourRight.setText(((FiveBean) data.get(groupPosition))
                            .getFour());
                    ht.fiveRight.setText(((FiveBean) data.get(groupPosition))
                            .getFive());
                }
                break;

            default:
                break;
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
        switch (countNum) {
            case 3:
                ThreeBean tb = (ThreeBean) data.get(groupPosition);
                convertView = tb.getChild();

                break;

            case 5:
                FiveBean fb = (FiveBean) data.get(groupPosition);
                convertView = fb.getChild();

                break;

            default:
                break;
        }
        return convertView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {

        switch (countNum) {
            case 3:
                ThreeBean three = (ThreeBean) data.get(groupPosition);
                if (three.getChild() != null) {
                    return 1;
                } else {
                    return 0;
                }

            case 5:
                FiveBean five = (FiveBean) data.get(groupPosition);
                if (five.getChild() != null) {
                    return 1;
                } else {
                    return 0;
                }

            default:
                return 0;
        }

    }

    class HolderThree {
        TextView oneLeft;
        TextView oneRight;
        TextView twoLeft;
        TextView twoRight;
        TextView threeLeft;
        TextView threeRight;
    }

    class HolderFive {
        TextView oneLeft;
        TextView oneRight;
        TextView twoLeft;
        TextView twoRight;
        TextView threeLeft;
        TextView threeRight;
        TextView fourLeft;
        TextView fourRight;
        TextView fiveLeft;
        TextView fiveRight;
    }

}

