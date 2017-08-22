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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import com.wondersgroup.commerce.utils.FileHelper;
import com.wondersgroup.commerce.utils.LogHelper;
import com.wondersgroup.commerce.widget.LoadingDialog;
import com.wondersgroup.commerce.ynwq.bean.LoginResult;
import com.wondersgroup.commerce.ynwq.bean.UserResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
                        //免登陆测试
//                        makeMenu(new ArrayList<MenuBean>(),false);
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
                        makeMenu(menus,true);
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

    //配置首页、业务办理、信息查询、统计分析中的菜单、四川的菜单
    public void makeMenu(List<MenuBean> menus,boolean checkable){
        Gson gson = new Gson();
        //读取本地配置的menu
        if (Constants.YN.equals(RootAppcation.getInstance().getVersion())){
            String result = FileHelper.getFromAssets("config_yn.txt", this);
            try {
                JSONObject jsonObject = new JSONObject(result);
                //底部菜单栏
                ArrayList<String> bottom = new ArrayList<>();
                JSONArray bottomMenu = new JSONArray(jsonObject.getString("bottomMenu"));
                for (int i=0; i<bottomMenu.length(); i++){
                    bottom.add(bottomMenu.getJSONObject(i).getString("name"));
                }
                RootAppcation.getInstance().setBottomMenus(bottom);

                //首页菜单
                ArrayList<MenuBean> firstMenus = gson.fromJson(jsonObject.getString("firstMenus"),new TypeToken<ArrayList<MenuBean>>(){}.getType());
                //根据权限按需加载
                if (checkable)
                    remakeMenus(firstMenus,menus);
                //设置颜色
                for(MenuBean menuBean : firstMenus){
                    menuBean.setResId(Constants.firstColorMap().get(menuBean.getMenuId()));
                }
                Hawk.put(Constants.FIRST_PAGE_MENU,firstMenus);

                //业务办理
                ArrayList<MenuInfo> ywblMenus = gson.fromJson(jsonObject.getString("ywblMenus"), new TypeToken<ArrayList<MenuInfo>>() {
                }.getType());
                //根据权限按需加载
                if (checkable)
                    for (MenuInfo menuInfo : ywblMenus) {
                        remakeMenus(menuInfo.getMenus(), menus);
                    }
                //删除子项menus为空的对象
                for (int i=0; i<ywblMenus.size(); i++){
                    if (ywblMenus.get(i).getMenus().size() == 0) {
                        ywblMenus.remove(i);
                        i--;
                    }
                }
                //配置图标
                for (MenuInfo menuInfo : ywblMenus){
                    for (MenuBean menuBean : menuInfo.getMenus()){
                        menuBean.setResId(Constants.menuIconMapYN().get(menuBean.getMenuId()));
                    }
                }
                Hawk.put(Constants.YWBL_MNEUINFO,ywblMenus);

                //查询统计
                ArrayList<MenuInfo> tongjiMenus = gson.fromJson(jsonObject.getString("tongjiMenus"),new TypeToken<ArrayList<MenuInfo>>(){}.getType());
                //公示信息、统计分析两个模块对所有用户放开
                ArrayList<MenuBean> menusTemp = new ArrayList<>();
                //公示信息
                MenuBean bean1 = new MenuBean(Constants.GSXX_ID,0);
                menusTemp.add(bean1);
                //统计分析
                MenuBean bean2 = new MenuBean(Constants.CXTJ_ID,0);
                menusTemp.add(bean2);
                //法律法规的权限和案件调查保持一致
                boolean isHave = false;
                loop1:for (MenuInfo menuInfo : ywblMenus){
                    loop2:for (MenuBean bean : menuInfo.getMenus()){
                                if (Constants.AJDC_ID.equals(bean.getMenuId())){
                                    isHave = true;
                                    break loop1;
                                }
                    }
                }
                //法律法规
                if (isHave){
                    MenuBean bean3 = new MenuBean(Constants.FLFG_ID,0);
                    menusTemp.add(bean3);
                }

                menusTemp.addAll(menus);
                //根据权限按需加载
                if (checkable)
                    for (MenuInfo menuInfo : tongjiMenus) {
                        remakeMenus(menuInfo.getMenus(), menusTemp);
                    }
                //删除子项menus为空的对象
                for (int i=0; i<tongjiMenus.size(); i++){
                    if (tongjiMenus.get(i).getMenus().size() == 0){
                        tongjiMenus.remove(i);
                        i--;
                    }
                }
                //配置图标
                for (MenuInfo menuInfo : tongjiMenus){
                    for (MenuBean menuBean : menuInfo.getMenus()){
                        menuBean.setResId(Constants.menuIconMapYN().get(menuBean.getMenuId()));
                    }
                }
                Hawk.put(Constants.MESSAGE_MENUINFO,tongjiMenus);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if (Constants.SC.equals(RootAppcation.getInstance().getVersion())){
            String result = FileHelper.getFromAssets("config_sc.txt", this);
            ArrayList<MenuBean> menuList = gson.fromJson(result,new TypeToken<ArrayList<MenuBean>>(){}.getType());
            remakeMenus(menuList,menus);
            //配置图标
            for (MenuBean menuBean : menuList){
                menuBean.setResId(Constants.menuIconMapSC().get(menuBean.getMenuId()));
            }
            Hawk.put(Constants.MENU_SC,menuList);
            RootAppcation.getInstance().setBottomMenus(new ArrayList<String>());
        }
    }

    public void remakeMenus(List<MenuBean> menuBeans,List<MenuBean> menus){
        for (int i=0; i<menuBeans.size(); i++){
            MenuBean menuBean = menuBeans.get(i);
            if (menuBean.getType() == 0){
                String menuId = menuBean.getMenuId();
                boolean isHave = false;
                for (MenuBean bean : menus){
                    if (menuId.equals(bean.getMenuId())){
                        isHave = true;
                        break;
                    }
                }
                if (!isHave){
                    menuBeans.remove(i);
                    i--;
                }
            }else {
                String[] menuIds = menuBean.getMenuId().split(",");
                boolean isHave = false;
                loop1:for (String menuId : menuIds){
                    loop2:for (MenuBean bean : menus){
                        if (menuId.equals(bean.getMenuId())){
                            isHave = true;
                            break loop1;
                        }
                    }
                }
                if (!isHave){
                    menuBeans.remove(i);
                    i--;
                }
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
