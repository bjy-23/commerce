package com.wondersgroup.commerce.application;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Vibrator;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.DisplayMetrics;import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;
import com.wondersgroup.commerce.model.MenuBean;
import com.wondersgroup.commerce.model.MenuInfo;
import com.wondersgroup.commerce.model.TotalLoginBean;
import com.wondersgroup.commerce.model.MenuModel;
import com.wondersgroup.commerce.model.ShLoginBean;
import com.wondersgroup.commerce.model.yn.YnLoginBean;
import com.wondersgroup.commerce.teamwork.dailycheck.BigBean;
import com.wondersgroup.commerce.teamwork.dailycheck.EtpsBean;
import com.wondersgroup.commerce.teamwork.dailycheck.InfoBean;
import com.wondersgroup.commerce.teamwork.dailycheck.LoginUserInfo;
import com.wondersgroup.commerce.teamwork.mysupervision.AllDic;
import com.wondersgroup.commerce.teamwork.mysupervision.CatalogFixBean;
import com.wondersgroup.commerce.teamwork.mysupervision.CnApp;
import com.wondersgroup.commerce.teamwork.mysupervision.CnCaseVo;
import com.wondersgroup.commerce.teamwork.mysupervision.CnUpload;
import com.wondersgroup.commerce.teamwork.mysupervision.CompanyListBean;
import com.wondersgroup.commerce.teamwork.mysupervision.PersonBean;
import com.wondersgroup.commerce.teamwork.mysupervision.RecordBean;
import com.wondersgroup.commerce.utils.CrashHandler;
import com.wondersgroup.commerce.utils.PicassoImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;import java.util.List;

/**
 * Created by kangrenhui on 2016/1/18.
 */
public class RootAppcation extends MultiDexApplication {
    private static RootAppcation instance;

    private ArrayList<MenuModel> menuBtnList = new ArrayList<>();
    private String myTheme;
    private String version;
    private ArrayList<String> bottomMenus;
    private Map<String, Integer> meduleMap;

    private TotalLoginBean totalLoginBean;
    private ShLoginBean shLoginBean;
    private YnLoginBean ynLoginBean;
    private List<String> totalDeptList;

    private ArrayList<Integer> functionList;

    private String cachePath;
    private List<RecordBean> recordBeans;
    private Boolean isSign;
    private Boolean hasGetDic = false;
    private AllDic allDic;
    private List<CnApp> searchCnApps = new ArrayList<CnApp>();
    private List<CnApp> complainCnApps;
    private List<CnApp> reportCnApps;
    private CnCaseVo cnCaseVo = new CnCaseVo();
    private ArrayList<CompanyListBean> dataList = new ArrayList<CompanyListBean>();
    private HashMap<String, String> dicEntTypeHashMap;
    private HashMap<String, ArrayList<CatalogFixBean>> dicLayoutConfigMap;
    private String countUser = "";
    private String countOrgan = "";
    private String checkTypeName = "";
    private List<CnUpload> cnUploads = new ArrayList<CnUpload>();
    private List<PersonBean> personBeans = new ArrayList<PersonBean>();

    private int widthPixels,heightPixels;
    private List<MenuBean> firstPageMenu;
    private List<MenuInfo> ywblMenuInfos;
    private List<MenuInfo> messageMenuInfos;
    private List<MenuBean> scMenuInfos;

    private String versionName;//版本号

    public static RootAppcation getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        cachePath = getApplicationContext().getExternalCacheDir().toString();

        //捕获全局异常
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(this);

        //初始化ImageLoader
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(
                getApplicationContext());
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs();
        ImageLoader.getInstance().init(config.build());

        Hawk.init(this)
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
                .setStorage(HawkBuilder.newSqliteStorage(this))
                .setLogLevel(LogLevel.FULL)
                .build();

//        JPushInterface.init(this);
//        JPushInterface.setDebugMode(true);

        //第三方-图片选择器配置
        imgPickerConfig();

        //获取手机屏幕宽高
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        widthPixels = dm.widthPixels;
        //高度值包含了状态栏的高度(华为手机，其他未知)
        heightPixels = dm.heightPixels;

        //获取状态栏高度
        int statusBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0){
            Log.e("status_bar_height", getResources().getDimensionPixelSize(resourceId) + "");
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }

        heightPixels -= statusBarHeight;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public String getCachePath() {
        return cachePath;
    }

    public ArrayList<MenuModel> getMenuBtnList() {
        return menuBtnList;
    }

    public void setMenuBtnList(ArrayList<MenuModel> menuBtnList) {
        this.menuBtnList = menuBtnList;
    }

    public String getMyTheme() {
        return myTheme;
    }

    public void setMyTheme(String myTheme) {
        this.myTheme = myTheme;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public TotalLoginBean getTotalLoginBean() {
        return totalLoginBean;
    }

    public void setTotalLoginBean(TotalLoginBean totalLoginBean) {
        this.totalLoginBean = totalLoginBean;
    }

    public ShLoginBean getShLoginBean() {
        return shLoginBean;
    }

    public void setShLoginBean(ShLoginBean shLoginBean) {
        this.shLoginBean = shLoginBean;
    }

    public YnLoginBean getYnLoginBean() {
        return ynLoginBean;
    }

    public void setYnLoginBean(YnLoginBean ynLoginBean) {
        this.ynLoginBean = ynLoginBean;
    }

    public List<String> getTotalDeptList() {
        return totalDeptList;
    }

    public void setTotalDeptList(List<String> totalDeptList) {
        this.totalDeptList = totalDeptList;
    }

    public Map<String, Integer> getMeduleMap() {
        return meduleMap;
    }
    public ArrayList<Integer> getFunctionList() {
        return functionList;
    }

    public void setFunctionList(ArrayList<Integer> functionList) {
        this.functionList = functionList;
    }

    public void imgPickerConfig(){
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new PicassoImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }

    //---------------------------------------云南曲靖-------------------------------------//
    private LoginUserInfo loginUserInfo = new LoginUserInfo();
    private List<EtpsBean> etpsBeans;
    private String caseId;
    private BigBean bigBean = new BigBean();
    private InfoBean infoBean = new InfoBean();
    private String recordId;
    private String gpsInfoString = "";
    private String gpsInfo = "";
    private String problemName = "";
    private String problem = "";
    private float density;

    public LoginUserInfo getLoginUserInfo() {
        return loginUserInfo;
    }

    public void setLoginUserInfo(LoginUserInfo loginUserInfo) {
        this.loginUserInfo = loginUserInfo;
    }

    public List<EtpsBean> getEtpsBeans() {
        return etpsBeans;
    }

    public void setEtpsBeans(List<EtpsBean> etpsBeans) {
        this.etpsBeans = etpsBeans;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public BigBean getBigBean() {
        return bigBean;
    }

    public void setBigBean(BigBean bigBean) {
        this.bigBean = bigBean;
    }

    public InfoBean getInfoBean() {
        return infoBean;
    }

    public void setInfoBean(InfoBean infoBean) {
        this.infoBean = infoBean;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getGpsInfoString() {
        return gpsInfoString;
    }

    public void setGpsInfoString(String gpsInfoString) {
        this.gpsInfoString = gpsInfoString;
    }

    public String getGpsInfo() {
        return gpsInfo;
    }

    public void setGpsInfo(String gpsInfo) {
        this.gpsInfo = gpsInfo;
    }

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public float getDensity() {
        return density;
    }

    public void setDensity(float density) {
        this.density = density;
    }

    public String getCheckTypeName() {
        return checkTypeName;
    }

    public void setCheckTypeName(String checkTypeName) {
        this.checkTypeName = checkTypeName;
    }

    public String getCountUser() {
        return countUser;
    }

    public void setCountUser(String countUser) {
        this.countUser = countUser;
    }

    public String getCountOrgan() {
        return countOrgan;
    }

    public void setCountOrgan(String countOrgan) {
        this.countOrgan = countOrgan;
    }

    public ArrayList<CompanyListBean> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<CompanyListBean> dataList) {
        this.dataList = dataList;
    }

    public List<CnApp> getComplainCnApps() {
        return complainCnApps;
    }

    public void setComplainCnApps(List<CnApp> complainCnApps) {
        this.complainCnApps = complainCnApps;
    }

    public AllDic getAllDic() {
        return allDic;
    }

    public void setAllDic(AllDic allDic) {
        this.allDic = allDic;
    }


    public List<CnUpload> getCnUploads() {
        return cnUploads;
    }

    public void setCnUploads(List<CnUpload> cnUploads) {
        this.cnUploads = cnUploads;
    }


    public int getWidthPixels() {
        return widthPixels;
    }

    public int getHeightPixels() {
        return heightPixels;
    }

    public List<MenuBean> getFirstPageMenu() {
        return firstPageMenu;
    }

    public ArrayList<String> getBottomMenus() {
        return bottomMenus;
    }

    public void setBottomMenus(ArrayList<String> bottomMenus) {
        this.bottomMenus = bottomMenus;
    }

    public void setFirstPageMenu(List<MenuBean> firstPageMenu) {
        this.firstPageMenu = firstPageMenu;
    }

    public List<MenuInfo> getYwblMenuInfos() {
        return ywblMenuInfos;
    }

    public void setYwblMenuInfos(List<MenuInfo> ywblMenuInfos) {
        this.ywblMenuInfos = ywblMenuInfos;
    }

    public List<MenuInfo> getMessageMenuInfos() {
        return messageMenuInfos;
    }

    public void setMessageMenuInfos(List<MenuInfo> messageMenuInfos) {
        this.messageMenuInfos = messageMenuInfos;
    }

    public List<MenuBean> getScMenuInfos() {
        return scMenuInfos;
    }

    public void setScMenuInfos(List<MenuBean> scMenuInfos) {
        this.scMenuInfos = scMenuInfos;
    }

    public String getVersionName() {
        if (versionName == null){
            synchronized (RootAppcation.class){
                if (versionName == null){
                    PackageManager packageManager = this.getPackageManager();
                    PackageInfo packInfo = null;
                    try {
                        packInfo = packageManager.getPackageInfo(this.getPackageName(), 0);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                    versionName = packInfo.versionName;
                }
            }
        }
        return versionName;
    }
}
