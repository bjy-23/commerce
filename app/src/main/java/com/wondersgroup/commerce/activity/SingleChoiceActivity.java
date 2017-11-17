package com.wondersgroup.commerce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.adapter.SingleChoiceAdapter;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.fgdj.bean.AreaBean;
import com.wondersgroup.commerce.model.KeyValue;
import com.wondersgroup.commerce.model.TreeBean;
import com.wondersgroup.commerce.model.ccjc.Tree;
import com.wondersgroup.commerce.teamwork.casedeal.CaseEnquireActivity;
import com.wondersgroup.commerce.utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SingleChoiceActivity extends BaseActivity implements SingleChoiceAdapter.OnClickListener{
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private ArrayList<TreeBean> array;
    private SingleChoiceAdapter adapter;

    private String type;
    private List<TreeBean> caseQueryDic;
    private List<TreeBean> caseQueryList;
    private HashMap<String, Integer> positionMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_single_choice);
        ButterKnife.bind(this);

        Log.e("endTime", System.currentTimeMillis()+"");
        tvTitle.setText("请选择");
        if(getIntent().getStringExtra(Constants.TITLE) != null)
            tvTitle.setText(getIntent().getStringExtra(Constants.TITLE));
        type = getIntent().getStringExtra(Constants.TYPE);
        if ("caseQuery".equals(type)){//多层，树
            tvOption.setVisibility(View.VISIBLE);
            tvOption.setText("确认");
            tvOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int positon = positionMap.get("position");
                    if (positon == -1)
                        Toast.makeText(SingleChoiceActivity.this, "请选择！", Toast.LENGTH_SHORT).show();
                    else {
                        Intent intent = new Intent();
                        intent.putExtra("data", caseQueryList.get(positon));
                        setResult(-1, intent);
                        finish();
                    }
                }
            });

            positionMap = new HashMap<>();
            positionMap.put("position", -1);
            TreeBean root = getIntent().getParcelableExtra("root");
            if (caseQueryDic == null)
                caseQueryDic = Hawk.get("caseQueryDic");
            caseQueryList = new ArrayList<>();
            for (TreeBean treeBean: caseQueryDic){
                if (root.getId().equals(treeBean.getpId()))
                    caseQueryList.add(treeBean);
            }
            loop1:
            for (TreeBean treeBean : caseQueryList) {
                loop2:
                for (TreeBean bean: caseQueryDic){
                    if (treeBean.getId().equals(bean.getpId())){
                        treeBean.setChilds(new ArrayList<TreeBean>());
                        break loop2;
                    }
                }
            }
            caseQueryList.add(0, root);
            adapter = new SingleChoiceAdapter(this, caseQueryList, 1);
            adapter.setOnClick(new SingleChoiceAdapter.OnClick() {
                @Override
                public void back(int position) {
                    TreeBean treeBean = caseQueryList.get(position);
                    if (treeBean.getChilds() != null){
                        //复制一个同数据的对象
                        TreeBean root = new TreeBean();
                        root.setId(treeBean.getId());
                        root.setName(treeBean.getName());
                        Intent intent = new Intent(SingleChoiceActivity.this, SingleChoiceActivity.class);
                        intent.putExtra("root", root);
                        intent.putExtra(Constants.TITLE, root.getName());
                        intent.putExtra(Constants.TYPE, "caseQuery");
                        startActivityForResult(intent, 10);
                    }else {
                        int oldPosition = positionMap.get("position");
                        if (oldPosition == position)
                            return;
                        else {
                            if (oldPosition != -1)
                                caseQueryList.get(oldPosition).setSelected(false);
                            caseQueryList.get(position).setSelected(true);
                            positionMap.put("position", position);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            });
            recyclerView.setAdapter(adapter);
        }else if ("caseQuery2".equals(type)){//单层
            tvOption.setVisibility(View.VISIBLE);
            tvOption.setText("确认");
            tvOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int positon = positionMap.get("position");
                    if (positon == -1)
                        Toast.makeText(SingleChoiceActivity.this, "请选择！", Toast.LENGTH_SHORT).show();
                    else {
                        Intent intent = new Intent();
                        intent.putExtra("data", caseQueryList.get(positon));
                        setResult(-1, intent);
                        finish();
                    }
                }
            });

            positionMap = new HashMap<>();
            positionMap.put("position", -1);

            caseQueryList = getIntent().getParcelableArrayListExtra("list");
            adapter = new SingleChoiceAdapter(this, caseQueryList, 1);
            adapter.setOnClick(new SingleChoiceAdapter.OnClick() {
                @Override
                public void back(int position) {
                    TreeBean treeBean = caseQueryList.get(position);
                    if (treeBean.getChilds() != null){
                        //复制一个同数据的对象
                        TreeBean bean = null;
                        try {
                            bean = (TreeBean) treeBean.clone();
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                        if (treeBean.equals(bean)){
                            Log.e("", "equals");
                        }
                        TreeBean root = new TreeBean();
                        root.setId(treeBean.getId());
                        root.setName(treeBean.getName());
                        Intent intent = new Intent(SingleChoiceActivity.this, SingleChoiceActivity.class);
                        intent.putExtra("root", root);
                        intent.putExtra(Constants.TYPE, "caseQuery");
                        startActivityForResult(intent, 10);
                    }else {
                        int oldPosition = positionMap.get("position");
                        if (oldPosition == position)
                            return;
                        else {
                            if (oldPosition != -1)
                                caseQueryList.get(oldPosition).setSelected(false);
                            caseQueryList.get(position).setSelected(true);
                            positionMap.put("position", position);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            });
            recyclerView.setAdapter(adapter);
        }else if ("caseQuery3".equals(type)){
            tvOption.setVisibility(View.VISIBLE);
            tvOption.setText("确认");
            tvOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    ArrayList<TreeBean> list = new ArrayList<TreeBean>();
                    for (TreeBean treeBean: caseQueryList){
                        if (treeBean.isSelected())
                            list.add(treeBean);
                    }
                    if (list.size() == 0){
                        Toast.makeText(SingleChoiceActivity.this, "请选择", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    intent.putParcelableArrayListExtra("list", list);
                    setResult(-1, intent);
                    finish();
                }
            });
            caseQueryList = getIntent().getParcelableArrayListExtra("list");
            adapter = new SingleChoiceAdapter(this, caseQueryList, 1);
            adapter.setOnClick(new SingleChoiceAdapter.OnClick() {
                @Override
                public void back(int position) {
                    TreeBean treeBean = caseQueryList.get(position);
                    if (treeBean.isSelected())
                        treeBean.setSelected(false);
                    else
                        treeBean.setSelected(true);
                    adapter.notifyDataSetChanged();
                }
            });
            recyclerView.setAdapter(adapter);
        }else {
            tvOption.setVisibility(View.VISIBLE);
            tvOption.setText("确认");
            tvOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int positon = positionMap.get("position");
                    if (positon == -1)
                        Toast.makeText(SingleChoiceActivity.this, "请选择！", Toast.LENGTH_SHORT).show();
                    else {
                        Intent intent = new Intent();
                        intent.putExtra(Constants.TREE_BEAN, array.get(positon));
                        setResult(-1, intent);
                        finish();
                    }
                }
            });

            positionMap = new HashMap<>();
            positionMap.put("position", -1);
            array =  getIntent().getParcelableArrayListExtra(Constants.ARRAY);
            adapter = new SingleChoiceAdapter(this, array, 1);
            adapter.setOnClick(new SingleChoiceAdapter.OnClick() {
                @Override
                public void back(int position) {
                    TreeBean treeBean = array.get(position);
                    if (treeBean.getChilds() != null){
                        //复制一个同数据的对象
                        TreeBean bean = null;
                        try {
                            bean = (TreeBean) treeBean.clone();
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                        if (treeBean.equals(bean)){
                            Log.e("", "equals");
                        }
                        TreeBean root = new TreeBean();
                        root.setId(treeBean.getId());
                        root.setName(treeBean.getName());
                        Intent intent = new Intent(SingleChoiceActivity.this, SingleChoiceActivity.class);
                        intent.putExtra("root", root);
                        intent.putExtra(Constants.TYPE, "caseQuery");
                        startActivityForResult(intent, 10);
                    }else {
                        int oldPosition = positionMap.get("position");
                        if (oldPosition == position)
                            return;
                        else {
                            if (oldPosition != -1)
                                array.get(oldPosition).setSelected(false);
                            array.get(position).setSelected(true);
                            positionMap.put("position", position);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            });
            recyclerView.setAdapter(adapter);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Constants.RESPONSE_AREA_CODE){
            setResult(Constants.RESPONSE_AREA_CODE,data);
            finish();
        }else if (resultCode == -1){
            setResult(-1, data);
            finish();
        }
    }

    @Override
    public void onNext(String id,String title) {
        ArrayList<AreaBean> list = new ArrayList<>();
        ArrayList<AreaBean> arrayList;
        if (Constants.BASE_AREA_LIST.equals(getIntent().getStringExtra(Constants.TYPE)))
            arrayList = Hawk.get(Constants.BASE_AREA_LIST);
        else
            arrayList = Hawk.get(Constants.PARTY_AREA_LIST);

        for (int i=0;i<arrayList.size();i++){
            if (id.equals(arrayList.get(i).getpId())){
                list.add(arrayList.get(i));
            }
        }

        for (int i=0;i<list.size();i++){
            for (int j=0;j<arrayList.size();j++){
                if (arrayList.get(j).getpId()!=null){
                    if ((arrayList.get(j).getpId()).equals(list.get(i).getId())){
                        list.get(i).setHasChild(true);
                        break;
                    }
                }
            }
        }
        Intent intent = new Intent(this, SingleChoiceActivity.class);
        intent.putExtra(Constants.TITLE,title);
        intent.putExtra(Constants.TYPE,getIntent().getStringExtra(Constants.TYPE));
        intent.putExtra(Constants.AREA_LIST,list);
        startActivityForResult(intent,Constants.REQUEST_AREA_CODE);
    }

    @Override
    public void onBack(KeyValue keyValue) {
        Intent intent = new Intent();
        intent.putExtra(Constants.KEY_VALUE,keyValue);
        setResult(Constants.RESPONSE_AREA_CODE,intent);
        finish();
    }
}
