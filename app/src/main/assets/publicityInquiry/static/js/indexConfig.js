/**
 * Created by George on 2017/4/20.
 */
// 先列出所有的模块
var totalParts = [
    // 0 基础信息--营业执照
    {imgName: "icon_yyzz.png", title: "营业执照", stateName: "home.yingyezhizhao"},
    // 1 基础信息--股东及出资
    {imgName: "icon_gdcz.png", title: "股东及出资", stateName: "home.gudongchuzi"},
    // 2 基础信息--投资人信息
    {imgName: "icon_hhr.png", title: "投资人", stateName: "home.touziren"},
    // 3 基础信息--主要人员
    {imgName: "icon_zyry.png", title: "主要人员", stateName: "home.zhuyaorenyuan"},
    // 4 基础信息--合伙人信息
    {imgName: "icon_hhr.png", title: "合伙人", stateName: "home.hehuoren"},
    // 5 基础信息--合作各方信息
    {imgName: "icon_hhr.png", title: "合作各方", stateName: "home.hezuogefang"},
    // 6 基础信息--主管部门（出资人）信息
    {imgName: "icon_hhr.png", title: "主管部门（出资人）", stateName: "home.zhuguanbumen"},
    // 7 基础信息--分支机构
    {imgName: "icon_fzjg.png", title: "分支机构", stateName: "home.fenzhijigouxinxi"},
    // 8 基础信息--清算信息
    {imgName: "icon_qsxx.png", title: "清算信息", stateName: "home.qingsuan"},
    // 9 基础信息--变更信息
    {imgName: "icon_bgxx.png", title: "变更信息", stateName: "home.biangengxinxi"},
    // 10 基础信息--动产抵押
    {imgName: "icon_dcdy.png", title: "动产抵押", stateName: "home.dongchandiyadengjixinxi"},
    // 11 基础信息--股权出质
    {imgName: "icon_gqcz.png", title: "股权出质", stateName: "home.guquanchuzhidengjixinxi"},
    // 12 基础信息--知识产权出质
    {imgName: "icon_zscq.png", title: "知识产权出质", stateName: "home.zhishichanquanchuzhidengjixinxi"},
    // 13 基础信息--商标注册
    {imgName: "icon_sbzc.png", title: "商标注册", stateName: "home.shangbiaozhuce"},
    // 14 基础信息--抽查检查
    {imgName: "icon_ccjc.png", title: "抽查检查", stateName: "home.chouchajianchajieguoxinxi"},
    // 15 基础信息--司法协助
    {imgName: "icon_sfxz.png", title: "司法协助", stateName: "home.sifaxiezhu"},
    // 16 企业公示信息--企业年报
    {imgName: "icon_qynb_q.png", title: "企业年报", stateName: "home.report"},
    // 17 企业公示信息--股东出资
    {imgName: "icon_gdcz_q.png", title: "股东及出资", stateName: "home.qi_gudongchuzi"}, // wang
    // 18 企业公示信息--股权变更
    {imgName: "icon_gqbg_q.png", title: "股权变更", stateName: "home.guquanbiangeng"}, // wang
    // 19 企业公示信息--行政许可
    {imgName: "icon_xzxk_q.png", title: "行政许可", stateName: "home.xingzhengxuke"}, // wang
    // 20 企业公示信息--知识产权出质
    {imgName: "icon_zscq_q.png", title: "知识产权出质", stateName: "home.zhishichanquan"}, // wang
    // 21 企业公示信息--行政处罚
    {imgName: "icon_xzcf_q.png", title: "行政处罚", stateName: "home.qi_xingzhengchufa"}, // wang
    // 22 行政许可
    {imgName: "icon_xzxk_a.png", title: "行政许可", stateName: "home.xingzhengxukexinxi"},
    // 23 行政处罚
    {imgName: "icon_xzcf_a.png", title: "行政处罚", stateName: "home.xingzhengchufaxinxi"},
    // 24 经营异常
    {imgName: "icon_lryi.png", title: "经营异常", stateName: "home.jingyingyichang"},
    // 25 失信企业
    {imgName: "icon_lrwei.png", title: "失信企业", stateName: "home.shixinqiye"},
    // 26 空白
    {imgName: "icon_blank.png", title: "", stateName: ""},
    // 27 简易注销
    {imgName: "icon_zxcx.png", title: "简易注销", stateName: "easyCancellation"},
];

var differentTypeParts = {
    "1": {
        baseInfoParts: [
            totalParts[0], totalParts[1], totalParts[3], totalParts[7],
            totalParts[8], totalParts[9], totalParts[10], totalParts[11],
            totalParts[12], totalParts[13], totalParts[14], totalParts[15]],
        entShowParts: [
            totalParts[16], totalParts[17], totalParts[18], totalParts[19],
            totalParts[20], totalParts[21], totalParts[26], totalParts[26]],
        jyxxParts: [totalParts[22], totalParts[26], totalParts[26], totalParts[26]],
        fxxxParts: [totalParts[23], totalParts[24], totalParts[25], totalParts[26]]
    },
    "2": {
        baseInfoParts: [
            totalParts[0], totalParts[9], totalParts[10], totalParts[12],
            totalParts[13], totalParts[14], totalParts[26], totalParts[26]],
        entShowParts: [
            totalParts[16], totalParts[19], totalParts[20], totalParts[21]],
        jyxxParts: [totalParts[22], totalParts[26], totalParts[26], totalParts[26]],
        fxxxParts: [totalParts[23], totalParts[24], totalParts[25], totalParts[26]]
    },
    "3": {
        baseInfoParts: [
            totalParts[0], totalParts[6], totalParts[9], totalParts[10],
            totalParts[12], totalParts[13], totalParts[14], totalParts[26]],
        entShowParts: [totalParts[16], totalParts[19], totalParts[20], totalParts[21]],
        jyxxParts: [totalParts[22], totalParts[26], totalParts[26], totalParts[26]],
        fxxxParts: [totalParts[23], totalParts[24], totalParts[25], totalParts[26]]
    },
    "4": {
        baseInfoParts: [
            totalParts[0], totalParts[9], totalParts[10], totalParts[12],
            totalParts[13], totalParts[14], totalParts[26], totalParts[26]],
        entShowParts: [totalParts[16], totalParts[19], totalParts[20], totalParts[21]],
        jyxxParts: [totalParts[22], totalParts[26], totalParts[26], totalParts[26]],
        fxxxParts: [totalParts[23], totalParts[24], totalParts[25], totalParts[26]]
    },
    "5": {
        baseInfoParts: [
            totalParts[0], totalParts[4], totalParts[7], totalParts[8],
            totalParts[9], totalParts[10], totalParts[12], totalParts[13],
            totalParts[14], totalParts[26], totalParts[26], totalParts[26]],
        entShowParts: [totalParts[16], totalParts[19], totalParts[20], totalParts[21]],
        jyxxParts: [totalParts[22], totalParts[26], totalParts[26], totalParts[26]],
        fxxxParts: [totalParts[23], totalParts[24], totalParts[25], totalParts[26]]
    },
    "6": {
        baseInfoParts: [
            totalParts[0], totalParts[9], totalParts[10], totalParts[12],
            totalParts[13], totalParts[14], totalParts[26], totalParts[26]],
        entShowParts: [totalParts[16], totalParts[19], totalParts[20], totalParts[21]],
        jyxxParts: [totalParts[22], totalParts[26], totalParts[26], totalParts[26]],
        fxxxParts: [totalParts[23], totalParts[24], totalParts[25], totalParts[26]]
    },
    "7": {
        baseInfoParts: [
            totalParts[0], totalParts[2], totalParts[7], totalParts[9],
            totalParts[10], totalParts[12], totalParts[13], totalParts[14]],
        entShowParts: [totalParts[16], totalParts[19], totalParts[20], totalParts[21]],
        jyxxParts: [totalParts[22], totalParts[26], totalParts[26], totalParts[26]],
        fxxxParts: [totalParts[23], totalParts[24], totalParts[25], totalParts[26]]
    },
    "8": {
        baseInfoParts: [
            totalParts[0], totalParts[9], totalParts[10], totalParts[12],
            totalParts[13], totalParts[14], totalParts[26], totalParts[26]],
        entShowParts: [totalParts[16], totalParts[19], totalParts[20], totalParts[21]],
        jyxxParts: [totalParts[22], totalParts[26], totalParts[26], totalParts[26]],
        fxxxParts: [totalParts[23], totalParts[24], totalParts[25], totalParts[26]]
    },
    "9": {
        baseInfoParts: [
            totalParts[0], totalParts[9], totalParts[10], totalParts[12],
            totalParts[13], totalParts[14], totalParts[26], totalParts[26]],
        entShowParts: [totalParts[16], totalParts[19], totalParts[20], totalParts[21]],
        jyxxParts: [totalParts[22], totalParts[26], totalParts[26], totalParts[26]],
        fxxxParts: [totalParts[23], totalParts[24], totalParts[25], totalParts[26]]
    },
    "10": {
        baseInfoParts: [
            totalParts[0], totalParts[1], totalParts[3], totalParts[7],
            totalParts[8], totalParts[9], totalParts[10], totalParts[11],
            totalParts[12], totalParts[13], totalParts[14], totalParts[15]],
        entShowParts: [
            totalParts[16], totalParts[17], totalParts[18], totalParts[19],
            totalParts[20], totalParts[21], totalParts[26], totalParts[26]],
        jyxxParts: [totalParts[22], totalParts[26], totalParts[26], totalParts[26]],
        fxxxParts: [totalParts[23], totalParts[24], totalParts[25], totalParts[26]]
    },
    "11": {
        baseInfoParts: [
            totalParts[0], totalParts[9], totalParts[10], totalParts[12],
            totalParts[13], totalParts[14], totalParts[26], totalParts[26]],
        entShowParts: [totalParts[16], totalParts[19], totalParts[20], totalParts[21]],
        jyxxParts: [totalParts[22], totalParts[26], totalParts[26], totalParts[26]],
        fxxxParts: [totalParts[23], totalParts[24], totalParts[25], totalParts[26]]
    },
    "12": {
        baseInfoParts: [
            totalParts[0], totalParts[4], totalParts[7], totalParts[8],
            totalParts[9], totalParts[10], totalParts[12], totalParts[13],
            totalParts[14], totalParts[26], totalParts[26], totalParts[26]],
        entShowParts: [totalParts[16], totalParts[19], totalParts[20], totalParts[21]],
        jyxxParts: [totalParts[22], totalParts[26], totalParts[26], totalParts[26]],
        fxxxParts: [totalParts[23], totalParts[24], totalParts[25], totalParts[26]]
    },
    "13": {
        baseInfoParts: [
            totalParts[0], totalParts[9], totalParts[10], totalParts[12],
            totalParts[13], totalParts[14], totalParts[26], totalParts[26]],
        entShowParts: [totalParts[16], totalParts[19], totalParts[20], totalParts[21]],
        jyxxParts: [totalParts[22], totalParts[26], totalParts[26], totalParts[26]],
        fxxxParts: [totalParts[23], totalParts[24], totalParts[25], totalParts[26]]
    },
    "14": {
        baseInfoParts: [
            totalParts[0], totalParts[9], totalParts[10], totalParts[12],
            totalParts[13], totalParts[14], totalParts[26], totalParts[26]],
        entShowParts: [totalParts[16], totalParts[19], totalParts[20], totalParts[21]],
        jyxxParts: [totalParts[22], totalParts[26], totalParts[26], totalParts[26]],
        fxxxParts: [totalParts[23], totalParts[24], totalParts[25], totalParts[26]]
    },
    "15": {
        baseInfoParts: [
            totalParts[0], totalParts[5], totalParts[9], totalParts[10],
            totalParts[12], totalParts[13], totalParts[14], totalParts[26]],
        entShowParts: [totalParts[16], totalParts[19], totalParts[20], totalParts[21]],
        jyxxParts: [totalParts[22], totalParts[26], totalParts[26], totalParts[26]],
        fxxxParts: [totalParts[23], totalParts[24], totalParts[25], totalParts[26]]
    },
    "16": {
        baseInfoParts: [
            totalParts[0], totalParts[3], totalParts[9], totalParts[10],
            totalParts[12], totalParts[13], totalParts[14], totalParts[26]],
        entShowParts: [totalParts[16], totalParts[26], totalParts[26], totalParts[26]],
        jyxxParts: [totalParts[22], totalParts[26], totalParts[26], totalParts[26]],
        fxxxParts: [totalParts[23], totalParts[24], totalParts[26], totalParts[26]]
    },
    "17": {
        baseInfoParts: [
            totalParts[0], totalParts[3], totalParts[9], totalParts[10],
            totalParts[12], totalParts[13], totalParts[14], totalParts[26]],
        entShowParts: [totalParts[16], totalParts[26], totalParts[26], totalParts[26]],
        jyxxParts: [totalParts[22], totalParts[26], totalParts[26], totalParts[26]],
        fxxxParts: [totalParts[23], totalParts[24], totalParts[26], totalParts[26]]
    },
    "18": {
        baseInfoParts: [
            totalParts[0], totalParts[9], totalParts[10], totalParts[12],
            totalParts[13], totalParts[14], totalParts[26], totalParts[26]],
        entShowParts: [],
        jyxxParts: [totalParts[22], totalParts[26], totalParts[26], totalParts[26]],
        fxxxParts: [totalParts[23], totalParts[24], totalParts[26], totalParts[26]]
    },
    "19": {
        baseInfoParts: [
            totalParts[0], totalParts[10], totalParts[14], totalParts[26]],
        entShowParts: [totalParts[16], totalParts[19], totalParts[20], totalParts[21]],
        jyxxParts: [totalParts[22], totalParts[26], totalParts[26], totalParts[26]],
        fxxxParts: [totalParts[23], totalParts[24], totalParts[25], totalParts[26]]
    },
    "20": {
        baseInfoParts: [
            totalParts[0], totalParts[10], totalParts[14], totalParts[26]],
        entShowParts: [totalParts[16], totalParts[19], totalParts[20], totalParts[21]],
        jyxxParts: [totalParts[22], totalParts[26], totalParts[26], totalParts[26]],
        fxxxParts: [totalParts[23], totalParts[24], totalParts[25], totalParts[26]]
    }
};