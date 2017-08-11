app.controller('qi_gudongchuziCtrl', ['$scope', '$http','$state',function ($scope,$http,$state) {
    console.log(window.sessionStorage.getItem(uuidKey));
    $http({
        url: PublicSearchUrl.qi_gudongchuziUrl,
        params : {UUID : window.sessionStorage.getItem(uuidKey)},
        data: '',
        method: "GET",
        dataType: "json",
        cache: false,
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    }).success(function (data) {
        if (data instanceof Object) {
            if (data.errorCode !== '400') {
                $scope.gudongchuzi = data;
                console.log(data);
                if( $scope.gudongchuzi.list.length === 0){
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
        $state.go('home.qi_gudongchuziDetail',{
            data : item.id
        });
    };
    $scope.changeClick = function () {
        $state.go('home.gudongxiugai' ,{
            data : $scope.gudongchuzi.id
        })
    }
}]);