/**
 * Created by IkeBan on 2017/4/20.
 */
app.controller('jingyingyichangCtrl', ['$scope','$state', '$http', function ($scope, $state, $http) {
    $scope.jingyingyichangData = [];
	 $scope.type = window.sessionStorage.getItem(entTypeKey);
	 
    $http(
        {
            url: PublicSearchUrl.jyycUrl,
            method: "GET",
            dataType: "json",
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            params: {UUID: window.sessionStorage.getItem(uuidKey)}
        }
    ).success(function (data) {
        if (data.errCode == 400 || data.errCode == 401) {
            $scope.showAlert("查询异常，请稍后再试！");
        } else {
            $scope.jingyingyichangData = data;
            if (!$scope.jingyingyichangData || $scope.jingyingyichangData.length == 0) {
                $scope.isEmpty = true;
                return;
            }
        };
    }).error(function () {
        $scope.showAlert("网络连接错误，请检查网络连接情况");
    });

}]);