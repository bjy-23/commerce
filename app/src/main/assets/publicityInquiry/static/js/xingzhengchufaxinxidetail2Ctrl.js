/**
 * Created by yellow1ong on 2017/4/20.
 */
app.controller('xingzhengchufaxinxidetail2Ctrl',['$scope', '$http', '$stateParams', function ($scope, $http, $stateParams) {

    $scope.penFilePath = $stateParams.penFilePath;

    $scope.path =

    "download/viewPdf?pdfName=" + $stateParams.penFilePath;
    // console.log($scope.penFilePath);
}]);