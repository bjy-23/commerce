package com.wondersgroup.yngs.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.orhanobut.hawk.Hawk;
import com.wondersgroup.yngs.R;
import com.wondersgroup.yngs.entity.DicItem;
import com.wondersgroup.yngs.entity.DicResult;
import com.wondersgroup.yngs.entity.DicT;
import com.wondersgroup.yngs.service.ApiManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by 薛定猫 on 2016/1/27.
 */
public class ListDialog extends DialogFragment implements View.OnClickListener{
    ListView list;
    TextView title;
    Button back;
    private String titleString;
    private String type;
    private String errorMsg;
    private ArrayList<String> items=new ArrayList<>();
    private List<DicItem> dicItems=new ArrayList<>();
    public interface OnSelected{
        void onSelected(String type,String typeCode);
    }
    private OnSelected listener;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Translucent_NoTitleBar);
        if(getArguments()!=null){
            titleString=getArguments().getString("title");
            type=getArguments().getString("type");
        }
    }

    public static ListDialog newInsance(String title,String type){
        ListDialog dialog=new ListDialog();
        Bundle args=new Bundle();
        args.putString("title",title);
        args.putString("type", type);
        dialog.setArguments(args);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getContext(), R.layout.view_dialog_list,null);
        list=(ListView)view.findViewById(R.id.list_list);
        title=(TextView)view.findViewById(R.id.toolbar_title);
        back=(Button)view.findViewById(R.id.toolbar_back);
        back.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        title.setText(titleString);
        if("SQLX".equals(type)){
            errorMsg ="获取申请类型失败";
        }else if("SQZT".equals(type)){
            errorMsg ="获取申请状态失败";
        }else if("CDXZ".equals(type)){
            errorMsg="获取场地性质失败";
        }else if("BMXZ".equals(type)){
            errorMsg="获取部门失败";
        }
        DicT dic= Hawk.get("Dic");
        if(dic==null){
            SharedPreferences sp=getContext().getSharedPreferences("Default", Context.MODE_PRIVATE);
            Map<String,String> body =new HashMap<>();
            int selection=sp.getInt("selection", 0);
            body.put("deptId", sp.getString("deptId", "").split(",")[selection]);
            body.put("organId", sp.getString("organId", "").split(",")[selection]);
            body.put("wsCodeReq", ApiManager.getWsCodeReq());
            Call<DicResult> call = ApiManager.yunNanApi.getDic(body);
            call.enqueue(new Callback<DicResult>() {
                @Override
                public void onResponse(Response<DicResult> response, Retrofit retrofit) {
                    if (response.isSuccess()&&response.body()!=null) {
                        DicResult result = response.body();
                        if ("200".equals(result.getResultCode())) {
                            Hawk.put("Dic", result.getResult());
                            setTypes(result.getResult());
                        } else {
                            Toast.makeText(getContext(), errorMsg, Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(getContext(),errorMsg,Toast.LENGTH_LONG).show();
                }
            });
        }else {
            setTypes(dic);
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getContext(),R.layout.view_list_item,items);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if ("其它".equals(items.get(position))) {
                    new MaterialDialog.Builder(getContext())
                            .title("请输入类型名")
                            .inputType(InputType.TYPE_CLASS_TEXT)
                            .input("输入类型名", "", new MaterialDialog.InputCallback() {
                                @Override
                                public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                                    if(listener!=null){
                                        listener.onSelected(items.get(position),dicItems.get(position).getName());
                                        getDialog().dismiss();
                                    }
                                }
                            })
                            .cancelable(false)
                            .show();
                } else {
                    if(listener!=null){
                        listener.onSelected(items.get(position),dicItems.get(position).getName());
                        getDialog().dismiss();
                    }
                }
            }
        });
    }

    public void setListener(OnSelected listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog= super.onCreateDialog(savedInstanceState);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.gravity = Gravity.TOP|Gravity.LEFT;
        dialog.getWindow().setAttributes(params);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }
    public void setTypes(DicT dic){
        dicItems.clear();
        items.clear();
        if("SQLX".equals(type)){
            dicItems.addAll(dic.getDicAppType());
        }else if("SQZT".equals(type)){
            dicItems.addAll(dic.getFlowStatus());
        }else if("CDXZ".equals(type)){
            dicItems.addAll(dic.getRunSpaceType());
        }else if("BMXZ".equals(type)){
            //List<DicItem> bmItems=Hawk.get("BMXZDic");
            dicItems.addAll(dic.getDicDeptTypeAll());
        }
        for (DicItem i :
                dicItems) {
            items.add(i.getValue());
        }
    }

    @Override
    public void onClick(View v) {
        if(v.equals(back)){
            getDialog().dismiss();
        }
    }
}
