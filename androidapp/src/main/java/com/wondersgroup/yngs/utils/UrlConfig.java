package com.wondersgroup.yngs.utils;

/**
 * Created by 1229 on 2015/12/29.
 */
public class UrlConfig {
    /**
     * 控制服务器 切换
     */
    /** 测试服务器地址 */
    public final static String TEST_URL_1 = "http://10.1.8.121:8014/notice/ws/data/";
    public final static String TEST_URL_2 = "http://gsxt.ynaic.gov.cn/notice/ws/data/";
    public final static String TEST_URL_3 = "http://10.10.17.17:8080/netme/";
    public final static String TEST_URL_4 = "http://10.10.4.116:8080/zhjg/";

    /** 正式服务器地址 */
    public final static String NOR_URL_1 = "http://www.hebscztxyxx.gov.cn/noticehb/ws/data/";

    /** 灵活变量 */
    public final static String CHG_URL = TEST_URL_2;

    // 目录
    public static String URL_Catalog_LIST = CHG_URL
            + "mobapp_config/20140804-01";

    // notice_config/20140804-01
    // 企业信息查询
    public static String URL_COMPANY_LIST = CHG_URL + "ent_info_list";

    // 企业详细信息查询
    public static String URL_COMPANY_DETAIL = CHG_URL + "ent_info";

    // 股权出质
    public static String URL_PLEDGE = CHG_URL + "ent_pledge";

    // 行政处罚
    public static String URL_MORTGAGE = CHG_URL + "ent_mortgage";

    // 行政处罚
    public static String URL_PUNISH = CHG_URL + "ent_punish";

    // 投资人详情
    public static String URL_INVESTOR = CHG_URL + "ent_investor";

    // 经营异常名录(基本)
    public static String URL_JYYCML_JB = CHG_URL + "ent_except_list/%20";

    // 经营异常名录查询
    public static String URL_JYYCML_CX = CHG_URL + "ent_except_list";

    // 严重违法企业名单(基本)
    public static String URL_YZQYWF_JB = CHG_URL + "ent_black_list/%20";

    // 严重违法企业名单
    public static String URL_YZQYWF_CX = CHG_URL + "ent_black_list";

}

