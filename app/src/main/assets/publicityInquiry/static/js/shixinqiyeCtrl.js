/**
 * Created by George on 2017/4/20.
 */
app.controller('shixinqiyeCtrl', ['$scope', '$http', function ($scope, $http) {

    $scope.sxqyInfoList = [
        // {
        //     type: "严重违法失信企业名单",
        //     serIllRea: "未依照《企业信息公示暂行条例》第八条规定的期限公示年度 报告的",
        //     abnTime: "2014年2月1日",
        //     abnOrgan: "北京市工商局",
        //     remExcpRes: "未依照《企业信息公示暂行条例》第八条规定的期限公示年度 报告的",
        //     remDate: "2014年2月1日",
        //     remOrgan: "北京市工商局"
        // }
    ];

    $http({
        method: "GET",
        url: PublicSearchUrl.sxqyUrl,
        params: {UUID: window.sessionStorage.getItem(uuidKey)}
    }).success(function (data) {
        if (data.errCode == 400) {
            $scope.showAlert("查询异常，请稍后再试！");
        } else {
            $scope.sxqyInfoList = data;
            // 如果得到的结果为空，则显示空界面
            if (!$scope.sxqyInfoList || $scope.sxqyInfoList.length == 0) {
                $scope.isEmpty = true;
            }
        }
    }).error(function () {
        $scope.showAlert("网络连接错误，请检查网络连接情况");
    });
}]);