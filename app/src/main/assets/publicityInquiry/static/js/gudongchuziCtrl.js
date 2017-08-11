/**
 * Created by George on 2017/4/17.
 */
app.controller('gudongchuziCtrl', ['$scope', '$state', '$http', function ($scope, $state, $http) {

    $scope.gdczInfoList = [
        // {
        //     inv: "中远财务有限公司",
        //     invType: "企业法人",
        //     blicType: "法人营业执照",
        //     blicNO: "23987495971289345",
        //     detail: true
        // }
    ];
    $scope.typeName = '股东';
    $scope.dataCompleted = false;

    $scope.goGDCZDetail = function (gdczInfoID, initiatorTag) {
        $state.go('.gudongchuziDetail', {gdczInfoID: gdczInfoID, gdczInfoIsInitiator:initiatorTag});
    };

    $http({
        method: "GET",
        url: PublicSearchUrl.gdczUrl,
        params: {UUID: window.sessionStorage.getItem(uuidKey)}
    }).success(function (data) {
        if (data.errCode == 400 || data.errCode == 401) {
            $scope.showAlert("查询异常，请稍后再试！");
        } else {
            $scope.gdczInfoList = data;
            // 如果得到的结果为空，则显示空界面
            if (!$scope.gdczInfoList || $scope.gdczInfoList.length == 0) {
                $scope.isEmpty = true;
            } else {
                $scope.dataCompleted = true;
                // 如果是标记为发起人，则修改为发起人
                if ($scope.gdczInfoList[0].initiator) {
                    $scope.typeName = "发起人";
                }
            }
        }
    }).error(function () {
        $scope.showAlert("网络连接错误，请检查网络连接情况");
    });
}]);
