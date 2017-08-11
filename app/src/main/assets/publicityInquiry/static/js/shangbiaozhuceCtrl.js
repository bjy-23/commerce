/**
 * Created by IkeBan on 2017/4/19.
 */
app.controller('shangbiaozhuceCtrl', ['$scope', '$state','$http', function ($scope, $state, $http) {
    $scope.shangbiaozhuceData = [];
    $scope.goSBZCDetail = function (shangBiaoId) {
        $state.go('.shangbiaozhuceDetail', {shangBiaoId: shangBiaoId});
    }
    $http(
        {
            url: PublicSearchUrl.sbzcUrl,
            method: "GET",
            dataType: "json",
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            params: {UUID: window.sessionStorage.getItem(uuidKey)}
        }
    ).success(function (data) {
        if (data.errCode == 400 || data.errCode == 401) {
            $scope.showAlert("查询异常，请稍后再试！");
        } else {
            $scope.shangbiaozhuceData = data;
            if (!$scope.shangbiaozhuceData || $scope.shangbiaozhuceData.length == 0) {
                $scope.isEmpty = true;
                return;
            }
        };
    }).error(function () {
        $scope.showAlert("网络连接错误，请检查网络连接情况");
    });

}]);
app.controller('shangbiaozhuceDetailCtrl', ['$scope','$state', '$http','$stateParams', function ($scope,$state,$http,$stateParams) {
    $scope.shangbiaozhuceDetailData = [];
    $scope.presentID = $stateParams.shangBiaoId;
    $http(
        {
            url: PublicSearchUrl.sbzcxqUrl,
            method: "GET",
            dataType: "json",
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            params: {UUID:  $scope.presentID}
        }
    ).success(function (data) {
        if (data.errCode == 400 || data.errCode == 401) {
            $scope.showAlert("查询异常，请稍后再试！");
        } else {
            var type = data.intCls;
            data.intCls = "第" + type + "类";
            var firstDate = data.propertyBgnDate;
            var lastDate = data.propertyEndDate;
            data.propertyBgnDate = firstDate + "至" + lastDate;
            $scope.shangbiaozhuceDetailData = data;
            var el = document.getElementById('ttt');
            el.innerHTML = $scope.shangbiaozhuceDetailData.goodsCnName;
        };
    }).error(function () {
        $scope.showAlert("网络连接错误，请检查网络连接情况");
    });
}]);