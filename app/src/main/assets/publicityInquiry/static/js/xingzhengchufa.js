
app.controller('xingzhengchufaCtrl',['$scope', '$http', '$stateParams',function ($scope,$http,$stateParams) {
    console.log($stateParams.data);
    $http({
        url: PublicSearchUrl.qi_xingzhengchufaUrlDetail,
        params : {UUID : $stateParams.data},
        data: '',
        method: "GET",
        dataType: "json",
        cache: false,
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    }).success(function (data) {
        if (data instanceof Object) {
            if (data.errorCode !== '400') {
                $scope.xingzhengchufa = data;
                console.log(data);
                if (!$scope.xingzhengchufa || $scope.xingzhengchufa.length ===0){
                    $scope.isEmpty = true;
                }
            }else {
                $scope.showAlert("查询异常，请稍后再试！");
            }

        } else {

        }
    }).error(function (error) {

    });
}]);