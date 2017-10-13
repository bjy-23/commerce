/**
 * Created by Shadow on 2017/4/17.
 */
//四川
//var publicSearchBaseUrl = 'http://182.131.3.110:8012/notice/ws';
var publicSearchBaseUrl = 'http://sc.gsxt.gov.cn/notice/ws';
//云南
//var publicSearchBaseUrl = 'http://220.163.27.42:8021/notice/ws';
//var publicSearchBaseUrl = 'http://gsxt.ynaic.gov.cn/notice/ws';

var PublicSearchUrl = {
    /**
     * 搜索接口
     */
    //  市场主体列表查询-条件分页
    searchEntUrl: publicSearchBaseUrl + '/mpsearch/list',
    // 经营异常市场主体列表查询-条件分页
    searchExceptEntUrl: publicSearchBaseUrl + '/mpsearch/exceptList',
    // 严重违法市场主体列表查询-条件分页
    searchBlackEntUrl: publicSearchBaseUrl + '/mpsearch/blackList',
    // 获取企业信息
    getEntInfo: publicSearchBaseUrl + '/mpsearch/summary',

    /**
     * 基本信息模块接口
     */
    // 基本信息--股东及出资信息
    gdczUrl: publicSearchBaseUrl + '/mpsearch/info/investor',
    // 基本信息--股东及出资信息详情
    gdczDetailUrl: publicSearchBaseUrl + '/mpsearch/info/investor/detail',
    gdczDetailFaqirenUrl: publicSearchBaseUrl + '/mpsearch/info/initiator/detail',
    // 列入严重违法失信企业名单信息接口
    sxqyUrl: publicSearchBaseUrl + '/mpsearch/info/black',
    //清算信息
    qi_qingsuanUrl: publicSearchBaseUrl + '/mpsearch/info/clear',
    //营业执照信息
    qi_yingyezhizhaoUrl: publicSearchBaseUrl + '/mpsearch/info/license',
    //股东及出资信息
    qi_gudongchuziUrl: publicSearchBaseUrl + '/mpsearch/info/stoInvest',
    //股东出资信息详情
    qi_gudongchuziDetailUrl: publicSearchBaseUrl + '/mpsearch/info/stoInvest/detail',
    //股东修改记录
    qi_gudongxiugaiUrl: publicSearchBaseUrl + '/mpsearch/info/stoInvest/alt',
    //行政许可信息
    qi_xingzhengxukeUrl: publicSearchBaseUrl + '/mpsearch/info/permit',
    //股权变更
    qi_guquanbiangengUrl: publicSearchBaseUrl + '/mpsearch/info/stoEquChange',
    //行政许可信息
    qi_xingzhengxukeUrl: publicSearchBaseUrl + '/mpsearch/info/etpsPermit',
    //行政许可详细信息
    qi_xingzhengxukeDetailUrl: publicSearchBaseUrl + '/mpsearch/info/etpsPermit/detail',
    // 企业公示-行政处罚信息列表
    qi_xingzhengchufaList: publicSearchBaseUrl + '/mpsearch/info/etpsPunish',
    //企业公示行政处罚信息
    qi_xingzhengchufaUrlDetail: publicSearchBaseUrl + '/mpsearch/info/etpsPunish/detail',
    //知识产权出质登记信息
    qi_zhishichanquanUrl: publicSearchBaseUrl + '/mpsearch/info/etpsIprs',
    //知识产权出质详细信息

    qi_zhishichanquanDetailUrl: publicSearchBaseUrl + '/mpsearch/info/etpsIprs/detail',
    //分支机构信息
    fenzhijigouxinxi: publicSearchBaseUrl + '/mpsearch/info/branch',
    //股权出质登记信息
    guquanchuzhidengjixinxi: publicSearchBaseUrl + '/mpsearch/info/pledge',
    //股权出质登记信息详情
    guquanchuzhidengjixinxidetail: publicSearchBaseUrl + '/mpsearch/info/pledge/detail',
    //行政处罚信息
    xingzhengchufaxinxi: publicSearchBaseUrl + '/mpsearch/info/punish',
    //行政处罚详情
    xingzhengchufaxinxidetail: publicSearchBaseUrl + '/mpsearch/info/',
    //行政许可信息
    xingzhengxukexinxi: publicSearchBaseUrl + '/mpsearch/info/permit',
    //行政许可详情
    xingzhengxukexinxidetail: publicSearchBaseUrl + '/mpsearch/info/permit/detail',
    //知识产权出质登记信息
    zhishichanquanchuzhidengjixinxi: '',
    //变更信息
    biangengxinxi: publicSearchBaseUrl + '/mpsearch/info/alter',
    //抽查检查结果信息
    chouchajianchajieguoxinxi: publicSearchBaseUrl + '/mpsearch/info/spotCheck',
    //动产抵押登记信息
    dongchandiyadengjixinxi: publicSearchBaseUrl + '/mpsearch/info/mortgage',
    //动产抵押登记信息详情
    dongchandiyadengjixinxidetail: publicSearchBaseUrl + '/mpsearch/info/mortgage/detail',

    qi_zhishichanquanDetailUrl: publicSearchBaseUrl + '/mpsearch/info/etpsIprs/detail',

    //投资人、合伙人、合作各方、主管部门信息
    tzrUrl: publicSearchBaseUrl + '/mpsearch/info/investor',
    // 主要人员
    zyryUrl: publicSearchBaseUrl + '/mpsearch/info/member',
    // 商标注册
    sbzcUrl: publicSearchBaseUrl + '/mpsearch/info/brand',
    // 商标注册详情
    sbzcxqUrl: publicSearchBaseUrl + '/mpsearch/info/brand/detail',
    // 司法协助
    sfxzUrl: publicSearchBaseUrl + '/mpsearch/info/judAssistance',
    // 司法协助详情
    sfxzDetail: publicSearchBaseUrl + '/mpsearch/info/judStoFreeze/detail',
    //司法协助 股权变更
    sfxzChangeDetail: publicSearchBaseUrl + '/mpsearch/info/judStoChange/detail',
    // 经营异常
    jyycUrl: publicSearchBaseUrl + '/mpsearch/info/except',

    //年报信息
    nianbaoList: publicSearchBaseUrl + '/mpsearch/info/annlList',
    //年报详情
    nianbaoxiangqi: publicSearchBaseUrl + '/mpsearch/info/annl/detail',

    jyycUrl: publicSearchBaseUrl + '/mpsearch/info/except'

};

function toParamsUrl(data) {
    var result = "";
    for (var key in data) {
        if (result.length > 0) {
            result += "&"
        }
        if (data.hasOwnProperty(key)) {
            var value = data[key];
            result += encodeURIComponent(key) + '=' + encodeURIComponent(value);
        }
    }
    return result;
}

