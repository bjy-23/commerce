/**
 * Created by George on 2017/4/19.
 */
app.controller('entInfoIndexCtrl', ['$scope', '$state', '$stateParams', '$http', '$rootScope', function ($scope, $state, $stateParams, $http, $rootScope) {

    // 是否显示地图
    $scope.showAddressMap = Config_Province == Province.sichuan;

    // 如果从列表页进入，传入了UUID和entType则刷新sessionStorage中的数据；否则是刷新页面了，直接从sessionStorage中获取UUID和entType
    if ($stateParams.entUUID && $stateParams.entUUID != '') {

        if (window.sessionStorage.getItem(uuidKey)) {
            window.sessionStorage.removeItem(uuidKey);
        }
        window.sessionStorage.setItem(uuidKey, $stateParams.entUUID);
    }
    if ($stateParams.entType && $stateParams.entType != '') {


        if (window.sessionStorage.getItem(entTypeKey)) {
            window.sessionStorage.removeItem(entTypeKey)
        }
        window.sessionStorage.setItem(entTypeKey, $stateParams.entType);
    }

    // 判断当前的UUID企业是否已经存在于收藏列表
    $rootScope.isCollected = false;
    var shoucangListJson = window.localStorage.getItem(shoucangListKey);
    console.log(shoucangListJson);
    if (shoucangListJson) {
        var shoucangList = JSON.parse(shoucangListJson);
        for (var i = 0; i < shoucangList.length; i++) {
            if (shoucangList[i].uuid === window.sessionStorage.getItem(uuidKey)) {
                $rootScope.isCollected = true;
                break;
            }
        }
    }


    $scope.entInfo = {};
    // 企业的存续状态与存续id,页面展示时，对应不同的样式类型
    var entStateDict1 = {
        '1': 'isCunxu',
        '2': 'isDiaoxiao',
        '3': 'isZhuxiao',
        '4': 'isZhuxiao',
        '5': 'isZhuxiao',
        '6': 'isQianchu',
        '9': 'isQianchu'
    };
    $http({
        method: "GET",
        url: PublicSearchUrl.getEntInfo,
        params: {UUID: window.sessionStorage.getItem(uuidKey)}
    }).success(function (data) {
        if (data.errCode == 400) {
            $scope.showAlert("查询异常，请稍后再试！");
        } else {
            $scope.entInfo = data;

            // 是否有企业名称，没有则设为：经营者名称（无字号名称）
            if ($scope.entInfo.entName === '' || $scope.entInfo.entName == null) {
                $scope.entInfo.entName = $scope.entInfo.lerep + '（无字号名称）';
            }
            $scope.entStateCSSClass = entStateDict1[$scope.entInfo.opStateId];

            // 看是否显示简易注销模块
            if (data.ifShowSimreg) {
                if (data.entType === '17' || data.entType === '18') {
                    $scope.fxxxPartsList[2] = totalParts[27];
                } else {
                    $scope.fxxxPartsList[3] = totalParts[27];
                }
            }

            // 拿到数据，保存当前的企业信息到localstorage，用来收藏到收藏列表
            if (window.localStorage.getItem(presentEntInfoKey)) {
                window.localStorage.removeItem(presentEntInfoKey);
            }
            window.localStorage.setItem(presentEntInfoKey, JSON.stringify($scope.entInfo));

            // 拿到数据之后，保存当前的企业到浏览历史记录
            $scope.updateLiulanHistoryArray();
        }
    }).error(function () {
        $scope.showAlert("网络连接错误，请检查网络连接情况");
    });

    /**
     * 根据企业类型处理不同模块的显示
     */
    $scope.presentEntType = window.sessionStorage.getItem(entTypeKey);

    var intEntType = parseInt($scope.presentEntType);

// 法定代表人显示的文字：
    if (intEntType == 1 || intEntType == 3 || intEntType == 10 || intEntType == 17) {
        $scope.lerepName = '法定代表人';
    } else if (intEntType == 2 || intEntType == 4 || intEntType == 6 || intEntType == 8 || intEntType == 9
        || intEntType == 11 || intEntType == 13 || intEntType == 14 || intEntType == 15 || intEntType == 18
        || intEntType == 19 || intEntType == 20) {
        $scope.lerepName = '负责人';
    } else if (intEntType == 5 || intEntType == 12) {
        $scope.lerepName = '执行事务合伙人'
    } else if (intEntType == 7) {
        $scope.lerepName = '投资人'
    } else if (intEntType == 16) {
        $scope.lerepName = '经营者';
    }

    if (intEntType == 5 || intEntType == 12) {
        $scope.place = '主要经营场所';
    } else if (intEntType == 16 || intEntType == 18) {
        $scope.place = '经营场所';
    } else if (intEntType == 1 || intEntType == 10 || intEntType == 3 || intEntType == 7 || intEntType == 17) {
        $scope.place = '住所';
    } else {
        $scope.place = '营业场所';
    }
// 判断entType是否在正常范围内
    if (intEntType >= 1 && intEntType <= 20) {
        // 基础信息中的模块
        $scope.baseInfoPartsList = angular.copy(differentTypeParts[$scope.presentEntType].baseInfoParts);// 深拷贝，防止修改源数据
        // 企业公示信息中的模块
        $scope.entShowPartsList = angular.copy(differentTypeParts[$scope.presentEntType].entShowParts);
        // 经营信息模块
        $scope.jyxxPartsList = angular.copy(differentTypeParts[$scope.presentEntType].jyxxParts);
        // 风险信息模块
        $scope.fxxxPartsList = angular.copy(differentTypeParts[$scope.presentEntType].fxxxParts);
        // 当企业类型为"个体工商户"时，模块名称显示不同
        if ($scope.presentEntType == "16") {
            $scope.baseInfoPartsList[1].title = "参加经营的家庭成员姓名";
        }
        // 企业类型为"农民专业合作社"时
        if ($scope.presentEntType == "17") {
            $scope.baseInfoPartsList[1].title = "成员名册";
        }
        // 企业类型为1或者10时，股东及出资人模块展示不同
        // if ($scope.presentEntType == "1" || $scope.presentEntType == '10') {
        // $scope.baseInfoPartsList[1].title = "股东及出资信息";
        // }
    } else {
        // 基础信息中的模块
        $scope.baseInfoPartsList = [];
        // 企业公示信息中的模块
        $scope.entShowPartsList = [];
        // 经营信息模块
        $scope.jyxxPartsList = [];
        // 风险信息模块
        $scope.fxxxPartsList = [];
    }

// 点击对应模块，打开对应的页面
    $scope.openPage = function (aStateName) {

        if (aStateName && aStateName != "") {
            $state.go(aStateName)
        }
    };

    $scope.goAddressPage = function () {
        $state.go('home.entAddressPage', {address: $scope.entInfo.dom});
    };

    $scope.goHistoryPage = function () {
        // 如果有传backUrl参数的话，则是从公众应用主页面跳入，应该返回公众主页面
        if ($stateParams.backUrl === 'home') {
            window.location = '../../home/index.html#/home';
        } else if ($stateParams.backUrl === 'myCollection') {
            window.location.href = '../../home/index.html#/setting/myCollection';
        } else if ($stateParams.searchKey) { // 还存有关键字的话就返回搜索列表页
            $state.go('entInfoResultList', {
                inputedSearchKey: $stateParams.searchKey,
                searchType: $stateParams.searchType
            });
        } else { //没有关键字就直接返回搜索页面吧
            $state.go('entInfoQuery', {
                inputedSearchKey: $stateParams.searchKey,
                searchType: $stateParams.searchType
            })
        }
    };

// 保存当前企业的UUID到浏览历史记录
    $scope.updateLiulanHistoryArray = function () {
        // 如果对应为空数组，则建立第一个数组值，否则，判断的是否已经存在于数组中，不在就再判断长度是否大于十，是的话就删除最早的，加上，否则直接加上
        // var liulanHistoryArray = new Array();
        var liulanHistoryArrayJson = window.localStorage.getItem(liulanHistoryListKey);
        if (liulanHistoryArrayJson) {
            var liulanHistoryArray = JSON.parse(liulanHistoryArrayJson);
            // 已经存在于数组中，则先移除
            if (indexInArray(liulanHistoryArray) != -1) {
                liulanHistoryArray.splice(indexInArray(liulanHistoryArray), 1);
            }
            // 数组长度是否大于10,则删除最开始进入的一个，列位为最后的元素
            if (liulanHistoryArray.length >= 10) {
                liulanHistoryArray.pop();
            }
            // 添加元素,添加到第一个，列为最新
            liulanHistoryArray.unshift($scope.entInfo);
            var tempString = JSON.stringify(liulanHistoryArray);
            window.localStorage.removeItem(liulanHistoryListKey);
            window.localStorage.setItem(liulanHistoryListKey, tempString);
        } else {
            // 空数组则建立数组，添加
            var tempArray = new Array();
            tempArray.unshift($scope.entInfo);
            var tempJsonStr = JSON.stringify(tempArray);
            window.localStorage.setItem(liulanHistoryListKey, tempJsonStr);
        }
    };
// 判断当前的企业是否已经保存在浏览历史中了，返回所在的位置
    function indexInArray(anArray) {
        for (var i = 0; i < anArray.length; i++) {
            if (anArray[i].uuid == $scope.entInfo.uuid) {
                return i;
            }
        }
        return -1;
    }

}
])
;