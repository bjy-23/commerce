package com.wondersgroup.commerce.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.activity.FragmentActivity;
import com.wondersgroup.commerce.activity.PicCropActivity;
import com.wondersgroup.commerce.activity.RecyclerActivity;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.activity.TableListActivity;
import com.wondersgroup.commerce.activity.ViewPagerActivity;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.fgdj.activity.ListInfoActivity;
import com.wondersgroup.commerce.fgdj.activity.ListQueryActivity;
import com.wondersgroup.commerce.model.FunctionModel;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.teamwork.addressbox.TXLActivity;
import com.wondersgroup.commerce.teamwork.casedeal.CaseEnquireActivity;
import com.wondersgroup.commerce.teamwork.casedeal.CaseInvestigateActivity;
import com.wondersgroup.commerce.teamwork.dailycheck.DailyCheckActivity;
import com.wondersgroup.commerce.teamwork.keysuperrise.KeySuperviseActivity;
import com.wondersgroup.commerce.teamwork.memorandum.MemoActivity;
import com.wondersgroup.commerce.teamwork.myhwggdq.HwggdqActivity;
import com.wondersgroup.commerce.teamwork.myrcxc.RCXCActivity;
import com.wondersgroup.commerce.teamwork.myspecialcheck.SpecialCheckActivity;
import com.wondersgroup.commerce.teamwork.mysupervision.SupervisionActivity;
import com.wondersgroup.commerce.teamwork.myxqzgdq.XqzgdqActivity;
import com.wondersgroup.commerce.teamwork.simpleprocedurecase.ProcedureCaseListActivity;
import com.wondersgroup.commerce.teamwork.wywork.SumIndexActivity;
import com.wondersgroup.commerce.teamwork.wywork.fwqy.EntServiceActivity;
import com.wondersgroup.commerce.teamwork.wywork.jcjgno.JCJGBZCActivity;
import com.wondersgroup.commerce.teamwork.wywork.jyqx.JYQXDQActivity;
import com.wondersgroup.commerce.teamwork.wywork.ydjyxz.YDJYXZActivity;
import com.wondersgroup.commerce.teamwork.ztxxcx.InputActivity;
import com.wondersgroup.commerce.ynwq.activity.ToDoActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kangrenhui on 2016/1/19.
 */
public class FragmentNav extends RootFragment {
    private ArrayList<FunctionModel> functionList;
    private RootAppcation appcation;

    private Map<String, Integer> meduleMap;
    private SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        appcation = (RootAppcation) getActivity().getApplicationContext();
        meduleMap = appcation.getMeduleMap();
        sp = getActivity().getSharedPreferences("Default", Context.MODE_PRIVATE);

        View view = View.inflate(getActivity(), R.layout.fragment_nav, null);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linearlayout);

        //动态生成页面内菜单
        View menu = null;
        for (int i = 0; i < functionList.size(); i++) {
            LinearLayout menuLayout;
            if (i % 2 == 0) {
                menu = View.inflate(getActivity(), R.layout.item_menu, null);

                TextView tv = (TextView) menu.findViewById(R.id.tv_title_one);
                tv.setText(functionList.get(i).getName());
                ImageView iv = (ImageView) menu.findViewById(R.id.iv_title_one);
                if (meduleMap.get(functionList.get(i).getName()) != null)
                    iv.setImageResource(meduleMap.get(functionList.get(i).getName()));

                menuLayout = (LinearLayout) menu.findViewById(R.id.layout_one);
                menuLayout.setTag(functionList.get(i).getName());
                menuLayout.setVisibility(View.VISIBLE);
            } else {
                TextView tv = (TextView) menu.findViewById(R.id.tv_title_two);
                tv.setText(functionList.get(i).getName());
                ImageView iv = (ImageView) menu.findViewById(R.id.iv_title_two);
                if (meduleMap.get(functionList.get(i).getName()) != null)
                    iv.setImageResource(meduleMap.get(functionList.get(i).getName()));

                menuLayout = (LinearLayout) menu.findViewById(R.id.layout_two);
                menuLayout.setTag(functionList.get(i).getName());
                menuLayout.setVisibility(View.VISIBLE);
            }

            switch ((String) menuLayout.getTag()) {
                case Constants.fgdjcx:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ListQueryActivity.class);
                            startActivity(intent);
                        }
                    });
                    break;
                case Constants.fgdjgl:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ListInfoActivity.class);
                            intent.putExtra(Constants.TYPE,Constants.GUAN_LI);
                            startActivity(intent);
                        }
                    });
                    break;

                case Constants.wqcbcs:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            HashMap<String, String> body = new HashMap<>();
                            body.put("userId",sp.getString("userId","xtywz"));
                            body.put("deptId",sp.getString("deptId",""));
                            body.put("flowStatus","0103");
                            body.put("organId",sp.getString("organId",""));

                            Intent intent = new Intent(getActivity(), ToDoActivity.class);
                            intent.putExtra("title","财政补助待资格初审");
                            intent.putExtra("body",body);
                            startActivity(intent);
                        }
                    });
                    break;

                //公文批阅
                case Constants.gwpy:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ViewPagerActivity.class);
                            intent.putExtra("title", "公文批阅");
                            intent.putExtra("type", "GWPY");
                            startActivity(intent);
                        }
                    });
                    break;

                //公文检索
                case Constants.gwjs:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ViewPagerActivity.class);
                            intent.putExtra("title", "公文检索");
                            intent.putExtra("type", "GWJS");
                            startActivity(intent);
                        }
                    });
                    break;


                //无证经营
                case Constants.wzjy:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getActivity(), Constants.wzjy, Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;

                //日常检查
                case Constants.rcjc:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            Intent intent = new Intent(getActivity(), ViewPagerActivity.class);
//                            intent.putExtra("title", "日常检查");
//                            intent.putExtra("type", "云南-日常检查");
//                            startActivity(intent);
                            Intent intent = new Intent(getActivity(), DailyCheckActivity.class);
                            startActivity(intent);
                        }
                    });
                    break;

                //专项检查
                case Constants.zxjc:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getActivity(), Constants.zxjc, Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;

                //案件调查
                case Constants.ajdc:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle = new Bundle();
                            bundle.putString("activityType", ApiManager.caseApi.INVESTIGATE_CASE_LIST);
                            Intent intent = new Intent(getActivity(), CaseInvestigateActivity.class);
                            intent.putExtras(bundle);
                            getActivity().startActivity(intent);
                        }
                    });
                    break;

                //案件查询
                case Constants.ajcx:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), CaseEnquireActivity.class);
                            getActivity().startActivity(intent);
                        }
                    });
                    break;

                //主体信息查询
                case Constants.ztxxcx:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getActivity().startActivity(new Intent(getActivity(), InputActivity.class));
                        }
                    });
                    break;

                //户外广告查询
                case Constants.hwggcx:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getActivity(), Constants.hwggcx, Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;

                //限期整改到期
                case Constants.xqzgdq:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), XqzgdqActivity.class);
                            startActivity(intent);
                        }
                    });
                    break;

                //户外广告到期
                case Constants.hwggdq:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), HwggdqActivity.class);
                            startActivity(intent);
                        }
                    });
                    break;

                //业务系统
                case Constants.ywxt:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getActivity(), Constants.ywxt, Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;

                //备忘录
                case Constants.bwl:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), MemoActivity.class);
                            getActivity().startActivity(intent);
                        }
                    });
                    break;

                //通讯录
                case Constants.TXL:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, TXLActivity.class);

                            startActivity(intent);
                        }
                    });

                    break;

                //业务统计
                case Constants.ywtj:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, SumIndexActivity.class);
                            startActivity(intent);
                        }
                    });
                    break;

                //统计
                case Constants.tj:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, FragmentActivity.class);
                            startActivity(intent);
                        }
                    });
                    break;

                //监督管理
                case Constants.jdgl:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), SupervisionActivity.class);

                            startActivity(intent);
                        }
                    });
                    break;

                //日常巡查
                case Constants.rcxc:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), RCXCActivity.class);

                            startActivity(intent);
                        }
                    });
                    break;

                //专项整治
                case Constants.zxzz:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), SpecialCheckActivity.class);

                            startActivity(intent);
                        }
                    });
                    break;
                //企业服务
                case Constants.fwqy:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), EntServiceActivity.class);

                            startActivity(intent);
                        }
                    });
                    break;
                //图片裁剪
                case Constants.tpcj:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), PicCropActivity.class);
                            startActivity(intent);
                        }
                    });
                    break;

                //经营期限到期
                case Constants.jyqxdq:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), JYQXDQActivity.class);
                            startActivity(intent);
                        }
                    });
                    break;
                //检查结果不正常
                case Constants.jcjgno:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), JCJGBZCActivity.class);
                            startActivity(intent);
                        }
                    });
                    break;

                //投诉举报处理
                case Constants.tsjbcl:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ViewPagerActivity.class);
                            intent.putExtra("type", "TSJBCL");
                            Hawk.put("YN_userId", "admin");
                            Hawk.put("YN_organId", "530000000");
                            Hawk.put("YN_deptId", "53000000026");
                            Hawk.put("YN_pageSize", "25");
                            startActivity(intent);
                        }
                    });
                    break;

                //投诉举报查询
                case Constants.tsjbcx:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ViewPagerActivity.class);
                            intent.putExtra("type", "TSJBCX");
                            intent.putExtra("title", "投诉举报查询");
                            Hawk.put("YN_userId", "admin");
                            Hawk.put("YN_pageSize", "25");
                            startActivity(intent);
                        }
                    });

                    break;

                //重点监管
                case Constants.zdjg:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), KeySuperviseActivity.class);
                            startActivity(intent);
                        }
                    });

                    break;

                //抽查检查待办
                case Constants.ccjcdb:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ApiManager.getInstance().ccInit();
                            Intent intent=new Intent(getActivity(),RecyclerActivity.class);
                            intent.putExtra("type","CCJCDB");
                            intent.putExtra("title",Constants.ccjcdb);
                            startActivity(intent);
                        }
                    });
                    break;

                //抽查检查查询
                case Constants.ccjccx:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ApiManager.getInstance().ccInit();
                            Intent intent=new Intent(getActivity(), TableListActivity.class);
                            intent.putExtra("title",Constants.ccjccx);
                            intent.putExtra("type","CCJCCX");
                            startActivity(intent);
                        }
                    });
                    break;

                //异地经营新增
                case Constants.ydjyxz:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(getActivity(), YDJYXZActivity.class);
                            //0为企业1为个人
                            intent.putExtra("type","0");
                            startActivity(intent);
                        }
                    });

                    break;
                //简易程序案件
                case Constants.jycxaj:
                    menuLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle = new Bundle();
                            bundle.putString("activityType", ApiManager.caseApi.INVESTIGATE_CASE_LIST);
                            Intent intent = new Intent(getActivity(), ProcedureCaseListActivity.class);
                            intent.putExtras(bundle);
                            getActivity().startActivity(intent);
                        }
                    });
                    break;
            }

            if (i % 2 == 1 || i == (functionList.size() - 1)) {
                linearLayout.addView(menu);
            }
        }


        return view;
    }


    public ArrayList<FunctionModel> getFunctionList() {
        return functionList;
    }

    public void setFunctionList(ArrayList<FunctionModel> functionList) {
        this.functionList = functionList;
    }
}
