package com.wondersgroup.commerce.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.adapter.FirstPageAdapter;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.CaseInvestigateListBean;
import com.wondersgroup.commerce.model.MenuBean;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.model.ccjc.CCToDoResult;
import com.wondersgroup.commerce.model.yn.ToDoBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.service.CaseApi;
import com.wondersgroup.commerce.ynwq.bean.ToDoResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by bjy on 2017/6/20.
 */

public class FragmentFirstPage extends Fragment {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private FirstPageAdapter adapter;
    private List<MenuBean> data;

    private TotalLoginBean loginBean;
    private int numTS,numJB;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_page,container,false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginBean = Hawk.get(Constants.LOGIN_BEAN);
        data = Hawk.get(Constants.FIRST_PAGE_MENU);
        if (data == null)
            data = new ArrayList<>();

        adapter = new FirstPageAdapter(getActivity(),data,1);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2, LinearLayoutManager.VERTICAL,false));

        Map<String, String> map;
        for (int i=0;i<data.size();i++ ){
            MenuBean menuBean = data.get(i);
            final int index = i;
            switch (menuBean.getMenuId()){
                case Constants.TSJBCL_ID:
                    Call<ToDoBean> callTS = ApiManager.ynApi.getTodo("1", loginBean.getResult().getUserId(), loginBean.getResult().getOrganId()
                            , loginBean.getResult().getDeptId(), "1", "25");
                    callTS.enqueue(new Callback<ToDoBean>() {
                        @Override
                        public void onResponse(Response<ToDoBean> response, Retrofit retrofit) {
                            if (response.isSuccess() && response.body() != null) {
                                ToDoBean result = response.body();
                                if (200 == result.getCode() && result.getResult()!= null) {
                                    numTS = Integer.parseInt(result.getResult().getTotalRecord());
                                    data.get(index).setNumber(numTS + numJB + "");
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {

                        }
                    });

                    Call<ToDoBean> callJB = ApiManager.ynApi.getTodo("2", loginBean.getResult().getUserId(), loginBean.getResult().getOrganId()
                            , loginBean.getResult().getDeptId(), "1", "25");
                    callJB.enqueue(new Callback<ToDoBean>() {
                        @Override
                        public void onResponse(Response<ToDoBean> response, Retrofit retrofit) {
                            if (response.isSuccess() && response.body() != null) {
                                ToDoBean result = response.body();
                                if (200 == result.getCode() && result.getResult()!= null) {
                                    numJB = Integer.parseInt(result.getResult().getTotalRecord());
                                    data.get(index).setNumber(numTS+numJB+"");
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {

                        }
                    });
                    break;
                case Constants.AJDC_ID:
                    map = new HashMap<String, String>();
                    map.put("userId", loginBean.getResult().getUserId());
                    map.put("currentPage", "1");
                    map.put("wsCodeReq", "03010002");
                    String url = CaseApi.URL_CASE_1 + CaseApi.INVESTIGATE_CASE_LIST;
                    Call<CaseInvestigateListBean> call_2 = ApiManager.caseApi.getMyCaseList(url,map);
                    call_2.enqueue(new Callback<CaseInvestigateListBean>() {
                        @Override
                        public void onResponse(Response<CaseInvestigateListBean> response, Retrofit retrofit) {
                            CaseInvestigateListBean bean = response.body();
                            if (bean!=null){
                                data.get(index).setNumber(String.valueOf(bean.getResult().getTotalRecord()));
                                adapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {

                        }
                    });
                    break;
                case Constants.CCJCLR_ID:
                    map = new HashMap<>();
                    map.put("wsCodeReq","000201");
                    map.put("pageNo","0");
                    map.put("userId",loginBean.getResult().getUserId());
                    map.put("organId",loginBean.getResult().getOrganId().substring(0,6));
                    Call<CCToDoResult> call_3 = ApiManager.ccjcApi.ccTodo(map);
                    call_3.enqueue(new Callback<CCToDoResult>() {
                        @Override
                        public void onResponse(Response<CCToDoResult> response, Retrofit retrofit) {
                            if (response.isSuccess() && response.body()!=null && response.body().getResult() != null) {
                                data.get(index).setNumber(response.body().getTotalRecord()+"");
                                adapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {

                        }
                    });
                    break;
                case Constants.WQCB_ID:
                    map = new HashMap<>();
                    map.put("wsCodeReq","01100001");
                    map.put("deptId",loginBean.getResult().getDeptId());
                    map.put("pageNo","0");
                    map.put("organId",loginBean.getResult().getOrganId());
                    map.put("userId",loginBean.getResult().getUserId());
                    map.put("flowStatus","0103");
                    Call<ToDoResult> call_4= ApiManager.ynWqApi.getToDoList(map);
                    call_4.enqueue(new Callback<ToDoResult>() {
                        @Override
                        public void onResponse(Response<ToDoResult> response, Retrofit retrofit) {
                            if (response.isSuccess()&&response.body()!=null) {
                                data.get(index).setNumber(response.body().getTotalRecord());
                                adapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {

                        }
                    });
                    break;
            }
        }
    }
}
