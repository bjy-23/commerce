/**
 * Created by Administrator on 2017/4/20.
 */
// / 从sessionStorage中获取到当前企业的uuid和entType
// $scope.presentUUID = window.sessionStorage.getItem(uuidKey);
// $scope.presentEntType = window.sessionStorage.getItem(entTypeKey);
// console.log($scope.presentUUID);
//     ?UUID=TrB_ttQ5rp8_m.R9LSilZABK44lqvLHF
// var UUIDs = 'TrB_ttQ5rp8_m.R9LSilZABK44lqvLHF';
// var nianbaoList = '/notice/ws/mpsearch/info/annlList';
app.controller('qiyenianbaoCtrl', ['$scope', '$http', function ($scope, $http) {

    $scope.chakan = function (date) {
        console.log('date   ==' + date);
    };
    $http({
        url: PublicSearchUrl.nianbaoList,
        // params: {UUID:  $scope.presentUUID},
        params: {UUID: window.sessionStorage.getItem(uuidKey)},
        data: '',
        method: "GET",
        dataType: "json",
        cache: false,
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    }).success(function (data) {
        // console.log(data);
        if (data.errCode !== '400') {
            $scope.reportList = data;

            console.log($scope.reportList);
        }
    }).error(function () {

    });


}]);
