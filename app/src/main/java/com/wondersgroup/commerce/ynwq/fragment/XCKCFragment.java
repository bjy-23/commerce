package com.wondersgroup.commerce.ynwq.fragment;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.orhanobut.hawk.Hawk;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.picasso.Picasso;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.model.ccjc.DicItem;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.widget.LoadingDialog;
import com.wondersgroup.commerce.ynwq.activity.InputActivity;
import com.wondersgroup.commerce.ynwq.activity.ListActivity;
import com.wondersgroup.commerce.ynwq.activity.PicShowActivity;
import com.wondersgroup.commerce.ynwq.adapter.XckcAdapter;
import com.wondersgroup.commerce.ynwq.bean.DicT;
import com.wondersgroup.commerce.ynwq.bean.GeneralResult;
import com.wondersgroup.commerce.ynwq.bean.MeInfoResult;
import com.wondersgroup.commerce.ynwq.bean.PicBean;
import com.wondersgroup.commerce.ynwq.widget.LabelInput;
import com.wondersgroup.commerce.ynwq.widget.LabelNum;
import com.wondersgroup.commerce.ynwq.widget.LabelSwitch;
import com.wondersgroup.commerce.ynwq.widget.LabelText;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link XCKCFragment#newInstance} factory method to
 * create an instance of this fragment.
 * <p>
 * 现场勘查
 */
public class XCKCFragment extends Fragment implements View.OnClickListener, PicShowActivity.DeleteListener {
    @BindView(R.id.xckc_entName)
    LabelText entName;
    @BindView(R.id.xckc_legRpName)
    LabelText person;
    @BindView(R.id.xckc_phone)
    LabelText phone;
    @BindView(R.id.xckc_addr)
    LabelInput addr;
    @BindView(R.id.xckc_addrType)
    LabelInput addrType;
    @BindView(R.id.xckc_area)
    LabelNum area;
    @BindView(R.id.xckc_isRight)
    LabelSwitch isRight;
    @BindView(R.id.xckc_isNecessary)
    LabelSwitch isNecessary;
    @BindView(R.id.xckc_assess)
    LabelInput assess;
    @BindView(R.id.xckc_checkMan)
    TextView checkTitle;
    @BindView(R.id.xckc_checkManOne)
    EditText firstBlood;
    @BindView(R.id.xckc_checkManTwo)
    EditText doubleKill;
    @BindView(R.id.xckc_note)
    LabelInput note;
    @BindView(R.id.xckc_submit)
    Button submit;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private static final String ARG_ID = "paramId";
    private static final String ARG_XCKCTYPE = "XCKCTYPE";


    private String mParamId;
    private String xckcType;
    private String runSpaceType = null;
    private String runSpaceTypeOther = null;

    private boolean needFetch = true;
    private boolean submitted;

    private List<PicBean> picList = new ArrayList<PicBean>();

    private int upSum,downSum;

    private XckcAdapter mAdapter;
    private List<PicBean> images;
    private final static int IMG_PICKER = 10000;
    private LoadingDialog loadingDialog;
    private TotalLoginBean loginBean;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {

                picList = new ArrayList<>();
                for (int i = 0; i < images.size(); i++) {
                    PicBean picBean = images.get(i);
                    if (picBean != null) {
                        picBean.setPicNum(i + 1);
                        picBean.setPicName(mParamId + "_" + (i + 1) + ".jpg");
                        picList.add(picBean);
                        upSum++;
                        uploadImage(picBean);
                    }
                }
                if (upSum == 0) {
                    if (loadingDialog.isShowing())
                        loadingDialog.dismiss();
                    Toast.makeText(getActivity(), "提交成功", Toast.LENGTH_SHORT).show();
                    getActivity().finish();

                }
            } else if (msg.what == 200) {
                for (int i = 0; i < images.size(); i++) {
                    PicBean picBean = images.get(i);
                    if (picBean != null){
                        downSum++;
                        downLoadImage(picBean, i);
                    }
                }
            }
        }
    };


    public XCKCFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment XCKCFragment.
     */

    public static XCKCFragment newInstance(String param1, String xckcType) {
        XCKCFragment fragment = new XCKCFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ID, param1);
        args.putString(ARG_XCKCTYPE, xckcType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParamId = getArguments().getString(ARG_ID);
            xckcType = getArguments().getString(ARG_XCKCTYPE);
            loginBean = Hawk.get(Constants.LOGIN_BEAN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xckc, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        if ("2".equals(xckcType))
            disableInput();
        if (getUserVisibleHint()) {
            fetchData();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (getView() != null && needFetch) {
                fetchData();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == getActivity().RESULT_OK) {
            addrType.setContent(data.getStringExtra("CDXZ"));
            runSpaceType = data.getStringExtra("typeCode");
            if ("9".equals(runSpaceType))
                runSpaceTypeOther = data.getStringExtra("CDXZ");
        } else if (requestCode == 200 && resultCode == getActivity().RESULT_OK) {
            assess.setContent(data.getStringExtra("input_string"));
        } else if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMG_PICKER) {
                ArrayList<ImageItem> imageArray = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (imageArray != null && imageArray.size() != 0) {
                    images.remove(images.size() - 1);
                    for (ImageItem imageItem : imageArray) {
                        PicBean picBean = new PicBean();
                        picBean.setPicPath(imageItem.path);
                        picBean.setType(1);
                        images.add(picBean);
                    }
                    if (images.size() < 5) {
                        images.add(null);
                    }
                    mAdapter.notifyDataSetChanged();
                }
            }
        } else if (requestCode == 1229) {
            images.clear();
            ArrayList<PicBean> list = data.getParcelableArrayListExtra("images");
            images.addAll(list);
            if (images.size() < 5)
                images.add(null);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 818) {
            if (permissions[0] == android.Manifest.permission.WRITE_EXTERNAL_STORAGE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(getActivity(), ImageGridActivity.class);
                startActivityForResult(intent, IMG_PICKER);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(submit)) {
            if (isAllFilled()) {
                if (downSum == 0){
                    loadingDialog = new LoadingDialog.Builder(getActivity())
                            .msg("提交中...")
                            .build();
                    loadingDialog.show();
                    Map<String, String> body = new HashMap<>();
                    body.put("wsCodeReq", "01100001");
                    body.put(Constants.USER_ID, loginBean.getResult().getUserId());
                    body.put(Constants.ORGAN_ID, loginBean.getResult().getOrganId());
                    body.put(Constants.DEPT_ID, loginBean.getResult().getDeptId());
                    body.put("meId", mParamId);
                    body.put("runAddress", addr.getText());
                    body.put("runSpaceType", runSpaceType);
                    if (runSpaceTypeOther != null) body.put("runSpaceTypeOther", runSpaceTypeOther);
                    body.put("runArea", area.getText());
                    body.put("areaEqScale", "" + isRight.getSelection());
                    body.put("hasEquipment", "" + isNecessary.getSelection());
                    body.put("explorationOpnn", assess.getText());
                    body.put("explorationPerson", firstBlood.getText().toString());
                    body.put("explorationPerson2", doubleKill.getText().toString());
                    body.put("memo", note.getText());
                    StringBuilder sBuilder = new StringBuilder();
                    for (int i = 0; i < images.size(); i++) {
                        PicBean picBean = images.get(i);
                        if (picBean != null)
                            sBuilder.append(i + 1 + ",");
                    }
                    String picNums = "";
                    if (!TextUtils.isEmpty(sBuilder.toString()))
                        picNums = sBuilder.toString().substring(0, sBuilder.toString().length()-1);
                    body.put("picNums", picNums);
//                body.put("picNums", "1,2,3,4,5");
                    Call<GeneralResult> call = ApiManager.ynWqApi.submitMeExploration(body);
                    call.enqueue(new Callback<GeneralResult>() {
                        @Override
                        public void onResponse(Response<GeneralResult> response, Retrofit retrofit) {
                            if (response.isSuccess() && response.body() != null) {
                                GeneralResult result = response.body();
                                if ("200".equals(result.getResultCode())) {
                                    submitted = true;

                                    //提交图片
                                    handler.sendEmptyMessage(100);
                                } else {
                                    new MaterialDialog.Builder(getContext())
                                            .title("提交失败")
                                            .content(result.getMessage())
                                            .positiveText("确定")
                                            .show();
                                    if (loadingDialog.isShowing())
                                        loadingDialog.dismiss();
                                }
                            } else {
                                new MaterialDialog.Builder(getContext())
                                        .title("提交失败")
                                        .content("连接服务器失败")
                                        .positiveText("确定")
                                        .show();
                                if (loadingDialog.isShowing())
                                    loadingDialog.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            new MaterialDialog.Builder(getContext())
                                    .title("提交失败")
                                    .content("网络错误")
                                    .positiveText("确定")
                                    .show();

                            if (loadingDialog.isShowing())
                                loadingDialog.dismiss();
                        }
                    });
                }else {
                    new MaterialDialog.Builder(getContext())
                            .content("请等待图片加载完成再提交")
                            .show();
                }
            } else {
                new MaterialDialog.Builder(getContext())
                        .content("请填入所有必填项")
                        .show();
            }
        }
    }

    public void fetchData() {
        Map<String, String> body = new HashMap<>();
        body.put("meId", mParamId);
        body.put("wsCodeReq", "01100001");
        Call<MeInfoResult> call = ApiManager.ynWqApi.getMeInfo(body);
        call.enqueue(new Callback<MeInfoResult>() {
            @Override
            public void onResponse(Response<MeInfoResult> response, Retrofit retrofit) {
                if (response.isSuccess() && response.body() != null) {
                    MeInfoResult result = response.body();
                    if ("200".equals(result.getResultCode())) {
                        needFetch = false;
                        String baseUrl = result.getResult().getBaseUrl();
                        if (!TextUtils.isEmpty(result.getResult().getImgFileName1())) {
                            PicBean picBean = new PicBean();
                            picBean.setPicPath(baseUrl + result.getResult().getImgFileName1());
                            picBean.setPicName(result.getResult().getImgFileName1());
                            picBean.setPicNum(1);
                            picBean.setType(0);
                            images.add(picBean);
                        }
                        if (!TextUtils.isEmpty(result.getResult().getImgFileName2())) {
                            PicBean picBean = new PicBean();
                            picBean.setPicPath(baseUrl + result.getResult().getImgFileName2());
                            picBean.setPicName(result.getResult().getImgFileName2());
                            picBean.setPicNum(2);
                            picBean.setType(0);
                            images.add(picBean);
                        }
                        if (!TextUtils.isEmpty(result.getResult().getImgFileName3())) {
                            PicBean picBean = new PicBean();
                            picBean.setPicPath(baseUrl + result.getResult().getImgFileName3());
                            picBean.setPicName(result.getResult().getImgFileName3());
                            picBean.setPicNum(3);
                            picBean.setType(0);
                            images.add(picBean);
                        }
                        if (!TextUtils.isEmpty(result.getResult().getImgFileName4())) {
                            PicBean picBean = new PicBean();
                            picBean.setPicPath(baseUrl + result.getResult().getImgFileName4());
                            picBean.setPicName(result.getResult().getImgFileName4());
                            picBean.setPicNum(4);
                            picBean.setType(0);
                            images.add(picBean);
                        }

                        if (!TextUtils.isEmpty(result.getResult().getImgFileName5())) {
                            PicBean picBean = new PicBean();
                            picBean.setPicPath(baseUrl + result.getResult().getImgFileName5());
                            picBean.setPicName(result.getResult().getImgFileName5());
                            picBean.setPicNum(5);
                            picBean.setType(0);
                            images.add(picBean);
                        }

                        //显示图片
                        if (images.size() < 5)
                            images.add(null);
                        mAdapter.notifyDataSetChanged();
                        entName.setContent(result.getResult().getEntName());
                        person.setContent(result.getResult().getLeRepName());
                        phone.setContent(result.getResult().getPhoneNum());
                        addr.setContent(result.getResult().getRunAddress());
                        if (!TextUtils.isEmpty(result.getResult().getRunSpaceType())){
                            addrType.setContent(result.getResult().getRunSpaceType());
                            DicT dic= Hawk.get("Dic2");
                            if (dic != null){
                                List<DicItem> list = dic.getRunSpaceType();
                                for (DicItem dicItem :list){
                                    if (dicItem.getValue().equals(result.getResult().getRunSpaceType())){
                                        runSpaceType = dicItem.getName();
                                        break;
                                    }
                                }
                            }
                        }
                        area.setContent(result.getResult().getRunArea());
                        assess.setContent(result.getResult().getExplorationOpnn());
                        if ("是".equals(result.getResult().getAreaEqScale())) {
                            isRight.check(true);
                        } else {
                            isRight.check(false);
                        }
                        if ("是".equals(result.getResult().getHasEquipment())) {
                            isNecessary.check(true);
                        } else {
                            isNecessary.check(false);
                        }
                        firstBlood.setText(result.getResult().getExplorationPerson());
                        doubleKill.setText(result.getResult().getExplorationPerson2());
                        note.setContent(result.getResult().getMemo());
                        handler.sendEmptyMessage(200);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private void disableInput() {
        addr.setInputType(InputType.TYPE_NULL);
        addr.setHint("");
        addrType.setInputType(InputType.TYPE_NULL);
        addrType.setHint("");
        area.setInputType(InputType.TYPE_NULL);
        area.setHint("");
        isRight.disable();
        isNecessary.disable();
        assess.setInputType(InputType.TYPE_NULL);
        assess.setHint("");
        checkTitle.setText("场地勘察人");
        firstBlood.setInputType(InputType.TYPE_NULL);
        doubleKill.setInputType(InputType.TYPE_NULL);
        note.setInputType(InputType.TYPE_NULL);
        note.setHint("");
        submit.setVisibility(View.GONE);

        submitted = true;
    }

    public void init() {
        entName.setTitle("企业名称");
        person.setTitle("企业负责人姓名");
        phone.setTitle("手机号码");
        addr.setTitle("经营详细地址");
        if ("1".equals(xckcType)) addr.isRequired(true);
        addr.setHint("输入场地详细地址");
        addrType.setTitle("经营场地性质");
        if ("1".equals(xckcType)) addrType.isRequired(true);
        addrType.setHint("请选择经营场地性质");
        if ("1".equals(xckcType)) {
            addrType.setContentClick(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), ListActivity.class);
                    intent.putExtra("title", "请选择场地性质");
                    intent.putExtra("type", "CDXZ");
                    startActivityForResult(intent, 100);
                }
            });
        }
        area.setTitle("经营场地面积");
        if ("1".equals(xckcType)) area.isRequired(true);
        area.setHint("输入场地面积");
        area.setUnit("(平方米)");
        isRight.setTitle("经营场地是否与企业经营规模相适应");
        if ("1".equals(xckcType)) isRight.isRequired(true);
        isNecessary.setTitle("企业经营是否具备必要的设备或办公用品");
        if ("1".equals(xckcType)) isNecessary.isRequired(true);
        assess.setTitle("经营场所勘察意见");
        if ("1".equals(xckcType)) assess.isRequired(true);
        assess.setHint("输入经营场所勘察意见");
        if ("1".equals(xckcType)) {
            assess.setContentClick(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), InputActivity.class);
                    intent.putExtra("title", "经营场所勘察意见");
                    startActivityForResult(intent, 200);
                }
            });
        }
        SpannableString sb = new SpannableString("场地勘察人*");
        sb.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.red)), sb.length() - 1, sb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        checkTitle.setText(sb);
        note.setTitle("备注");
        note.setHint("输入备注");
        if ("1".equals(xckcType)) note.isRequired(true);
        submit.setOnClickListener(this);

        images = new ArrayList<>();
        mAdapter = new XckcAdapter(getActivity(), images);
        mAdapter.setOnItemClickListener(new XckcAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                int sum = 0;
                for (PicBean picBean : images) {
                    if (picBean != null) {
                        sum++;
                    }
                }
                ImagePicker imagePicker = ImagePicker.getInstance();
                imagePicker.setSelectLimit(5 - sum);
                if (images.get(position) == null) {
                    //权限处理
                    if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED) {
                        XCKCFragment.this.requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 818);
                    } else {
                        Intent intent = new Intent(getActivity(), ImageGridActivity.class);
                        startActivityForResult(intent, IMG_PICKER);
                    }
                } else {
                    ArrayList<PicBean> list = new ArrayList<PicBean>();
                    for (PicBean picBean : images) {
                        if (picBean != null)
                            list.add(picBean);
                    }
                    Intent intent = new Intent(getActivity(), PicShowActivity.class);
                    intent.putExtra(Constants.POSITION, position);
                    intent.putParcelableArrayListExtra("images", list);
                    startActivityForResult(intent, 1229);
                }
            }
        });
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3
                , LinearLayoutManager.VERTICAL, false));
    }

    public boolean isAllFilled() {
        if (!addr.getText().isEmpty() && !addrType.getText().isEmpty() && !area.getText().isEmpty() && isRight.getSelection() != -1 && isNecessary.getSelection() != -1 && !assess.getText().isEmpty() && !firstBlood.getText().toString().isEmpty() && !doubleKill.getText().toString().isEmpty())
            return true;
        return false;
    }

    public boolean confirmQuit() {
        if (submitted) return false;
        else
            return !addr.getText().isEmpty() || !addrType.getText().isEmpty() || !area.getText().isEmpty() || isRight.getSelection() != -1 || isNecessary.getSelection() != -1 || !assess.getText().isEmpty() || !firstBlood.getText().toString().isEmpty() || !doubleKill.getText().toString().isEmpty();
    }


    public void uploadImage(PicBean bean) {
        String name = bean.getPicName();
        String path = bean.getPicPath();
        int num = bean.getPicNum();

        File file = new File(path);
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        RequestBody requestBody = new MultipartBuilder()
                .type(MultipartBuilder.FORM)
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"picNum\""),
                        RequestBody.create(null, num + ""))
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"meId\""),
                        RequestBody.create(null, mParamId))
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"userId\""),
                        RequestBody.create(null, loginBean.getResult().getUserId()))
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"organId\""),
                        RequestBody.create(null, loginBean.getResult().getOrganId()))
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"deptId\""),
                        RequestBody.create(null, loginBean.getResult().getDeptId()))
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"mFile\"; filename=" + name), fileBody)
                .build();
        String baseUrl = "";
        baseUrl = ApiManager.API_RE_ROOT;
        Request request = new Request.Builder()
                .url(baseUrl + "mobileUploadFile.do")
                .post(requestBody)
                .build();

        ApiManager.getInstance().okHttpClient.newCall(request).enqueue(new com.squareup.okhttp.Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "提交图片失败！", Toast.LENGTH_SHORT).show();
                    }
                });
                if (loadingDialog.isShowing())
                    loadingDialog.dismiss();
            }

            @Override
            public void onResponse(com.squareup.okhttp.Response response) throws IOException {
                upSum--;
                if (upSum == 0){
                    if (loadingDialog.isShowing())
                        loadingDialog.dismiss();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "提交成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                    getActivity().finish();
                }
            }
        });
    }

    public void downLoadImage(final PicBean picBean, final int index) {
        Request request = new Request.Builder()
                .url(picBean.getPicPath())
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(60, TimeUnit.SECONDS);
        okHttpClient.setWriteTimeout(60, TimeUnit.SECONDS);
        okHttpClient.newCall(request).enqueue(new com.squareup.okhttp.Callback() {

            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(com.squareup.okhttp.Response response) throws IOException {
                String path = Environment.getExternalStorageDirectory() + "/DCIM/Camera/" +System.currentTimeMillis()+picBean.getPicName();
                File file = new File(path);
                BufferedSink sink = Okio.buffer(Okio.sink(file));
                BufferedSource source = response.body().source();
                long bytesRead = 0;
                while (source.read(sink.buffer(), 2048) != -1) {
                    bytesRead += 2048;
                }
                sink.writeAll(source);
                sink.close();

                picBean.setType(1);
                picBean.setPicPath(path);
                images.set(index, picBean);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyItemChanged(index);
                    }
                });
                downSum--;
            }
        });

    }

    @Override
    public void onDelete(int position) {

    }
}
