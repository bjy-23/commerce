package com.wondersgroup.commerce.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.adapter.RecyclerAdapter;
import com.wondersgroup.commerce.model.MsgItem;
import com.wondersgroup.commerce.model.ReqResult;
import com.wondersgroup.commerce.model.TodoMessageBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.widget.PromptDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by yclli on 2015/11/26.
 */
public class MessageActivity extends AppCompatActivity implements View.OnClickListener,SwipeRefreshLayout.OnRefreshListener,RecyclerAdapter.OnItemTouchListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_txt) TextView title;
    @BindView(R.id.toolbar_btn) Button button;
    @BindView(R.id.toolbar_btn2) Button btn;
    @BindView(R.id.msg_swipe)
    SwipeRefreshLayout swipe;
    @BindView(R.id.msg_recycler)RecyclerView recyclerView;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter adapter;
    private List<MsgItem> msgItems = new ArrayList<MsgItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        title.setText("待办提醒");
        toolbar.setTitle("");
        button.setVisibility(View.GONE);
        btn.setVisibility(View.VISIBLE);
        btn.setText("全部标记已读    ");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);

        swipe.setOnRefreshListener(this);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        /*ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT ) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                msgItems.remove(viewHolder.getAdapterPosition());
                recyclerView.getAdapter().notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);*/

        btn.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
        adapter = new RecyclerAdapter(this,msgItems);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemTouchListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btn){
            /*Intent intent=new Intent(MessageActivity.this,MsgManageActivity.class);
            MsgItem.msgItems = msgItems;
            startActivity(intent);*/
            if(msgItems.size() == 0){
                return;
            }
            final PromptDialog dialog = new PromptDialog(MessageActivity.this);
            dialog.setTitle("全标已读")
                    .setContent("将所有待办都标记已读？")
                    .setPositiveBtn("全部标为已读", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String todoMesssageIds = "";
                            for (int i = 0; i < msgItems.size(); i++) {
                                todoMesssageIds += msgItems.get(i).getUuId() + ",";
                            }
                            todoMesssageIds = todoMesssageIds.substring(0, todoMesssageIds.length() - 1);
                            Map<String, String> map = new HashMap<String, String>();
                            map.put("wsCodeReq", "00000005");
                            map.put("todoMesssageIds", todoMesssageIds);
                            Call<ReqResult> call = ApiManager.hbApi.apiTodoMsgDoned(map);
                            call.enqueue(new Callback<ReqResult>() {
                                @Override
                                public void onResponse(Response<ReqResult> response, Retrofit retrofit) {
                                    if ("0".equals(response.body().getResult())) {
                                        Toast.makeText(MessageActivity.this, "标记成功", Toast.LENGTH_SHORT).show();
                                        msgItems.clear();
                                        adapter.notifyDataSetChanged();
                                    } else {
                                        Toast.makeText(MessageActivity.this, "标记失败", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Throwable t) {
                                    Toast.makeText(MessageActivity.this, "网络错误！", Toast.LENGTH_SHORT).show();
                                }
                            });
                            dialog.dismiss();
                        }
                    })
                    .setNegativeBtn("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    }).show();
        }
    }

    @Override
    public void onRefresh() {
        getData();
        swipe.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(false);
            }
        }, 600);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void getData(){
        msgItems.clear();
        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "00000004");
        map.put("userId", "a0a0e39441ad45050141b068ab9803a8");
        map.put("deptId", "13000000001");
        map.put("organId", "130000000");
//        map.put("userId", (String) dataShared.get("userId", ""));
//        map.put("deptId", (String) dataShared.get("deptId", ""));
//        map.put("organId", (String) dataShared.get("organId", ""));

        Call<TodoMessageBean> call = ApiManager.hbApi.apiTodoMsgList(map);
        call.enqueue(new Callback<TodoMessageBean>() {
            @Override
            public void onResponse(Response<TodoMessageBean> todoMessageBean, Retrofit retrofit) {
                if (todoMessageBean.body().getResult() != null) {
                    int count = Integer.parseInt(todoMessageBean.body().getResult().getCount());
                    for (int i = 0; i < count; i++) {
                        TodoMessageBean.TodoMessageInfo todoMessageInfo =
                                todoMessageBean.body().getResult().getTodoMessageInfo().get(i);
                        MsgItem msg = new MsgItem();
                        msg.setAppName("功能模块:  " + todoMessageInfo.getAppName());
                        msg.setDate("提交时间:  " + todoMessageInfo.getCreateDate());
                        msg.setFlowName("流程名称:  " + todoMessageInfo.getFlowName());
                        msg.setTitle(todoMessageInfo.getTitle());
                        msg.setUser("提  交  人:  " + todoMessageInfo.getCreateUser());
                        msg.setUuId(todoMessageInfo.getUuid());
                        msgItems.add(msg);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(MessageActivity.this, "网络错误！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemTouchListener(final int position, View view) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "00000005");
        map.put("todoMesssageIds", msgItems.get(position).getUuId());
        Call<ReqResult> call = ApiManager.hbApi.apiTodoMsgDoned(map);
        call.enqueue(new Callback<ReqResult>() {
            @Override
            public void onResponse(Response<ReqResult> response, Retrofit retrofit) {
                if ("0".equals(response.body().getResult())) {
                    Toast.makeText(MessageActivity.this, "标记成功", Toast.LENGTH_SHORT).show();
                    msgItems.remove(position);
                    adapter.notifyItemRemoved(position);
                    adapter.notifyItemRangeChanged(position, adapter.getItemCount());

                } else {
                    Toast.makeText(MessageActivity.this, "标记失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(MessageActivity.this, "网络错误！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
