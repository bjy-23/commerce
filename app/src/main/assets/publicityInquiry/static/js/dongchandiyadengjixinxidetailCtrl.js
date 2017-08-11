/**
 * Created by yellow1ong on 2017/4/21.
 */
app.controller('dongchandiyadengjixinxidetailCtrl', ['$scope', '$http','$stateParams', function ($scope, $http, $stateParams) {

    $http({
        url: PublicSearchUrl.dongchandiyadengjixinxidetail,
        params: {UUID: $stateParams.id},
        method: "GET",
        dataType: "json",
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    }).success(function (data) {


        if (data.errCode == '400') {
            $scope.showAlert("查询异常，请稍后再试！");
        } else {
            $scope.dataArray = data;
        }
    }).error(function () {
        $scope.showAlert("网络连接错误，请检查网络连接情况");
    });
}]);