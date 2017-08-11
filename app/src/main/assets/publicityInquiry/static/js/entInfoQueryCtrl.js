/**
 * Created by George on 2017/4/20.
 */
app.controller('entInfoQueryCtrl', ['$scope', '$state', '$stateParams', function ($scope, $state, $stateParams) {

    // 传过来的关键字（显示在搜索输入框中）
    $scope.searchKey = $stateParams.inputedSearchKey;
    // 企业查询--'1'，经营异常查询--'2'，严重违法失信企业查询--'3'
    $scope.presentSearchType = $stateParams.searchType;

    // 保存入口页到localStorage
    if ($stateParams.backUrl && $stateParams.backUrl != '') {
        if (window.localStorage.getItem(savedBackUrlKey)) {
            window.localStorage.removeItem(savedBackUrlKey);
        }
        window.localStorage.setItem(savedBackUrlKey, $stateParams.backUrl);
    }

    // 从localStorage中获取搜索历史和浏览历史列表
    $scope.liulanHistoryList = [];
    var liulanHistoryArrayJson = window.localStorage.getItem(liulanHistoryListKey);
    if (liulanHistoryArrayJson) {
        $scope.liulanHistoryList = JSON.parse(liulanHistoryArrayJson);
    }

    $scope.searchHistoryList = [];
    var searchHistoryArrayJson = window.localStorage.getItem(searchHistoryListKey);
    if (searchHistoryArrayJson) {
        $scope.searchHistoryList = JSON.parse(searchHistoryArrayJson);
    }

    $scope.clearSearchHisroty = function () {
        // 清空搜索历史
        $scope.searchHistoryList = [];
        window.localStorage.removeItem(searchHistoryListKey);
    };

    $scope.clearLiulanHisroty = function () {
        // 清空浏览历史
        $scope.liulanHistoryList = [];
        window.localStorage.removeItem(liulanHistoryListKey);
    };

    $scope.clearInputSearchKey = function () {
        // 清空输入框
        $scope.searchKey = '';
    };
    $scope.cancelSearch = function () {
        // 嵌入湖南监管项目的原生项目中，如果有这个与原生交互的对象则，调用安卓原生的额goBackHunan方法
        if (window.interaction) {
            window.interaction.goBackHunan();
        } else {
            // 根据进入的页面，判断返回的页面
            var presentBackUrl = window.localStorage.getItem(savedBackUrlKey);
            if (presentBackUrl == 'home') {
                window.location.href = '../../home/index.html#/home';
            } else if (presentBackUrl == 'search') {
                window.location.href = '../../home/index.html#/search';
            } else {
                // 默认返回主页
                window.location.href = '../../home/index.html#/home';
            }
            window.localStorage.removeItem(savedBackUrlKey);
        }
    };

    $scope.searchByKey = function (event) {
        var keyCode = window.event ? event.keyCode : event.which;
        // 如果是回车键
        if (keyCode == 13) {
            // 跳转到结果列表页进行搜索
            if (!$scope.searchKey || $scope.searchKey == '') {
                $scope.showAlert("请输入关键字进行查询！");
            } else {
                // 准备要搜索了，将当前的搜索关键字存入搜索列表
                $state.go('entInfoResultList', {
                    inputedSearchKey: $scope.searchKey,
                    searchType: $scope.presentSearchType
                });
            }
        }
    };
}]);