package com.wondersgroup.yngs.activity;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.orhanobut.hawk.Hawk;
import com.wondersgroup.yngs.R;
import com.wondersgroup.yngs.adapter.ToDoAdapter;
import com.wondersgroup.yngs.entity.DicItem;
import com.wondersgroup.yngs.entity.DicResult;
import com.wondersgroup.yngs.entity.DicT;
import com.wondersgroup.yngs.entity.ToDoItem;
import com.wondersgroup.yngs.entity.ToDoResult;
import com.wondersgroup.yngs.service.ApiManager;
import com.wondersgroup.yngs.utils.EndlessRecyclerViewScrollListener;
import com.wondersgroup.yngs.widget.CountBar;
import com.wondersgroup.yngs.widget.FilterSheet;

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
    @Bind(R.id.net_area)RelativeLayout netArea;
    @Bind(R.id.yushen_recycler)RecyclerView recycler;
    @Bind(R.id.net_msg)TextView netMsg;

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
        DicT dic= Hawk.get("Dic");
        if(dic!=null){
            typeDic.addAll(dic.getDicAppType());
        }else {
            SharedPreferences sp=getSharedPreferences("Default", Context.MODE_PRIVATE);
            Map<String,String> body =new HashMap<>();
            int selection=sp.getInt("selection",0);
            body.put("deptId",sp.getString("deptId","").split(",")[selection]);
            body.put("organId",sp.getString("organId","").split(",")[selection]);
            body.put("wsCodeReq",ApiManager.getWsCodeReq());
            Call<DicResult> call = ApiManager.yunNanApi.getDic(body);
            call.enqueue(new Callback<DicResult>() {
                @Override
                public void onResponse(Response<DicResult> response, Retrofit retrofit) {
                    if(response.isSuccess()&&response.body()!=null){
                        DicResult result=response.body();
                        if("200".equals(result.getResultCode())){
                            Hawk.put("Dic",result.getResult());
                            typeDic.addAll(result.getResult().getDicAppType());
                        }else {
                            new MaterialDialog.Builder(ToDoActivity.this)
                                    .content("获取申请类型失败")
                                    .show();
                        }
                    }else {
                        new MaterialDialog.Builder(ToDoActivity.this)
                                .content("获取申请类型失败")
                                .show();
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
        body.put("wsCodeReq",ApiManager.getWsCodeReq());
        Call<ToDoResult> call= ApiManager.yunNanApi.getToDoList(body);
        call.enqueue(new Callback<ToDoResult>() {
            @Override
            public void onResponse(Response<ToDoResult> response, Retrofit retrofit) {
                if (response.isSuccess()&&response.body()!=null) {
                    ToDoResult result = response.body();
                    if ("200".equals(result.getResultCode())) {
                        netArea.setVisibility(View.GONE);
                        items.addAll(result.getResult());
                        adapter.notifyItemRangeInserted(0, items.size() - 1);
                        setTotal(result.getTotalRecord());
                    } else if ("403".equals(result.getResultCode())) {
                        loginFailed();
                    } else {
                        netArea.setVisibility(View.VISIBLE);
                        netMsg.setText(result.getMessage());
                    }
                } else {
                    netArea.setVisibility(View.VISIBLE);
                    netMsg.setText("加载失败");
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
        Call<ToDoResult> call = ApiManager.yunNanApi.getToDoList(body);
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
                        loginFailed();
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
    private void loginFailed() {
        new MaterialDialog.Builder(ToDoActivity.this)
                .title("登录过期")
                .content("请重新登录")
                .positiveText("确定")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        SharedPreferences sp = getSharedPreferences("Default", Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed = sp.edit();
                        ed.clear();
                        ed.apply();
                        startActivity(new Intent(ToDoActivity.this, LoginActivity.class));
                        finish();
                    }
                })
                .cancelable(false)
                .show();
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
            netArea.setVisibility(View.VISIBLE);
            netMsg.setText("加载中...");
            Call<ToDoResult> call=ApiManager.yunNanApi.getToDoList(body);
            call.enqueue(new Callback<ToDoResult>() {
                @Override
                public void onResponse(Response<ToDoResult> response, Retrofit retrofit) {
                    if(response.isSuccess()&&response.body()!=null){
                        ToDoResult result=response.body();
                        if("200".equals(result.getResultCode())){

                            if(result.getResult().size()!=0) {
                                netArea.setVisibility(View.GONE);
                                items.addAll(result.getResult());
                                adapter.notifyItemRangeInserted(0, items.size());
                            }
                        }else {
                            netArea.setVisibility(View.VISIBLE);
                            netMsg.setText(result.getMessage());
                        }
                    }else {
                        netArea.setVisibility(View.VISIBLE);
                        netMsg.setText("与服务器连接失败");
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    netArea.setVisibility(View.VISIBLE);
                    netMsg.setText("连接超时");
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
        netArea.setVisibility(View.VISIBLE);
        netMsg.setText("加载中...");
        Call<ToDoResult> call=ApiManager.yunNanApi.getToDoList(body);
        call.enqueue(new Callback<ToDoResult>() {
            @Override
            public void onResponse(Response<ToDoResult> response, Retrofit retrofit) {
                if (response.isSuccess()&&response.body()!=null) {
                    final ToDoResult result = response.body();
                    if ("200".equals(result.getResultCode())) {

                        if(result.getResult().size()!=0){
                            netArea.setVisibility(View.GONE);
                            items.addAll(result.getResult());
                            adapter.notifyItemRangeInserted(0, items.size());
                        } else {
                            netArea.setVisibility(View.VISIBLE);
                            netMsg.setText(result.getMessage());
                        }
                    }else {
                        netArea.setVisibility(View.VISIBLE);
                        netMsg.setText(result.getMessage());
                    }
                }else {
                    netArea.setVisibility(View.VISIBLE);
                    netMsg.setText("与服务器连接失败");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                netArea.setVisibility(View.VISIBLE);
                netMsg.setText("连接超时");
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
