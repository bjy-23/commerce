/**
 * Created by IkeBan on 2017/4/18.
 */
app.controller('zhuyaorenyuanCtrl',['$scope', '$http', function ($scope, $http) {
    var type = window.sessionStorage.getItem(entTypeKey);
    if (type == "16") {
        $scope.zyryTitle = "参加经营的家庭成员姓名";
    } else if (type == "17") {
        $scope.zyryTitle = "成员名册";
    } else {
        $scope.zyryTitle = "主要人员";
    }
    $http(
        {
            url: PublicSearchUrl.zyryUrl,
            method: "GET",
            dataType: "json",
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            params: {UUID: window.sessionStorage.getItem(uuidKey)}
        }
    ).success(function (data) {
        if ((data.errCode == 400 || data.errCode == 401)) {
            $scope.showAlert("查询异常，请稍后再试！");
        } else {
            $scope.zhuyaorenyuanData = data;
            if (!$scope.zhuyaorenyuanData || $scope.zhuyaorenyuanData.length == 0) {
                $scope.isEmpty = true;
                return
            }
        };
    }).error(function () {
        $scope.showAlert("网络连接错误，请检查网络连接情况");
    });

}]);