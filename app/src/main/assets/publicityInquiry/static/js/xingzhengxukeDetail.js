
app.controller('xingzhengxukeDetailCtrl',['$scope', '$http', '$stateParams', function ($scope,$http,$stateParams) {
    console.log($stateParams.data);
    $scope.qingsuanMembers = '';
    $http({
        url: PublicSearchUrl.qi_xingzhengxukeDetailUrl,
        params : {UUID :$stateParams.data},
        data: '',
        method: "GET",
        dataType: "json",
        cache: false,
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    }).success(function (data) {
        if (data instanceof Object) {
            if (data.errorCode !== '400') {
                $scope.xingzhengxukeDetail = data;
                changeItems( $scope.xingzhengxukeDetail.changeItems[0]);
                console.log(data);
            }
        } else {

        }
    }).error(function (error) {

    });

    function changeItems(data) {
        if (data){
            if (data.cancelType === '101' ){
                $scope.infoTitle = '注销许可注销信息',
                    $scope.firstTitle = '注销原因',
                    $scope.firstContent = data.cancelReason,
                    $scope.secondTitle = '注销日期',
                    $scope.secondContent = data.cancelDate
            }else if(data.cancelType === '102'){
                $scope.infoTitle = '行政许可吊销信息',
                    $scope.firstTitle = '吊销原因',
                    $scope.firstContent = data.cancelReason,
                    $scope.secondTitle = '吊销日期',
                    $scope.secondContent = data.cancelDate
            }else if (data.cancelType === '103'){
                $scope.infoTitle = '行政许可其它无效信息',
                    $scope.firstTitle = ' 无效原因',
                    $scope.firstContent = data.cancelReason,
                    $scope.secondTitle = '无效日期',
                    $scope.secondContent = data.cancelDate
            }
        }
    }


}]);