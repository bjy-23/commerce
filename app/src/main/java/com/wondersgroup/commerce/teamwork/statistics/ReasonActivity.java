package com.wondersgroup.commerce.teamwork.statistics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.teamwork.statistics.bean.In;
import com.wondersgroup.commerce.teamwork.statistics.bean.Reason;
import com.wondersgroup.commerce.teamwork.statistics.constant.ConstantIn;
import com.wondersgroup.commerce.teamwork.statistics.constant.ConstantOut;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import java.lang.reflect.Type;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * 移入移出原因
 */
public class ReasonActivity extends AppCompatActivity {
    @Bind(R.id.mid_toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_title)
    TextView title;
    @Bind(R.id.recycler)
    RecyclerView recyclerView;
    @Bind(R.id.reason)
    TextView reason;
    private HashMap<String, String> params = new HashMap<>();
    private ReasonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reason);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
        title.setText("列入移出");
        initView();
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        Bundle bundle = getIntent().getExtras();
        if (null != bundle.getSerializable("PARAMS")) {
            params = (HashMap<String, String>) bundle.getSerializable("PARAMS");
        }
        initData();
    }

    /**
     * 形如TOTAL_QI_IN_D_COUNT
     *
     * @return
     */
    private Map<Integer, String> getReasonMap() {
        Map<Integer, String> reasonMap = new HashMap<>();
        String statType = params.get("statType");
        if (!TextUtils.isEmpty(statType)) {
            if (statType.contains("IN")) {
                reason.setText("列入原因");
            } else {
                reason.setText("移出原因");
            }
            String[] v = statType.split("_");
            if (v[0].equals("TOTAL")) {
                if (v[1].equals("QY")) {
                    if (v[2].equals("IN")) {
                        reasonMap = ConstantIn.QY();
                    } else {
                        reasonMap = ConstantOut.QY();
                    }
                }
                if (v[1].equals("NM")) {
                    if (v[2].equals("IN")) {
                        reasonMap = ConstantIn.NZ();
                    } else {
                        reasonMap = ConstantOut.NZ();
                    }
                }
                if (v[1].equals("GT")) {
                    if (v[2].equals("IN")) {
                        reasonMap = ConstantIn.GT();
                    } else {
                        reasonMap = ConstantOut.GT();
                    }
                }
            } else {
                if (v[0].equals("QY")) {
                    if (v[1].equals("IN")) {
                        reasonMap = ConstantIn.QY();
                    } else {
                        reasonMap = ConstantOut.QY();
                    }
                }
                if (v[0].equals("NM")) {
                    if (v[1].equals("IN")) {
                        reasonMap = ConstantIn.NZ();
                    } else {
                        reasonMap = ConstantOut.NZ();
                    }
                }
                if (v[0].equals("GT")) {
                    if (v[1].equals("IN")) {
                        reasonMap = ConstantIn.GT();
                    } else {
                        reasonMap = ConstantOut.GT();
                    }
                }
            }
        }
        return reasonMap;
    }

    private void initData() {
        MyProgressDialog.show(this);
        ApiManager.tjApi.getExceptReasonStatInfo(params).enqueue(new Callback<Reason>() {
            @Override
            public void onResponse(Response<Reason> response, Retrofit retrofit) {
                MyProgressDialog.dismiss();
                Reason result = response.body();
                if (result != null && result.getResult() != null) {
                    if (result.getCode() == 200) {
                        adapter = new ReasonAdapter(ReasonActivity.this, generateData(result.getResult()));
                        adapter.setReasonMap(getReasonMap());
                        recyclerView.setAdapter(adapter);
                    } else {
                        showMsg(getResources().getString(R.string.error_connect));
                    }
                } else {
                    showMsg(getResources().getString(R.string.error_connect));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                MyProgressDialog.dismiss();
                showMsg(getResources().getString(R.string.error_connect));
            }
        });
    }

    private List<Map.Entry<Integer, String>> generateData(JsonElement result) {
        List<Map.Entry<Integer, String>> data = new ArrayList<>();
        // 保持服务器json顺序
        java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<LinkedHashMap<String, Integer>>() {
        }.getType();
        LinkedHashMap ret = initGson().fromJson(result, type);
        Iterator ite = ret.keySet().iterator();
        while (ite.hasNext()) {
            String key = (String) ite.next();
            data.add(new AbstractMap.SimpleEntry(Integer.valueOf(key), "" + ret.get(key)));
        }
        return data;
    }

    private Gson initGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Double.class, new JsonSerializer<Double>() {
            public JsonElement serialize(Double src, Type typeOfSrc,
                                         JsonSerializationContext context) {
                if (src == src.longValue())
                    return new JsonPrimitive(src.longValue());
                return new JsonPrimitive(src);
            }
        });
        return gsonBuilder.create();
    }

    private void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
