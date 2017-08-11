package com.wondersgroup.commerce.fgdj.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.fgdj.widget.InputUnit;
import com.wondersgroup.commerce.fgdj.widget.SelectUnit;
import com.wondersgroup.commerce.fgdj.widget.TimeUnit;

/**
 * Created by bjy on 2017/7/18.
 */

public class CheckUtil {
    private Context context;

    public CheckUtil() {
        context = RootAppcation.getInstance();
    }

    public boolean checkInput(InputUnit inputUnit){
        if (TextUtils.isEmpty(inputUnit.getInput())){
            Toast.makeText(context,inputUnit.getName()+"不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean checkSelect(SelectUnit selectUnit){
       if (TextUtils.isEmpty(selectUnit.getSelect())){
           Toast.makeText(context,"请选择"+selectUnit.getName(),Toast.LENGTH_SHORT).show();
           return false;
       }
       return true;
    }

    public boolean checkTime(TimeUnit timeUnit){
        if (timeUnit.getTime().equals("请选择")){
            Toast.makeText(context,"请选择"+timeUnit.getName(),Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean checkPositiveInt(InputUnit inputUnit){
        if (!inputUnit.getInput().matches("^\\d+$")){
            Toast.makeText(context,inputUnit.getName() + "只能输入非负整数",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean checkPositive(InputUnit inputUnit){
        if (!inputUnit.getInput().matches("^\\d+(\\.\\d+)?$")){
            Toast.makeText(context,inputUnit.getName() + "只能输入非负数",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //电话号码格式
    public boolean checkTel(InputUnit inputUnit){
        if (!inputUnit.getInput().matches("^((0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)|(1+\\d{10})$")){
            Toast.makeText(context,inputUnit.getName() + "格式错误",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //身份证号码
    public boolean checkIdentity(String num){
        if (!num.matches("(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$)")){
            Toast.makeText(context,"身份证号格式有误",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
