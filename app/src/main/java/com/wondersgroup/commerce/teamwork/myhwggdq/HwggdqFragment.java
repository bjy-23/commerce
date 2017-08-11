package com.wondersgroup.commerce.teamwork.myhwggdq;

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
import com.wondersgroup.commerce.teamwork.myxqzgdq.PullToRefreshLayout;
import com.wondersgroup.commerce.widget.MyProgressDialog;

import java.util.HashMap;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by jiajiangyi on 2016/4/26 0026.
 */
public class HwggdqFragment extends Fragment {

    public static int pageNo = 1;

    private ListView listView;
    private PullToRefreshLayout pullToRefreshLayout;
    private HwggdqBean hwggdqBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hwggdq, null);

        pullToRefreshLayout = (PullToRefreshLayout) view.findViewById(R.id.refresh_view);
        pullToRefreshLayout.setOnRefreshListener(new MyListener());

        listView = (ListView) view.findViewById(R.id.content_view);

        initData();
        return view;
    }

    private void initData() {

        MyProgressDialog.show(getActivity());

        pageNo = 1;
        Map<String, String> map = new HashMap<String, String>();
        map.put("pageNo", pageNo + "");

        Call<HwggdqBean> call = ApiManager.shApi.queryHWGGDQExpire(map);
        call.enqueue(new Callback<HwggdqBean>() {
            @Override
            public void onResponse(Response<HwggdqBean> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    hwggdqBean = response.body();

//                    Toast.makeText(getActivity(), "haha" + hwggdqBean.getMsg(), Toast.LENGTH_SHORT).show();

                    HWGGDQAdapter adapter = new HWGGDQAdapter(hwggdqBean, getActivity());
                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            Toast.makeText(getActivity(),"click on"+position,Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), HwggdqDetailActivity.class);
                            intent.putExtra("realNo", hwggdqBean.getValues().getResult().get(position).getRealNo());
                            startActivity(intent);

                        }
                    });

                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
                MyProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                MyProgressDialog.dismiss();
                Toast.makeText(getActivity(), getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
                getActivity().finish();
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
            map.put("pageNo", ++pageNo + "");

            Call<HwggdqBean> call = ApiManager.shApi.queryHWGGDQExpire(map);
            call.enqueue(new Callback<HwggdqBean>() {
                @Override
                public void onResponse(Response<HwggdqBean> response, Retrofit retrofit) {
                    if (response.isSuccess()) {
                        HwggdqBean dataMore = response.body();


                        int len = dataMore.getValues().getResult().size();
                        for (int i = 0; i < len; i++) {
                            hwggdqBean.getValues().getResult().add(dataMore.getValues().getResult().get(i));
                        }
                        HWGGDQAdapter adapter = new HWGGDQAdapter(hwggdqBean, getActivity());
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
