package com.wondersgroup.commerce.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.panxw.android.imageindicator.ImageIndicatorView;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.RecyclerActivity;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.activity.TableListActivity;
import com.wondersgroup.commerce.activity.ViewPagerActivity;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.Ggcx;
import com.wondersgroup.commerce.model.GwNx;
import com.wondersgroup.commerce.model.MenuModel;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.teamwork.casedeal.CaseEnquireActivity;
import com.wondersgroup.commerce.teamwork.casedeal.CaseInvestigateActivity;
import com.wondersgroup.commerce.teamwork.tztg.GGDetailActivity;
import com.wondersgroup.commerce.teamwork.tztg.TZTGActivity;
import com.wondersgroup.commerce.utils.DataShared;
import com.wondersgroup.commerce.widget.ForScrollListView;
import com.wondersgroup.commerce.widget.MyProgressDialog;

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
 * Created by kangrenhui on 2016/1/19.
 */
public class FragmentHome extends RootFragment {
    @BindView(R.id.indicate_view)
    ImageIndicatorView imageIndicatorView;
    @BindView(R.id.layout_home_menu)
    LinearLayout homeMenuLayout;
    @BindView(R.id.fragment_ydbg_list_sw)
    ForScrollListView gwListView;
    @BindView(R.id.viewpager_tztg)
    ViewPager viewPagerTZTG;

    private RootAppcation rootAppcation;
    private Map<String, Integer> meduleMap;
    private GwNx gw;
    private View[] viewArray = new View[5];
    private List<View> mViewList = new ArrayList<>();
    ArrayList<TZTG_Model> tztgList = new ArrayList<TZTG_Model>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = View.inflate(getActivity(), R.layout.fragment_home, null);
        ButterKnife.bind(this, view);

        rootAppcation = (RootAppcation) getActivity().getApplicationContext();
        meduleMap = rootAppcation.getMeduleMap();
        for(int i=0; i<5; i++){
            viewArray[i] = inflater.inflate(R.layout.item_home_tztg, null);
            viewArray[i].setTag(i);
            mViewList.add(viewArray[i]);
        }

        imageIndicatorView.setBackgroundResource(R.mipmap.login_bg);
//        imageIndicatorView.setIndicateStyle(ImageIndicatorView.INDICATE_USERGUIDE_STYLE);
        imageIndicatorView.show();

        createMenu();

        //十条最近公文
           getGwData();
        gwListView.setAdapter(tenGwAdapter);
        gwListView.setFocusable(false);
        gwListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ViewPagerActivity.class);
                intent.putExtra("title", gw.getResult().getResult().get(position).getTitle());
                intent.putExtra("type", "GWJSXQ");
                Hawk.put("gwType", "1".equals(gw.getResult().getResult().get(position).getFlowType()) ? "发文管理" : "收文管理");
                Hawk.put("gwId", gw.getResult().getResult().get(position).getDocId());
                startActivity(intent);
            }
        });


        getTZTGData();

        return view;
    }

    private void getTZTGData() {

        final List<Map<String, Object>> datalist = new ArrayList<Map<String, Object>>();
        DataShared dataShared = new DataShared(mContext);
        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "07010001");
        map.put("userId", (String) dataShared.get("userId", ""));
        map.put("deptId", (String) dataShared.get("deptId", ""));
        map.put("organId", (String) dataShared.get("organId", ""));

        Log.d("getTZTGData", "initData()---------" + (String) dataShared.get("userId", "") + "------------map.toString()=" + map.toString());
        Call<Ggcx> call = ApiManager.oaApi.getBulletinList(map);
        Log.d("getTZTGData", "map.toString() = " + map.toString());

        call.enqueue(new Callback<Ggcx>() {
            @Override
            public void onResponse(Response<Ggcx> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    MyProgressDialog.dismiss();
                    Ggcx ggcx = response.body();
                    Log.d("getTZTGData", "TZTGActivity --------------- 1");
                    if ((null == ggcx) || (null == ggcx.getResult())) {
                        Toast.makeText(mContext, "获得待审查数据错误", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Log.d("getTZTGData", "TZTGActivity --------------- 2");
                    if(ggcx.getResult().getBulletinInfo()!=null) {
                        for (int i = 0; i < ggcx.getResult().getBulletinInfo().size(); i++) {
                            TZTG_Model data = new TZTG_Model();
                            data.setTitle(ggcx.getResult().getBulletinInfo().get(i).getTitle());
                            data.setDate(ggcx.getResult().getBulletinInfo().get(i).getRegdate());
                            data.setId(ggcx.getResult().getBulletinInfo().get(i).getBulletinId());
                            tztgList.add(data);
                            if (i == 4)
                                break;
                        }
                    }
                    Log.d("getTZTGData", "TZTGActivity ---------------tztgList.size = " + tztgList.size());
                    if (tztgList.size() > 0)
                        addTZTGData();
                } else {
                    Log.d("getTZTGData", "TZTGActivity --------------- 5");
                    Toast.makeText(mContext, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("getTZTGData", "myCaseToInvestigate --------------- 5t.getMessage() = " + t.getMessage());
                Toast.makeText(mContext, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void addTZTGData() {

        for(int i=0; i<tztgList.size(); i++){
            View mView = mViewList.get(i);
            TextView titleTV = (TextView)mView.findViewById(R.id.item_textView_title);
            titleTV.setText(tztgList.get(i).getTitle());
            TextView dateTV = (TextView)mView.findViewById(R.id.item_textView_date);
            dateTV.setText(tztgList.get(i).getDate());
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("addTZTGData", "view.setOnClickListener()--------" + v.getTag());
                    Intent intent = new Intent(mContext, GGDetailActivity.class);
                    intent.putExtra("bulletinId", tztgList.get((int) v.getTag()).getId());
                    startActivity(intent);
                }
            });
            if(i<5)imageIndicatorView.addViewItem(mView);
        }
		
//        MyPagerAdapter mAdapter = new MyPagerAdapter(mViewList);
//        viewPagerTZTG.setAdapter(mAdapter);

        imageIndicatorView.setBackgroundResource(R.mipmap.login_bg);
        imageIndicatorView.setIndicateStyle(ImageIndicatorView.INDICATE_USERGUIDE_STYLE);
        imageIndicatorView.show();


    }

    private void createMenu() {
        LinearLayout linearLayout = null;

        for (int i = 0; i < rootAppcation.getMenuBtnList().size(); i++) {
            if (rootAppcation.getMenuBtnList().get(i).getName().equals("首页")) {
                MenuModel menuModel = rootAppcation.getMenuBtnList().get(i);
                for (int j = 0; j < menuModel.getFunctionList().size(); j++) {
                    if (j % 2 == 0) {
                        linearLayout = (LinearLayout) View.inflate(getActivity(), R.layout.item_homepage_menu, null);

                        TextView tv = (TextView) linearLayout.findViewById(R.id.tv_one);
                        tv.setText(menuModel.getFunctionList().get(j).getName());
                        ImageView iv = (ImageView) linearLayout.findViewById(R.id.img_one);
                        if(meduleMap.get(menuModel.getFunctionList().get(j).getName())!=null)
                            iv.setImageResource(meduleMap.get(menuModel.getFunctionList().get(j).getName()));

                        setTextViewMenuOnClick(tv);

                    } else {
                        TextView tv = (TextView) linearLayout.findViewById(R.id.tv_two);
                        tv.setText(menuModel.getFunctionList().get(j).getName());
                        ImageView iv = (ImageView) linearLayout.findViewById(R.id.img_two);
                        if(meduleMap.get(menuModel.getFunctionList().get(j).getName())!=null)
                            iv.setImageResource(meduleMap.get(menuModel.getFunctionList().get(j).getName()));

                        homeMenuLayout.addView(linearLayout);

                        setTextViewMenuOnClick(tv);
                    }
                }
            }
        }
    }

    private void setTextViewMenuOnClick(final TextView textView){
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("FragmentHome","setTextViewMenuOnClick()--------in");
                if(((TextView)v).getText().toString().equals("通知通告")){
                    getActivity().startActivity(new Intent(getActivity(), TZTGActivity.class));
                }else if(((TextView)v).getText().toString().equals("公文批阅")){
                    Intent intent = new Intent(getActivity(), ViewPagerActivity.class);
                    intent.putExtra("title", "公文批阅");
                    intent.putExtra("type", "GWPY");
                    startActivity(intent);

                }else if(((TextView)v).getText().toString().equals("公文检索")){
                    Intent intent = new Intent(getActivity(), ViewPagerActivity.class);
                    intent.putExtra("title", "公文检索");
                    intent.putExtra("type", "GWJS");
                    startActivity(intent);
                }else if(((TextView)v).getText().toString().equals("无照经营")){
                    Toast.makeText(getActivity(), Constants.wzjy, Toast.LENGTH_SHORT).show();
                }else if(((TextView)v).getText().toString().equals("案件调查")){
                    Bundle bundle = new Bundle();
                    bundle.putString("activityType", ApiManager.caseApi.INVESTIGATE_CASE_LIST);
                    Intent intent = new Intent(getActivity(), CaseInvestigateActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else if(((TextView)v).getText().toString().equals("案件查询")){
                    Intent intent = new Intent(getActivity(), CaseEnquireActivity.class);
                    startActivity(intent);
                }else if(((TextView)v).getText().toString().equals("抽查检查待办")){
                    ApiManager.getInstance().ccInit();
                    Intent intent=new Intent(getActivity(),RecyclerActivity.class);
                    intent.putExtra("type","CCJCDB");
                    intent.putExtra("title",Constants.ccjcdb);
                    startActivity(intent);
                }else if(((TextView)v).getText().toString().equals("抽查检查查询")){
                    ApiManager.getInstance().ccInit();
                    Intent intent=new Intent(getActivity(), TableListActivity.class);
                    intent.putExtra("title",Constants.ccjccx);
                    intent.putExtra("type","CCJCCX");
                    startActivity(intent);
                }
            }
        });
    }

    private void getGwData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "07010008");
        map.put("userId", "a0a0e39441ad45050141b068ab9803a8");
        map.put("deptId", "13000000001");
        map.put("organId", "130000000");
//        map.put("userId", (String) dataShared.get("userId", ""));
//        map.put("deptId", (String) dataShared.get("deptId", ""));
//        map.put("organId", (String) dataShared.get("organId", ""));
        Call<GwNx> call =ApiManager.oaApi.apiGetNuxGw(map);
        call.enqueue(new Callback<GwNx>() {
            @Override
            public void onResponse(Response<GwNx> response, Retrofit retrofit) {
                if(response.body() != null){
                    gw = response.body();
                    tenGwAdapter.notifyDataSetChanged();
                }
                getTZTGData();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(mContext, "服务器返回数据失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    static class ViewHolder {
        @BindView(R.id.tv_content) TextView contentTv;
        @BindView(R.id.tv_date) TextView dateTv;
        @BindView(R.id.tv_type) TextView typeTv;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    private BaseAdapter tenGwAdapter = new BaseAdapter() {

        @Override
        public int getCount() {
            if (gw == null || gw.getResult().getResult() == null) {
                return 0;
            }
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_search_result, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.contentTv.setText(gw.getResult().getResult().get(position).getTitle());
            holder.dateTv.setText(gw.getResult().getResult().get(position).getCreateAtDateStr());
            holder.typeTv.setText(gw.getResult().getResult().get(position).getType());

            return convertView;
        }
    };

    class MyPagerAdapter extends PagerAdapter{
        private List<View> mViewList;

        public MyPagerAdapter(List<View> mViewList) {
            this.mViewList = mViewList;
        }

        @Override
        public int getCount() {
            return mViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position));
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewList.get(position));
        }

    }

    public class TZTG_Model{
        private String title;
        private String date;
        private String id;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("FragmentHome","onResume");
    }

}
