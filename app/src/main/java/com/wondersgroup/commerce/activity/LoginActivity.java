package com.wondersgroup.commerce.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.logger.Logger;
import com.squareup.okhttp.ResponseBody;
import com.wondersgroup.commerce.R;
import com.wondersgroup.commerce.application.RootAppcation;
import com.wondersgroup.commerce.constant.Constants;
import com.wondersgroup.commerce.interface_.TextChanger;
import com.wondersgroup.commerce.model.DynamicComponentObject;
import com.wondersgroup.commerce.model.Menu;
import com.wondersgroup.commerce.model.MenuBean;
import com.wondersgroup.commerce.model.MenuInfo;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.model.ShLoginBean;
import com.wondersgroup.commerce.model.ShLoginDeptBean;
import com.wondersgroup.commerce.model.yn.RequestLoginBean;
import com.wondersgroup.commerce.model.yn.YnLoginBean;
import com.wondersgroup.commerce.service.ApiManager;
import com.wondersgroup.commerce.service.Result;
import com.wondersgroup.commerce.teamwork.dailycheck.ImgFirstBean;
import com.wondersgroup.commerce.teamwork.dailycheck.LoginUserInfo;
import com.wondersgroup.commerce.utils.DataShared;
import com.wondersgroup.commerce.utils.LogHelper;
import com.wondersgroup.commerce.widget.LoadingDialog;
import com.wondersgroup.commerce.ynwq.bean.LoginResult;
import com.wondersgroup.commerce.ynwq.bean.UserResult;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * Created by yclli on 2015/11/18.
 */
public class LoginActivity extends RootActivity {
    @Bind(R.id.login_editName)
    EditText nameText;
    @Bind(R.id.login_editPwd)
    EditText pwdText;
    @Bind(R.id.login_clear)
    Button clearNameBtn;
    @Bind(R.id.login_clear2)
    Button clearPwdBtn;
    @Bind(R.id.login_btnLogin)
    Button loginBtn;
    @Bind(R.id.img_remember)
    ImageView imgRemember;
    @Bind(R.id.img_login_head)
    ImageView imgLoginHead;

    //上海工商
    @Bind(R.id.login_dept)
    EditText deptEdit;
    @Bind(R.id.dept_choose_clear)
    Button deptClearBtn;

    private int organFlag;
    private List<ShLoginDeptBean.Values> deptValueList;
    private List<String> detpList;
    private String loginName,password;
    private List<MenuBean> firstPageMenu;
    private List<MenuInfo> ywblMenuInfos;
    private List<MenuInfo> messageMenuInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        //初始化Logger
        Logger.init(myApplication.getVersion() + "工商版本");
        LogHelper.debug("程序启动了");

        clearBtnShow();

        loginName = Hawk.get(Constants.LOGIN_NAME);
        if (loginName!=null)
            nameText.setText(loginName);
        password = Hawk.get(Constants.PASSWORD);
        if (password!=null)
            pwdText.setText(Hawk.get(Constants.PASSWORD).toString());

        switch (myApplication.getVersion()){
            case "四川":
                imgLoginHead.setImageResource(R.drawable.login_bg_sc_1);
                break;
            case "云南":
                imgLoginHead.setImageResource(R.drawable.login_bg_yn_2);
                break;
        }
    }

    private void clearBtnShow() {
        nameText.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                if ("".equals(String.valueOf(s))){
                    clearNameBtn.setVisibility(View.GONE);
                }else {
                    clearNameBtn.setVisibility(View.VISIBLE);
                }
            }
        });

        pwdText.addTextChangedListener(new TextChanger(){
            @Override
            public void afterTextChanged(Editable s) {
                if ("".equals(String.valueOf(s))){
                    clearPwdBtn.setVisibility(View.GONE);
                }else {
                    clearPwdBtn.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @OnClick({R.id.login_touchName, R.id.login_touchPwd, R.id.login_clear, R.id.login_clear2
            , R.id.login_btnLogin,R.id.login_dept,R.id.img_remember})
    public void onClick(View v) {
        InputMethodManager imm;
        switch (v.getId()) {
            default:
                break;
            case R.id.login_btnLogin:
                loginName = nameText.getText().toString().trim();
                password = pwdText.getText().toString().trim();

                Hawk.put(Constants.LOGIN_NAME,loginName);

                if ("".equals(loginName) || "".equals(password)) {
                    Toast.makeText(mContext, "请输入正确的用户名或密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                switch (myApplication.getVersion()) {
                    case "云南":
                    case "四川":
                        gsythLoginNet("1");
//                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        break;
                    case "上海":
                        shLoginNet();
                        break;
                }
                break;
            case R.id.login_touchName:
                nameText.requestFocus();
                //打开输入法窗口，接受软键盘显示
                imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(nameText, InputMethodManager.SHOW_IMPLICIT);
                break;
            case R.id.login_touchPwd:
                pwdText.requestFocus();
                imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(pwdText, InputMethodManager.SHOW_IMPLICIT);
                break;
            //部门选择
            case R.id.login_dept:
                showDeptDialog();
                break;
            case R.id.login_clear:
                nameText.setText("");
                clearNameBtn.setVisibility(View.INVISIBLE);
                break;
            case R.id.login_clear2:
                pwdText.setText("");
                clearPwdBtn.setVisibility(View.INVISIBLE);
                break;
        }
    }


    /**
     * 工商一体化登陆
     */
    private void gsythLoginNet(String deptId) {
        final SweetAlertDialog dialog = LoadingDialog.showCanCancelable(mContext);
        dialog.setTitleText("登录...");
        dialog.show();
        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "00000001");
        map.put("deptId", deptId);
        map.put("loginName", loginName);
        map.put("password", password);


        Call<TotalLoginBean> call = ApiManager.hbApi.login(map);
        call.enqueue(new Callback<TotalLoginBean>() {
            @Override
            public void onResponse(Response<TotalLoginBean> response, Retrofit retrofit) {
                dialog.dismiss();
                if (response.isSuccess()){
                    if (response.body().getCode() == 200 && null == response.body().getResult().getErrorMsg()){
                        TotalLoginBean login = response.body();
                        Hawk.put(Constants.LOGIN_BEAN,login);
                        Hawk.put(Constants.PASSWORD,password);
                        Map<String,String> body = new HashMap<>();
                        body.put("wsCodeReq","00000009");
                        body.put("userId",login.getResult().getUserId());
                        body.put("deptId",login.getResult().getDeptId());
                        body.put("appCode","0801");

                        getMenu(body);
                    }else if (response.body().getCode() == 200 && "请选择部门登录".equals(response.body().getResult().getErrorMsg())){
                        detpList = response.body().getResult().getDeptIdInfo();
                        showDeptDialog();
                    }
                    else if (response.body().getCode() == 400){
                        Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                if(dialog.isShowing())
                    dialog.dismiss();
                Toast.makeText(LoginActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getMenu(Map<String,String> body){
        final SweetAlertDialog dialog = LoadingDialog.showCanCancelable(mContext);
        dialog.setTitleText("正在获取配置信息，请稍等...");
        dialog.show();
        Call<Result<Menu>> call = ApiManager.hbApi.getMenu(body);
        call.enqueue(new Callback<Result<Menu>>() {
            @Override
            public void onResponse(Response<Result<Menu>> response, Retrofit retrofit) {
                if (response.body()!=null){
                    List<MenuBean> menus = response.body().getObject().getMenus();
                    if(menus != null)
                        makeMenu(menus);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dialog.dismiss();
                Toast.makeText(LoginActivity.this,"获取权限字典失败",Toast.LENGTH_SHORT);
            }
        });
    }

    //配置首页、业务办理、信息查询、统计分析中的菜单
    public void makeMenu(List<MenuBean> menus){
        //首页
        List<MenuBean> menu1 = RootAppcation.getInstance().getFirstPageMenu();
        firstPageMenu = new ArrayList<>();
        for (MenuBean menuBean : menu1){
            if (menuBean.getType() == 0){
                makeData(menuBean,menuBean.getMenuId(),menus);
            }else {
                for (String menuId : menuBean.getMenuIdList()){
                    makeData(menuBean,menuId,menus);
                }
            }
        }
        Hawk.put(Constants.FIRST_PAGE_MENU,firstPageMenu);
        //业务办理
        ywblMenuInfos = new ArrayList<>();
        List<MenuInfo> menu2 = RootAppcation.getInstance().getYwblMenuInfos();
        for (MenuInfo menuInfo : menu2){
            List<MenuBean> beans = menuInfo.getMenus();
            for (MenuBean bean : beans){
                if (bean.getType() == 0){
                    makeData2(bean,bean.getMenuId(),menus,ywblMenuInfos);
                }else {
                    for (String menuId : bean.getMenuIdList()){
                        makeData2(bean,menuId,menus,ywblMenuInfos);
                    }
                }
            }
        }
        Hawk.put(Constants.YWBL_MNEUINFO,ywblMenuInfos);

        //信息查询
        messageMenuInfos = new ArrayList<>();
        //公示信息对所有用户放开
        MenuInfo menuInfo_message_1 = new MenuInfo();
        menuInfo_message_1.setTitle("查询");
        ArrayList<MenuBean> arrayList_message_1 = new ArrayList<>();
        MenuBean bean_message_1 = new MenuBean(Constants.GSXX_NAME,Constants.GSXX_ID,R.mipmap.icon_gsxx);
        arrayList_message_1.add(bean_message_1);
        menuInfo_message_1.setMenus(arrayList_message_1);
        messageMenuInfos.add(menuInfo_message_1);

        List<MenuInfo> menu3 = RootAppcation.getInstance().getMessageMenuInfos();
        for (MenuInfo menuInfo : menu3){
            List<MenuBean> beans = menuInfo.getMenus();
            for (MenuBean bean : beans){
                if (bean.getType() == 0){
                    makeData2(bean,bean.getMenuId(),menus,messageMenuInfos);
                }else {
                    for (String menuId : bean.getMenuIdList()){
                        makeData2(bean,menuId,menus,messageMenuInfos);
                    }
                }
            }
        }

        //统计分析对所有用户放开
        MenuInfo menuInfo = new MenuInfo();
        menuInfo.setTitle("统计");
        ArrayList<MenuBean> list = new ArrayList<>();
        MenuBean menuBean = new MenuBean(Constants.CXTJ_NAME,Constants.CXTJ_ID,R.mipmap.cxtj);
        list.add(menuBean);
        menuInfo.setMenus(list);
        messageMenuInfos.add(menuInfo);
        Hawk.put(Constants.MESSAGE_MENUINFO,messageMenuInfos);
    }

    public void makeData(MenuBean menuBean,String menuId,List<MenuBean> arrayList){
        for (MenuBean bean : arrayList){
            if (menuId.equals(bean.getMenuId())){
                boolean isHave = false;
                for (MenuBean beanData : firstPageMenu) {
                    if (beanData.getMenuName().equals(menuBean.getMenuName())) {
                        isHave = true;
                        break;
                    }
                }
                if (!isHave){
                    firstPageMenu.add(menuBean);
                }
                break;
            }
        }
    }

    public void makeData2(MenuBean menuBean, String menuId, List<MenuBean> list,List<MenuInfo> data){
        for (MenuBean bean : list){
            if (menuId.equals(bean.getMenuId())){
                boolean isHave = false;
                int index = 0;
                String title = "";
                switch (menuBean.getMenuName()){
                    case Constants.AJDC_NAME:
                    case Constants.AJCX_NAME:
                        if(Constants.AJDC_ID.equals(menuId) || Constants.AJCX_ID.equals(menuId))
                            title = "执法办案 (工商行政执法系统)";
                        else
                            title = "执法办案 (市场监督管理局行政执法系统)";
                        break;
                    case Constants.CCJCLR_NAME:
                    case Constants.CCJCCX_NAME:
                        title = "公示抽查检查";
                        break;
                    case Constants.TSJBCL_NAME:
                    case Constants.TSJBCX_NAME:
                        title = "消费维权";
                        break;
                    case Constants.FGDJGL_NAME:
                    case Constants.FGDJCX_NAME:
                        title = "非公党建";
                        break;
                    case Constants.WQCB_NAME:
                        title = "小微企业扶持";
                        break;
                    case Constants.GSXX_NAME:
                    case Constants.FLFG_NAME:
                        title = "查询";
                        break;
                    case Constants.CXTJ_NAME:
                        title = "统计";
                        break;
                }
                for (int i=0;i<data.size();i++){
                    if (data.get(i).getTitle().equals(title)){
                        isHave = true;
                        index = i;
                        break;
                    }
                }
                if (isHave){
                    List<MenuBean> beanList = data.get(index).getMenus();
                    boolean have = false;
                    for (MenuBean bean1 : beanList){
                        if (bean1.getMenuName().equals(menuBean.getMenuName())){
                            have = true;
                            break;
                        }
                    }
                    if (!have){
                        data.get(index).getMenus().add(menuBean);
                    }

                }else {
                    MenuInfo menuInfo1 = new MenuInfo();
                    menuInfo1.setTitle(title);
                    ArrayList<MenuBean> arrayList1 = new ArrayList<>();
                    arrayList1.add(menuBean);
                    menuInfo1.setMenus(arrayList1);
                    data.add(menuInfo1);
                }

                break;
            }
        }
    }

    /**
     * 上海登陆网络请求
     */
    private void shLoginNet() {
        final SweetAlertDialog dialog = LoadingDialog.showNotCancelable(mContext);

        Map<String, String> map = new HashMap<String, String>();
        map.put("padId", "860622020026621");
        map.put("loginName", "cjm0101");
        map.put("password", "111111");
        map.put("organCode", "010000");

        Call<ShLoginBean> call = ApiManager.shApi.login(map);
        call.enqueue(new Callback<ShLoginBean>() {
            @Override
            public void onResponse(Response<ShLoginBean> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    ShLoginBean shLoginBean = response.body();
                    myApplication.setShLoginBean(shLoginBean);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dialog.dismiss();
                Toast.makeText(LoginActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 河北工商登陆二次网络请求
     */
    private void hbLoginNet2() {
        final SweetAlertDialog dialog = LoadingDialog.showNotCancelable(mContext);
//        myApplication.setVersion("河北");
        Map<String, String> body = new HashMap<>();
        final String name = nameText.getText().toString().trim();
        final String pwd = pwdText.getText().toString().trim();


        Map<String, String> map = new HashMap<String, String>();
        map.put("wsCodeReq", "00000001");
        map.put("loginName", name);
        map.put("password", pwd);
        //map.put("password", DigestUtil.md5Hex4String(pwd));
        map.put("deptId", detpList.get(organFlag).split(",")[0]);

//        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        Call<TotalLoginBean> call = ApiManager.hbApi.login(map);
        call.enqueue(new Callback<TotalLoginBean>() {
            @Override
            public void onResponse(Response<TotalLoginBean> response, Retrofit retrofit) {
                if (response.isSuccess()) {

                    TotalLoginBean login = response.body();

                    if ((null == login) || (null == login.getResult()) || !(null == login.getResult().getErrorMsg())) {
                        Toast.makeText(LoginActivity.this, "用户名密码错误", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        return;
                    }else{
                        Log.d("LoginAcitivity", "save info userId = "+login.getResult().getUserId()+"  deptId = "+login.getResult().getDeptId()
                                +"  organId = "+login.getResult().getOrganId());
                        DataShared dataShared = new DataShared(LoginActivity.this);
                        dataShared.put("name", name);
                        dataShared.put("pwd", pwd);
                        dataShared.put("userId",login.getResult().getUserId());
                        dataShared.put("deptId",login.getResult().getDeptId());
                        dataShared.put("organId",login.getResult().getOrganId());
                        dataShared.put("userName",login.getResult().getUserName());
                        dataShared.put("deptName",login.getResult().getDeptName());
                        dataShared.put("organName",login.getResult().getOrganName());
                    }

                    myApplication.setTotalLoginBean(login);
                    myApplication.setTotalDeptList(detpList);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {

                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }

                dialog.dismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                if(dialog.isShowing())dialog.dismiss();
                Toast.makeText(LoginActivity.this, getResources().getString(R.string.error_connect), Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 部门选择dialog
     */
    private void showDeptDialog() {
        if("云南".equals(myApplication.getVersion())) {
            ArrayList<String> items = new ArrayList<String>();
            for(int i=0; i<detpList.size(); i++){
                items.add(detpList.get(i).split(",")[1]);
            }
            final String[] deptStringArray = new String[items.size()];
            items.toArray(deptStringArray);
            new MaterialDialog.Builder(this)
                    .items(deptStringArray)
                    .widgetColor(ContextCompat.getColor(this, R.color.blue))
                    .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                        @Override
                        public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                            organFlag = which;
                            gsythLoginNet(detpList.get(which).split(",")[0]);
                            return true;
                        }
                    })
                    .positiveText("确定")
                    .positiveColor(ContextCompat.getColor(this, R.color.blue))
                    .show();
        }
    }
}
