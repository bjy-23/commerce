/**
 * Created by IkeBan on 2017/4/19.
 */

app.controller('hehuorenCtrl', ['$scope','$state', '$http', function ($scope, $state, $http) {
    $scope.hehuorenData = [];
    $scope.hehuorenAnotherData = [];

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
            var type = window.sessionStorage.getItem(entTypeKey);
            if (type == "5") {
                $scope.hehuorenData = data;
                if (!$scope.hehuorenData || $scope.hehuorenData.length == 0) {
                    $scope.isEmpty = true;
                }
            } else if (type == "12"){
                $scope.hehuorenAnotherData = data;
                if (!$scope.hehuorenAnotherData || $scope.hehuorenAnotherData.length == 0) {
                    $scope.isEmpty = true;
                }
            }
        }
    }).error(function () {
        $scope.showAlert("网络连接错误，请检查网络连接情况");
    });

}]);