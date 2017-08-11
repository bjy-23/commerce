app.controller('qi_xingzhengchufaCtrl', ['$scope', '$http','$state',function ($scope,$http,$state) {
    // $scope.presentUUID = window.sessionStorage.getItem(uuidKey);
    $http({
        url: PublicSearchUrl.qi_xingzhengchufaList,
        params : {UUID : window.sessionStorage.getItem(uuidKey)},
        data: '',
        method: "GET",
        dataType: "json",
        cache: false,
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    }).success(function (data) {
        if (data instanceof Object) {
            if (data.errorCode !== '400') {
                $scope.qi_xingzhengchufa = data;
                console.log(data);
                if (!$scope.qi_xingzhengchufa || $scope.qi_xingzhengchufa.length ===0){
                    $scope.isEmpty = true;
                }

            }else {
                $scope.showAlert('查询异常，请稍后再试');
            }
        } else {

        }
    }).error(function (error) {

    });

    $scope.lookDetail = function (item) {
        $state.go('home.qi_xingzhengchufa.xingzhengchufa',{
            data : item.id
        })
    }
}]);