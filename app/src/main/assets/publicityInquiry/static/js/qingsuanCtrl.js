// var app = angular.module('home');
app.controller('qingsuanCtrl', ['$scope', '$http', function ($scope, $http) {
    // $scope.presentUUID = window.sessionStorage.getItem(uuidKey);

    $scope.dataCompleted = false;
    $scope.qingsuanMembers = '';
    $http({
        url: PublicSearchUrl.qi_qingsuanUrl,
        params: {UUID: window.sessionStorage.getItem(uuidKey)},
        data: '',
        method: "GET",
        dataType: "json",
        cache: false,
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    }).success(function (data) {
        if (data instanceof Object) {
            if (data.errorCode !== '400') {
                console.log(data);
                $scope.qingsuanMembers = data;
                // 如果得到的结果为空，则显示空界面
                if (($scope.qingsuanMembers.charge == null || $scope.qingsuanMembers.charge === '') &&
                    ($scope.qingsuanMembers.members == null || $scope.qingsuanMembers.members.length == 0)) {
                    $scope.isEmpty = true;
                    return;
                }
                $scope.dataCompleted = true;
            }
        } else {

        }
    }).error(function (error) {

    });

}]);