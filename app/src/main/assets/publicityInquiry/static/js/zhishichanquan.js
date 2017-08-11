app.controller('zhishichanquanCtrl',['$scope', '$http', '$state',function ($scope,$http,$state) {
     console.log(window.sessionStorage.getItem(uuidKey));
    // $scope.presentUUID = 'u9Abs75MdJiAncMQZthLZjHo1m3FCid4' ;
    $http({
        url: PublicSearchUrl.qi_zhishichanquanUrl,
        params : {UUID :window.sessionStorage.getItem(uuidKey)},
        data: '',
        method: "GET",
        dataType: "json",
        cache: false,
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    }).success(function (data) {
        if (data instanceof Object) {
            if (data.errorCode !== '400') {
                $scope.zhishichanquanData = data;
                if (!$scope.zhishichanquanData || $scope.zhishichanquanData.length === 0){
                    $scope.isEmpty = true;
                }

            }else {
                $scope.showAlert('查询异常，请稍后再试');
            }
        } else {

        }
    }).error(function (error) {

    });


    $scope.zhishichanquanClick = function (item) {
        $state.go('home.zhishichanquanDetail',{
            data : item.id
        });
        console.log(item)
    }


}]);

