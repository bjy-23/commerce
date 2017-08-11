
app.controller('zhishichanquanDetailCtrl',['$scope', '$http', '$stateParams', function ($scope,$http,$stateParams) {

    if ($stateParams.data !== null){
        console.log($stateParams.data);
    }
    $scope.date = "";
    $http({
        url:PublicSearchUrl.qi_zhishichanquanDetailUrl,
        params : {UUID :$stateParams.data},
        data: '',
        method: "GET",
        dataType: "json",
        cache: false,
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    }).success(function (data) {
        if (data instanceof Object) {
            if (data.errorCode !== '400') {
                console.log(data);
                $scope.zhishichanquanDetailData = data;
                if (data.revokeItems){
                    revokeItemsMethod(data.revokeItems);
                }
                var date = data.pleRegPerFrom + " - " + data.pleRegPerTo;
                $scope.date = date;
            }
        } else {

        }
    }).error(function (error) {

    });

   var data = [
        {
            cancelType : '301',
            cancelDate : '123',
            cancelReason: '安全2'
        },
        {
            cancelType : '303',
            cancelDate : '12是是是3',
            cancelReason: '发发发2'
        }
    ]

    function revokeItemsMethod(revokeItems) {
        $scope.revokeItems = [];
        angular.forEach(revokeItems,function (item) {
            //  "301" 注销日期 注销原因
            if (item.cancelType === '301'){
                var dic = {};
                dic.title = '注销',
                dic.firstTitle = '注销日期',
                dic.cancelDate = item.cancelDate,
                dic.secondTitle = '注销原因',
                dic.cancelReason = item.cancelReason,
                $scope.revokeItems.push(dic)
            }
            // "303" 无效日期 无效原因
            else if (item.cancelType === '303'){
                var dic = {};
                dic.title = '无效',
                    dic.firstTitle = '无效日期',
                    dic.cancelDate = item.cancelDate,
                    dic.secondTitle = '无效原因',
                    dic.cancelReason = item.cancelReason,
                    $scope.revokeItems.push(dic)
            }
        })
    }
}])