package com.wondersgroup.commerce.ynwq.activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.model.ccjc.DicItem;

import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.utils.EndlessRecyclerViewScrollListener;
import com.wondersgroup.commerce.ynwq.adapter.ToDoAdapter;
import com.wondersgroup.commerce.ynwq.bean.DicResult;
import com.wondersgroup.commerce.ynwq.bean.DicT;
import com.wondersgroup.commerce.ynwq.bean.ToDoItem;
import com.wondersgroup.commerce.ynwq.bean.ToDoResult;
import com.wondersgroup.commerce.ynwq.widget.CountBar;
import com.wondersgroup.commerce.ynwq.widget.FilterSheet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ToDoActivity extends AppCompatActivity {
    @Bind(R.id.mid_toolbar)Toolbar toolbar;
    @Bind(R.id.toolbar_title)TextView title;
    @Bind(R.id.yushen_recycler)RecyclerView recycler;
    @Bind(R.id.view_error) View viewError;
    @Bind(R.id.tv_error) TextView tvError;
    EditText searchText;
    MenuItem searchItem;
    MenuItem filterItem;
    View searchBar;
    FilterSheet sheetDialog;

    private ToDoAdapter adapter;
    private List<ToDoItem> items;
    private Map<String,String> body;
    private ArrayList<DicItem> typeDic;
    private static String startDate="";
    private static String endDate="";
    private static int tabSelection=0;

    private int total=0;
    private CountBar countBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        searchBar= LayoutInflater.from(this).inflate(R.layout.view_toolbar_search, null);
        searchText=(EditText)searchBar.findViewById(R.id.searh_text);

        typeDic=new ArrayList<>();
        DicT dic= Hawk.get("Dic2");
        if(dic!=null){
            typeDic.addAll(dic.getDicAppType());
        }else {
            Map<String,String> body =new HashMap<>();
            TotalLoginBean loginBean = Hawk.get(Constants.LOGIN_BEAN);
            body.put("deptId",loginBean.getResult().getDeptId());
            body.put("organId",loginBean.getResult().getOrganId());
            body.put("wsCodeReq","01100001");
            Call<DicResult> call = ApiManager.ynWqApi.getDic(body);
            call.enqueue(new Callback<DicResult>() {
                @Override
                public void onResponse(Response<DicResult> response, Retrofit retrofit) {
                    if(response.isSuccess()&&response.body()!=null){
                        DicResult result=response.body();
                        if("200".equals(result.getResultCode())){
                            Hawk.put("Dic2",result.getResult());
                            typeDic.addAll(result.getResult().getDicAppType());
                        }
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }
        title.setText(getIntent().getStringExtra("title"));
        //recycler.setHasFixedSize(true);
        final LinearLayoutManager manager=new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);
        recycler.setItemAnimator(new SlideInUpAnimator());
        items=new ArrayList<>();
        adapter=new ToDoAdapter(items);
        adapter.setOnItemClickListener(new ToDoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(ToDoActivity.this, ViewPagerActivity.class);
                intent.putExtra("type", "DBXW");
                intent.putExtra("meId", items.get(position).getMeId());
                intent.putExtra("etpsId", items.get(position).getEtpsId());
                intent.putExtra("entName", items.get(position).getTitle());
                intent.putExtra("XCKCType",XCKCType());
//                intent.putExtra("XCKCType","2");
                startActivity(intent);
            }
        });
        recycler.setAdapter(adapter);

        init();
        recycler.addOnScrollListener(new EndlessRecyclerViewScrollListener(manager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                fetchData(page);
            }
        });

        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(countBar!=null) {
                    if (dy > 0) {
                        //if(!countBar.isVisible()&&!countBar.isAdded())countBar.show(getSupportFragmentManager(),"CountBar");
                        if (countBar.getDialog() != null) countBar.getDialog().show();
                        if (countBar.isVisible()) {
                            countBar.setCur(manager.findLastCompletelyVisibleItemPosition() + 1);
                            if (total == 0)
                                countBar.setTotal(recyclerView.getAdapter().getItemCount());
                            else countBar.setTotal(total);
                        }
                    } else {
                        if (countBar.isVisible()) countBar.getDialog().hide();
                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if(newState==RecyclerView.SCROLL_STATE_IDLE){
                    if(countBar.isVisible())countBar.getDialog().hide();
                }else if(newState==RecyclerView.SCROLL_STATE_DRAGGING){
                    synchronized (this) {
                        if (countBar == null) {
                            if(recyclerView.getAdapter()!=null) {
                                countBar = CountBar.newInstance(total == 0 ? recyclerView.getAdapter().getItemCount() : total);
                                countBar.show(getSupportFragmentManager(), "CountBar");
                            }
                        }
                    }
                }
            }
        });
    }
    public void init(){
        body=(HashMap<String,String>)getIntent().getSerializableExtra("body");
        body.put("pageNo", "0");
        body.put("wsCodeReq","01100001");
        Call<ToDoResult> call= ApiManager.ynWqApi.getToDoList(body);
        call.enqueue(new Callback<ToDoResult>() {
            @Override
            public void onResponse(Response<ToDoResult> response, Retrofit retrofit) {
                if (response.isSuccess()&&response.body()!=null) {
                    ToDoResult result = response.body();
                    if ("200".equals(result.getResultCode())) {
                        items.addAll(result.getResult());
                        adapter.notifyItemRangeInserted(0, items.size() - 1);
                        setTotal(result.getTotalRecord());
                    } else {
                        viewError.setVisibility(View.VISIBLE);
                        tvError.setText(Constants.NO_DB);
                    }
                } else {
                    viewError.setVisibility(View.VISIBLE);
                    tvError.setText(Constants.NO_DB);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        /*items=OfflineData.toDoItems;
        adapter=new ToDoAdapter(items);
        adapter.setOnItemClickListener(new ToDoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(ToDoActivity.this,ViewPagerActivity.class);
                intent.putExtra("type","DBXW");
                startActivity(intent);
            }
        });
        recycler.setAdapter(adapter);*/
    }
    public void fetchData(int page) {
        body.put("pageNo", "" + page);
        Call<ToDoResult> call = ApiManager.ynWqApi.getToDoList(body);
        call.enqueue(new Callback<ToDoResult>() {
            @Override
            public void onResponse(Response<ToDoResult> response, Retrofit retrofit) {
                if (response.isSuccess()&&response.body()!=null) {
                    ToDoResult result = response.body();
                    if ("200".equals(result.getResultCode())) {
                        int curSize = adapter.getItemCount();
                        items.addAll(result.getResult());
                        adapter.notifyItemRangeInserted(curSize, items.size() -1);
                    }else if("403".equals(result.getResultCode())) {

                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private String XCKCType(){
        String flowStatus;
        if(body!=null)flowStatus=body.get("flowStatus");
        else return "3";
        if("0103".equals(flowStatus)||"0203".equals(flowStatus)) return "1";
        else if("0105".equals(flowStatus)||"0205".equals(flowStatus)) return "2";
        else return "3";
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(countBar!=null&&countBar.isVisible()){
            countBar.getDialog().hide();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_yushen,menu);
        searchItem=menu.findItem(R.id.action_search);
        filterItem=menu.findItem(R.id.action_filter);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        if(!filterItem.isVisible()){
            toolbar.removeView(searchBar);
            title.setVisibility(View.VISIBLE);
            searchItem.setIcon(R.drawable.app_search);
            filterItem.setVisible(true);
            return true;
        }
        onBackPressed();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_search){
            if(filterItem.isVisible()) {
                title.setVisibility(View.GONE);
                toolbar.addView(searchBar);
                searchItem.setIcon(null);
                filterItem.setVisible(false);
                return true;
            }else {
                search();
            }
        }else if(id==R.id.action_filter){
            int[] cords=new int[2];
            toolbar.getLocationOnScreen(cords);
            sheetDialog=FilterSheet.newInstance(cords[1]+toolbar.getHeight(),typeDic,startDate,endDate,tabSelection);
            sheetDialog.setOnFilterListener(new FilterSheet.OnFilterListener() {
                @Override
                public void OnFiltered(String type, String startDate, String endDate,int tabSelection) {
                    //Toast.makeText(ToDoActivity.this,type + " " + startDate + "-" + endDate, Toast.LENGTH_SHORT).show();

                    filter(type, startDate, endDate, tabSelection);
                }
            });
            sheetDialog.show(getSupportFragmentManager(), "filterSheet");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void search(){
        if(!searchText.getText().toString().isEmpty()){
            body.put("etpsNameOrRegNo",searchText.getText().toString());
            body.put("pageNo",""+0);
            int curSize=adapter.getItemCount();
            items.clear();
            adapter.notifyItemRangeRemoved(0, curSize);
            Call<ToDoResult> call=ApiManager.ynWqApi.getToDoList(body);
            call.enqueue(new Callback<ToDoResult>() {
                @Override
                public void onResponse(Response<ToDoResult> response, Retrofit retrofit) {
                    if(response.isSuccess()&&response.body()!=null){
                        ToDoResult result=response.body();
                        if("200".equals(result.getResultCode())){

                            if(result.getResult().size()!=0) {
                                items.addAll(result.getResult());
                                adapter.notifyItemRangeInserted(0, items.size());
                            }
                        }else {
                            viewError.setVisibility(View.VISIBLE);
                            tvError.setText(Constants.NO_DB);
                        }
                    }else {
                        viewError.setVisibility(View.VISIBLE);
                        tvError.setText(Constants.NO_DB);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    viewError.setVisibility(View.VISIBLE);
                    tvError.setText(Constants.NO_DB);
                }
            });
        }
    }
    private void filter(String type,String startDate,String endDate,int tabSelection){
        if(type!=null)body.put("type",type);
        else body.remove("type");
        if(startDate!=null) {
            body.put("startDate", startDate);
            this.startDate=startDate;
        }else {
            body.remove("startDate");
            this.startDate="";
        }
        if(endDate!=null) {
            body.put("endDate", endDate);
            this.endDate=endDate;
        }else {
            body.remove("endDate");
            this.endDate="";
        }
        this.tabSelection=tabSelection;
        body.put("pageNo",""+0);
        int curSize = adapter.getItemCount();
        items.clear();
        adapter.notifyItemRangeRemoved(0, curSize);
        Call<ToDoResult> call=ApiManager.ynWqApi.getToDoList(body);
        call.enqueue(new Callback<ToDoResult>() {
            @Override
            public void onResponse(Response<ToDoResult> response, Retrofit retrofit) {
                if (response.isSuccess()&&response.body()!=null) {
                    final ToDoResult result = response.body();
                    if ("200".equals(result.getResultCode())) {

                        if(result.getResult().size()!=0){
                            items.addAll(result.getResult());
                            adapter.notifyItemRangeInserted(0, items.size());
                        } else {
                            viewError.setVisibility(View.VISIBLE);
                            tvError.setText(Constants.NO_DB);
                        }
                    }else {
                        viewError.setVisibility(View.VISIBLE);
                        tvError.setText(Constants.NO_DB);
                    }
                }else {
                    viewError.setVisibility(View.VISIBLE);
                    tvError.setText(Constants.NO_DB);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                viewError.setVisibility(View.VISIBLE);
                tvError.setText(Constants.NO_DB);
            }
        });
    }

    public void setTotal(String totalRecord){
        try {
            total=Integer.parseInt(totalRecord);
        }catch (Exception e){

        }
    }
}
