package com.wondersgroup.commerce.teamwork.memorandum;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 1229 on 2016/3/28.
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpaceItemDecoration(int space){
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect rect,View view,RecyclerView parent,RecyclerView.State state){
        if(parent.getChildPosition(view)!=0){
            rect.top = space;
        }
    }

}
