package com.wondersgroup.commerce.teamwork.myxqzgdq;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import java.util.HashMap;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Administrator on 2016/4/11 0011.
 */
public class CompanyListFragment extends Fragment {
    public static int pageCompanyNo = 1;
    private View view;
    private CompanyPersonBean companyBean;

    private ListView listView;
    private PullToRefreshLayout pullToRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_xqzgdq, null);


        pullToRefreshLayout = (PullToRefreshLayout) view.findViewById(R.id.refresh_view);
        pullToRefreshLayout.setOnRefreshListener(new MyListener());

        listView = (ListView) view.findViewById(R.id.content_view);

        initData();

        return view;
    }


    private void initData() {

//        final SweetAlertDialog dialog = LoadingDialog.showNotCancelable(getActivity());
        MyProgressDialog.show(getActivity());

        pageCompanyNo = 1;
        Map<String, String> map = new HashMap<String, String>();
        map.put("pageNo", pageCompanyNo + "");

        Call<CompanyPersonBean> call = ApiManager.shApi.queryXQZGDQCompany(map);
        call.enqueue(new Callback<CompanyPersonBean>() {
            @Override
            public void onResponse(Response<CompanyPersonBean> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    companyBean = response.body();

                    XqzgdqCompanyAdapter adapter = new XqzgdqCompanyAdapter(companyBean, getActivity());
                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            Toast.makeText(getActivity(),"click on"+position,Toast.LENGTH_SHORT).show();

                            XqzgdqActivity.setCurrTab(0);

                            Intent intent = new Intent(getActivity(), DetailCompanyActivity.class);
                            intent.putExtra("entityId", companyBean.getValues().getResult().get(position).getEntityId());
                            startActivity(intent);

                        }
                    });

                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
                MyProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                MyProgressDialog.dismiss();
                Toast.makeText(getActivity(), getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class MyListener implements PullToRefreshLayout.OnRefreshListener {

        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            // 下拉刷新操作
            Toast.makeText(getActivity(), "刷新数据", Toast.LENGTH_SHORT).show();
            initData();
            // 千万别忘了告诉控件刷新完毕了哦！
            pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);

        }

        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
            // 加载操作
            Toast.makeText(getActivity(), "加载数据", Toast.LENGTH_SHORT).show();


            MyProgressDialog.show(getActivity());


            Map<String, String> map = new HashMap<String, String>();
            map.put("pageNo", ++pageCompanyNo + "");

            Call<CompanyPersonBean> call = ApiManager.shApi.queryXQZGDQCompany(map);
            call.enqueue(new Callback<CompanyPersonBean>() {
                @Override
                public void onResponse(Response<CompanyPersonBean> response, Retrofit retrofit) {
                    if (response.isSuccess()) {
                        CompanyPersonBean dataMore = response.body();


                        int len = dataMore.getValues().getResult().size();
                        for (int i = 0; i < len; i++) {
                            companyBean.getValues().getResult().add(dataMore.getValues().getResult().get(i));
                        }
                        XqzgdqCompanyAdapter adapter = new XqzgdqCompanyAdapter(companyBean, getActivity());
                        listView.setAdapter(adapter);

                    } else {
                        Toast.makeText(getActivity(), getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                    }
                    MyProgressDialog.dismiss();
                }

                @Override
                public void onFailure(Throwable t) {
                    MyProgressDialog.dismiss();
                    Toast.makeText(getActivity(), getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
                }
            });

            // 千万别忘了告诉控件加载完毕了哦！
            pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);


        }
    }
}
