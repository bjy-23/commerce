package com.wondersgroup.commerce.teamwork.dailycheck;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;
import com.squareup.okhttp.ResponseBody;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.service.ApiManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class UnlicensedAddFragment extends Fragment {

    private View view;
    private AppCompatActivity activity;
    private RootAppcation application;
    private Gson gson = new Gson();
    private List<EtpsBean> etpsBeans = new ArrayList<EtpsBean>();
    private ListView listView;
    private String recordId = "";
    private TotalLoginBean loginBean;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        loginBean = Hawk.get(Constants.LOGIN_BEAN);
        view = inflater.inflate(R.layout.mode_addandlist, null);
        activity = (AppCompatActivity) getActivity();
        application = (RootAppcation) activity.getApplication();
        initSelfList();
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initSelfList() {
        HashMap<String, String> param = new HashMap<>();
        param.put("submitUser", loginBean.getResult().getUserId());
        param.put("checkType", "4");
        param.put("organId", loginBean.getResult().getOrganId());
        param.put("tmpFlag", "1");
        Call<EtpsFirstBean> call = ApiManager.consumerwApi.searchList(param);
        call.enqueue(new Callback<EtpsFirstBean>() {
            @Override
            public void onResponse(Response<EtpsFirstBean> response, Retrofit retrofit) {
                EtpsFirstBean firstSelfBean = response.body();

                if (firstSelfBean != null && firstSelfBean.getCode() == 200) {
                    etpsBeans = firstSelfBean.getResult();
                    EtpsAdapter adapter = new EtpsAdapter(getActivity(),
                            R.layout.mode_list_item1, etpsBeans);
                    listView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        listView = (ListView) view.findViewById(R.id.demo_list);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                recordId = etpsBeans.get(position).getRecordId();
                Call<ResponseBody> call = ApiManager.consumerwApi.getRecordInfo(loginBean.getResult().getDeptId(), recordId);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {

                        InfoFirstBean infoFirstBean = null;
                        try {
                            infoFirstBean = gson.fromJson(response.body().string(), InfoFirstBean.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if (infoFirstBean != null && infoFirstBean.getCode() == 200) {
                            application.setRecordId(recordId);
                            UtilForFragment.switchContentWithStack(activity,
                                    new UnlicensedTempFragment(), R.id.content);
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            activity.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
