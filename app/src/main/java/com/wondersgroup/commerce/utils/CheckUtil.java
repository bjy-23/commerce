package com.wondersgroup.commerce.utils;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.wondersgroup.commerce.constant.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/5/11.
 */
public class CheckUtil {

    /**
     * 限制TextView的最大输入字数
     * @param textView
     * @param limitCount
     */
    public static void limitCheckMaxCount(final TextView textView,final int limitCount){
        textView.addTextChangedListener(new TextWatcher() {
            String strTemp = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("haha", "" + s);
                if (textView.getText().toString().length() > limitCount) {
                    textView.setText("");
                    textView.append(strTemp);
                } else {
                    if (!textView.getText().toString().equals("")) {
                        strTemp = textView.getText().toString();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 限制TextView的最小输入字数
     * @param textView
     */
    public static void limitCheckMinCount(final TextView textView,final int limitCount){
        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(s.equals("")){
                    if(textView.getError()==null){
                        textView.setError("最少输入字数为"+limitCount);
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (textView.getText().toString().length()<limitCount) {
                    textView.setError("最少输入字数为"+limitCount);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    /**
     * 限制输入只能为email
     * @param textView
     */
    public static void checkInputEmail(final TextView textView){
        textView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!checkIsEmail(textView.getText().toString())) {
                        textView.setError("邮箱格式不正确");
                    }
                }
            }
        });
    }


    /**
     * 限制输入只能为电话号
     * @param textView
     */
    public static void checkInputPhone(final TextView textView){
        textView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!checkIsPhone(textView.getText().toString())){
                        textView.setError("手机号不正确");
                    }
                }
            }
        });
    }


    /**
     * 判断是否为邮箱
     * 必须包含一个并且只有一个符号“@”
     * 第一个字符不得是“@”或者“.”
     * 不允许出现“@.”或者.@
     * 结尾不得是字符“@”或者“.”
     * 允许“@”前的字符中出现“＋”
     * 不允许“＋”在最前面，或者“＋@”
     * @return
     */
    public static boolean checkIsEmail( String email){
        return email.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
    }

    /**
     * 判断是否为手机号
     * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
     * 联通：130、131、132、152、155、156、185、186
     * 电信：133、153、180、189、（1349卫通）
     * @return
     */
    public static boolean checkIsPhone( String phone){
//        return phone.matches("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
          return phone.length()==11;
    }


    /**
     * 设置TextView只能输入中文
     * @param textView
     */
    public static void checkOnlyInputChinese(final TextView textView){
        textView.addTextChangedListener(new TextWatcher() {
            String strTemp  =   "";
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!textView.getText().toString().matches("[\\u4E00-\\u9FA5]")&&!textView.getText().toString().equals("")){
                    int i   =   0;
                    if(count>=1){
                        for (;i<s.length();i++){
                            if(!isChinese(String.valueOf(s.charAt(i)))){
                                break;
                            }
                        }
                    }
                    if(i<s.length()) {
                        textView.setText("");
                        textView.append(strTemp);
                    }else{
                        if(!textView.getText().toString().equals("")){
                            strTemp =   textView.getText().toString();
                        }
                    }
                }else{
                    if(!textView.getText().toString().equals("")){
                        strTemp =   textView.getText().toString();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 设置TextView只能输入数字和字母
     * @param textView
     */
    public static void checkInputNumberAndChar(final TextView textView){
        textView.addTextChangedListener(new TextWatcher() {
            String strTemp  =   "";
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!textView.getText().toString().matches("[\\da-zA-Z]+")&&!textView.getText().toString().equals("")){
                    textView.setText("");
                    textView.append(strTemp);
                }else{
                    if(!textView.getText().toString().equals("")) {
                        strTemp = textView.getText().toString();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    // 判断中文汉字和符号
    private static boolean isChinese(String s) {
       return s.matches("[\\u4E00-\\u9FA5]");
    }


    /**
     * 弹出软键盘
     * @param checkedNumber
     * @param mContext
     */
    public static void showSoftInput(TextView checkedNumber, Context mContext) {
        checkedNumber.requestFocus();
        InputMethodManager imm = (InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(checkedNumber, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 关闭软键盘
     * @param checkedNumber
     * @param mContext
     */
    public static void hideSoftInput(TextView checkedNumber, Context mContext) {
        InputMethodManager imm = (InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(checkedNumber.getWindowToken(), 0);
    }

    /*
    * 是否包含汉字
    * */

    public static boolean containChinese(CharSequence charSequence){
        Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher matcher = pattern.matcher(charSequence);
        if (matcher.find()){
            return true;
        }

        return false;
    }

}
