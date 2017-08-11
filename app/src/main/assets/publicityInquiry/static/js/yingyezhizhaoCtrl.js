//var app = angular.module('home');

app.controller('yingyezhizhaoCtrl',['$scope', '$http', '$state', function ($scope,$http,$state) {
    //获取18中企业类型
    console.log(PublicSearchUrl.qi_yingyezhizhaoUrl);
    console.log(window.sessionStorage.getItem(uuidKey));
    var type = window.sessionStorage.getItem(entTypeKey);

     function zhucema(item) {
         console.log(item);
         if(item.uniScid === ''){
             $scope.regNO = '注册号';
             $scope.yingyezhizhao.relatedNum = $scope.yingyezhizhao.regNO;
         }else {
             $scope.regNO = '统一社会信用代码';
             $scope.yingyezhizhao.relatedNum = $scope.yingyezhizhao.uniScid;
         }
     }

    $scope.entName = '企业名称';
    $scope.entType = '类型';

    // leRep 字段
    if(type == '1' || type == '10' || type == '3' || type == '17'){
       $scope.leRep = '法定代表人';
    }else if (type == '2' || type == '4' ||type == '6' || type == '8'
        || type == '9' || type == '11' || type == '13' || type == '14'
        || type == '15' || type == '18' || type === '19' || type == '20'){
        $scope.leRep = '负责人';
    }else  if (type == '5' || type == '12' ){
        $scope.leRep = '执行事务合伙人'
    }else if (type =='7'){
        $scope.leRep = '投资人'
    }else if (type == '16'){
        $scope.leRep = '经营者';
    }
    //regCap 字段 isRegCapShow 判断控件是否隐藏  || type == '16'
    if(type == '1' || type == '10' || type == '3'){
        $scope.regCap = '注册资本';
        $scope.isRegCapShow = true;
    }else{
        $scope.isRegCapShow = false;
    }
   //estDate 字段
    if (type == '16'){
        $scope.estDate = '注册日期';
    }else {
        $scope.estDate = '成立日期';
    }
    //opFrom 字段 isOpFromShow
    if (type == '3' || type == '1'){
        $scope.isOpFromShow = true;
        $scope.opFrom = '经营期限自';
    }else  if (type == '5' || type == '12'){
        $scope.isOpFromShow = true;
        $scope.opFrom = '合伙期限自'
    }else  if (type == '7' || type == '16' || type == '17' || type == '18'){
        $scope.isOpFromShow = false;
    }else {
        $scope.isOpFromShow = true;
        $scope.opFrom = '营业期限自'
    }
    //opTo 字段 isOpTpShow      opTo   opFrom  新增type = 1 判断
    if (type == '3' || type == '1'){
        $scope.isOpTpShow  = true;
        $scope.opTo = '经营期限至';
    }else  if (type == '5' || type == '12'){
        $scope.isOpTpShow = true;
        $scope.opTo = '合伙期限至'
    }else  if (type == '7' || type == '16' || type == '17' || type == '18'){
        $scope.isOpTpShow = false;
    }else {
        $scope.isOpTpShow = true;
        $scope.opTo = '营业期限至'
    }
    //regOrg 字段
    $scope.regOrg = '登记机关';
    $scope.apprDate = '核准日期';
    $scope.regState = '登记状态';

    //dom 字段
    if (type == '5' || type == '12'){
        $scope.dom = '主要经营场所';
    }else  if (type =='16' || type == '18'){
        $scope.dom = '经营场所';
    }else  if (type =='1' || type == '10' || type == '3' || type == '7' || type == '17'){
        $scope.dom = '住所';
    }else {
        $scope.dom = '营业场所';
    }

    //opScope 字段
    if (type == '17' || type == '18'){
        $scope.opScope = '业务范围';
    }else{
        $scope.opScope = '经营范围';
    }

    function myFunction(item) {
        //RevDate 字段 isCanDateShow  isRevDateShow
        if(item == '2'){
            $scope.isCanDateShow = false;
            $scope.isRevDateShow = true;
            $scope.canDate = '注销日期';
            $scope.RevDate = '吊销日期';
        }else if (item == '3'){
            $scope.isCanDateShow = true;
            $scope.isRevDateShow = true;
            $scope.RevDate = '吊销日期';
            $scope.canDate = '注销日期';
        }else if (item == '4'){
            $scope.isCanDateShow = true;
            $scope.isRevDateShow = false;
            $scope.canDate = '注销日期';
            $scope.RevDate = '吊销日期';
        }else {
            $scope.isCanDateShow = false;
            $scope.isRevDateShow = false;
        }
    }

    //sconForm
    if (type == '16'){
        $scope.sconForm = '组成形式';
        $scope.isSconFormShow = true
    }else {
        $scope.isSconFormShow = false
    }

    if (type == '17'){
        $scope.ismebTotalCapShow = true;
        $scope.mebTotalCap = '成员出资总额';
    }else {
        $scope.ismebTotalCapShow = false;
    }




    $http({
        url: PublicSearchUrl.qi_yingyezhizhaoUrl,
        params : {UUID :window.sessionStorage.getItem(uuidKey)},
        data: '',
        method: "GET",
        dataType: "json",
        cache: false,
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    }).success(function (data) {
        if (data instanceof Object) {
            if (data.errorCode !== '400') {
                $scope.yingyezhizhao = data;
                myFunction($scope.yingyezhizhao.opState);
                zhucema(data);
                console.log($scope.yingyezhizhao)
            }
        } else {

        }
    }).error(function (error) {

    });
}])