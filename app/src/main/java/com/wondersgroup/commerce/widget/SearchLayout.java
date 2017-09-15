package com.wondersgroup.commerce.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.interface_.TextChanger;
import com.wondersgroup.commerce.utils.DWZH;
import com.wondersgroup.commerce.utils.KeyBoardHelper;

/**
 * Created by bjy on 2017/5/19.
 */

public class SearchLayout extends RelativeLayout implements View.OnClickListener {
    private Context mContext;
    private ImageView imgSearch;
    private EditText editText;
    private TextView tvHint;

    private int width1, width2, height2;
    private String hint;
    private int textPadding = DWZH.dp(5);

    private TranslateAnimation translateAnimation;

    private SearchListener listener;

    public SearchLayout(Context context) {
        this(context, null);
    }

    public SearchLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        getParams(attrs);
        init();
    }

    public void getParams(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.searchLayout);
        hint = typedArray.getString(R.styleable.searchLayout_hint) == null ? "搜索" : typedArray.getString(R.styleable.searchLayout_hint);

        typedArray.recycle();
    }

    private void init() {
        //设置点击事件
//        setOnClickListener(this);
        //设置背景
        Drawable drawableBg = mContext.getResources().getDrawable(R.drawable.rect_corner_white);
        setBackgroundDrawable(drawableBg);

        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(DWZH.dp(15), DWZH.dp(15));
        lp1.addRule(RelativeLayout.CENTER_VERTICAL);
        lp1.setMargins(DWZH.dp(10), 0, DWZH.dp(10), 0);
        imgSearch = new ImageView(mContext);
        imgSearch.setId(R.id.img_search);
        imgSearch.setLayoutParams(lp1);
        imgSearch.setImageResource(R.mipmap.search_linkman);
        imgSearch.setClickable(false);
        addView(imgSearch);

        RelativeLayout.LayoutParams lp2 = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.addRule(RelativeLayout.RIGHT_OF, imgSearch.getId());
        lp2.addRule(RelativeLayout.CENTER_VERTICAL);
        editText = new EditText(mContext);
        editText.setLayoutParams(lp2);
        editText.setPadding(textPadding, textPadding, textPadding, textPadding);
        editText.setBackgroundDrawable(null);
//        editText.setBackgroundDrawable(getResources().getDrawable(R.drawable.rect_corner_blue));
        editText.setTextSize(12);
        editText.setHintTextColor(getResources().getColor(R.color.gray));
        editText.setFocusable(false);
//        editText.setEnabled(false);
        editText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        editText.setSingleLine(true);
        addView(editText);

        RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp3.addRule(RelativeLayout.CENTER_IN_PARENT);
        tvHint = new TextView(mContext);
        tvHint.setLayoutParams(lp3);
        tvHint.setTextColor(getResources().getColor(R.color.gray));
        tvHint.setText(hint);
        tvHint.setTextSize(12);
//        tvHint.setBackgroundDrawable(getResources().getDrawable(R.drawable.rect_corner_orange));
        addView(tvHint);

        editText.addTextChangedListener(new TextChanger() {
            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(String.valueOf(s).trim()))
                    tvHint.setVisibility(GONE);
                else
                    tvHint.setVisibility(VISIBLE);
            }
        });

        editText.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    //隐藏键盘
                    ((InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    if (listener != null) {
                        listener.search(editText.getText().toString().trim());
                    }

                    return true;
                }
                return false;
            }
        });


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        width1 = tvHint.getLeft();
        width2 = editText.getLeft();

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == event.ACTION_UP) {
            if (!editText.hasFocusable()) {
                setAnime();
                tvHint.startAnimation(translateAnimation);

//                ObjectAnimator.ofFloat(tvHint, "translationX", 0, (width2 - width1 + textPadding)).setDuration(300).start();

                editText.setFocusable(true);
                editText.setFocusableInTouchMode(true);
                editText.requestFocus();
                editText.requestFocusFromTouch();
            }
//            editText.setEnabled(true);
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);

        }

        return true;
    }

    @Override
    public void onClick(View v) {

    }

    private void setAnime(){
        if (translateAnimation == null) {
            translateAnimation = new TranslateAnimation(0, (width2 - width1 + textPadding), 0, 0);
            translateAnimation.setDuration(300);
            translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    tvHint.setVisibility(GONE);
                    editText.setHint(hint);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }

    public String getInput() {
        return editText.getText().toString().trim();
    }

    public interface SearchListener {
        void search(String content);
    }

    public void setSearchListenr(SearchListener listenr) {
        this.listener = listenr;
    }
}
