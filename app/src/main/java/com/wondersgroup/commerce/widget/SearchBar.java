package com.wondersgroup.commerce.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.utils.DWZH;

/**
 * Created by bjy on 2017/11/27.
 */

public class SearchBar extends RelativeLayout {
    private Context mContext;

    private ImageView imgSearch;
    private EditText editText;
    private TextView tvSearch;

    private String hint;
    private static final int TEXT_PADDING = DWZH.dp(5);

    private SearchListener searchListener;

    public SearchBar(Context context) {
        this(context, null);
    }

    public SearchBar(Context context, AttributeSet attrs) {
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
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.addRule(LEFT_OF, R.id.tv_search);
        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setLayoutParams(lp);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        Drawable drawableBg = getResources().getDrawable(R.drawable.rect_corner_white);
        linearLayout.setBackgroundDrawable(drawableBg);
        addView(linearLayout);

        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(DWZH.dp(15), DWZH.dp(15));
        lp1.setMargins(DWZH.dp(5), 0, 0 ,0);
        lp1.gravity = Gravity.CENTER_VERTICAL;
        imgSearch = new ImageView(mContext);
        imgSearch.setLayoutParams(lp1);
        imgSearch.setImageResource(R.mipmap.search_linkman);
        imgSearch.setClickable(false);
        linearLayout.addView(imgSearch);

        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        editText = new EditText(mContext);
        editText.setLayoutParams(lp2);
        editText.setPadding(TEXT_PADDING, TEXT_PADDING, TEXT_PADDING, TEXT_PADDING);
        editText.setBackgroundDrawable(null);
        editText.setTextSize(12);
        editText.setHint(hint);
        editText.setHintTextColor(getResources().getColor(R.color.gray));
        editText.setFocusable(false);
        editText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        editText.setSingleLine(true);
        editText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.hasFocusable()){
                    editText.setFocusableInTouchMode(true);
                }
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        editText.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    //隐藏键盘
                    ((InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    if (searchListener != null){
                        searchListener.search(editText.getText().toString().trim());
                    }

                    return true;
                }
                return false;
            }
        });
        linearLayout.addView(editText);

        RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(DWZH.dp(50), DWZH.dp(30));
        lp3.addRule(CENTER_VERTICAL);
        lp3.addRule(ALIGN_PARENT_RIGHT);
        lp3.setMargins(DWZH.dp(10), 0, 0, 0);
        tvSearch = new TextView(mContext);
        tvSearch.setLayoutParams(lp3);
        tvSearch.setId(R.id.tv_search);
        tvSearch.setGravity(Gravity.CENTER);
        tvSearch.setBackgroundDrawable(getResources().getDrawable(R.drawable.rect_corner_blue));
        tvSearch.setText("搜索");
        tvSearch.setTextSize(14);
        tvSearch.setTextColor(getResources().getColor(R.color.white));
        tvSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //隐藏键盘
                ((InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                if (searchListener != null){
                    searchListener.search(editText.getText().toString().trim());
                }
            }
        });
        addView(tvSearch);
    }

    public interface SearchListener{
        void search(String content);
    }

    public void setSearchListener(SearchListener searchListener) {
        this.searchListener = searchListener;
    }

    public String getInput(){
        return editText.getText().toString().trim();
    }
}
