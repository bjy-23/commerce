package com.wondersgroup.commerce.widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.utils.DWZH;
import com.wondersgroup.commerce.utils.DateUtil;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import lecho.lib.hellocharts.model.Line;

/**
 * Created by 薛定猫 on 2016/3/10.
 */
public class TableRow extends LinearLayout implements View.OnClickListener {
    private RelativeLayout contentLayout;
    private TextView title;
    private TextView colon;//冒号（：）
    private ClearEditText clearEditText;
    private EditText content;
    private EditText content2;
    private TextView tvContent;
    private ImageView mapImg;
    private final Builder mBuilder;
    private OnMultiClick onMultiClick;

    protected enum Type {
        TEXT,
        INPUT,
        SELECT,
        MSG,
        MSG_WITH_TITLE,
        MSG_MULTIPLE_LINE,
        TITLE,
        MULTIINPUT,
        MULTISELECT,
        MULTI_SELECT_2,
        MAP,
        SELECT_WITH_ARROW,
        INPUT_WITH_ARROW,
        TIME
    }

    protected TableRow(Builder mBuilder) {
        super(mBuilder.getContext());
        this.mBuilder = mBuilder;
        build();
    }

    private void build() {
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(params);
        this.setOrientation(VERTICAL);
        contentLayout = new RelativeLayout(getContext());
        LinearLayout.LayoutParams rootParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        contentLayout.setLayoutParams(rootParams);
        this.addView(contentLayout);
        switch (mBuilder.mType) {
            case TEXT:
                this.setBackgroundColor(ContextCompat.getColor(mBuilder.mContext, R.color.white));
                this.addTitle();
                this.addContent();
                this.content.setInputType(InputType.TYPE_NULL);
                this.content.setTextIsSelectable(true);
                this.content.setSingleLine(false);
                this.content.setMaxLines(3);
                this.content.setEllipsize(TextUtils.TruncateAt.END);
                break;
            case INPUT:
                this.setBackgroundColor(ContextCompat.getColor(mBuilder.mContext, R.color.white));
                this.addTitle();
                this.addClearEdit();
                this.clearEditText.setHint(mBuilder.inputHint);
                this.clearEditText.setInputType(mBuilder.inputType);
                this.clearEditText.setSingleLine(false);
//                this.clearEditText.setMaxLines(1);
                this.clearEditText.setFocusable(false);
                this.clearEditText.setOnClickListener(this);
                break;
            case SELECT:
                this.setBackgroundColor(ContextCompat.getColor(mBuilder.mContext, R.color.white));
                this.addTitle();
                this.addContent();
                this.addIndex();
                this.content.setInputType(InputType.TYPE_NULL);
                this.content.setHint(mBuilder.selectHint);
                this.content.setSingleLine(false);
                this.content.setMaxLines(2);
                this.content.setEllipsize(TextUtils.TruncateAt.END);
                this.content.setOnClickListener(this);
                break;
            case MSG:
                this.setBackgroundColor(ContextCompat.getColor(mBuilder.mContext, R.color.white));
                this.addMsg();
                break;
            case MSG_WITH_TITLE:
                this.setBackgroundColor(ContextCompat.getColor(mBuilder.mContext, R.color.white));
                this.addTitle();
                this.addMsgWithTitle();
                break;
            case MSG_MULTIPLE_LINE:
                this.setBackgroundColor(ContextCompat.getColor(mBuilder.mContext, R.color.white));
                this.addTitle();
                this.addMsgWithMutiplyLine();
                break;
            case TITLE:
                this.setBackgroundColor(ContextCompat.getColor(mBuilder.mContext, R.color.whiter_gray));
                this.asTitle();
                break;
            case MULTIINPUT:
                this.setBackgroundColor(ContextCompat.getColor(mBuilder.mContext, R.color.white));
                this.addTitle();
                this.addMulti();
                this.content.setInputType(mBuilder.inputType);
                this.content2.setInputType(mBuilder.inputType);
                this.content.setSingleLine(false);
                this.content2.setSingleLine(false);
                this.content.setMaxLines(1);
                this.content2.setMaxLines(1);
                this.content.setEllipsize(TextUtils.TruncateAt.END);
                this.content2.setEllipsize(TextUtils.TruncateAt.END);
                break;
            case MULTISELECT:
                this.setBackgroundColor(ContextCompat.getColor(mBuilder.mContext, R.color.white));
                this.addTitle();
                this.addMulti();
                this.content.setInputType(InputType.TYPE_NULL);
                this.content2.setInputType(InputType.TYPE_NULL);
                this.content.setSingleLine(false);
                this.content2.setSingleLine(false);
                this.content.setMaxLines(2);
                this.content2.setMaxLines(2);
                this.content.setEllipsize(TextUtils.TruncateAt.END);
                this.content2.setEllipsize(TextUtils.TruncateAt.END);
                this.content.setOnClickListener(this);
                this.content2.setOnClickListener(this);
                break;
            case MULTI_SELECT_2:
                this.setBackgroundColor(ContextCompat.getColor(mBuilder.mContext, R.color.white));
                this.addTitle();
                this.addMulti2();
                break;
            case MAP:
                this.setBackgroundColor(ContextCompat.getColor(mBuilder.mContext, R.color.white));
                this.addTitle();
                this.addContentWithImg();
                this.content.setInputType(InputType.TYPE_NULL);
                this.content.setTextIsSelectable(true);
                this.content.setSingleLine(false);
                this.content.setMaxLines(2);
                this.content.setEllipsize(TextUtils.TruncateAt.END);
                this.mapImg.setOnClickListener(this);
                break;
            case SELECT_WITH_ARROW:
                this.setBackgroundColor(ContextCompat.getColor(mBuilder.mContext, R.color.white));
                this.addTitle();
                this.addContentWithArrowImg();
//                this.content.setInputType(InputType.TYPE_NULL);
                this.tvContent.setTextIsSelectable(true);
                this.tvContent.setSingleLine(false);
                this.tvContent.setMaxLines(2);
                this.tvContent.setEllipsize(TextUtils.TruncateAt.END);
                this.mapImg.setOnClickListener(this);
                break;
            case INPUT_WITH_ARROW:
                this.setBackgroundColor(ContextCompat.getColor(mBuilder.mContext, R.color.white));
                this.addTitle();
                this.addEditTextWithArrowImg();
                this.content.setInputType(InputType.TYPE_NULL);
                this.content.setHint(mBuilder.inputHint);
                this.content.setTextIsSelectable(true);
                this.content.setSingleLine(false);
                this.content.setMaxLines(2);
                this.content.setEllipsize(TextUtils.TruncateAt.END);
                this.mapImg.setOnClickListener(this);
                break;
            case TIME:
                this.setBackgroundColor(ContextCompat.getColor(mBuilder.mContext, R.color.white));
                this.addTitle();
                this.addTimes();
                break;
            default:
        }
        if (mBuilder.showBtmLine) this.addBtmLine();
        if (content != null && mBuilder.mType != Type.MULTIINPUT && mBuilder.mType != Type.MULTISELECT) {
            final View parent = (View) content.getParent();
            parent.post(new Runnable() {
                @Override
                public void run() {
                    final Rect r = new Rect();
                    content.getHitRect(r);
                    r.top -= mBuilder.marginV;
                    r.bottom += mBuilder.marginV;
                    parent.setTouchDelegate(new TouchDelegate(r, content));
                }
            });
        }
    }

    private void addTitle() {
        Context context = mBuilder.mContext;
        title = new TextView(context);
        RelativeLayout.LayoutParams titleParams = new RelativeLayout.LayoutParams(DWZH.dp(120), ViewGroup.LayoutParams.WRAP_CONTENT);
        titleParams.addRule(RelativeLayout.CENTER_VERTICAL);
        titleParams.setMargins((int) mBuilder.marginH, (int) mBuilder.marginV + 30, 0, (int) mBuilder.marginV);
        title.setLayoutParams(titleParams);
        title.setTextSize(mBuilder.textSize);
        title.setTextColor(mBuilder.titleColor);
        setTitle(mBuilder.titleString);
        contentLayout.addView(title);
    }

    private void addColon(){
        Context context = mBuilder.mContext;
        colon = new TextView(context);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        lp.setMargins(DWZH.dp(140),0,0,0);
        colon.setLayoutParams(lp);
        colon.setText(":");
        contentLayout.addView(colon);
    }

    private void addContent() {
        Context context = mBuilder.mContext;
        content = new EditText(context);
        RelativeLayout.LayoutParams contentParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        contentParams.addRule(RelativeLayout.CENTER_VERTICAL);
        contentParams.setMargins((int) mBuilder.titleW, (int) mBuilder.marginV, (int) mBuilder.marginH, (int) mBuilder.marginV);
        content.setLayoutParams(contentParams);
        content.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
        content.setPadding(0, 0, 0, 0);
        content.setTextSize(mBuilder.textSize);
        content.setTextColor(mBuilder.textColor);
        content.setHintTextColor(mBuilder.hintColor);
        this.content.setText(mBuilder.contentString);
        contentLayout.addView(content);
    }

    private void addIndex(){
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(DWZH.dp(10), DWZH.dp(10));
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        lp.addRule(RelativeLayout.ALIGN_PARENT_END);
        lp.setMargins(0,0,DWZH.dp(15),0);
        ImageView imageView = new ImageView(mBuilder.mContext);
        imageView.setLayoutParams(lp);
        imageView.setImageResource(R.drawable.right_arrow);

        contentLayout.addView(imageView);
    }

    private void addClearEdit() {
        Context context = mBuilder.mContext;
        clearEditText = new ClearEditText(context);
        RelativeLayout.LayoutParams contentParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        contentParams.addRule(RelativeLayout.CENTER_VERTICAL);
        contentParams.setMargins((int) mBuilder.titleW, (int) mBuilder.marginV, (int) mBuilder.marginH, (int) mBuilder.marginV);
        clearEditText.setLayoutParams(contentParams);
        clearEditText.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
        clearEditText.setPadding(0, 0, 0, 0);
        clearEditText.setTextSize(mBuilder.textSize);
        clearEditText.setTextColor(mBuilder.textColor);
        clearEditText.setHintTextColor(mBuilder.hintColor);
        this.clearEditText.setText(mBuilder.contentString);
        contentLayout.addView(clearEditText);
    }

    private void addMsg() {
        Context context = mBuilder.mContext;
        content = new EditText(context);
        RelativeLayout.LayoutParams contentParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        contentParams.addRule(RelativeLayout.CENTER_VERTICAL);
        contentParams.setMargins((int) mBuilder.marginH, (int) mBuilder.marginV, 0, (int) mBuilder.marginV);
        content.setLayoutParams(contentParams);
        content.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
        content.setPadding(0, 0, 0, 0);
        content.setTextSize(mBuilder.textSize);
        content.setTextColor(mBuilder.textColor);
        content.setHintTextColor(mBuilder.hintColor);
        content.setText(mBuilder.msgString);
        content.setInputType(InputType.TYPE_NULL);
        content.setEllipsize(TextUtils.TruncateAt.END);
        contentLayout.addView(content);
    }

    private void addMsgWithTitle() {
        Context context = mBuilder.mContext;
        content = new EditText(context);
        RelativeLayout.LayoutParams contentParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        contentParams.addRule(RelativeLayout.CENTER_VERTICAL);
        contentParams.setMargins((int) mBuilder.titleW, (int) mBuilder.marginV, (int) mBuilder.marginH, (int) mBuilder.marginV);
        content.setLayoutParams(contentParams);
        content.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
        content.setPadding(0, 0, 0, 0);
        content.setTextSize(mBuilder.textSize);
        content.setTextColor(mBuilder.textColor);
        content.setHintTextColor(mBuilder.hintColor);
        content.setText(mBuilder.msgString);
        content.setSingleLine(false);
        content.setMaxLines(10);
        content.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);
        content.setEnabled(false);
        contentLayout.addView(content);
    }

    private void addMsgWithMutiplyLine() {
        Context context = mBuilder.mContext;
        tvContent = new TextView(context);
        RelativeLayout.LayoutParams contentParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        contentParams.addRule(RelativeLayout.CENTER_VERTICAL);
        contentParams.setMargins((int) mBuilder.titleW, (int) mBuilder.marginV, (int) mBuilder.marginH, (int) mBuilder.marginV);
        tvContent.setLayoutParams(contentParams);
        tvContent.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
        tvContent.setPadding(0, 0, 0, 0);
        tvContent.setTextSize(mBuilder.textSize);
        tvContent.setTextColor(mBuilder.textColor);
        tvContent.setHintTextColor(mBuilder.hintColor);
        tvContent.setText(mBuilder.msgString);
        tvContent.setSingleLine(false);
        tvContent.setMaxLines(4);
        tvContent.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
        contentLayout.addView(tvContent);
    }

    private void asTitle() {
        Context context = mBuilder.mContext;
        content = new EditText(context);
        RelativeLayout.LayoutParams contentParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        contentParams.addRule(RelativeLayout.CENTER_VERTICAL);
        contentParams.setMargins((int) mBuilder.marginH, (int) DWZH.dp2pt(context, 10), (int) mBuilder.marginH, (int) DWZH.dp2pt(context, 10));
        content.setLayoutParams(contentParams);
        content.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
        content.setPadding(0, 0, 0, 0);
        content.setTextSize(12);
        content.setTextColor(ContextCompat.getColor(mBuilder.mContext, R.color.blue));
        content.setText(mBuilder.msgString);
        content.setInputType(InputType.TYPE_NULL);
        content.setEllipsize(TextUtils.TruncateAt.END);
        contentLayout.addView(content);
    }

    private void addBtmLine() {
        Context context = mBuilder.mContext;
        View view = new View(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1);
        params.setMargins(DWZH.dp(5), 0, DWZH.dp(5), 0);
        view.setLayoutParams(params);
        view.setBackgroundColor(ContextCompat.getColor(context, R.color.linecolor));
        this.addView(view);
    }

    @TargetApi(17)
    private void addMulti() {
        Context context = mBuilder.mContext;
        RelativeLayout root = new RelativeLayout(context);
        RelativeLayout.LayoutParams rootParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rootParams.setMargins((int) mBuilder.titleW, 0, (int) mBuilder.marginH, 0);
        root.setLayoutParams(rootParams);

        TextView centerView = new TextView(context);
        centerView.setId(View.generateViewId());
        RelativeLayout.LayoutParams centerParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        centerParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        centerView.setLayoutParams(centerParams);
        centerView.setTextSize(mBuilder.textSize);
        centerView.setTextColor(mBuilder.textColor);
        centerView.setText(mBuilder.seperator);
        root.addView(centerView);

        content = new EditText(context);
        RelativeLayout.LayoutParams contentParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        contentParams.addRule(RelativeLayout.CENTER_VERTICAL);
        contentParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        contentParams.addRule(RelativeLayout.LEFT_OF, centerView.getId());
        //contentParams.setMargins(0, (int) mBuilder.marginV, 0, (int) mBuilder.marginV);
        content.setLayoutParams(contentParams);
        content.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
        content.setPadding(0, (int) mBuilder.marginV, 0, (int) mBuilder.marginV);
        content.setTextSize(mBuilder.textSize);
        content.setTextColor(mBuilder.textColor);
        content.setHintTextColor(mBuilder.hintColor);
        this.content.setHint(mBuilder.multiHintOne);
        root.addView(content);

        content2 = new EditText(context);
        RelativeLayout.LayoutParams con2Params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        con2Params.addRule(RelativeLayout.CENTER_VERTICAL);
        con2Params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        con2Params.addRule(RelativeLayout.RIGHT_OF, centerView.getId());
        //con2Params.setMargins(0, (int) mBuilder.marginV, 0, (int) mBuilder.marginV);
        content2.setLayoutParams(con2Params);
        content2.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
        content2.setPadding(0, (int) mBuilder.marginV, 0, (int) mBuilder.marginV);
        content2.setTextSize(mBuilder.textSize);
        content2.setTextColor(mBuilder.textColor);
        content2.setHintTextColor(mBuilder.hintColor);
        this.content2.setHint(mBuilder.multiHintTwo);
        root.addView(content2);

        contentLayout.addView(root);
    }

    private void addMulti2() {
        LinearLayout root = new LinearLayout(mBuilder.mContext);
        root.setOrientation(LinearLayout.VERTICAL);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins((int) mBuilder.titleW, 0, 0, 0);
        root.setLayoutParams(lp);

        int size = mBuilder.multiHints.size();
        //textView的参数
        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        lp1.setMargins(0,0,DWZH.dp(25),0);
        //分割线的参数
        LinearLayout.LayoutParams lp2 = new LayoutParams(LayoutParams.MATCH_PARENT,1);
        lp2.setMargins(0, 0, DWZH.dp(10), 0);
        RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        //索引图标参数
        RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams(DWZH.dp(10),DWZH.dp(10));
        lp4.addRule(RelativeLayout.CENTER_VERTICAL);
        lp4.addRule(RelativeLayout.ALIGN_PARENT_END);
        lp4.setMargins(0, 0, DWZH.dp(15), 0);
        for (int i=0; i<size; i++){
            RelativeLayout layout = new RelativeLayout(mBuilder.mContext);
            layout.setLayoutParams(lp3);

            final TextView textView = new TextView(mBuilder.mContext);
            textView.setTag(i+"");
            textView.setLayoutParams(lp1);
            textView.setText(mBuilder.multiHints.get(i));
            textView.setTextColor(mBuilder.textColor);
            textView.setTextSize(mBuilder.textSize);
            textView.setPadding(0, (int) mBuilder.marginV, 0, (int) mBuilder.marginV);
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onMultiClick != null){
                        onMultiClick.onClick(Integer.parseInt(textView.getTag().toString()));
                    }
                }
            });
            layout.addView(textView);

            //添加索引图标
            ImageView index = new ImageView(mBuilder.mContext);
            index.setLayoutParams(lp4);
            index.setImageResource(R.drawable.right_arrow);
            layout.addView(index);

            root.addView(layout);

            //添加分割线
            if (i <size -1){
                View line = new View(mBuilder.mContext);
                line.setLayoutParams(lp2);
                line.setBackgroundResource(R.color.linecolor);
                root.addView(line);
            }
        }

        contentLayout.addView(root);
    }

    private void addTimes(){
        LinearLayout root = new LinearLayout(mBuilder.mContext);
        root.setOrientation(LinearLayout.VERTICAL);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins((int) mBuilder.titleW, 0, 0, 0);
        root.setLayoutParams(lp);

        int size = mBuilder.times.size();
        LinearLayout.LayoutParams lp1 = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //分割线的参数
        LinearLayout.LayoutParams lp2 = new LayoutParams(LayoutParams.MATCH_PARENT,1);
        lp2.setMargins(0, 0, DWZH.dp(10), 0);
        for (int i=0; i<size; i++){
            final TextView textView = new TextView(mBuilder.mContext);
            textView.setLayoutParams(lp1);
            textView.setTextSize(12);
            textView.setTextColor(mBuilder.hintColor);
            textView.setTag(mBuilder.times.get(i));
            textView.setHint(mBuilder.times.get(i));
            textView.setHintTextColor(mBuilder.hintColor);
//            textView.setText(mBuilder.times.get(i));
            textView.setPadding(0, (int) mBuilder.marginV, 0, (int) mBuilder.marginV);
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    DateUtil.createDatePicker((Activity) mBuilder.mContext, new DateUtil.DateListener() {
                        @Override
                        public void back(Date date) {
                            String time = DateUtil.getYMD(date);
                            textView.setText(time);
                            mBuilder.timeListener.timeBack(String.valueOf(textView.getTag()), time);
                        }
                    });
                }
            });
            root.addView(textView);

            //添加分割线
            if (i <size -1){
                View line = new View(mBuilder.mContext);
                line.setLayoutParams(lp2);
                line.setBackgroundResource(R.color.linecolor);
                root.addView(line);
            }
        }

        contentLayout.addView(root);
    }

    public void clearTime() {
        int size = mBuilder.times.size();
        for (int i = 0; i < size; i++) {
            TextView tv = (TextView) contentLayout.findViewWithTag(mBuilder.times.get(i));
            tv.setText("");
        }
    }

    @TargetApi(17)
    private void addContentWithImg() {
        Context context = mBuilder.mContext;
        RelativeLayout root = new RelativeLayout(context);
        RelativeLayout.LayoutParams rootParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rootParams.setMargins((int) mBuilder.titleW, 0, (int) mBuilder.marginH, 0);
        root.setLayoutParams(rootParams);

        mapImg = new ImageView(context);
        mapImg.setId(View.generateViewId());
        RelativeLayout.LayoutParams imgParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imgParams.addRule(RelativeLayout.CENTER_VERTICAL);
        imgParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        mapImg.setLayoutParams(imgParams);
        mapImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.map));
        mapImg.setPadding(0, (int) mBuilder.marginV, 0, (int) mBuilder.marginV);
        root.addView(mapImg);

        content = new EditText(context);
        content.setId(View.generateViewId());
        RelativeLayout.LayoutParams contentParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        contentParams.addRule(RelativeLayout.CENTER_VERTICAL);
        contentParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        contentParams.addRule(RelativeLayout.LEFT_OF, mapImg.getId());
        //contentParams.setMargins(0, (int) mBuilder.marginV, 0, (int) mBuilder.marginV);
        content.setLayoutParams(contentParams);
        content.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
        content.setPadding(0, (int) mBuilder.marginV, (int) DWZH.dp2pt(context, 12), (int) mBuilder.marginV);
        content.setTextSize(mBuilder.textSize);
        content.setTextColor(mBuilder.textColor);
        content.setHintTextColor(mBuilder.hintColor);
        this.content.setText(mBuilder.contentString);
        root.addView(content);

        contentLayout.addView(root);
    }

    @TargetApi(17)
    private void addContentWithArrowImg() {
        Context context = mBuilder.mContext;
        RelativeLayout root = new RelativeLayout(context);
        RelativeLayout.LayoutParams rootParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rootParams.setMargins((int) mBuilder.titleW, 0, (int) mBuilder.marginH, 0);
        root.setLayoutParams(rootParams);

        mapImg = new ImageView(context);
        mapImg.setId(View.generateViewId());
        RelativeLayout.LayoutParams imgParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imgParams.addRule(RelativeLayout.CENTER_VERTICAL);
        imgParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        mapImg.setLayoutParams(imgParams);
        mapImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.right_arrow));
        mapImg.setPadding(0, (int) mBuilder.marginV, 0, (int) mBuilder.marginV);
        root.addView(mapImg);

        tvContent = new TextView(context);
        tvContent.setId(View.generateViewId());
        RelativeLayout.LayoutParams contentParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        contentParams.addRule(RelativeLayout.CENTER_VERTICAL);
        contentParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        contentParams.addRule(RelativeLayout.LEFT_OF, mapImg.getId());
        //contentParams.setMargins(0, (int) mBuilder.marginV, 0, (int) mBuilder.marginV);
        tvContent.setLayoutParams(contentParams);
        tvContent.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
        tvContent.setPadding(0, (int) mBuilder.marginV, (int) DWZH.dp2pt(context, 12), (int) mBuilder.marginV);
        tvContent.setTextSize(mBuilder.textSize);
        tvContent.setTextColor(mBuilder.textColor);
        tvContent.setHintTextColor(mBuilder.hintColor);
        this.tvContent.setText(mBuilder.contentString);
        root.addView(tvContent);

        contentLayout.addView(root);
    }

    @TargetApi(17)
    private void addEditTextWithArrowImg() {
        Context context = mBuilder.mContext;
        RelativeLayout root = new RelativeLayout(context);
        RelativeLayout.LayoutParams rootParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rootParams.setMargins((int) mBuilder.titleW, 0, (int) mBuilder.marginH, 0);
        root.setLayoutParams(rootParams);

        mapImg = new ImageView(context);
        mapImg.setId(View.generateViewId());
        RelativeLayout.LayoutParams imgParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imgParams.addRule(RelativeLayout.CENTER_VERTICAL);
        imgParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        mapImg.setLayoutParams(imgParams);
        mapImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.right_arrow));
        mapImg.setPadding(0, (int) mBuilder.marginV, 0, (int) mBuilder.marginV);
        root.addView(mapImg);

        content = new EditText(context);
        content.setId(View.generateViewId());
        RelativeLayout.LayoutParams contentParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        contentParams.addRule(RelativeLayout.CENTER_VERTICAL);
        contentParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        contentParams.addRule(RelativeLayout.LEFT_OF, mapImg.getId());
        //contentParams.setMargins(0, (int) mBuilder.marginV, 0, (int) mBuilder.marginV);
        content.setLayoutParams(contentParams);
        content.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
        content.setPadding(0, (int) mBuilder.marginV, (int) DWZH.dp2pt(context, 12), (int) mBuilder.marginV);
        content.setTextSize(mBuilder.textSize);
        content.setTextColor(mBuilder.textColor);
        content.setHintTextColor(mBuilder.hintColor);
        this.content.setText(mBuilder.contentString);
        root.addView(content);

        contentLayout.addView(root);
    }

    public void setTitle(String title) {
        if (this.title != null) {
            if (mBuilder.isRequired) {
                SpannableString sb = new SpannableString("* " + title);
                sb.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.red)), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                this.title.setText(sb);
            } else {
                this.title.setText(title);
            }
        }
    }

    public void setContent(String content) {
        if (this.content != null) {
            this.content.setText(content);
        }
    }

    public void setChildContent(String tag, String content) {
        RelativeLayout contentLayout = (RelativeLayout) getChildAt(0);
        LinearLayout root = (LinearLayout) contentLayout.getChildAt(1);
        for (int i=0; i< root.getChildCount(); i++){
            View child = root.getChildAt(i);
            if (child instanceof RelativeLayout){
                RelativeLayout layout = (RelativeLayout) child;
                for (int j=0; j<layout.getChildCount(); j++){
                    if (layout.getChildAt(j).getTag() != null && tag.equals(layout.getChildAt(j).getTag())){
                        ((TextView)layout.getChildAt(j)).setText(content);
                    }
                }
            }
        }

    }

    public void setTvContent(String content) {
        if (this.tvContent != null) {
            this.tvContent.setText(content);
//            this.tvContent.setMovementMethod(ScrollingMovementMethod.getInstance());
        }
    }

    public void setSelect(SelectCallBack onSelect) {
        mBuilder.onSelect = onSelect;
    }

    public void setMultiOne(String multiOne) {
        if (this.content != null && (mBuilder.mType == Type.MULTIINPUT || mBuilder.mType == Type.MULTISELECT)) {
            this.content.setText(multiOne);
        }
    }

    public String getMultiOne(){
        if (this.content != null && (mBuilder.mType == Type.MULTIINPUT || mBuilder.mType == Type.MULTISELECT)) {
            return content.getText().toString();
        }
        return "";
    }

    public void setMultiTwo(String multiTwo) {
        if (this.content2 != null && (mBuilder.mType == Type.MULTIINPUT || mBuilder.mType == Type.MULTISELECT)) {
            this.content2.setText(multiTwo);
        }
    }

    public String getMultiTwo(){
        if (this.content2 != null && (mBuilder.mType == Type.MULTIINPUT || mBuilder.mType == Type.MULTISELECT)) {
           return content2.getText().toString();
        }
        return "";
    }

    public void setMsg(String msg) {
        setContent(msg);
    }

    public String getTitle() {
        if (this.title != null) {
            return this.title.getText().toString();
        } else {
            return null;
        }
    }

    public String getContent() {
        if (this.content != null) {
            return this.content.getText().toString();
        } else {
            return "";
        }
    }

    public String getClearEditText(){
        if(this.clearEditText != null){
            return this.clearEditText.getText().toString();
        }else
            return "";
    }

    public void setInput(String content) {
        if (this.clearEditText != null)
            this.clearEditText.setText(content);
    }

    public String getInput() {
        if (this.clearEditText != null) {
            return this.clearEditText.getText().toString();
        } else {
            return "";
        }
    }

    public String getTvContent() {
        if (this.tvContent != null) {
            return this.tvContent.getText().toString();
        } else {
            return null;
        }
    }

    public TextView getTvContentView() {
        return tvContent;
    }

    public void setEllipsize(TextUtils.TruncateAt ellipsis){
        if(content!=null)
            content.setEllipsize(ellipsis);
    }

    public String getMsg() {
        return getContent();
    }

    public TextView getTitleView() {
        return title;
    }

    public EditText getContentView() {
        return content;
    }

    public void setTvContentOnToucher(View.OnTouchListener touchListener){
        if(tvContent!=null){
            tvContent.setOnTouchListener(touchListener);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(content)) {
            if (mBuilder.onSelect != null) {
                mBuilder.onSelect.onSelect(this, 1);
            }
        } else if (v.equals(content2)) {
            if (mBuilder.onSelect != null) {
                mBuilder.onSelect.onSelect(this, 2);
            }
        } else if (v.equals(mapImg)) {
            if (mBuilder.onSelect != null) {
                mBuilder.onSelect.onSelect(this, 2);
            }
        } else if (v.equals(clearEditText)) {
            v.setFocusable(true);
            v.setFocusableInTouchMode(true);
            v.requestFocus();
            v.requestFocusFromTouch();
            InputMethodManager inputMethodManager = (InputMethodManager) mBuilder.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(v, 0);
        }
    }

    public static class Builder {
        private Context mContext;
        private Type mType;

        private float marginH;
        private float marginV;
        private float titleW;
        private float textSize;
        private int titleColor;
        private int textColor;
        private int hintColor;
        private boolean isRequired;
        private boolean showBtmLine;

        private SelectCallBack onSelect;

        private int inputType;

        private String titleString;
        private String contentString;
        private String msgString;
        private String selectHint;
        private String inputHint;
        private String multiHintOne;
        private String multiHintTwo;
        private List<String> multiHints;
        private String seperator;
        private List<String> times;
        private TimeListener timeListener;

        public Builder(Context mContext) {
            this.mContext = mContext;
            marginH = DWZH.dp(25);
            marginV = DWZH.dp(10);
            titleW = DWZH.dp(145);
            textSize = 14;
            titleColor = ContextCompat.getColor(getContext(), R.color.gray);
            textColor = ContextCompat.getColor(getContext(), R.color.deep_gray);
            hintColor = ContextCompat.getColor(getContext(), R.color.white_gray);
            isRequired = false;
            mType = Type.TEXT;
            showBtmLine = true;
            inputType = InputType.TYPE_CLASS_TEXT;
            seperator = "、";
        }

        public Builder title(String title) {
            this.titleString = title;
            return this;
        }

        public Builder title(@StringRes int titleRes) {
            title(this.mContext.getString(titleRes));
            return this;
        }

        public Builder content(String content) {
            this.contentString = content;
            return this;
        }

        public Builder content(@StringRes int contentRes) {
            content(this.mContext.getString(contentRes));
            return this;
        }

        public Builder msg(String msgString) {
            this.mType = Type.MSG;
            this.msgString = msgString;
            return this;
        }


        public Builder msgWithTitle(String msgString){
            this.mType = Type.MSG_WITH_TITLE;
            this.msgString = msgString;
            return this;
        }

        public Builder msgWithMutiplyLine(String msgString){
            this.mType = Type.MSG_MULTIPLE_LINE;
            this.msgString = msgString;
            return this;
        }

        public Builder msg(@StringRes int msgRes) {
            msg(this.mContext.getString(msgRes));
            return this;
        }

        public Builder time(String... times){
            this.mType = Type.TIME;
            this.times = Arrays.asList(times);
            return this;
        }

        public Builder timeBack(TimeListener timeListener){
            this.timeListener = timeListener;
            return this;
        }

        public Builder select(String hint) {
            this.mType = Type.SELECT;
            this.selectHint = hint;
            return this;
        }

        public Builder select(@StringRes int hintRes) {
            select(this.mContext.getString(hintRes));
            return this;
        }

        public Builder onSelect(SelectCallBack onSelect) {
            this.onSelect = onSelect;
            return this;
        }

        public Builder input(String hint) {
            this.mType = Type.INPUT;
            this.inputHint = hint;
            return this;
        }

        public Builder editTextSelectorHint(String hint) {
            this.inputHint = hint;
            return this;
        }

        public Builder input(@StringRes int hintRes) {
            input(this.mContext.getString(hintRes));
            return this;
        }

        public Builder multiInput(String hintOne, String hintTwo) {
            this.multiHintOne = hintOne;
            this.multiHintTwo = hintTwo;
            this.mType = Type.MULTIINPUT;
            return this;
        }

        public Builder multiInput(@StringRes int hintOne, @StringRes int hintTwo) {
            multiInput(this.mContext.getString(hintOne), this.mContext.getString(hintTwo));
            return this;
        }

        public Builder multiSelect(String hintOne, String hintTwo) {
            this.multiHintOne = hintOne;
            this.multiHintTwo = hintTwo;
            this.mType = Type.MULTISELECT;
            return this;
        }

        public Builder multiSelect2(List<String> data) {
            this.multiHints = data;
            this.mType = Type.MULTI_SELECT_2;
            return this;
        }

        public Builder multiSelect(@StringRes int hintOneRes, @StringRes int hintTwoRes) {
            multiSelect(this.mContext.getString(hintOneRes), this.mContext.getString(hintTwoRes));
            return this;
        }

        public Builder multiSeperator(String seperator) {
            this.seperator = seperator;
            return this;
        }

        public Builder multiSeperator(@StringRes int seperatorRes) {
            multiSeperator(this.mContext.getString(seperatorRes));
            return this;
        }

        public Builder asTitle(String title) {
            this.mType = Type.TITLE;
            this.msgString = title;
            return this;
        }

        public Builder asTitle(@StringRes int titleRes) {
            asTitle(this.mContext.getString(titleRes));
            return this;
        }

        public Builder inputType(int inputType) {
            this.inputType = inputType;
            return this;
        }

        public Builder required() {
            this.isRequired = true;
            return this;
        }

        public Builder map() {
            this.mType = Type.MAP;
            return this;
        }

        public Builder arrowSelect() {
            this.mType = Type.SELECT_WITH_ARROW;
            return this;
        }

        public Builder arrowSelectWithEditText() {
            this.mType = Type.INPUT_WITH_ARROW;
            return this;
        }

        public Builder hideBtmLine() {
            this.showBtmLine = false;
            return this;
        }

        public Builder marginH(int marginH) {
            this.marginH = DWZH.dp2pt(this.mContext, marginH);
            return this;
        }

        public Builder marginV(int dp) {
            this.marginV = DWZH.dp(dp);
            return this;
        }

        public Builder titleW(int titleW) {
            this.titleW = DWZH.dp2pt(this.mContext, titleW);
            return this;
        }

        public Builder textSize(int textSize) {
            this.textSize = textSize;
            return this;
        }

        public Builder titleColor(@ColorRes int titleColor) {
            this.titleColor = ContextCompat.getColor(this.mContext, titleColor);
            return this;
        }

        public Builder textColor(@ColorRes int textColor) {
            this.textColor = ContextCompat.getColor(this.mContext, textColor);
            return this;
        }

        public Builder hintColor(@ColorRes int hintColor) {
            this.hintColor = ContextCompat.getColor(this.mContext, hintColor);
            return this;
        }

        public TableRow build() {
            return new TableRow(this);
        }

        private Context getContext() {
            return mContext;
        }

        public interface TimeListener{
            void timeBack(String key, String value);
        }

        public void setTimeListener(TimeListener timeListener) {
            this.timeListener = timeListener;
        }
    }

    public interface SelectCallBack {
        void onSelect(TableRow row, int which);
    }

    public interface OnMultiClick{
        void onClick(int position);
    }

    public void setOnMultiClick(OnMultiClick onMultiClick) {
        this.onMultiClick = onMultiClick;
    }
}
