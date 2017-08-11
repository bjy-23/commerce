/**
 * Created by Shadow on 2017/4/18.
 */


//?UUID=lJVqFA_YwFD3F.j8MfK0UuvIpBusVBJagKsg2tVhxIpf.aBhv0IrOA==
// var nianbaoxiangqi = '/notice/ws/mpsearch/info/annl/detail';
app.controller('jibenxinxiCtrl',['$scope','$http','$stateParams', function ($scope, $http, $stateParams) {
    console.log('id:' + $stateParams.data);
    $scope.id = $stateParams.data;
    console.log('年报详情信息');
    $scope.rptType = window.sessionStorage.getItem(entTypeKey);
    // $scope.rptType =17;
    console.log('rptType:' + $scope.rptType);

    $http({
        url: PublicSearchUrl.nianbaoxiangqi,
        data: '',
        params: {UUID: $scope.id},
        method: "GET",
        dataType: "json",
        cache: false,
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    }).success(function (data) {

        if (data.errCode === 400) {
            $scope.showAlert("查询项异常，无法获取数据！");
        } else if (data.errCode === 500) {
            $scope.showAlert("服务器内部错误！");
        } else {
            //基本信息数据，供页面引用
            $scope.years = data.year; //年份year
            // console.log('sss  ' + $scope.year);
            $scope.year = parseInt($scope.years);
            console.log('sss  ' + $scope.year);
            $scope.baseInfo = data.baseInfo;  //基本信息(baseInfo)
            $scope.webInfo = data.webInfo;    //网站或网店信息(webInfo)
            $scope.payInfo = data.payInfo;    //股东及出资信息(payInfo)
            $scope.inveInfo = data.inveInfo;                                    //对外投资信息(inveInfo)

            $scope.assetInfo = data.assetInfo;                               //企业资产状况信息(assetInfo)  生产经营者
            $scope.guaInfo = data.guaInfo;               //对外提供保证担保信息(guaInfo)
            $scope.socInfo = data.socInfo;                 //社保信息(socInfo)
            $scope.equChgInfo = data.equChgInfo;                            //股权变更信息(equChgInfo)
            $scope.altInfo = data.altInfo;                                   //修改信息(altInfo)

            // 将&nbsp;替换为空格
            if ($scope.altInfo.length > 0) {
                for (var i = 0; i < $scope.altInfo.length; i++) {
                    $scope.altInfo[i].altBe = $scope.altInfo[i].altBe.replace(/&nbsp;/g, " ");
                    $scope.altInfo[i].altAf = $scope.altInfo[i].altAf.replace(/&nbsp;/g, " ");
                }
            }

            $scope.permitInfo = data.permitInfo;   //行政许可信息
            $scope.branchInfo = data.branchInfo;      //分支机构

            $scope.baseInfos = data;
            console.log($scope.baseInfos);
            $scope.dataIsLoaded = true;
            // $scope.Data = Data.result;
            // $scope.BaseInfo = Data.result.BaseInfo;
            // $scope.WebInfo = Data.result.WebInfo;
            // $scope.InvInfo = Data.result.InvInfo;

        }
    }).error(function () {
        $scope.showAlert("网络连接失败或服务器错误");
    });
}])
;





