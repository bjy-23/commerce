package com.wondersgroup.commerce.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.utils.DWZH;

/**
 * Created by bjy on 2017/5/19.
 */

public class SearchLayout extends RelativeLayout implements View.OnClickListener{
    private Context mContext;
    private ImageView imgSearch;
    private EditText editText;
    private TextView tvHint;

    private int width1,width2,height2;
    private String hint;

    private TranslateAnimation translateAnimation;

    public SearchLayout(Context context) {
        this(context,null);
    }

    public SearchLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        getParams(attrs);
        init();
    }

    public void getParams(AttributeSet attrs){
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.searchLayout);
        hint = typedArray.getString(R.styleable.searchLayout_hint) == null ? "搜索" : typedArray.getString(R.styleable.searchLayout_hint);

        typedArray.recycle();
    }

    private void init(){
        //设置点击事件
        setOnClickListener(this);
        //设置背景
        Drawable drawableBg = mContext.getResources().getDrawable(R.drawable.rect_corner_white);
        setBackgroundDrawable(drawableBg);

        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(DWZH.dp(15),DWZH.dp(15));
        lp1.addRule(RelativeLayout.CENTER_VERTICAL);
        lp1.setMargins(DWZH.dp(10),0,DWZH.dp(10),0);
        imgSearch = new ImageView(mContext);
        imgSearch.setId(R.id.img_search);
        imgSearch.setLayoutParams(lp1);
        imgSearch.setImageResource(R.mipmap.search_linkman);
        addView(imgSearch);

        RelativeLayout.LayoutParams lp2 = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.addRule(RelativeLayout.RIGHT_OF, imgSearch.getId());
        lp2.addRule(RelativeLayout.CENTER_VERTICAL);
        editText = new EditText(mContext);
        editText.setLayoutParams(lp2);
        editText.setPadding(DWZH.dp(5),DWZH.dp(5),DWZH.dp(5),DWZH.dp(5));
        editText.setBackgroundDrawable(null);
        editText.setTextSize(14);
        editText.setFocusable(false);
        addView(editText);

        RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp3.addRule(RelativeLayout.CENTER_IN_PARENT);
        tvHint = new TextView(mContext);
        tvHint.setLayoutParams(lp3);
        tvHint.setTextColor(getResources().getColor(R.color.gray));
        tvHint.setText(hint);
        tvHint.setTextSize(12);
        addView(tvHint);
    }

    @Override
    public void onClick(View v) {
        if (width1 == 0 )
            width1 = getWidth();
        if (width2 == 0){
            width2 = tvHint.getWidth();
            height2 = tvHint.getHeight();
        }
        if (translateAnimation == null){
            translateAnimation = new TranslateAnimation((width1 - width2) /2, height2 / 2, 0,height2 / 2);
            translateAnimation.setDuration(500);
            translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    tvHint.setVisibility(GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
        tvHint.setAnimation(translateAnimation);
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        editText.requestFocusFromTouch();
        editText.setHint(hint);
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText,0);
    }

    public String getInput(){
        return editText.getText().toString().trim();
    }
}
