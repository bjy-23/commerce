/**
 * Created by George on 2017/4/20.
 */
app.controller('entInfoResultListCtrl', ['$scope', '$state', '$stateParams', '$http', function ($scope, $state, $stateParams, $http) {

    $scope.resultList = [
        // {
        //     uuid: 'b0hjHl4esLj8XpdyL',
        //     entName: "泰康人寿保险股份有限公司",
        //     regNo: "130000600000089",
        //     uniScid: "统一社会信用代码",
        //     lerep: "测试",
        //     estDate: "2014年8月6日",
        //     opState: '存续（在营、开业、在册）',
        //     opStateId: '1',
        //     isExcept: true,
        //     isBlack: false,
        //     isSimreg: true,
        //     entType: '10',
        //     canDate: '2012年7月14日',
        //     repealRes: '吊销原因',
        //     revokeDate: '吊销日期',
        //     revokeReason: '吊销凭证'
        // }
    ];
    // 企业的存续状态与存续id,页面展示时，对应不同的样式类型
    var entStateDict = {
        '1': 'isCunxu',
        '2': 'isDiaoxiao',
        '3': 'isZhuxiao',
        '4': 'isZhuxiao',
        '5': 'isZhuxiao',
        '6': 'isQianchu',
        '9': 'isQianchu'
    };

    // $scope.presentPageCount = 1;

    // 获取传递过来的搜索关键字
    $scope.searchedKey = $stateParams.inputedSearchKey;
    $scope.searchType = $stateParams.searchType;

    // 判断使用企业查询--'1'，经营异常查询--'2'，严重违法失信企业查询--'3'中的哪一个接口
    $scope.finalUrl = PublicSearchUrl.searchEntUrl;
    switch ($scope.searchType) {
        case '2': {
            $scope.finalUrl = PublicSearchUrl.searchExceptEntUrl;
        }
            break;
        case '3': {
            $scope.finalUrl = PublicSearchUrl.searchBlackEntUrl;
        }
            break;
        default: {
            $scope.finalUrl = PublicSearchUrl.searchEntUrl;
        }
    }

    $scope.requestData = function () {
        $http({
            method: "GET",
            url: $scope.finalUrl,
            params: {Q: $scope.searchedKey}
        }).success(function (data) {
            if (data.errCode == 400) {
                $scope.showAlert("请输入更为详细的查询条件！");
            } else {
                $scope.resultList = data.result;
                // 如果得到的结果为空，则显示空界面
                if (!$scope.resultList || $scope.resultList.length == 0) {
                    $scope.isEmpty = true;
                    return;
                } else {
                    $scope.isEmpty = false;
                }
                // 否则正常执行
                for (var i = 0; i < $scope.resultList.length; i++) {
                    // 如果企业的存续状态不同，文字的背景颜色不同
                    $scope.resultList[i].entStateCSSClass = entStateDict[$scope.resultList[i].opStateId];
                    var intEntType = parseInt($scope.resultList[i].entType);

                    // 法定代表人显示的文字：
                    if (intEntType == 1 || intEntType == 3 || intEntType == 10 || intEntType == 17) {
                        $scope.resultList[i].lerepName = '法定代表人';
                    } else if (intEntType == 2 || intEntType == 4 || intEntType == 6 ||
                        intEntType == 8 || intEntType == 9 || intEntType == 11 ||
                        intEntType == 13 || intEntType == 14 || intEntType == 15 ||
                        intEntType == 18 || intEntType == 19 || intEntType == 20) {
                        $scope.resultList[i].lerepName = '负责人';
                    } else if (intEntType == 5 || intEntType == 12) {
                        $scope.resultList[i].lerepName = '执行事务合伙人'
                    } else if (intEntType == 7) {
                        $scope.resultList[i].lerepName = '投资人'
                    } else if (intEntType == 16) {
                        $scope.resultList[i].lerepName = '经营者';
                        // 是否有企业名称，没有则设为：经营者名称（无字号名称）
                        if ($scope.resultList[i].entName === '' || $scope.resultList[i].entName == null) {
                            $scope.resultList[i].entName = $scope.resultList[i].lerep + '（无字号名称）';
                        }
                    }
                }
            }
        }).error(function () {
            $scope.showAlert("网络连接错误，请检查网络连接情况");
        });
    };

    // 更新搜索历史列表
    $scope.updateSearchHistoryArray = function () {
        if ($scope.searchedKey) {
            // 如果对应为空数组，则建立第一个数组值，否则，判断的是否已经存在于数组中，不在就再判断长度是否大于十，是的话就删除最早的，加上，否则直接加上
            var searchHistoryArrayJson = window.localStorage.getItem(searchHistoryListKey);
            if (searchHistoryArrayJson) {
                var searchHistoryArray = JSON.parse(searchHistoryArrayJson);
                // 已经存在于数组中，则先移除
                if (indexInSearchHistoryList(searchHistoryArray) != -1) {
                    searchHistoryArray.splice(indexInSearchHistoryList(searchHistoryArray), 1);
                }
                // 数组长度是否大于10,则删除最开始进入的一个，列位为最后的元素
                if (searchHistoryArray.length >= 10) {
                    searchHistoryArray.pop();
                }
                // 添加元素,添加到第一个，列为最新
                searchHistoryArray.unshift($scope.searchedKey);
                var tempString = JSON.stringify(searchHistoryArray);
                window.localStorage.removeItem(searchHistoryListKey);
                window.localStorage.setItem(searchHistoryListKey, tempString);
            } else {
                // 空数组则建立数组，添加
                var tempArray = new Array();
                tempArray.unshift($scope.searchedKey);
                var tempJsonStr = JSON.stringify(tempArray);
                window.localStorage.setItem(searchHistoryListKey, tempJsonStr);
            }
            // console.log(window.localStorage.getItem(searchHistoryListKey));
        }
    };

    // 搜索进入记得更新搜索历史
    if ($scope.searchedKey) {
        $scope.updateSearchHistoryArray();
    }
    // 然后请求数据
    $scope.requestData();

    // 如果重新修改输入关键字，并按回车搜索则，需要重新更新搜索历史并且重新请求数据
    $scope.searchByKey = function (event) {
        var keyCode = window.event ? event.keyCode : event.which;
        // 如果是回车键
        if (keyCode == 13) {
            // 跳转到结果列表页进行搜索
            if (!$scope.searchedKey || $scope.searchedKey == '') {
                $scope.showAlert("请输入关键字进行查询！");
            } else {
                // 更新搜索列表，重新请求数据
                $scope.updateSearchHistoryArray();
                $scope.requestData();
            }
        }
    };
    $scope.startSearching = function () {
        // 跳转到结果列表页进行搜索
        if (!$scope.searchedKey || $scope.searchedKey == '') {
            $scope.showAlert("请输入关键字进行查询！");
        } else {
            // 更新搜索列表，重新请求数据
            $scope.updateSearchHistoryArray();
            $scope.requestData();
        }
    };

    $scope.goBackHome = function () {
        // 如果是从首页跳进来的，那就回到首页，否则回到搜索页(后面改过了主页的跳转方案，不存在这种情况了)
        if ($stateParams.backUrl == 'home') {
            window.location.href = '../../home/index.html#/home';
        } else {
            $state.go('entInfoQuery', {inputedSearchKey: $scope.searchedKey, searchType: $scope.searchType});
        }
    };

    // 点击行，跳转到对应企业的信息展示详首页
    $scope.goEntInfoIndex = function (selectedEntUUID, selectedEntType) {
        $state.go('home', {
            entUUID: selectedEntUUID,
            entType: selectedEntType,
            searchKey: $scope.searchedKey,
            searchType: $scope.searchType
        });
    };

    // 清空搜索输入框的内容
    $scope.clearSearchKey = function () {
        $scope.searchedKey = '';
    };

    // 判断当前的企业是否已经保存在浏览历史中了，返回所在的位置
    function indexInSearchHistoryList(anArray) {
        for (var i = 0; i < anArray.length; i++) {
            if (anArray[i] == $scope.searchedKey) {
                return i;
            }
        }
        return -1;
    }

    // // 加载更多，下一页
    // $scope.loadNextPage = function () {
    //     // 当前的页面计数加一
    //     $scope.presentPageCount = $scope.presentPageCount + 1;
    //     $http({
    //         method: "GET",
    //         url: $scope.finalUrl,
    //         params: {Q: $scope.searchedKey, Page: $scope.presentPageCount, Limit: 5}
    //     }).success(function (data) {
    //         if (data.errCode == 400) {
    //             $scope.showAlert("查询异常，请稍后再试！");
    //         } else {
    //             $scope.resultList = data.result;
    //
    //             for (var i = 0; i < $scope.resultList.length; i++) {
    //                 // 如果企业的存续状态不同，文字的背景颜色不同
    //                 $scope.resultList[i].entStateCSSClass = entStateDict[$scope.resultList[i].opStateId];
    //             }
    //         }
    //     }).error(function () {
    //         $scope.showAlert("网络连接错误，请检查网络连接情况");
    //     });
    // };
}]);