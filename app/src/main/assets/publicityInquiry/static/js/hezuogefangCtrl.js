/**
 * Created by IkeBan on 2017/4/20.
 */
app.controller('hezuogefangCtrl', ['$scope','$state', '$http', function ($scope, $state, $http) {
    $scope.hezuogefangData = [];

    $http(
        {
            url: PublicSearchUrl.tzrUrl,
            method: "GET",
            dataType: "json",
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            params: {UUID: window.sessionStorage.getItem(uuidKey)}
        }
    ).success(function (data) {
        if ((data.errCode == 400 || data.errCode == 401)) {
            $scope.showAlert("查询异常，请稍后再试！");
        } else {
            $scope.hezuogefangData = data;
            if (!$scope.hezuogefangData || $scope.hezuogefangData.length == 0) {
                $scope.isEmpty = true;
                return;
            }
        };
    }).error(function () {
        $scope.showAlert("网络连接错误，请检查网络连接情况");
    });

}]);