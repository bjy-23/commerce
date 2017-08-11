/**
 * Created by yellow1ong on 2017/4/19.
 */
app.controller('zhishichanquanchuzhidengjixinxiCtrl',['$scope', '$http',  function ($scope, $http) {

    console.log(window.sessionStorage.getItem(uuidKey));
    $http({
        url: PublicSearchUrl.zhishichanquanchuzhidengjixinxi,
        params: {UUID: window.sessionStorage.getItem(uuidKey)},
        // params: {UUID: "lgMETe7QcFcYyteS.s68R4MW9ky4eian"},
        method: "GET",
        dataType: "json",
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    }).success(function (data) {
        if (data.errCode == '400') {
            $scope.showAlert("查询异常，请稍后再试！");
        } else {
            $scope.dataArray = data;
            if (!$scope.dataArray || $scope.dataArray.length == 0) {
                $scope.isEmpty = true;
                return;
            }
            for (var i = 0; i < $scope.dataArray.length; i++) {
                if ($scope.dataArray[i].type == "有效") {
                    $scope.dataArray[i].valid = "dcdydixx_youxiao";
                } else {
                    $scope.dataArray[i].valid = "dcdydixx_wuxiao";
                }
            }
        }
    }).error(function () {
        $scope.showAlert("网络连接错误，请检查网络连接情况");
    });
}]);