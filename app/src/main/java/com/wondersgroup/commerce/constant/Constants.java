package com.wondersgroup.commerce.constant;

import com.wondersgroup.commerce.R;

import java.util.HashMap;

/**
 * Created by yclli on 2015/12/1.
 */
public class Constants {

    public final static String SC = "四川";
    public final static String YN = "云南";
    public final static String MODE_1 = "release";
    public final static String MODE_2 = "debug";

    public final static String VERSION = YN;
    public final static String MODE = MODE_2;

    public static HashMap<String,Integer> firstColorMap;
    public static HashMap<String,Integer> menuIconMapYN;
    public static HashMap<String,Integer> menuIconMapSC;

    /*
    * 提示语
    * */
    public final static String NO_DB = "暂无待办事项";
    /*
   * 通用标识符
   * */
    public final static String DATA = "data";
    public final static String TYPE = "type";
    public final static String USER_ID = "userId";
    public final static String ORGAN_ID = "organId";
    public final static String DEPT_ID = "deptId";
    public final static String LOGIN_BEAN = "loginBean";
    public final static String LOGIN_NAME = "loginName";
    public final static String PASSWORD = "password";
    public final static String AUTH_MENU ="authMenu";
    public final static String TITLE = "title";
    public final static String POSITION = "position";

    /*
    * 通用操作符
    * */
    public final static String ADD = "add";
    public final static String EDIT = "edit";
    public final static String SAVE = "保存";


    /*
    * 菜单
    * */
    public final static String FIRST_PAGE_MENU = "firstPageMenu";
    public final static String YWBL_MNEUINFO = "ywblMenuInfo";
    public final static String MESSAGE_MENUINFO = "messageMenuInfo";
    public final static String MENU_SC = "menuSC";


    /*
    * 中文
    * */

    public final static String ADD_CHS = "添加";
    public final static String DEPT = "省工商行政管理局";


    /*
    * 抽查检查
    * */
    public final static String SDHC = "实地核查";

    //输入限制
    public static final int inputMinCount   =   3;
    public static final int inputMaxCount   =   50;

    public static final String SY = "首页";
    public static final String YWBL = "业务办理";
    public static final String XXCX = "查询统计";
    public static final String WD = "设置";

    public static final String CCJCLR_ID = "05021530,05022530,05023530,05024530";
    public static final String CCJCLR_NAME = "抽查检查录入\n(实地核查)";
    public static final String CCJCCX_ID = "05021580,05022580,05023580,05024580";
    public static final String CCJCCX_NAME = "抽查检查查询";
    public static final String AJDC_ID = "03010033";
    public static final String AJDC_NAME = "案件调查";
    public static final String WDAJCX_ID = "03010033";
    public static final String WDAJCX_NAME = "我的案件查询";
    public static final String AJCX_ID = "03010009";
    public static final String AJCX_NAME = "案件查询";
    public static final String AJDC_ID_2 = "03040033";
    public static final String AJDC_NAME_2 = "案件调查";
    public static final String WDAJCX_ID_2 = "03040033";
    public static final String WDAJCX_NAME_2 = "我的案件查询";
    public static final String JYCX_ID = "08011007";
    public static final String JYCX_NAME = "简易程序处理";
    public static final String TSJBCL_ID = "02050141";
    public static final String TSJBCL_NAME = "投诉举报处理";
    public static final String TSJBCX_ID = "02050214";
    public static final String TSJBCX_NAME = "投诉举报查询";
    public static final String FGDJGL_ID = "01120603";
    public static final String FGDJGL_NAME = "非公党建管理";
    public static final String FGDJCX_ID = "01120602";
    public static final String FGDJCX_NAME = "非公党建查询";
    public static final String WQCB_ID = "01103000";
    public static final String WQCB_NAME = "微企财补初审";
    public static final String GSXX_ID = "08012001";
    public static final String GSXX_NAME = "公示信息查询";
    public static final String QYGSXX_NAME = "企业信用公示\n信息查询";
    public static final String XYGS_DJ_NAME = "双随机结果录入";
    public static final String XYGS_DJ_ID = "333333";
    public static final String XYGS_JL_NAME = "双随机结果查询";
    public static final String XYGS_JL_ID = "222222";
    public static final String SBCX_ID = "02077110";
    public static final String SBCX_NAME = "注册商标查询";
    public static final String FLFG_ID = "03010033";//法律法规的权限和案件调查保持一致
    public static final String FLFG_NAME = "法律法规查询";
    public static final String CXTJ_ID = "03010502";
    public static final String CXTJ_NAME = "统计分析";
    public static final String TXL_ID = "08013001";
    public static final String TXL_NAME = "通讯录";
    public static final String TZGG_ID = "08013003";
    public static final String TZGG_NAME = "通知公告";
    public static final String GWPY_ID = "08011003";
    public static final String GWPY_NAME = "公文批阅";
    public static final String GWJS_ID = "08011004";
    public static final String GWJS_NAME = "公文检索";

    public final static String REMIND = "消息提醒";
    public final static String ABOUT_US = "关于我们";
    public final static String UPDATE = "版本更新";


    public static final String gwpy = "公文批阅";
    public static final String gwjs = "公文检索";
    public static final String wzjy = "无照经营";
    public static final String rcjc = "日常检查";
    public static final String zxjc = "专项检查";
    public static final String ajdc = "案件调查";
    public static final String ajcx = "案件查询";
    public static final String ztxxcx = "主体信息查询";
    public static final String hwggcx = "户外广告查询";
    public static final String xqzgdq = "限期整改到期";
    public static final String hwggdq = "户外广告到期";
    public static final String ywxt = "业务系统";
    public static final String fwqy = "服务企业";
    public static final String TXL = "通讯录";
    public static final String bwl = "备忘录";
    public static final String tpcj = "图片裁剪";
    public static final String ywtj = "云南业务统计";
    public static final String tj = "统计";
    public static final String jdgl = "监督管理";
    public static final String jcjgno = "检查结果不正常";
    public static final String jyqxdq = "经营期限到期";
    public static final String zxzz = "专项整治";
    public static final String tsjbcl = "投诉举报处理";
    public static final String tsjbcx = "投诉举报查询";
    public static final String zdjg = "重点监管";
    public static final String ccjcdb="抽查检查录入";//双随机
    public static final String ccjccx="抽查检查查询";
    public static final String rcxc="日常巡查";
    public static final String ydjyxz="异地经营新增";
    public static final String jycxaj="简易程序案件";

    public static final String ccjglr="抽查结果录入";
    public static final String ccjgcx="抽查结果查询";
    public static final String gsxx="公示信息查询";
    public static final String wqcbcs="微企财补初审";
    public static final String fgdjgl="非公党建管理";
    public static final String fgdjcx="非公党建查询";
    public static final String LAW_RULE_QUERY = "法律法规查询";
    public static final String QUERY_COUNT = "查询统计";
    public static final String BUSINESS_COUNT = "业务统计";


    //统计地址,测试环境
    public static final String TJ_URL_1 ="http://10.1.8.203/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e5%86%85%e8%b5%84%e7%99%bb%e8%ae%b0%27%5d%2ffolder%5b%40name%3d%27%e5%86%85%e8%b5%84%e6%9c%ac%e6%9c%9f%27%5d%2freport%5b%40name%3d%27%e5%86%85%e8%b5%84%e6%9c%ac%e6%9c%9f1%27%5d&ui.name=%e5%86%85%e8%b5%84%e6%9c%ac%e6%9c%9f1&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public static final String TJ_URL_2 ="http://10.1.8.203/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e5%86%85%e8%b5%84%e7%99%bb%e8%ae%b0%27%5d%2ffolder%5b%40name%3d%27%e5%86%85%e8%b5%84%e6%9c%ac%e6%9c%9f%27%5d%2freport%5b%40name%3d%27%e5%86%85%e8%b5%84%e6%9c%ac%e6%9c%9f2%27%5d&ui.name=%e5%86%85%e8%b5%84%e6%9c%ac%e6%9c%9f2&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public static final String TJ_URL_3 ="http://10.1.8.203/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e5%86%85%e8%b5%84%e7%99%bb%e8%ae%b0%27%5d%2ffolder%5b%40name%3d%27%e5%86%85%e8%b5%84%e6%9c%ac%e6%9c%9f%27%5d%2freport%5b%40name%3d%27%e5%86%85%e8%b5%84%e6%9c%ac%e6%9c%9f3%27%5d&ui.name=%e5%86%85%e8%b5%84%e6%9c%ac%e6%9c%9f3&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public static final String TJ_URL_4 ="http://10.1.8.203/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e5%a4%96%e8%b5%84%e7%99%bb%e8%ae%b0%27%5d%2ffolder%5b%40name%3d%27%e5%a4%96%e8%b5%84%e6%9c%ac%e6%9c%9f%27%5d%2freport%5b%40name%3d%27%e5%a4%96%e8%b5%84%e6%9c%ac%e6%9c%9f1%27%5d&ui.name=%e5%a4%96%e8%b5%84%e6%9c%ac%e6%9c%9f1&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public static final String TJ_URL_5 ="http://10.1.8.203/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e5%a4%96%e8%b5%84%e7%99%bb%e8%ae%b0%27%5d%2ffolder%5b%40name%3d%27%e5%a4%96%e8%b5%84%e6%9c%ac%e6%9c%9f%27%5d%2freport%5b%40name%3d%27%e5%a4%96%e8%b5%84%e6%9c%ac%e6%9c%9f2%27%5d&ui.name=%e5%a4%96%e8%b5%84%e6%9c%ac%e6%9c%9f2&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public static final String TJ_URL_6 ="http://10.1.8.203/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e5%a4%96%e8%b5%84%e7%99%bb%e8%ae%b0%27%5d%2ffolder%5b%40name%3d%27%e5%a4%96%e8%b5%84%e6%9c%ac%e6%9c%9f%27%5d%2freport%5b%40name%3d%27%e5%a4%96%e8%b5%84%e6%9c%ac%e6%9c%9f4%27%5d&ui.name=%e5%a4%96%e8%b5%84%e6%9c%ac%e6%9c%9f4&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public static final String TJ_URL_7 ="http://10.1.8.203/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e5%92%a8%e8%af%a2%ef%bc%88%e6%9d%83%e9%99%90%ef%bc%89%27%5d%2freport%5b%40name%3d%27%e6%b6%88%e8%b4%b9%e7%bb%b4%e6%9d%83%e5%92%a8%e8%af%a2%e8%8c%83%e5%9b%b4%e7%9a%84%e5%92%a8%e8%af%a2%e6%83%85%e5%86%b5%27%5d&ui.name=%e6%b6%88%e8%b4%b9%e7%bb%b4%e6%9d%83%e5%92%a8%e8%af%a2%e8%8c%83%e5%9b%b4%e7%9a%84%e5%92%a8%e8%af%a2%e6%83%85%e5%86%b5&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public static final String TJ_URL_8 ="http://10.1.8.203:80/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%2712315%27%5d%2freport%5b%40name%3d%27%e6%9c%ac%e6%9c%9f%e6%8a%95%e8%af%89%e4%b8%be%e6%8a%a5%e7%99%bb%e8%ae%b0%e7%83%ad%e7%82%b9%e5%95%86%e5%93%81%27%5d&ui.name=%e6%9c%ac%e6%9c%9f%e6%8a%95%e8%af%89%e4%b8%be%e6%8a%a5%e7%99%bb%e8%ae%b0%e7%83%ad%e7%82%b9%e5%95%86%e5%93%81&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public static final String TJ_URL_9 ="http://10.1.8.203/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%2712315%27%5d%2freport%5b%40name%3d%27%e6%9c%ac%e6%9c%9f%e6%8a%95%e8%af%89%e4%b8%be%e6%8a%a5%e7%99%bb%e8%ae%b0%e7%83%ad%e7%82%b9%e6%9c%8d%e5%8a%a1%27%5d&ui.name=%e6%9c%ac%e6%9c%9f%e6%8a%95%e8%af%89%e4%b8%be%e6%8a%a5%e7%99%bb%e8%ae%b0%e7%83%ad%e7%82%b9%e6%9c%8d%e5%8a%a1&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public static final String TJ_URL_10 ="http://10.1.8.203/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%2712315%27%5d%2freport%5b%40name%3d%27%e6%8a%95%e8%af%89%e4%b8%be%e6%8a%a5%e8%b6%8b%e5%8a%bf%27%5d&ui.name=%e6%8a%95%e8%af%89%e4%b8%be%e6%8a%a5%e8%b6%8b%e5%8a%bf&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public static final String TJ_URL_11 ="http://10.1.8.203/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e8%a1%8c%e6%94%bf%e5%a4%84%e7%bd%9a%27%5d%2freport%5b%40name%3d%27%e8%a1%8c%e6%94%bf%e5%a4%84%e7%bd%9a1%27%5d&ui.name=%e8%a1%8c%e6%94%bf%e5%a4%84%e7%bd%9a1&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public static final String TJ_URL_12 ="http://10.1.8.203/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e8%a1%8c%e6%94%bf%e5%a4%84%e7%bd%9a%27%5d%2freport%5b%40name%3d%27%e8%a1%8c%e6%94%bf%e5%a4%84%e7%bd%9a2%27%5d&ui.name=%e8%a1%8c%e6%94%bf%e5%a4%84%e7%bd%9a2&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public static final String TJ_URL_13 ="http://10.1.8.203/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e8%a1%8c%e6%94%bf%e5%a4%84%e7%bd%9a%27%5d%2freport%5b%40name%3d%27%e8%a1%8c%e6%94%bf%e5%a4%84%e7%bd%9a3%27%5d&ui.name=%e8%a1%8c%e6%94%bf%e5%a4%84%e7%bd%9a3&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";

    //统计-云南
    public final static String TJ_YN_QYNB_LINK_1 = "http://172.28.129.51:80/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e4%bc%81%e4%b8%9a%e5%b9%b4%e6%8a%a5%27%5d%2freport%5b%40name%3d%271.%e8%bf%91%e4%ba%94%e5%b9%b4%e6%89%80%e6%9c%89%e5%b8%82%e5%9c%ba%e4%b8%bb%e4%bd%93%e5%b9%b4%e6%8a%a5%e4%b8%8a%e6%8a%a5%e8%b6%8b%e5%8a%bf%e5%9b%be%27%5d&ui.name=1.%e8%bf%91%e4%ba%94%e5%b9%b4%e6%89%80%e6%9c%89%e5%b8%82%e5%9c%ba%e4%b8%bb%e4%bd%93%e5%b9%b4%e6%8a%a5%e4%b8%8a%e6%8a%a5%e8%b6%8b%e5%8a%bf%e5%9b%be&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public final static String TJ_YN_QYNB_LINK_2 = "http://172.28.129.51:80/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e4%bc%81%e4%b8%9a%e5%b9%b4%e6%8a%a5%27%5d%2freport%5b%40name%3d%272.%e8%bf%91%e4%ba%94%e5%b9%b4%e7%ac%ac%e4%b8%80%e4%ba%8c%e4%b8%89%e4%ba%a7%e4%b8%9a%e5%8f%91%e5%b1%95%e8%b6%8b%e5%8a%bf%27%5d&ui.name=2.%e8%bf%91%e4%ba%94%e5%b9%b4%e7%ac%ac%e4%b8%80%e4%ba%8c%e4%b8%89%e4%ba%a7%e4%b8%9a%e5%8f%91%e5%b1%95%e8%b6%8b%e5%8a%bf&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public final static String TJ_YN_QYNB_LINK_3 = "http://172.28.129.51:80/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e4%bc%81%e4%b8%9a%e5%b9%b4%e6%8a%a5%27%5d%2freport%5b%40name%3d%273.%e5%8e%86%e5%b9%b4%e5%b7%b2%e5%b9%b4%e6%8a%a5%e3%80%81%e5%ba%94%e5%b9%b4%e6%8a%a5%e7%9a%84%e6%af%94%e7%8e%87%e8%b6%8b%e5%8a%bf%27%5d&ui.name=3.%e5%8e%86%e5%b9%b4%e5%b7%b2%e5%b9%b4%e6%8a%a5%e3%80%81%e5%ba%94%e5%b9%b4%e6%8a%a5%e7%9a%84%e6%af%94%e7%8e%87%e8%b6%8b%e5%8a%bf&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public final static String TJ_YN_QYNB_LINK_4 = "http://172.28.129.51:80/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e4%bc%81%e4%b8%9a%e5%b9%b4%e6%8a%a5%27%5d%2freport%5b%40name%3d%274.2016-2017%e8%bf%916%e4%b8%aa%e6%9c%88%e5%b7%b2%e5%b9%b4%e6%8a%a5%e5%90%8c%e6%af%94%e6%83%85%e5%86%b5%27%5d&ui.name=4.2016-2017%e8%bf%916%e4%b8%aa%e6%9c%88%e5%b7%b2%e5%b9%b4%e6%8a%a5%e5%90%8c%e6%af%94%e6%83%85%e5%86%b5&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public final static String TJ_YN_QYNB_LINK_5 = "http://172.28.129.51:80/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e4%bc%81%e4%b8%9a%e5%b9%b4%e6%8a%a5%27%5d%2freport%5b%40name%3d%275.%e6%8e%92%e5%90%8d%e5%8d%81%e4%bd%8d%e8%a1%8c%e4%b8%9a%e7%9a%84%e5%88%a9%e6%b6%a6%e7%bb%9f%e8%ae%a1%e5%9b%be%27%5d&ui.name=5.%e6%8e%92%e5%90%8d%e5%8d%81%e4%bd%8d%e8%a1%8c%e4%b8%9a%e7%9a%84%e5%88%a9%e6%b6%a6%e7%bb%9f%e8%ae%a1%e5%9b%be&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public final static String TJ_YN_QYNB_LINK_6 = "http://172.28.129.51:80/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e4%bc%81%e4%b8%9a%e5%b9%b4%e6%8a%a5%27%5d%2freport%5b%40name%3d%276.%e6%8e%92%e5%90%8d%e5%8d%81%e4%bd%8d%e8%a1%8c%e4%b8%9a%e7%9a%84%e8%a1%8c%e4%b8%9a%e7%ba%b3%e7%a8%8e%e7%bb%9f%e8%ae%a1%e5%9b%be%27%5d&ui.name=6.%e6%8e%92%e5%90%8d%e5%8d%81%e4%bd%8d%e8%a1%8c%e4%b8%9a%e7%9a%84%e8%a1%8c%e4%b8%9a%e7%ba%b3%e7%a8%8e%e7%bb%9f%e8%ae%a1%e5%9b%be&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public final static String TJ_YN_QYNB_LINK_7 = "http://172.28.129.51:80/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e4%bc%81%e4%b8%9a%e5%b9%b4%e6%8a%a5%27%5d%2freport%5b%40name%3d%277.%e6%8e%92%e5%90%8d%e5%8d%81%e4%bd%8d%e8%a1%8c%e4%b8%9a%e7%9a%84%e8%a1%8c%e4%b8%9a%e8%90%a5%e4%b8%9a%e6%94%b6%e5%85%a5%e7%bb%9f%e8%ae%a1%e5%9b%be%27%5d&ui.name=7.%e6%8e%92%e5%90%8d%e5%8d%81%e4%bd%8d%e8%a1%8c%e4%b8%9a%e7%9a%84%e8%a1%8c%e4%b8%9a%e8%90%a5%e4%b8%9a%e6%94%b6%e5%85%a5%e7%bb%9f%e8%ae%a1%e5%9b%be&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public final static String TJ_YN_QYNB_LINK_8 = "http://172.28.129.51:80/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e4%bc%81%e4%b8%9a%e5%b9%b4%e6%8a%a5%27%5d%2freport%5b%40name%3d%279.%e8%bf%9e%e7%bb%adn%e5%b9%b4%e6%9c%aa%e5%b9%b4%e6%8a%a5%e7%9a%84%e5%9b%be%e8%a1%a8%27%5d&ui.name=9.%e8%bf%9e%e7%bb%adn%e5%b9%b4%e6%9c%aa%e5%b9%b4%e6%8a%a5%e7%9a%84%e5%9b%be%e8%a1%a8&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";

    public final static String TJ_YN_XWXX_LINK_1 = "http://172.28.129.51:80/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e5%b0%8f%e5%be%ae%e4%bf%a1%e6%81%af%27%5d%2freport%5b%40name%3d%271.%e5%b0%8f%e5%be%ae%e4%bc%81%e4%b8%9a%e5%90%84%e6%b3%a8%e5%86%8c%e8%b5%84%e6%9c%ac%e8%8c%83%e5%9b%b4%e6%95%b0%e9%87%8f%e7%bb%9f%e8%ae%a1%e5%9b%be%27%5d&ui.name=1.%e5%b0%8f%e5%be%ae%e4%bc%81%e4%b8%9a%e5%90%84%e6%b3%a8%e5%86%8c%e8%b5%84%e6%9c%ac%e8%8c%83%e5%9b%b4%e6%95%b0%e9%87%8f%e7%bb%9f%e8%ae%a1%e5%9b%be&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public final static String TJ_YN_XWXX_LINK_2 = "http://172.28.129.51:80/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e5%b0%8f%e5%be%ae%e4%bf%a1%e6%81%af%27%5d%2freport%5b%40name%3d%272.%e5%90%84%e6%9c%ba%e5%85%b3%e5%b0%8f%e5%be%ae%e4%bc%81%e4%b8%9a%e6%95%b0%e9%87%8f%e7%bb%9f%e8%ae%a1%e5%9b%be%27%5d&ui.name=2.%e5%90%84%e6%9c%ba%e5%85%b3%e5%b0%8f%e5%be%ae%e4%bc%81%e4%b8%9a%e6%95%b0%e9%87%8f%e7%bb%9f%e8%ae%a1%e5%9b%be&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public final static String TJ_YN_XWXX_LINK_3 = "http://172.28.129.51:80/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e5%b0%8f%e5%be%ae%e4%bf%a1%e6%81%af%27%5d%2freport%5b%40name%3d%273.%e8%bf%916%e4%b8%aa%e6%9c%88%e5%85%a8%e7%9c%81%e5%b0%8f%e5%be%ae%e4%bc%81%e4%b8%9a%e4%ba%ab%e5%8f%97%e6%89%b6%e6%8c%81%e9%87%91%e9%a2%9d%e8%b6%8b%e5%8a%bf%e5%9b%be%27%5d&ui.name=3.%e8%bf%916%e4%b8%aa%e6%9c%88%e5%85%a8%e7%9c%81%e5%b0%8f%e5%be%ae%e4%bc%81%e4%b8%9a%e4%ba%ab%e5%8f%97%e6%89%b6%e6%8c%81%e9%87%91%e9%a2%9d%e8%b6%8b%e5%8a%bf%e5%9b%be&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public final static String TJ_YN_XWXX_LINK_4= "http://172.28.129.51:80/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e5%b0%8f%e5%be%ae%e4%bf%a1%e6%81%af%27%5d%2freport%5b%40name%3d%274.%e6%8e%92%e5%90%8d%e5%89%8d%e5%8d%81%e7%9a%84%e8%a1%8c%e4%b8%9a%e5%b0%8f%e5%be%ae%e4%bc%81%e4%b8%9a%e6%95%b0%e9%87%8f%e7%bb%9f%e8%ae%a1%e5%9b%be%27%5d&ui.name=4.%e6%8e%92%e5%90%8d%e5%89%8d%e5%8d%81%e7%9a%84%e8%a1%8c%e4%b8%9a%e5%b0%8f%e5%be%ae%e4%bc%81%e4%b8%9a%e6%95%b0%e9%87%8f%e7%bb%9f%e8%ae%a1%e5%9b%be&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public final static String TJ_YN_XWXX_LINK_5 = "http://172.28.129.51:80/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e5%b0%8f%e5%be%ae%e4%bf%a1%e6%81%af%27%5d%2freport%5b%40name%3d%275.%e5%90%84%e6%89%bf%e5%8a%9e%e9%83%a8%e9%97%a8%e5%b0%8f%e5%be%ae%e4%bc%81%e4%b8%9a%e6%89%b6%e6%8c%81%e6%95%b0%e9%87%8f%e7%bb%9f%e8%ae%a1%e5%9b%be%27%5d&ui.name=5.%e5%90%84%e6%89%bf%e5%8a%9e%e9%83%a8%e9%97%a8%e5%b0%8f%e5%be%ae%e4%bc%81%e4%b8%9a%e6%89%b6%e6%8c%81%e6%95%b0%e9%87%8f%e7%bb%9f%e8%ae%a1%e5%9b%be&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";

    public final static String TJ_YN_JYYC_LINK_1 = "http://172.28.129.51:80/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e7%bb%8f%e8%90%a5%e5%bc%82%e5%b8%b8%27%5d%2freport%5b%40name%3d%271.%e4%bc%81%e4%b8%9a%e5%88%97%e5%85%a5%e5%bc%82%e5%b8%b8%e5%90%8d%e5%bd%95%e5%8e%9f%e5%9b%a0%e6%95%b0%e9%87%8f%e7%bb%9f%e8%ae%a1%e5%9b%be%27%5d&ui.name=1.%e4%bc%81%e4%b8%9a%e5%88%97%e5%85%a5%e5%bc%82%e5%b8%b8%e5%90%8d%e5%bd%95%e5%8e%9f%e5%9b%a0%e6%95%b0%e9%87%8f%e7%bb%9f%e8%ae%a1%e5%9b%be&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public final static String TJ_YN_JYYC_LINK_2 = "http://172.28.129.51:80/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e7%bb%8f%e8%90%a5%e5%bc%82%e5%b8%b8%27%5d%2freport%5b%40name%3d%272.%e4%bc%81%e4%b8%9a%e7%a7%bb%e5%87%ba%e5%bc%82%e5%b8%b8%e5%90%8d%e5%bd%95%e5%8e%9f%e5%9b%a0%e6%95%b0%e9%87%8f%e7%bb%9f%e8%ae%a1%e5%9b%be%27%5d&ui.name=2.%e4%bc%81%e4%b8%9a%e7%a7%bb%e5%87%ba%e5%bc%82%e5%b8%b8%e5%90%8d%e5%bd%95%e5%8e%9f%e5%9b%a0%e6%95%b0%e9%87%8f%e7%bb%9f%e8%ae%a1%e5%9b%be&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public final static String TJ_YN_JYYC_LINK_3 = "http://172.28.129.51:80/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e7%bb%8f%e8%90%a5%e5%bc%82%e5%b8%b8%27%5d%2freport%5b%40name%3d%273.%e5%90%84%e6%9c%ba%e5%85%b3%e4%bc%81%e4%b8%9a%e5%88%97%e5%85%a5%e5%bc%82%e5%b8%b8%e5%90%8d%e5%bd%95%e6%95%b0%e9%87%8f%e7%bb%9f%e8%ae%a1%e5%9b%be%27%5d&ui.name=3.%e5%90%84%e6%9c%ba%e5%85%b3%e4%bc%81%e4%b8%9a%e5%88%97%e5%85%a5%e5%bc%82%e5%b8%b8%e5%90%8d%e5%bd%95%e6%95%b0%e9%87%8f%e7%bb%9f%e8%ae%a1%e5%9b%be&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public final static String TJ_YN_JYYC_LINK_4 = "http://172.28.129.51:80/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e7%bb%8f%e8%90%a5%e5%bc%82%e5%b8%b8%27%5d%2freport%5b%40name%3d%274.%e5%85%a8%e7%9c%81%e4%bc%81%e4%b8%9a%e8%bf%916%e4%b8%aa%e6%9c%88%e5%88%97%e5%85%a5%e5%bc%82%e5%b8%b8%e5%90%8d%e5%bd%95%e6%95%b0%e9%87%8f%e8%b6%8b%e5%8a%bf%e5%9b%be%27%5d&ui.name=4.%e5%85%a8%e7%9c%81%e4%bc%81%e4%b8%9a%e8%bf%916%e4%b8%aa%e6%9c%88%e5%88%97%e5%85%a5%e5%bc%82%e5%b8%b8%e5%90%8d%e5%bd%95%e6%95%b0%e9%87%8f%e8%b6%8b%e5%8a%bf%e5%9b%be&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public final static String TJ_YN_JYYC_LINK_5 = "http://172.28.129.51:80/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e7%bb%8f%e8%90%a5%e5%bc%82%e5%b8%b8%27%5d%2freport%5b%40name%3d%275.%e5%8e%86%e5%b9%b4%e5%9b%a0%e6%9c%aa%e5%b9%b4%e6%8a%a5%e5%88%97%e5%85%a5%e5%bc%82%e5%b8%b8%e5%90%8d%e5%bd%95%e5%90%8c%e6%af%94%e5%9b%be%27%5d&ui.name=5.%e5%8e%86%e5%b9%b4%e5%9b%a0%e6%9c%aa%e5%b9%b4%e6%8a%a5%e5%88%97%e5%85%a5%e5%bc%82%e5%b8%b8%e5%90%8d%e5%bd%95%e5%90%8c%e6%af%94%e5%9b%be&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public final static String TJ_YN_JYYC_LINK_6 = "http://172.28.129.51:80/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e7%bb%8f%e8%90%a5%e5%bc%82%e5%b8%b8%27%5d%2freport%5b%40name%3d%276.%e5%88%97%e5%85%a5%e5%bc%82%e5%b8%b8%e5%90%8d%e5%bd%95%e6%ac%a1%e6%95%b0%e7%9a%84%e4%bc%81%e4%b8%9a%e6%95%b0%e9%87%8f%27%5d&ui.name=6.%e5%88%97%e5%85%a5%e5%bc%82%e5%b8%b8%e5%90%8d%e5%bd%95%e6%ac%a1%e6%95%b0%e7%9a%84%e4%bc%81%e4%b8%9a%e6%95%b0%e9%87%8f&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";

    public final static String TJ_YN_GTGSH_LINK_1 = "http://172.28.129.51:80/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e4%b8%aa%e4%bd%93%e5%b7%a5%e5%95%86%e6%88%b7%27%5d%2freport%5b%40name%3d%271.%e6%9c%ac%e6%9c%9f%e6%96%b0%e8%ae%be%e4%b8%aa%e4%bd%93%e5%b7%a5%e5%95%86%e6%88%b7%e6%88%b7%e6%95%b0%e7%bb%9f%e8%ae%a1%e5%9b%be%ef%bc%88%e6%8c%89%e6%9c%ba%e5%85%b3%e5%88%86%e7%b1%bb%ef%bc%89%27%5d&ui.name=1.%e6%9c%ac%e6%9c%9f%e6%96%b0%e8%ae%be%e4%b8%aa%e4%bd%93%e5%b7%a5%e5%95%86%e6%88%b7%e6%88%b7%e6%95%b0%e7%bb%9f%e8%ae%a1%e5%9b%be%ef%bc%88%e6%8c%89%e6%9c%ba%e5%85%b3%e5%88%86%e7%b1%bb%ef%bc%89&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public final static String TJ_YN_GTGSH_LINK_2 ="http://172.28.129.51:80/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e4%b8%aa%e4%bd%93%e5%b7%a5%e5%95%86%e6%88%b7%27%5d%2freport%5b%40name%3d%272.%e4%b8%aa%e4%bd%93%e5%b7%a5%e5%95%86%e6%88%b7%e5%8d%a0%e5%90%84%e7%ac%ac%e4%b8%89%e4%ba%a7%e4%b8%9a%e7%b1%bb%e5%88%ab%e5%89%8d%e4%ba%94%e5%90%8d%27%5d&ui.name=2.%e4%b8%aa%e4%bd%93%e5%b7%a5%e5%95%86%e6%88%b7%e5%8d%a0%e5%90%84%e7%ac%ac%e4%b8%89%e4%ba%a7%e4%b8%9a%e7%b1%bb%e5%88%ab%e5%89%8d%e4%ba%94%e5%90%8d&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public final static String TJ_YN_GTGSH_LINK_3 ="http://172.28.129.51:80/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e4%b8%aa%e4%bd%93%e5%b7%a5%e5%95%86%e6%88%b7%27%5d%2freport%5b%40name%3d%273.%e4%b8%aa%e4%bd%93%e5%b7%a5%e5%95%86%e6%88%b7%e5%ad%98%e7%bb%ad%e5%91%a8%e6%9c%9f%e7%bb%9f%e8%ae%a1%e5%9b%be%27%5d&ui.name=3.%e4%b8%aa%e4%bd%93%e5%b7%a5%e5%95%86%e6%88%b7%e5%ad%98%e7%bb%ad%e5%91%a8%e6%9c%9f%e7%bb%9f%e8%ae%a1%e5%9b%be&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public final static String TJ_YN_GTGSH_LINK_4 ="http://172.28.129.51:80/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e4%b8%aa%e4%bd%93%e5%b7%a5%e5%95%86%e6%88%b7%27%5d%2freport%5b%40name%3d%274.%e8%bf%91%e5%8d%81%e5%b9%b4%e4%b8%aa%e4%bd%93%e5%b7%a5%e5%95%86%e6%88%b7%e6%9c%9f%e6%9c%ab%e7%bb%9f%e8%ae%a1%e5%9b%be%e6%8a%98%e7%ba%bf%e5%9b%be%27%5d&ui.name=4.%e8%bf%91%e5%8d%81%e5%b9%b4%e4%b8%aa%e4%bd%93%e5%b7%a5%e5%95%86%e6%88%b7%e6%9c%9f%e6%9c%ab%e7%bb%9f%e8%ae%a1%e5%9b%be%e6%8a%98%e7%ba%bf%e5%9b%be&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public final static String TJ_YN_GTGSH_LINK_5 ="http://172.28.129.51:80/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e4%b8%aa%e4%bd%93%e5%b7%a5%e5%95%86%e6%88%b7%27%5d%2freport%5b%40name%3d%276.%e6%97%a7%e7%85%a7%e6%8d%a2%e6%96%b0%e7%85%a7%e6%af%94%e4%be%8b%e5%9b%be%27%5d&ui.name=6.%e6%97%a7%e7%85%a7%e6%8d%a2%e6%96%b0%e7%85%a7%e6%af%94%e4%be%8b%e5%9b%be&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public final static String TJ_YN_GTGSH_LINK_6 ="http://172.28.129.51:80/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e4%b8%aa%e4%bd%93%e5%b7%a5%e5%95%86%e6%88%b7%27%5d%2freport%5b%40name%3d%277.2016-2017%e5%b9%b4%e4%b8%aa%e4%bd%93%e5%90%8c%e6%af%94%e6%8a%98%e7%ba%bf%e5%9b%be%ef%bc%88%e6%8c%89%e6%9c%88%e4%bb%bd%ef%bc%89%27%5d&ui.name=7.2016-2017%e5%b9%b4%e4%b8%aa%e4%bd%93%e5%90%8c%e6%af%94%e6%8a%98%e7%ba%bf%e5%9b%be%ef%bc%88%e6%8c%89%e6%9c%88%e4%bb%bd%ef%bc%89&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public final static String TJ_YN_GTGSH_LINK_7 ="http://172.28.129.51:80/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e4%b8%aa%e4%bd%93%e5%b7%a5%e5%95%86%e6%88%b7%27%5d%2freport%5b%40name%3d%278.%e4%b8%aa%e4%bd%93%e7%bb%8f%e8%90%a5%e8%80%85%e5%b9%b4%e9%be%84%e6%ae%b5%e5%8d%a0%e6%af%94%e5%9b%be%27%5d&ui.name=8.%e4%b8%aa%e4%bd%93%e7%bb%8f%e8%90%a5%e8%80%85%e5%b9%b4%e9%be%84%e6%ae%b5%e5%8d%a0%e6%af%94%e5%9b%be&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";
    public final static String TJ_YN_GTGSH_LINK_8 ="http://172.28.129.51:80/cognos8/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2ffolder%5b%40name%3d%27%e4%b8%9a%e5%8a%a1%e6%95%b0%e6%8d%ae%e5%88%86%e6%9e%90(%e6%9d%83%e9%99%90)%27%5d%2ffolder%5b%40name%3d%27%e4%b8%aa%e4%bd%93%e5%b7%a5%e5%95%86%e6%88%b7%27%5d%2freport%5b%40name%3d%279.%e4%b8%aa%e4%bd%93%e7%bb%8f%e8%90%a5%e8%80%85%e5%90%84%e5%b9%b4%e9%be%84%e6%ae%b5%e5%8d%a0%e5%89%8d%e5%8d%81%e8%a1%8c%e4%b8%9a%e6%95%b0%e9%87%8f%27%5d&ui.name=9.%e4%b8%aa%e4%bd%93%e7%bb%8f%e8%90%a5%e8%80%85%e5%90%84%e5%b9%b4%e9%be%84%e6%ae%b5%e5%8d%a0%e5%89%8d%e5%8d%81%e8%a1%8c%e4%b8%9a%e6%95%b0%e9%87%8f&run.outputFormat=&run.prompt=true&run.outputFormat=&run.prompt=false&&cv.header=false&cv.toolbar=false";

    /*
    * 非公党建
    * */
    public final static String DIC = "dic";
    public final static String MODULE_ID = "moduleId";
    public final static String ENT_ID = "entId";
    public final static String GENDER = "gender";
    public final static String NATION = "nation";
    public final static String CERT_TYPE = "certType";
    public final static String PARTY_LEVEL = "party_level";
    public final static String PARTY_BUILD_WAY = "party_build_way";
    public final static String PARTY_MEM_TYPE = "party_mem_type";
    public final static String PARTY_POSITION = "party_position";
    public final static String EDUCATION = "education";
    public final static String LEAGUE_LEVEL = "league_level";
    public final static String LEAGUE_MEM_TYPE = "league_mem_type";
    public final static String LEAGUE_POSITION = "league_position";
    public final static String FGDJ_ENT_TYPE = "fgdjEntType";
    public final static String EXIST_STATUS = "existStatus";
    public final static String IS_CLAIM_ENT = "isClaimEnt";
    public final static String IS_BASE = "isBase";
    public final static String OPER_TIME_START = "operTimeStart";
    public final static String OPER_TIME_END = "operTimeEnd";
    public final static String REG_NO = "regNo";
    public final static String ENT_NAME = "entName";
    public final static String ENT_TYPE = "entType";
    public final static String PAGE_NO = "pageNo";
    public final static String CUR_ORGAN_ID_VALUE = "530000";
    public final static String CUR_ORGAN_ID = "curOrganId";
    public final static String RESULT_HAVE = "resultHave";
    public final static String RESULT_YES = "resultYes";
    public final static String SOCIAL_DUTY = "socialDuty";
    public final static String POLITICAL_STATUS = "politicalStatus";
    public final static String ES_WAY = "esWay";
    public final static String AREA_LIST = "areaList";
    public final static String BASE_AREA_LIST = "baseAreaList";
    public final static String PARTY_AREA_LIST = "partyAreaList";
    public final static String BASE_INFO = "baseInfo";
    public final static String ENT_BASE_INFO = "entBaseInfo";
    public final static String LEADER_INFO = "leaderInfo";
    public final static String PARTY_MEMS = "partyMems";
    public final static String LEAGUE_MEMS = "leagueMems";
    public final static String PARTY_INFO = "partyInfo";
    public final static String LEAGUE_INFO = "leagueInfo";

    public final static String OPERATION = "operation";
    public final static String LOOK = "look";
    public final static String ADD_PARTY = "add_party";
    public final static String ADD_LEAGUE = "add_league";

    public final static String JSON = "json";

    public final static int LOAD_REFRESH = 1;
    public final static int LOAD_MORE = 2;

    public final static String CHA_XUN = "查询";
    public final static String GUAN_LI = "管理";

    public final static int REQUEST_ADD = 100;
    public final static int RESPONSE_ADD = 100;
    public final static int REQUEST_EDIT = 101;
    public final static int RESPONSE_EDIT = 101;
    public final static String PARTY_MEM = "partyMem";
    public final static String LEAGUE_MEM = "leagueMem";

    public final static String KEY_VALUE = "key_value";
    public final static int REQUEST_KEY_VALUE = 101;
    public final static int RESPONSE_KEY_VALUE = 101;
    public final static int REQUEST_CERT_TYPE = 102;
    public final static int RESPONSE_CERT_TYPE = 102;
    public final static int REQUEST_NATION = 103;
    public final static int RESPONSE_NATION = 103;
    public final static int REQUEST_PARTY_MEM_TYPE = 104;
    public final static int RESPONSE_PARTY_MEM_TYPE = 104;
    public final static int REQUEST_PARTY_POSITION = 105;
    public final static int RESPONSE_PARTY_POSITION = 105;
    public final static int REQUEST_EDUCATION = 106;
    public final static int RESPONSE_EDUCATION = 106;
    public final static int REQUEST_PARTY_LEVEL = 107;
    public final static int REQUEST_ACT_PLACE = 108;
    public final static int REQUEST_ACT_FUNDS = 109;
    public final static int WHETHER_MARKET = 110;
    public final static int BUILD_PARTY = 111;
    public final static int BUILD_LEAGUE = 112;
    public final static int DISPATCH_INSTRUCTOR = 113;
    public final static int BUILD_WF = 114;
    public final static int BUILD_LABOR = 115;
    public final static int REQUEST_LEAGUE_LEVEL = 116;
    public final static int REQUEST_EQUIP_WAY = 117;
    public final static int REQUEST_GENDER = 118;
    public final static int REQUEST_SOCIAL_DUTY = 119;
    public final static int REQUEST_ES_WAY = 120;
    public final static int REQUEST_POLITICAL_STATUS = 121;
    public final static int REQUEST_SECRETARY = 122;
    public final static int REQUEST_AREA_CODE = 123;
    public final static int RESPONSE_AREA_CODE = 123;
    public final static int REQUEST_PARTY_FLOAT = 124;
    public final static int RESPONSE_PARTY_FLOAT = 124;



    /*
    * 法律法规
    * */
    public final static String T = "T";
    public final static String K = "K";
    public final static String X = "X";
    public final static String M = "M";
    public final static String BEAN = "bean";
    public final static String LAW = "law";
    public final static String LAW_NAME = "lawName";
    public final static String QUERY_NAME = "queryName";
    public final static String LAW_TYPE = "law_type";


    /*
    * 备忘录
    * */
    public final static String NEW_MEMO = "新增备忘";
    public final static String ADD_CLOCK = "添加闹钟提醒";
    public final static String MEMO_CONTENT = "memoContent";
    public final static String TIME_CLOCK = "timeClock";


    /*
    * 首页图标颜色设置-云南
    * */

    public static HashMap<String,Integer> firstColorMap(){
        if (firstColorMap == null){
            firstColorMap = new HashMap<>();
            firstColorMap.put(Constants.TSJBCL_ID, R.drawable.circle_blue);
            firstColorMap.put(Constants.CCJCLR_ID, R.drawable.circle_yellow);
            firstColorMap.put(Constants.WQCB_ID, R.drawable.circle_green);
        }

        return firstColorMap;
    }

    /*
    * 菜单图标配置-云南
    * */

    public static HashMap<String, Integer> menuIconMapYN(){
        if (menuIconMapYN == null){
            menuIconMapYN = new HashMap<>();
            menuIconMapYN.put(Constants.AJDC_ID + Constants.AJDC_NAME,R.mipmap.icon_ajdc);
            menuIconMapYN.put(Constants.WDAJCX_ID + Constants.WDAJCX_NAME,R.mipmap.icon_wdajcx);
            menuIconMapYN.put(Constants.AJCX_ID + Constants.AJCX_NAME,R.mipmap.icon_ajcx);
            menuIconMapYN.put(Constants.AJDC_ID_2 + Constants.AJDC_NAME_2,R.mipmap.icon_ajdc);
            menuIconMapYN.put(Constants.WDAJCX_ID_2 + Constants.WDAJCX_NAME_2,R.mipmap.icon_wdajcx);
            menuIconMapYN.put(Constants.CCJCLR_ID + Constants.CCJCLR_NAME,R.mipmap.icon_cclr);
            menuIconMapYN.put(Constants.CCJCCX_ID + Constants.CCJCCX_NAME,R.mipmap.icon_cccx);
            menuIconMapYN.put(Constants.TSJBCL_ID + Constants.TSJBCL_NAME,R.mipmap.icon_tsjbcl);
            menuIconMapYN.put(Constants.TSJBCX_ID + Constants.TSJBCX_NAME,R.mipmap.icon_tsjbcx);
            menuIconMapYN.put(Constants.FGDJGL_ID + Constants.FGDJGL_NAME,R.mipmap.icon_djgl);
            menuIconMapYN.put(Constants.FGDJCX_ID + Constants.FGDJCX_NAME,R.mipmap.icon_djcx);
            menuIconMapYN.put(Constants.WQCB_ID + Constants.WQCB_NAME,R.mipmap.icon_cbcs);
            menuIconMapYN.put(Constants.GSXX_ID + Constants.GSXX_NAME,R.mipmap.icon_gsxx);
            menuIconMapYN.put(Constants.FLFG_ID + Constants.FLFG_NAME,R.mipmap.icon_flfg);
            menuIconMapYN.put(Constants.CXTJ_ID + Constants.CXTJ_NAME,R.mipmap.cxtj);
        }
        return menuIconMapYN;
    }

    /*
   * 菜单图标配置-四川
   * */
    public static HashMap<String,Integer> menuIconMapSC(){
        if (menuIconMapSC == null){
            menuIconMapSC = new HashMap<>();
            menuIconMapSC.put(Constants.AJDC_ID + Constants.AJDC_NAME,R.mipmap.ajdc_sc);
            menuIconMapSC.put(Constants.WDAJCX_ID + Constants.WDAJCX_NAME,R.mipmap.ajcx_sc);
            menuIconMapSC.put(Constants.JYCX_ID + Constants.JYCX_NAME,R.mipmap.jycx_sc);
            menuIconMapSC.put(Constants.XYGS_DJ_ID + Constants.XYGS_DJ_NAME,R.mipmap.xygs_dj);
            menuIconMapSC.put(Constants.XYGS_JL_ID + Constants.XYGS_JL_NAME,R.mipmap.xygs_jl);
            menuIconMapSC.put(Constants.GWPY_ID + Constants.GWPY_NAME,R.mipmap.txl_sc);
            menuIconMapSC.put(Constants.GWJS_ID + Constants.GWJS_NAME,R.mipmap.txl_sc);
            menuIconMapSC.put(Constants.GSXX_ID + Constants.GSXX_NAME,R.mipmap.qyxyxx);
            menuIconMapSC.put(Constants.SBCX_ID + Constants.SBCX_NAME,R.mipmap.sbcx);
            menuIconMapSC.put(Constants.TZGG_ID + Constants.TZGG_NAME,R.mipmap.tzgg_sc);
            menuIconMapSC.put(Constants.TXL_ID + Constants.TXL_NAME,R.mipmap.txl_sc);
        }

        return menuIconMapSC;
    }
}
