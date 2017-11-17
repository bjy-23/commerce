package com.wondersgroup.commerce.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.Dept;
import com.wondersgroup.commerce.model.ad.AdView;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.utils.DWZH;
import com.wondersgroup.commerce.widget.LoadingDialog;
import com.wondersgroup.commerce.widget.TableRow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import static com.wondersgroup.commerce.activity.ViewPagerActivity.BUENT_ID;

/**
 * Created by bjy on 2017/9/15.
 * 广告详情
 */

public class FragmentGGXQ extends Fragment {
    @BindView(R.id.layout_root)
    LinearLayout layoutRoot;
    @BindView(R.id.layout_error)
    View layoutError;
    private List<Dept.OrganInfo> organInfo = Hawk.get("ORGAN_INFO");

    public static FragmentGGXQ newInstance(String type) {
        FragmentGGXQ fragmentGGXQ = new FragmentGGXQ();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.TYPE, type);
        fragmentGGXQ.setArguments(bundle);
        return fragmentGGXQ;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ggxq, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getAdView();
    }

    private void getAdView() {
        final LoadingDialog loadingDialog = new LoadingDialog.Builder(getActivity()).build();
        loadingDialog.show();
        Map<String, String> params = new HashMap<>();
        params.put("buentId", BUENT_ID);
        ApiManager.adApi.adView(params).enqueue(new Callback<AdView>() {
            @Override
            public void onResponse(Response<AdView> response, Retrofit retrofit) {
                loadingDialog.dismiss();
                if (response.body() != null) {
                    AdView result = response.body();
                    if (result.getCode() == 200) {
                        switch (getArguments().getString(Constants.TYPE)) {
                            case "jydw":
                                jydw(result);//经营单位
                                break;
                            case "cyry":
                                cyry(result);//从业人员
                                break;
                            case "tjcl":
                                tjcl(result.getStuffList());//提交材料
                                break;
                        }
                    } else
                        Toast.makeText(getActivity(), result.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                loadingDialog.dismiss();
            }
        });
    }

    /**
     * 返回机关名称
     *
     * @param organId
     * @return
     */
    private String getOrganName(String organId) {
        if (organInfo != null) {
            for (Dept.OrganInfo item : organInfo) {
                if (organId.equals(item.getOrganId()))
                    return item.getOrganName();
            }
        }
        return "";
    }

    public void jydw(AdView adView) {
        AdView.AdBuentApp adBuentApp = adView.getAdBuentApp();
        if (adBuentApp != null) {
            TableRow jbxx = new TableRow.Builder(getActivity())
                    .asTitle("广告发布单位基本信息")
                    .marginV(5)
                    .build();
            layoutRoot.addView(jbxx);

            TableRow dwmc = new TableRow.Builder(getActivity())
                    .title("单位名称")
                    .marginV(5)
                    .content(adBuentApp.getBuentName())
                    .build();
            layoutRoot.addView(dwmc);

            TableRow zzjgdm = new TableRow.Builder(getActivity())
                    .title("统一社会信用代码")
                    .marginV(5)
                    .content(adBuentApp.getUniScid())
                    .build();
            layoutRoot.addView(zzjgdm);

            String bunetChar = getNullString(adBuentApp.getBunetChar());
            TableRow dwxz = new TableRow.Builder(getActivity())
                    .title("单位性质")
                    .marginV(5)
                    .content(bunetChar.equals("1") ? "企业法人" : bunetChar.equals("3") ? "事业法人" : "社团法人")
                    .build();
            layoutRoot.addView(dwxz);

            TableRow dwlx = new TableRow.Builder(getActivity())
                    .title("单位类型")
                    .marginV(5)
                    .content(buentType().get(getNullString(adBuentApp.getBuentType())))
                    .build();
            layoutRoot.addView(dwlx);

            TableRow zgdw = new TableRow.Builder(getActivity())
                    .title("主管单位")
                    .marginV(5)
                    .content(adBuentApp.getAdOpeOrg())
                    .build();
            layoutRoot.addView(zgdw);

            TableRow djjg = new TableRow.Builder(getActivity())
                    .title("登记机关")
                    .marginV(5)
                    .content(getOrganName(adBuentApp.getOrganId()))
                    .build();
            layoutRoot.addView(djjg);

            TableRow zs = new TableRow.Builder(getActivity())
                    .title("住所（经营场所)")
                    .marginV(5)
                    .content(adBuentApp.getOpLoc())
                    .build();
            layoutRoot.addView(zs);

            TableRow lxr = new TableRow.Builder(getActivity())
                    .title("联系人")
                    .marginV(5)
                    .content(adBuentApp.getLinkMan())
                    .build();
            layoutRoot.addView(lxr);

            TableRow dh = new TableRow.Builder(getActivity())
                    .title("电话")
                    .marginV(5)
                    .content(adBuentApp.getTel())
                    .build();
            layoutRoot.addView(dh);

            TableRow yddh = new TableRow.Builder(getActivity())
                    .title("移动电话")
                    .marginV(5)
                    .content(adBuentApp.getMoblie())
                    .build();
            layoutRoot.addView(yddh);

            TableRow email = new TableRow.Builder(getActivity())
                    .title("Email")
                    .marginV(5)
                    .content(adBuentApp.getEmail())
                    .build();
            layoutRoot.addView(email);

//            TableRow sqlxr = new TableRow.Builder(getActivity())
//                    .title("申请联系人")
//                    .marginV(5)
//                    .content(adBuentApp.getAdAppPerson())
//                    .build();
//            layoutRoot.addView(sqlxr);
//
//            TableRow sqlxdh = new TableRow.Builder(getActivity())
//                    .title("申请联系电话")
//                    .marginV(5)
//                    .content(adBuentApp.getAdAppContact())
//                    .build();
//            layoutRoot.addView(sqlxdh);
//
//            String adAppType = getNullString(adBuentApp.getAdAppType());
//            TableRow lxfs = new TableRow.Builder(getActivity())
//                    .title("申请方式")
//                    .marginV(5)
//                    .content((TextUtils.isEmpty(adAppType) || adAppType.equals("1")) ? "到登记场所直接申请" : adAppType.equals("9") ? "其他" : "")
//                    .build();
//            layoutRoot.addView(lxfs);

            AdView.AdBntappLeader leader = adView.getAdBntappLeader();
            TableRow fddbr = new TableRow.Builder(getActivity())
                    .asTitle("法定代表人情况")
                    .marginV(5)
                    .build();
            layoutRoot.addView(fddbr);

            TableRow xm = new TableRow.Builder(getActivity())
                    .title("姓名")
                    .content(leader.getLeaderName())
                    .marginV(5)
                    .build();
            layoutRoot.addView(xm);

            TableRow xb = new TableRow.Builder(getActivity())
                    .title("性别")
                    .marginV(5)
                    .content(leader.getSex())
                    .build();
            layoutRoot.addView(xb);

            TableRow zjlx = new TableRow.Builder(getActivity())
                    .title("证件类型")
                    .marginV(5)
                    .content(certTypeMap(leader.getCerType()))
                    .build();
            layoutRoot.addView(zjlx);

            TableRow zjhm = new TableRow.Builder(getActivity())
                    .title("证件号码")
                    .marginV(5)
                    .content(leader.getCerNo())
                    .build();
            layoutRoot.addView(zjhm);

            TableRow lxdh = new TableRow.Builder(getActivity())
                    .title("联系电话")
                    .marginV(5)
                    .content(leader.getTel())
                    .build();
            layoutRoot.addView(lxdh);

            TableRow zz = new TableRow.Builder(getActivity())
                    .title("住址")
                    .marginV(5)
                    .content(leader.getHouseAdd())
                    .build();
            layoutRoot.addView(zz);
        }

        List<AdView.AdBntappMediaList> adBntappMediaList = adView.getAdBntappMediaList();
        if (adBntappMediaList != null && adBntappMediaList.size() != 0) {
            TableRow mjxx = new TableRow.Builder(getActivity())
                    .asTitle("广告发布媒介信息")
                    .marginV(5)
                    .build();
            layoutRoot.addView(mjxx);

            layoutRoot.addView(divide());
            for (int i = 0; i < adBntappMediaList.size(); i++) {
                View view = View.inflate(getActivity(), R.layout.item_media_infomation, null);
                TextView tvName = (TextView) view.findViewById(R.id.tv_name);
                TextView tvDate = (TextView) view.findViewById(R.id.tv_date);
                TextView tvRemark = (TextView) view.findViewById(R.id.tv_remark);

                tvName.setText(adBntappMediaList.get(i).getMeidaName());
                tvDate.setText("有效期：" + StringToDate(getNullString(adBntappMediaList.get(i).getValFrom()), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"));
                tvRemark.setText("备注：" + adBntappMediaList.get(i).getRemark());
                layoutRoot.addView(view);
            }
        }
    }

    /**
     * 单位类型
     *
     * @return
     */
    private Map<String, String> buentType() {
        Map<String, String> buentType = new HashMap<>();
        buentType.put("100", "广告发布者");
        buentType.put("110", "广播电台");
        buentType.put("120", "电视台");
        buentType.put("130", "报社");
        buentType.put("140", "出版社");
        buentType.put("150", "期刊社");
        buentType.put("160", "经营广告的网站");
        buentType.put("170", "报刊出版单位");
        buentType.put("200", "广告经营者");
        buentType.put("210", "广告公司");
        buentType.put("211", "股份有限公司");
        buentType.put("212", "有限责任公司");
        buentType.put("220", "经营广告的企业");
        buentType.put("230", "兼营广告的企业");
        buentType.put("300", "事业单位（兼营广告）");
        buentType.put("900", "其他");
        buentType.put("", "");
        return buentType;
    }

    /**
     * 证照类型
     *
     * @param cerType
     * @return
     */
    private String certTypeMap(String cerType) {
        String retCerType = "";
        switch (cerType) {
            case "1":
                retCerType = "中华人民共和国居民身份证";
                break;
            case "2":
                retCerType = "中华人民共和国军官证";
                break;
            case "3":
                retCerType = "中华人民共和国警官证";
                break;
            case "4":
                retCerType = "外国（地区）护照";
                break;
            case "9":
                retCerType = "其他有效身份证件";
                break;
        }
        return retCerType;
    }

    public void cyry(AdView adView) {
        List<AdView.AdBntappPsn> adBntappPsnList = new ArrayList<>();
        List<AdView.AdBntappPsn> adBntappPsnList_01 = adView.getAdBntappPsnList_01();
        List<AdView.AdBntappPsn> adBntappPsnList_02 = adView.getAdBntappPsnList_02();
        List<AdView.AdBntappPsn> adBntappPsnList_03 = adView.getAdBntappPsnList_03();
        if (adBntappPsnList_01 != null) {
            for (AdView.AdBntappPsn item : adBntappPsnList_01) {
                item.setPsnType("广告机构负责人");
            }
            adBntappPsnList.addAll(adBntappPsnList_01);
        }
        if (adBntappPsnList_02 != null) {
            for (AdView.AdBntappPsn item : adBntappPsnList_02) {
                item.setPsnType("广告从业人员");
            }
            adBntappPsnList.addAll(adBntappPsnList_02);
        }
        if (adBntappPsnList_03 != null) {
            for (AdView.AdBntappPsn item : adBntappPsnList_03) {
                item.setPsnType("广告审查人员");
            }
            adBntappPsnList.addAll(adBntappPsnList_03);
        }
        if (adBntappPsnList.size() == 0) {
            layoutError.setVisibility(View.VISIBLE);
        } else {
            layoutRoot.addView(divide());
            for (int i = 0; i < adBntappPsnList.size(); i++) {
                View view = View.inflate(getActivity(), R.layout.item_employee, null);
                TextView tvName = (TextView) view.findViewById(R.id.tv_name);
                TextView tvPersonType = (TextView) view.findViewById(R.id.tv_person_type);
                TextView tvTel = (TextView) view.findViewById(R.id.tv_tel);
                TextView tvCertNo = (TextView) view.findViewById(R.id.tv_cert_no);
                TextView tvIdType = (TextView) view.findViewById(R.id.tv_id_type);
                TextView tvIdNo = (TextView) view.findViewById(R.id.tv_id_no);

                tvName.setText(adBntappPsnList.get(i).getAname());
                tvPersonType.setText("人员类型：" + getNullString(adBntappPsnList.get(i).getPsnType()));
                tvTel.setText("联系方式：" + getNullString(adBntappPsnList.get(i).getTelephone()));
                tvCertNo.setText("培训证书编号：" + getNullString(adBntappPsnList.get(i).getAdTrainCertNo()));
                tvIdType.setText("证件类型：" + certTypeMap(getNullString(adBntappPsnList.get(i).getCerType())));
                tvIdNo.setText("证件号码：" + getNullString(adBntappPsnList.get(i).getCerNo()));
                layoutRoot.addView(view);
            }
        }
    }

    public void tjcl(List<AdView.StuffList> stuffList) {
        final String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505908796812&di=c138eeaba54b0baaddac770379adf708&imgtype=0&src=http%3A%2F%2Fpic.qiantucdn.com%2F58pic%2F18%2F23%2F90%2F07v58PICWAT_1024.jpg";
        if (stuffList != null && stuffList.size() != 0) {
            layoutRoot.addView(divide());
            for (int i = 0; i < stuffList.size(); i++) {
                View view = View.inflate(getActivity(), R.layout.item_tjcl, null);
                TextView mTitle = (TextView) view.findViewById(R.id.title);
                TextView mStatus = (TextView) view.findViewById(R.id.status);
                TextView mDate = (TextView) view.findViewById(R.id.date);
                TextView mPage = (TextView) view.findViewById(R.id.page);
                TextView mDownload = (TextView) view.findViewById(R.id.download);
//            mDownload.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                    startActivity(intent);
//                }
//            });
                if (TextUtils.isEmpty(stuffList.get(i).getFilePath())) {
                    mDownload.setVisibility(View.GONE);
                }
                mTitle.setText(getNullString(stuffList.get(i).getStuffName()));
                mPage.setText(getNullString(stuffList.get(i).getPages()) + "页");
                String status = getNullString(stuffList.get(i).getCommited());
                mStatus.setText(status.equals("1") ? "已提交" : status.equals("2") ? "未提交" : "");
                mDate.setText(StringToDate(getNullString(stuffList.get(i).getCommitDate()), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"));
                layoutRoot.addView(view);
            }
        } else {
            layoutError.setVisibility(View.VISIBLE);
        }
    }

    private String getNullString(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }

    public View divide() {
        View view = new View(getActivity());
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DWZH.dp(1));
        view.setLayoutParams(lp);
        view.setBackgroundColor(getResources().getColor(R.color.light_gray));

        return view;
    }

    @SuppressLint("SimpleDateFormat")
    public static String StringToDate(String dateSrc, String formatSrc, String formatDes) {
        SimpleDateFormat format = new SimpleDateFormat(formatSrc);
        Date date = new Date();
        try {
            date = format.parse(dateSrc);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat s = new SimpleDateFormat(formatDes);
        return s.format(date);
    }
}
