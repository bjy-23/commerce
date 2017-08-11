app.controller('guquanbiangengCtrl', ['$scope', '$http',function ($scope,$http) {
    // $scope.presentUUID = window.sessionStorage.getItem(uuidKey);
    $http({
        url: PublicSearchUrl.qi_guquanbiangengUrl,
        params : {UUID:window.sessionStorage.getItem(uuidKey)},
        data: '',
        method: "GET",
        dataType: "json",
        cache: false,
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    }).success(function (data) {
        if (data instanceof Object) {
            if (data.errorCode !== '400') {
                $scope.gudongbiangeng = data;
                console.log(data);
                if (!$scope.gudongbiangeng ||  $scope.gudongbiangeng.length === 0){
                    $scope.isEmpty = true;
                }
            }else {
                $scope.showAlert('查询异常，请稍后再试')
            }
        } else {

        }
    }).error(function (error) {

    });

}])