/**
 * Created by George on 2017/4/17.
 */
/**
 * Created by George on 2017/4/17.
 */
app.controller('gudongchuziDetailCtrl', ['$scope', '$stateParams', '$http', function ($scope, $stateParams, $http) {

    $scope.gudongInfo = {
        // inv: '股东名称',
        // liAcConAm: '认缴额',
        // liSubConAm: '实缴额'
    };

    $scope.renjiaoDetailList = [
        // {
        //     conForm: '认缴方式',
        //     subConAm: '认缴出资额',
        //     conDate: '认缴出资日期'
        // }
    ];

    $scope.shijiaoDetailList = [
        // {
        //     conForm: '实缴方式',
        //     acConAm: '实缴出资额',
        //     conDate: '实缴出资日期'
        // }
    ];

    if ($stateParams.gdczInfoIsInitiator) {
        $scope.realUrl = PublicSearchUrl.gdczDetailFaqirenUrl;
        $scope.gudongTypeName = '发起人';
    } else  {
        $scope.realUrl = PublicSearchUrl.gdczDetailUrl;
        $scope.gudongTypeName = '股东';
    }

    $http({
        method: "GET",
        url: $scope.realUrl,
        params: {UUID: $stateParams.gdczInfoID}
    }).success(function (data) {
        if (data.errCode == 400) {
            $scope.showAlert("查询异常，请稍后再试！");
        } else {
            $scope.gudongInfo.inv = data.inv;
            $scope.gudongInfo.liAcConAm = data.liAcConAm;
            $scope.gudongInfo.liSubConAm = data.liSubConAm;
            $scope.renjiaoDetailList = data.payInfo;
            $scope.shijiaoDetailList = data.realPayInfo;
        }
    }).error(function () {
        $scope.showAlert("网络连接错误，请检查网络连接情况");
    });
}]);