package com.wondersgroup.commerce.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.model.ccjc.DicItem;
import com.wondersgroup.commerce.model.ccjc.DicResult;
import com.wondersgroup.commerce.model.ccjc.DicT;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.utils.DWZH;
import com.wondersgroup.commerce.utils.DataShared;

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
    private String organId;

    public interface OnSelected{
        void onSelected(String type, String typeCode);
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
        DataShared dataShared=new DataShared(getContext());
        organId=(String)dataShared.get("organId","");
        title.setText(titleString);
        if("CCZT".equals(type)){
            errorMsg="获取抽查状态失败";
        }else if("CCPC".equals(type)){
            errorMsg="获取抽查批次失败";
        }else if("HCQK".equals(type)){
            errorMsg="获取核查情况失败";
        }else if("CLQD".equals(type)){
            errorMsg="获取材料清单失败";
        }
        DicT dic= Hawk.get("Dic");
        if(dic==null){
            Map<String,String> body =new HashMap<>();
            body.put("organId", organId.substring(0,6));
            body.put("wsCodeReq", "020001");
            Call<DicResult> call = ApiManager.ccjcApi.ccDic(body);
            call.enqueue(new Callback<DicResult>() {
                @Override
                public void onResponse(Response<DicResult> response, Retrofit retrofit) {
                    if (response.isSuccess()&&response.body()!=null) {
                        DicResult result = response.body();
                        if ("200".equals(result.getCode())) {
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
        if("CLQD".equals(type)){
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.view_checked_list_item, items);
            list.setAdapter(adapter);
            list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            list.setItemsCanFocus(false);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    if("9".equals(dicItems.get(position).getName())){
                        new MaterialDialog.Builder(getContext())
                                .title("请输入类型名")
                                .inputType(InputType.TYPE_CLASS_TEXT)
                                .input("输入类型名", "", new MaterialDialog.InputCallback() {
                                    @Override
                                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                                        items.set(position,input.toString());
                                    }
                                })
                                .cancelable(false)
                                .show();
                    }
                }
            });
            LinearLayout ll=new LinearLayout(getContext());
            ll.setOrientation(LinearLayout.VERTICAL);
            Button btn=new Button(getContext());
            btn.setText("提交");
            btn.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            LinearLayout.LayoutParams contentParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            contentParams.setMargins((int) DWZH.dp2pt(getActivity(), 16), (int) DWZH.dp2pt(getActivity(), 8), (int) DWZH.dp2pt(getActivity(), 16),(int) DWZH.dp2pt(getActivity(), 8));
            btn.setLayoutParams(contentParams);
            btn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.blue));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null) {
                        SparseBooleanArray checked = list.getCheckedItemPositions();
                        String returntype = "";
                        String typeCode = "";
                        if (checked.size() == 0) {
                            listener.onSelected("", "");
                        } else{
                            for (int i = 0; i < checked.size(); i++) {
                                int key = checked.keyAt(i);
                                returntype += items.get(key) + ",";
                                typeCode += dicItems.get(key).getName() + ",";
                            }
                            listener.onSelected(returntype.substring(0, returntype.length() - 1), typeCode.substring(0, typeCode.length() - 1));
                        }
                        getDialog().dismiss();
                    }
                }
            });
            ll.addView(btn);
            list.addFooterView(ll);
        }else {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.view_list_item, items);
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
                                        if (listener != null) {
                                            listener.onSelected(items.get(position), dicItems.get(position).getName());
                                            getDialog().dismiss();
                                        }
                                    }
                                })
                                .cancelable(false)
                                .show();
                    } else {
                        if (listener != null) {
                            listener.onSelected(items.get(position), dicItems.get(position).getName());
                            getDialog().dismiss();
                        }
                    }
                }
            });
        }
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
        if("CCPC".equals(type)){
            DicItem defaultItem = new DicItem();
            defaultItem.setName("-1");
            defaultItem.setValue("请选择");
            dicItems.add(defaultItem);
            dicItems.addAll(dic.getBatchMap());
        }else if("CCZT".equals(type)){
            dicItems.addAll(dic.getDicCheckStatusSearch());
        }else if("HCQK".equals(type)){
            dicItems.addAll(dic.getDicCheckResult());
        }else if("CLQD".equals(type)){
            dicItems.addAll(dic.getDicCheckAppendixList());
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
