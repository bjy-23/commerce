
app.controller('qi_gudongchuziDetailCtrl', ['$scope', '$http','$stateParams',function ($scope,$http,$stateParams) {

    $http({
        url: PublicSearchUrl.qi_gudongchuziDetailUrl,
        params : {UUID : $stateParams.data},
        data: '',
        method: "GET",
        dataType: "json",
        cache: false,
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    }).success(function (data) {
        if (data instanceof Object) {
            if (data.errorCode !== '400') {
                $scope.gudongchuziDetailData = data;

                console.log(data)
            }else {
                $scope.showAlert('查询异常，请稍后再试')
            }
        } else {

        }
    }).error(function (error) {

    });

}]);