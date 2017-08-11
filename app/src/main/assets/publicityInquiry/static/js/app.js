/**
 * Created by Shadow on 2017/4/17.
 */

var app = angular.module('home', ['ui.bootstrap', 'ui.router', 'app-Directives', 'ngDialog']);

// 保存企业UUID和entType的key值，通过key值从sessionStorage中获取数据
var uuidKey = 'presentEntUUID';
var entTypeKey = 'presentEntType';
// 保存搜索历史和浏览历史的key
var searchHistoryListKey = 'searchHistoryListKey';
var liulanHistoryListKey = 'liulanHistoryListKey';
var shoucangListKey = 'shoucangListKey'; // 企业的收藏列表
var presentEntInfoKey = 'presentEntInfoKey'; // 用于收藏该企业使用
var savedBackUrlKey = 'backUrl';

// 省份配置（要配置的初始原因是信息总览页中地址的地图展示是否需要）
var Province = {
    default: 0,                 //默认
    hunan: 1,                   //湖南
    hubei: 2,                   //湖北
    sichuan: 3,                  //四川
    hebei: 4,                   //河北
    yunnan: 5,                   //云南
};

var Config_Province = Province.sichuan;           //当前省份配置

/**
 * 更新分享服务
 */
function updateSerivces() {
    console.log("先进入***222***");
    plus.share.getServices(function (s) {
        shares = {};
        for (var i in s) {
            var t = s[i];
            shares[t.id] = t;
        }
        console.log("先进入***AAA***");
        console.log("获取分享服务列表成功");
    }, function (e) {
        console.log("先进入***BBB***");
        console.log("获取分享服务列表失败：" + e.message);
    });
}
/**
 * 分享操作
 */
function shareAction(id, ex) {
    console.log("先进入***131313***");
    var s = null;
    if (!id || !(s = shares[id])) {
        console.log("先进入***141414***");
        alert("无效的分享服务！");
        return;
    }
    if (s.authenticated) {
        console.log("---已授权---");
        console.log("先进入***151515***");
        shareMessage(s, ex);
    } else {
        console.log("---未授权---");
        console.log("先进入***161616***");
        s.authorize(function () {
            shareMessage(s, ex);
            console.log("先进入***171717***");
        }, function (e) {
            alert("认证授权失败");
            console.log("先进入***181818***");
        });
    }
}
/**
 * 发送分享消息
 */
function shareMessage(s, ex) {
    console.log("先进入***222222***");
    var msg = {
        href: 'http://www.baidu.com',
        title: 'George分享测试-title',
        content: 'George-分享测试-content',
        thumbs: ['http://img3.3lian.com/2013/v10/4/87.jpg'], // http://b.hiphotos.baidu.com/zhidao/pic/item/1f178a82b9014a90e7eb9d17ac773912b21bee47.jpg
        pictures: ['http://img3.3lian.com/2013/v10/4/87.jpg'], // http://img3.3lian.com/2013/v10/4/87.jpg
        extra: {
            scene: ex
        }
    };
    s.send(msg, function () {
        console.log("先进入***333333***");
        alert("分享成功!");
    }, function (e) {
        console.log("先进入***444444***");
        alert("分享失败!");
    });
}
/**
 * 分享按钮点击事件
 */
function shareHref() {
    console.log("先进入***111111***");
    var ids = [{
            id: "weixin",
            ex: "WXSceneSession" /*微信好友*/
        }, {
            id: "weixin",
            ex: "WXSceneTimeline" /*微信朋友圈*/
        }, {
            id: "qq" /*QQ好友*/
        }, {
            id: "tencentweibo" /*腾讯微博*/
        }, {
            id: "sinaweibo" /*新浪微博*/
        }],
        bts = [{
            title: "发送给微信好友"
        }, {
            title: "分享到微信朋友圈"
        }, {
            title: "分享到QQ"
        }, {
            title: "分享到腾讯微博"
        }, {
            title: "分享到新浪微博"
        }];
    plus.nativeUI.actionSheet({
            cancel: "取消",
            buttons: bts
        },
        function (e) {
            console.log("先进入***121212***");
            var i = e.index;
            if (i > 0) {
                shareAction(ids[i - 1].id, ids[i - 1].ex);
            }
        }
    );
}

app.run(['$rootScope', '$state', 'ngDialog', function ($rootScope, $state, ngDialog) {
    $rootScope.$state = $state;

    $rootScope.isEmpty = false;

    /*
     *  企业的收藏、取消收藏操作
     */
    $rootScope.isCollected = false; // 初始状态，企业是未被收藏的
    $rootScope.collectEntInfo = function () {
        var anEntInfo = JSON.parse(window.localStorage.getItem(presentEntInfoKey));  // 先获取当前的企业对象，然后保存到数组中，再化为json字符串保存
        var shoucangListJson = window.localStorage.getItem(shoucangListKey);
        console.log(shoucangListJson);
        if (shoucangListJson) {
            var shoucangList = JSON.parse(shoucangListJson);
            // 添加元素,添加到第一个，列为最新
            shoucangList.unshift(anEntInfo);
            var tempString = JSON.stringify(shoucangList);
            console.log(tempString);
            window.localStorage.removeItem(shoucangListKey);
            window.localStorage.setItem(shoucangListKey, tempString);
        } else {
            // 空数组则建立数组，添加
            var tempArray = [];
            tempArray.unshift(anEntInfo);
            var tempJsonStr = JSON.stringify(tempArray);
            console.log(tempJsonStr);
            window.localStorage.setItem(shoucangListKey, tempJsonStr);
        }
        // 改变收藏状态
        $rootScope.isCollected = true;
    };
    $rootScope.cancelCollectEntInfo = function () {
        var anEntInfo = JSON.parse(window.localStorage.getItem(presentEntInfoKey));  // 先获取当前的企业对象，然后从收藏列表中移除
        var shoucangListJson = window.localStorage.getItem(shoucangListKey);
        console.log(shoucangListJson);
        if (shoucangListJson) {
            var shoucangList = JSON.parse(shoucangListJson);
            var indexInArray = 0;
            for (var i = 0; i < shoucangList.length; i++) {
                if (shoucangList[i].uuid === anEntInfo.uuid) {
                    indexInArray = i;
                    break;
                }
            }
            shoucangList.splice(indexInArray, 1);
            var tempString = JSON.stringify(shoucangList);
            console.log(tempString);
            window.localStorage.removeItem(shoucangListKey);
            window.localStorage.setItem(shoucangListKey, tempString);
        }
        // 改变收藏状态
        $rootScope.isCollected = false;
    };

    // 信息弹窗
    $rootScope.showAlert = function (message) {

        ngDialog.open({
            template: "template/contentAlert.html",
            className: 'ngdialog-theme-plain',
            controller: ['$scope', function ($scope) {
                $scope.message = message;
                $scope.close = function () {
                    ngDialog.close();
                }
            }]
        });
    };

    // 检测安卓返回键，提示是否退出
    document.addEventListener("plusready", function () {
        // 注册返回按键事件
        plus.key.addEventListener('backbutton', function () {
            // 事件处理
            plus.nativeUI.confirm("退出程序？", function (event) {
                if (event.index) {
                    plus.runtime.quit();
                }
            }, null, ["取消", "确定"]);
        }, false);

        // 在plusready之后，更新分享服务
        // updateSerivces();
    });
}]);
app.config(['$urlRouterProvider', '$stateProvider', function ($urlRouterProvider, $stateProvider) {
    $urlRouterProvider.when('', '/entInfoQuery');
    $urlRouterProvider.when('/', '/entInfoQuery');

    $stateProvider.state('homeOld', {
        url: '/homeOld',
        controller: 'companyInfoCtrl',
        templateUrl: 'companyInfo.html'
    });
    // 企业搜索页面
    $stateProvider.state('entInfoQuery', {
        // url: '/entInfoQuery',
        url: '/entInfoQuery?backUrl=&searchType=&inputedSearchKey=',
        params: {inputedSearchKey: null, searchType: null, backUrl: null},
        controller: 'entInfoQueryCtrl',
        templateUrl: 'entInfoQuery.html'
    });
    // 企业搜索结果列表页
    $stateProvider.state('entInfoResultList', {
        url: '/entInfoResultList',
        params: {inputedSearchKey: null, searchType: null, backUrl: null},
        controller: 'entInfoResultListCtrl',
        templateUrl: 'entInfoResultList.html'
    });
    // 企业信息首页
    $stateProvider.state('home', {
        url: '/entInfoIndex?entUUID=&entType=&backUrl=&searchKey=&searchType=',
        params: {entUUID: null, entType: null, backUrl: null, searchKey: null, searchType: null},
        controller: 'entInfoIndexCtrl',
        templateUrl: 'entInfoIndex.html'
    });
    // 企业地址
    $stateProvider.state('home.entAddressPage', {
        url: '/entAddressPage',
        params: {address: null},
        controller: 'entAddressPageCtrl',
        templateUrl: 'entAddressPage.html'
    });
    //营业执照信息
    $stateProvider.state('home.yingyezhizhao', {
        url: '/yingyezhizhaoCtrl',
        controller: 'yingyezhizhaoCtrl',
        templateUrl: 'baseInfo/yingyezhizhao.html'
    });

    // 基本信息--股东出资
    $stateProvider.state('home.gudongchuzi', {
        url: '/gudongchuzi',
        controller: 'gudongchuziCtrl',
        templateUrl: 'baseInfo/gudongchuzi.html'
    });
    // 股东及出资详情
    $stateProvider.state('home.gudongchuzi.gudongchuziDetail', {
        url: '/gudongchuziDetail',
        params: {gdczInfoID: null, gdczInfoIsInitiator: false},
        controller: 'gudongchuziDetailCtrl',
        templateUrl: 'baseInfo/gudongchuziDetail.html'
    });

    //股东修改记录
    $stateProvider.state('home.gudongxiugai', {
        params: {'data': null},
        url: '/gudongxiugaiCtrl',
        controller: 'gudongxiugaiCtrl',
        templateUrl: 'baseInfo/gudongxiugai.html'
    });
    //股权变更信息
    $stateProvider.state('home.guquanbiangeng', {
        url: '/guquanbiangengCtrl',
        controller: 'guquanbiangengCtrl',
        templateUrl: 'baseInfo/guquanbiangeng.html'
    });
    //知识产权出质登记信息
    $stateProvider.state('home.zhishichanquan', {
        params: {'data': null},
        url: '/zhishichanquanCtrl',
        controller: 'zhishichanquanCtrl',
        templateUrl: 'baseInfo/zhishichanquan.html'
    });
    //行政许可信息
    $stateProvider.state('home.xingzhengxuke', {
        url: '/xingzhengxukeCtrl',
        controller: 'xingzhengxukeCtrl',
        templateUrl: 'baseInfo/xingzhengxuke.html'
    });
    //行政许可详细信息
    $stateProvider.state('home.xingzhengxukeDetail', {
        params: {data: null},
        url: '/xingzhengxukeDetailCtrl',
        controller: 'xingzhengxukeDetailCtrl',
        templateUrl: 'baseInfo/xingzhengxukeDetail.html'
    });

    //行政处罚信息
    $stateProvider.state('home.qi_xingzhengchufa', {
        url: '/qi_xingzhengchufaCtrl',
        controller: 'qi_xingzhengchufaCtrl',
        templateUrl: 'baseInfo/qi_xingzhengchufaList.html'
    });

    //行政处罚信息
    $stateProvider.state('home.qi_xingzhengchufa.xingzhengchufa', {
        params: {data: null},
        url: '/xingzhengchufaCtrl',
        controller: 'xingzhengchufaCtrl',
        templateUrl: 'baseInfo/xingzhengchufa.html'
    });
    //知识产权详细信息
    $stateProvider.state('home.zhishichanquanDetail', {
        params: {'data': null},
        url: '/zhishichanquanDetailCtrl',
        controller: 'zhishichanquanDetailCtrl',
        templateUrl: 'baseInfo/zhishichanquanDetail.html'
    });
    // 企业公示信息--股东出资信息
    $stateProvider.state('home.qi_gudongchuzi', {
        params: {'data': null},
        url: '/qi_gudongchuzi',
        controller: 'qi_gudongchuziCtrl',
        templateUrl: 'baseInfo/qi_gudongchuzi.html'
    });
    // 企业公示信息--股东出资详细信息
    $stateProvider.state('home.qi_gudongchuziDetail', {
        params: {'data': null},
        url: '/qi_gudongchuziDetail',
        controller: 'qi_gudongchuziDetailCtrl',
        templateUrl: 'baseInfo/qi_gudongchuziDetail.html'
    });


    // 分支机构
    $stateProvider.state('home.fenzhijigouxinxi', {
        url: '/fenzhijigouxinxiCtrl',
        controller: 'fenzhijigouxinxiCtrl',
        templateUrl: 'baseInfo/fenzhijigouxinxi.html'
    });
    // 变更信息
    $stateProvider.state('home.biangengxinxi', {
        url: '/biangengxinxiCtrl',
        controller: 'biangengxinxiCtrl',
        templateUrl: 'baseInfo/biangengxinxi.html'
    });

    // 主要人员
    $stateProvider.state('home.zhuyaorenyuan', {
        url: '/zhuyaorenyuanCtrl',
        controller: 'zhuyaorenyuanCtrl',
        templateUrl: 'baseInfo/zhuyaorenyuan.html'
    });
    //商标注册
    $stateProvider.state('home.shangbiaozhuce', {
        url: '/shangbiaozhuceCtrl',
        controller: 'shangbiaozhuceCtrl',
        templateUrl: 'baseInfo/shangbiaozhuce.html'
    });
    // 商标注册详情
    $stateProvider.state('home.shangbiaozhuce.shangbiaozhuceDetail', {
        url: '/shangbiaozhuceDetailCtrl',
        params: {shangBiaoId: null},
        controller: 'shangbiaozhuceDetailCtrl',
        templateUrl: 'baseInfo/shangbiaozhuceDetail.html'
    });
    // 企业年报
    $stateProvider.state('home.report', {
        url: '/report',
        controller: 'qiyenianbaoCtrl',
        // params: {'data': null},
        templateUrl: 'baseInfo/reportInfo/report_qiyenianbaoxinxi.html'
    });
    // 企业年报--基本信息
    $stateProvider.state('home.report.jibenxinxi', {
        url: '/jibenxinxi',
        controller: 'jibenxinxiCtrl',
        params: {'data': null},
        // params: {data: null},
        templateUrl: 'baseInfo/reportInfo/report_jibenxinxi.html'
    });
    // 清算信息
    $stateProvider.state('home.qingsuan', {
        url: '/qingsuanCtrl',
        controller: 'qingsuanCtrl',
        templateUrl: 'baseInfo/qingsuan.html'
    });
    //司法协助
    $stateProvider.state('home.sifaxiezhu', {
        url: '/sifaxiezhuCtrl',
        controller: 'sifaxiezhuCtrl',
        templateUrl: 'baseInfo/sifaxiezhu.html'
    });
    //司法协助详情
    $stateProvider.state('home.sifaxiezhu.sifaxiezhuDetail', {
        url: '/sifaxiezhuDetailCtrl',
        controller: 'sifaxiezhuDetailCtrl',
        params: {sfid: null},
        templateUrl: 'baseInfo/sifaxiezhuxiangqing.html'
    });
    // 列入严重违法失信企业名单信息
    $stateProvider.state('home.shixinqiye', {
        url: '/shixinqiye',
        controller: 'shixinqiyeCtrl',
        templateUrl: 'baseInfo/shixinqiye.html'
    });
    //投资人
    $stateProvider.state('home.touziren', {
        url: '/touzirenCtrl',
        controller: 'touzirenCtrl',
        templateUrl: 'baseInfo/touziren.html'
    });
    //主管部门(出资人)信息
    $stateProvider.state('home.zhuguanbumen', {
        url: '/zhuguanbumenCtrl',
        controller: 'zhuguanbumenCtrl',
        templateUrl: 'baseInfo/zhuguanbumen.html'
    });
    // 合伙人
    $stateProvider.state('home.hehuoren', {
        url: '/hehuorenCtrl',
        controller: 'hehuorenCtrl',
        templateUrl: 'baseInfo/hehuoren.html'
    });
    // 经营异常
    $stateProvider.state('home.jingyingyichang', {
        url: '/jingyingyichangCtrl',
        controller: 'jingyingyichangCtrl',
        templateUrl: 'baseInfo/jingyingyichang.html'
    });
    // 合作各方
    $stateProvider.state('home.hezuogefang', {
        url: '/hezuogefangCtrl',
        controller: 'hezuogefangCtrl',
        templateUrl: 'baseInfo/hezuogefang.html'
    });
    // 动产抵押登记
    $stateProvider.state('home.dongchandiyadengjixinxi', {
        url: '/dongchandiyadengjixinxiCtrl',
        controller: 'dongchandiyadengjixinxiCtrl',
        templateUrl: 'baseInfo/dongchandiyadengjixinxi.html'
    });
    // 动产抵押登记详情
    $stateProvider.state('home.dongchandiyadengjixinxi.dongchandiyadengjixinxidetail', {
        url: '/dongchandiyadengjixinxidetailCtrl',
        params: {"id": null},
        controller: 'dongchandiyadengjixinxidetailCtrl',
        controllerAs: 'dctrl',
        templateUrl: 'baseInfo/dongchandiyadengjixinxidetail.html'
    });
    // 股权出质登记
    $stateProvider.state('home.guquanchuzhidengjixinxi', {
        url: '/guquanchuzhidengjixinxiCtrl',
        controller: 'guquanchuzhidengjixinxiCtrl',
        templateUrl: 'baseInfo/guquanchuzhidengjixinxi.html'
    });
    // 股权出质登记详情
    $stateProvider.state('home.guquanchuzhidengjixinxi.guquanchuzhidengjixinxidetail', {
        params: {"id": null},
        url: '/guquanchuzhidengjixinxidetailCtrl',
        controller: 'guquanchuzhidengjixinxidetailCtrl',
        templateUrl: 'baseInfo/guquanchuzhidengjixinxidetail.html'
    });

    // 知识产权出质登记
    $stateProvider.state('home.zhishichanquanchuzhidengjixinxi', {
        url: '/zhishichanquanchuzhidengjixinxiCtrl',
        controller: 'zhishichanquanchuzhidengjixinxiCtrl',
        templateUrl: 'baseInfo/zhishichanquanchuzhidengjixinxi.html'
    });
    // 知识产权出质登记详情
    $stateProvider.state('home.zhishichanquanchuzhidengjixinxi.zhishichanquanchuzhidengjixinxidetail', {
        url: '/zhishichanquanchuzhidengjixinxidetailCtrl',
        controller: 'zhishichanquanchuzhidengjixinxidetailCtrl',
        templateUrl: 'baseInfo/zhishichanquanchuzhidengjixinxidetail.html'
    });
    // 抽查检查结果
    $stateProvider.state('home.chouchajianchajieguoxinxi', {
        url: '/chouchajianchajieguoxinxiCtrl',
        controller: 'chouchajianchajieguoxinxiCtrl',
        templateUrl: 'baseInfo/chouchajianchajieguoxinxi.html'
    });
    // 行政许可
    $stateProvider.state('home.xingzhengxukexinxi', {
        url: '/xingzhengxukexinxiCtrl',
        controller: 'xingzhengxukexinxiCtrl',
        templateUrl: 'baseInfo/xingzhengxukexinxi.html'
    });
    $stateProvider.state('home.xingzhengxukexinxi.xingzhengxukexinxidetail', {
        params: {"id": null},
        url: '/xingzhengxukexinxidetailCtrl',
        controller: 'xingzhengxukexinxidetailCtrl',
        templateUrl: 'baseInfo/xingzhengxukexinxidetail.html'
    });
    // 行政处罚
    $stateProvider.state('home.xingzhengchufaxinxi', {
        url: '/xingzhengchufaxinxiCtrl',
        controller: 'xingzhengchufaxinxiCtrl',
        templateUrl: 'baseInfo/xingzhengchufaxinxi.html'
    });
    $stateProvider.state('home.xingzhengchufaxinxi.xingzhengchufaxinxidetail', {
        params: {"detailUrl": null, "id": null},
        url: '/xingzhengchufaxinxidetailCtrl',
        controller: 'xingzhengchufaxinxidetailCtrl',
        templateUrl: 'baseInfo/xingzhengchufaxinxidetail.html'
    });
    $stateProvider.state('home.xingzhengchufaxinxi.xingzhengchufaxinxidetail.xingzhengchufaxinxidetail2', {
        params: {"penFilePath": null},
        url: '/xingzhengchufaxinxidetail2Ctrl',
        controller: 'xingzhengchufaxinxidetail2Ctrl',
        templateUrl: 'baseInfo/xingzhengchufaxinxidetail2.html'
    });

}]);


app.controller('companyInfoCtrl', ['$scope', function ($scope) {

}]);


app.controller('companyInfoCtrl', function () {

});
