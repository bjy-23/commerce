package com.wondersgroup.commerce.ynwq.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.model.ccjc.Tree;
import com.wondersgroup.commerce.model.ccjc.TreeItem;
import com.wondersgroup.commerce.ynwq.adapter.DeptSelectAdapter;
import com.wondersgroup.commerce.ynwq.bean.DicT;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 薛定猫 on 2016/1/27.
 */
public class DeptSelectDialog extends DialogFragment implements View.OnClickListener{
    ImageButton back;
    TextView title;
    RecyclerView recycler;
    Button pre;
    Button ok;
    Button next;
    private DeptSelectAdapter adapter;
    private Tree root;
    private List<String> items;
    private int rootOrganId;
    private int curOrganId;
    private int selection=-1;
    private Handler handler;
    private Thread thread;
    private String titleString;
    private int organId;

    public interface OnItemSelect{
        void onItemSelect(int id, String name);
    }
    private OnItemSelect listener;

    public void setListener(OnItemSelect listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Translucent_NoTitleBar);
        if(getArguments()!=null){
            titleString=getArguments().getString("title");
            organId=getArguments().getInt("organId");
        }
    }

    public static DeptSelectDialog newInstance(String title,int organId){
        DeptSelectDialog dialog=new DeptSelectDialog();
        Bundle args=new Bundle();
        args.putString("title",title);
        args.putInt("organId", organId);
        dialog.setArguments(args);
        return dialog;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getContext(), R.layout.activity_dept_select,null);
        title=(TextView)view.findViewById(R.id.dept_select_title);
        recycler=(RecyclerView)view.findViewById(R.id.dept_select_recycler);
        pre=(Button)view.findViewById(R.id.dept_select_pre);
        ok=(Button)view.findViewById(R.id.dept_select_ok);
        next=(Button)view.findViewById(R.id.dept_select_next);
        back=(ImageButton)view.findViewById(R.id.dept_select_back);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        title.setText(titleString);
        rootOrganId=organId;
        curOrganId=rootOrganId;

        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==0){
                    root= Hawk.get("DeptSelectTree");
                    updateItem();
                }else if(msg.what==1){
                    Toast.makeText(getContext(), "没有上一级了", Toast.LENGTH_SHORT).show();
                }else if(msg.what==2){
                    Toast.makeText(getContext(),"没有下一级了",Toast.LENGTH_SHORT).show();
                }else if(msg.what==3){
                    Toast.makeText(getContext(),"初始化字典表失败",Toast.LENGTH_SHORT).show();
                }
            }
        };

        thread=new Thread() {
            private List<TreeItem> items;
            @Override
            public void run() {
                DicT dic= Hawk.get("Dic");
                items=dic.getJsonReceiveOrganTree();
                if(items!=null) {
                    Tree original = generateTree(curOrganId);
                    if (original.getChildren().size() != 0) {
                        Hawk.put("DeptSelectTree", original);
                        handler.sendEmptyMessage(0);
                    } else if (original.getId() == -1) {
                        handler.sendEmptyMessage(1);
                    } else {
                        handler.sendEmptyMessage(2);
                    }
                }else {
                    handler.sendEmptyMessage(3);
                }
            }
            private Tree generateTree(int rootId){
                Tree root=new Tree();
                for (TreeItem i :
                        items) {
                    int id = i.getId();
                    if(id==rootId){
                        root.setId(i.getId());
                        root.setName(i.getName());
                        root.setpId(i.getpId());
                    }else if(i.getpId()==rootId){
                        root.getChildren().add(i);
                    }
                }
                return root;
            }
        };

        handler.post(thread);

        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        recycler.setLayoutManager(manager);
        recycler.setHasFixedSize(true);
        adapter=new DeptSelectAdapter();
        items=new ArrayList<>();
        adapter.setItems(items);
        adapter.setOnItemClickListener(new DeptSelectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                adapter.setPosition(position);
                adapter.notifyItemChanged(selection);
                adapter.notifyItemChanged(position);
                selection = position;
            }
        });
        recycler.setAdapter(adapter);
        pre.setOnClickListener(this);
        ok.setOnClickListener(this);
        next.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    public void updateItem(){
        int curSize = items.size();
        items.clear();
        adapter.notifyItemRangeRemoved(0, curSize);
        adapter.setPosition(-1);
        List<String> tmp=new ArrayList<>();
        for(int i=0;i<root.getChildren().size();i++){
            tmp.add(root.getChildren().get(i).getName());
        }
        items.addAll(tmp);
        adapter.notifyItemRangeInserted(0, tmp.size());
        selection=-1;
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
    @Override
    public void onClick(View v) {
        if(v.equals(pre)){
            curOrganId = root.getpId();
            handler.post(thread);
        }else if(v.equals(ok)){
            if(listener!=null){
                listener.onItemSelect(root.getChildren().get(selection).getId(),root.getChildren().get(selection).getName());
                getDialog().dismiss();
            }
        }else if(v.equals(next)){
            if(selection!=-1) {
                curOrganId = root.getChildren().get(selection).getId();
                handler.post(thread);
            }else {
                Toast.makeText(getContext(),"请选择机关",Toast.LENGTH_SHORT).show();
            }
        }else if(v.equals(back)){
            getDialog().dismiss();
        }
    }
}
