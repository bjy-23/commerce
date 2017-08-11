app.controller('gudongxiugaiCtrl', ['$scope', '$http','$stateParams',function ($scope,$http,$stateParams) {
    $http({
        url: PublicSearchUrl.qi_gudongxiugaiUrl,
        params : {UUID : $stateParams.data},
        data: '',
        method: "GET",
        dataType: "json",
        cache: false,
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    }).success(function (data) {
        if (data instanceof Object) {
            if (data.errorCode !== '400') {
                $scope.gudongxiugaiData = data;
                console.log(data)
            }
        } else {

        }
    }).error(function (error) {

    });
}])