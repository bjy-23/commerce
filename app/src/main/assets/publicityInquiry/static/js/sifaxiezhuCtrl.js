/**
 * Created by IkeBan on 2017/4/19.
 */
app.controller('sifaxiezhuCtrl',['$scope', '$state', '$http',  function ($scope, $state, $http) {

    $scope.goSFXZDetail = function (sfid) {
        $state.go('.sifaxiezhuDetail', {sfid: sfid});
    }
    $scope.sifaxiezhuData = [];
    $http(
        {
            url: PublicSearchUrl.sfxzUrl,
            method: "GET",
            dataType: "json",
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            params: {UUID: window.sessionStorage.getItem(uuidKey)}
        }
    ).success(function (data) {
        if ((data.errCode == 400 || data.errCode == 401)) {
            $scope.showAlert("查询异常，请稍后再试！");
        } else {
            $scope.sifaxiezhuData = data;
            if (!$scope.sifaxiezhuData || $scope.sifaxiezhuData.length == 0) {
                $scope.isEmpty = true;
                return;
            }
        };
    }).error(function () {
        $scope.showAlert("网络连接错误，请检查网络连接情况");
    });
}]);
app.controller('sifaxiezhuDetailCtrl', ['$scope','$http','$stateParams', function ($scope,$http,$stateParams) {
    $scope.presentID = $stateParams.sfid;
    $scope.detailData = [];
    $http(
        {
            url: PublicSearchUrl.sfxzChangeDetail,
            method: "GET",
            dataType: "json",
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            params: {UUID: $scope.presentID}
        }
    ).success(function (data) {
        if ((data.errCode == 400 || data.errCode == 401)) {
        } else {
            $scope.changeData = data;
        };
    }).error(function () {
        $scope.showAlert("网络连接错误，请检查网络连接情况");
    });

    $http(
        {
            url: PublicSearchUrl.sfxzDetail,
            method: "GET",
            dataType: "json",
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            params: {UUID: $scope.presentID}
        }
    ).success(function (data) {
        if ((data.errCode == 400 || data.errCode == 401)) {
            $scope.showAlert("查询异常，请稍后再试！");
        } else {
            $scope.detailData = data;

        };
    }).error(function () {
        $scope.showAlert("网络连接错误，请检查网络连接情况");
    });
}]);