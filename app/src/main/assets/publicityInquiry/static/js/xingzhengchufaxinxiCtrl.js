/**
 * Created by yellow1ong on 2017/4/20.
 */
app.controller('xingzhengchufaxinxiCtrl',['$scope', '$http',  function ($scope, $http) {


    $http({
        url: PublicSearchUrl.xingzhengchufaxinxi,
        params: {UUID: window.sessionStorage.getItem(uuidKey)},
        // params: {UUID: "lgMETe7QcFcYyteS.s68R4MW9ky4eian"},
        method: "GET",
        dataType: "json",
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    }).success(function (data) {
        if (data.errCode == '400') {
            $scope.showAlert("查询异常，请稍后再试！");
        } else {
            $scope.dataArray = data.othPunInfos;
            $scope.entPunInfos = data.entPunInfos;

            if ((!$scope.dataArray || $scope.dataArray.length == 0) && (!$scope.entPunInfos || $scope.entPunInfos.length == 0)){
                $scope.isEmpty = true;
                return;
            }
        }
    }).error(function () {
        $scope.showAlert("网络连接错误，请检查网络连接情况");
    });
}]);