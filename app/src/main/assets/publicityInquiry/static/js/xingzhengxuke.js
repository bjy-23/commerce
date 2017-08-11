app.controller('xingzhengxukeCtrl',['$scope', '$http', '$state', function ($scope,$http,$state) {
    // 获取当前的UUID 到时候应用
    // $scope.presentUUID = ;
    console.log(PublicSearchUrl.f);
    $http({
        url: PublicSearchUrl.qi_xingzhengxukeUrl,
        params : {UUID: window.sessionStorage.getItem(uuidKey)},
        data: '',
        method: "GET",
        dataType: "json",
        cache: false,
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    }).success(function (data) {
        if (data instanceof Object) {
            if (data.errorCode !== '400') {
                $scope.xingzhengxuke = data;
                console.log(data);
                if ( !$scope.xingzhengxuke ||  $scope.xingzhengxuke.length === 0){
                    $scope.isEmpty = true;
                }
            }else {
                $scope.showAlert('查询异常，请稍后再试')
            }
        } else {

        }
    }).error(function (error) {

    });
    $scope.lookDetail = function (item) {
        $state.go('home.xingzhengxukeDetail',{
            data : item.id
        });
        console.log('1111')
    }

}])