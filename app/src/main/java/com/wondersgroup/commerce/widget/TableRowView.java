package com.wondersgroup.commerce.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.utils.DWZH;

/**
 * Created by 薛定猫 on 2016/3/4.
 */
public class TableRowView extends RelativeLayout{
    private TextView title;
    private EditText content;

    private float marginL;
    private float marginT;
    private float marginR;
    private float marginB;
    private float titleW;
    private float textSize;
    private int titleColor;
    private int textColor;
    private int hintColor;
    private boolean isRequired;

    public TableRowView(Context context) {
        super(context);
        init();
    }

    public TableRowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TableRowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        marginL= DWZH.dp2pt(getContext(),22);
        marginT=DWZH.dp2pt(getContext(),15);
        marginB=marginT;
        marginR=marginL;
        titleW=DWZH.dp2pt(getContext(), 112);
        textSize =14;
        titleColor= ContextCompat.getColor(getContext(), R.color.gray);
        textColor=ContextCompat.getColor(getContext(),R.color.deep_gray);
        hintColor=ContextCompat.getColor(getContext(),R.color.white_gray);
        isRequired=false;
        LayoutParams params=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(params);
    }

    public static TableRowView newTitleRow(Context context,int colorRes,String title){
        TableRowView row=new TableRowView(context);
        row.setBackgroundColor(ContextCompat.getColor(context, R.color.whiter_gray));
        row.setTextColor(ContextCompat.getColor(context, colorRes));
        row.setTextSize(12);
        row.setMarginT(DWZH.dp2pt(context, 10));
        row.setMarginB(DWZH.dp2pt(context, 10));
        row.addMsg(context);
        row.content.setText(title);
        row.content.setInputType(InputType.TYPE_NULL);
        row.content.setEllipsize(TextUtils.TruncateAt.END);
        return row;
    }

    public static TableRowView newTableRow(Context context,String title,String content){
        TableRowView row=new TableRowView(context);
        row.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        row.addTitle(context);
        row.title.setText(title);
        row.addContent(context);
        row.content.setText(content);
        row.content.setInputType(InputType.TYPE_NULL);
        row.content.setSingleLine(false);
        //row.content.setMaxLines(2);
        row.content.setEllipsize(TextUtils.TruncateAt.END);
        return row;
    }

    public static TableRowView newTableRow(Context context,String title,String content,boolean showBtm){
        TableRowView row=TableRowView.newTableRow(context,title,content);
        if(showBtm) row.addBtmLine(context);
        return row;
    }

    public static TableRowView newInputRow(Context context,String title,String hint,int inputType,boolean isRequired){
        TableRowView row=new TableRowView(context);
        row.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        row.addTitle(context);
        row.isRequired=isRequired;
        row.setTitle(title);
        row.addContent(context);
        row.content.setHint(hint);
        row.content.setInputType(inputType);
        row.content.setSingleLine(false);
        row.content.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
        return row;
    }

    public static TableRowView newSelectRow(Context context,String title,String hint,boolean isRequired,View.OnClickListener onClickListener){
        TableRowView row=new TableRowView(context);
        row.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        row.addTitle(context);
        row.isRequired=isRequired;
        row.setTitle(title);
        row.addContent(context);
        row.content.setHint(hint);
        row.content.setInputType(InputType.TYPE_NULL);
        row.content.setSingleLine(false);
        row.content.setMaxLines(2);
        row.content.setEllipsize(TextUtils.TruncateAt.END);
        row.content.setOnClickListener(onClickListener);
        row.addBtmLine(context);
        return row;
    }

    public static TableRowView newMsgView(Context context,String msg){
        TableRowView row=new TableRowView(context);
        row.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        row.addMsg(context);
        row.content.setText(msg);
        row.addBtmLine(context);
        return row;
    }

    public void setTitle(String title){
        if(this.title!=null){
            if(isRequired){
                SpannableString sb=new SpannableString(title+"*");
                sb.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(),R.color.red)),sb.length()-1,sb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                this.title.setText(sb);
            }else {
                this.title.setText(title);
            }
        }
    }

    public void setContent(String content){
        if(this.content!=null){
            this.content.setText(content);
        }
    }

    public String getTitle(){
        if(this.title!=null){
            return this.title.getText().toString();
        }else {
            return null;
        }
    }

    public String getContent(){
        if(this.content!=null){
            return this.content.getText().toString();
        }else {
            return null;
        }
    }

    private void addTitle(Context context){
        title=new TextView(context);
        LayoutParams titleParams=new LayoutParams((int)DWZH.dp2pt(context,62), ViewGroup.LayoutParams.WRAP_CONTENT);
        titleParams.addRule(CENTER_VERTICAL);
        titleParams.setMargins((int) marginL, (int) marginT, 0, (int) marginB);
        title.setLayoutParams(titleParams);
        title.setTextSize(textSize);
        title.setTextColor(titleColor);
        this.addView(title);
    }

    private void addContent(Context context){
        content=new EditText(context);
        LayoutParams contentParams=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        contentParams.addRule(CENTER_VERTICAL);
        contentParams.setMargins((int) titleW, (int) marginT,(int)marginR, (int) marginB);
        content.setLayoutParams(contentParams);
        content.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
        content.setPadding(0, 0, 0, 0);
        content.setTextSize(textSize);
        content.setTextColor(textColor);
        content.setHintTextColor(hintColor);
        this.addView(content);
    }

    private void addMsg(Context context){
        content=new EditText(context);
        LayoutParams contentParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        contentParams.addRule(CENTER_VERTICAL);
        contentParams.setMargins((int) marginL, (int) marginT,(int)marginR, (int) marginB);
        content.setLayoutParams(contentParams);
        content.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
        content.setPadding(0, 0, 0, 0);
        content.setTextSize(textSize);
        content.setTextColor(textColor);
        content.setHintTextColor(hintColor);
        this.addView(content);
    }

    private void addBtmLine(Context context){
        View view=new View(context);
        LayoutParams params=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,1);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.setMargins((int) DWZH.dp2pt(context, 5), 0, (int) DWZH.dp2pt(context, 5), 0);
        view.setLayoutParams(params);
        view.setBackgroundColor(ContextCompat.getColor(context, R.color.linecolor));
        this.addView(view);
    }

    public void setMarginL(float marginL) {
        this.marginL = marginL;
        if(this.title!=null){
            setTitleMargins();
        }
    }

    public void setMarginT(float marginT) {
        this.marginT = marginT;
        if(this.title!=null){
            setTitleMargins();
        }
        if(this.content!=null){
            setContentMargins();
        }
    }

    public void setMarginR(float marginR) {
        this.marginR = marginR;
        if(this.content!=null){
            setContentMargins();
        }
    }

    public void setMarginB(float marginB) {
        this.marginB = marginB;
        if(this.title!=null){
            setTitleMargins();
        }
        if(this.content!=null){
            setContentMargins();
        }
    }

    public void setTitleW(float titleW) {
        this.titleW = titleW;
        if(this.content!=null){
            setContentMargins();
        }
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
        if(this.title!=null){
            this.title.setTextSize(textSize);
        }
        if(this.content!=null){
            this.content.setTextSize(textSize);
        }
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
        if(this.title!=null){
            this.title.setTextColor(titleColor);
        }
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        if(this.content!=null){
            this.content.setTextColor(textColor);
        }
    }

    public void setHintColor(int hintColor) {
        this.hintColor = hintColor;
    }

    private void setTitleMargins(){
        LayoutParams titleParams= (LayoutParams) this.title.getLayoutParams();
        titleParams.setMargins((int) marginL, (int) marginT, 0, (int) marginB);
        this.title.setLayoutParams(titleParams);
    }
    private void setContentMargins(){
        LayoutParams contentParams= (LayoutParams) this.content.getLayoutParams();
        contentParams.setMargins((int) titleW, (int) marginT,(int)marginR, (int) marginB);
        this.title.setLayoutParams(contentParams);
    }
}
