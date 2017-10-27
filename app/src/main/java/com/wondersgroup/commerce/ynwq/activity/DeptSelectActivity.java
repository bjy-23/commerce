package com.wondersgroup.commerce.ynwq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeptSelectActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.mid_toolbar)Toolbar toolbar;
    @BindView(R.id.toolbar_title)TextView title;
    @BindView(R.id.dept_select_recycler)RecyclerView recycler;
    @BindView(R.id.dept_select_pre)Button pre;
    @BindView(R.id.dept_select_ok)Button ok;
    @BindView(R.id.dept_select_next)Button next;

    private DeptSelectAdapter adapter;
    private Tree root;
    private List<String> items;
    private int rootOrganId;
    private int curOrganId;
    private int selection=-1;
    private Handler handler;
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept_select);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        title.setText(getIntent().getStringExtra("title"));
        rootOrganId=getIntent().getIntExtra("organId", 0);
        curOrganId=rootOrganId;

        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==0){
                    root= Hawk.get("DeptSelectTree");
                    updateItem();
                }else if(msg.what==1){
                    Toast.makeText(DeptSelectActivity.this,"没有上一级了",Toast.LENGTH_SHORT).show();
                }else if(msg.what==2){
                    Toast.makeText(DeptSelectActivity.this,"没有下一级了",Toast.LENGTH_SHORT).show();
                }
            }
        };

        thread=new Thread() {
            private List<TreeItem> items;
            @Override
            public void run() {
                DicT dic= Hawk.get("Dic");
                items=dic.getJsonReceiveOrganTree();
                Tree original = generateTree(curOrganId);
                if (original.getChildren().size()!=0) {
                    Hawk.put("DeptSelectTree", original);
                    handler.sendEmptyMessage(0);
                } else if(original.getId()==-1){
                    handler.sendEmptyMessage(1);
                }else {
                    handler.sendEmptyMessage(2);
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

        LinearLayoutManager manager=new LinearLayoutManager(this);
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
                selection=position;
            }
        });
        recycler.setAdapter(adapter);
        pre.setOnClickListener(this);
        ok.setOnClickListener(this);
        next.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        if(v.equals(pre)){
            curOrganId = root.getpId();
            handler.post(thread);
        }else if(v.equals(ok)){
            Intent intent=new Intent();
            intent.putExtra("DeptSelectName",root.getChildren().get(selection).getName());
            intent.putExtra("DeptSelectId",root.getChildren().get(selection).getId());
            setResult(RESULT_OK,intent);
            finish();
        }else if(v.equals(next)){
            if(selection!=-1) {
                curOrganId = root.getChildren().get(selection).getId();
                handler.post(thread);
            }else {
                Toast.makeText(DeptSelectActivity.this,"请选择机关",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /*public Tree findRoot(Tree root){
        if(root.getId()==rootOrganId){
            root.setParent(null);
            return root;
        }else if(root.getChildren().size()!=0){
            for (Tree tree :
                    root.getChildren()) {
                return findRoot(tree);
            }
        }else {
            return null;
        }
        return null;
    }*/
}
