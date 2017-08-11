/**
 * Created by George on 2017/4/18.
 */
var appDirectives = angular.module('app-Directives', []);
// class="col-xs-4"   class="col-xs-8"
appDirectives.directive('keyValueRow', function () {
    return {
        restrict: 'E',
        scope: {
            rowKey: '@',
            rowValue: '='
        },
        template: '<div class="keyValueRow"><div class="rowLeftKey"><span>{{rowKey}}</span></div><div class="rowRightValue"><span>{{rowValue}}</span></div></div>'
    };
});

appDirectives.directive('indexBox', function () {
    return {
        restrict: 'E',
        scope: {
            item: '=',
            goPage: '&'
        },
        templateUrl: 'template/indexBox.html',
        controller:['$state','$scope','$window', function ($state, $scope,$window) {
            $scope.click = function () {
                if ($scope.item.stateName === 'easyCancellation') {
                    $window.location.href = '../../easyCancellation/noticeDetail.html';
                    return;
                }
                $scope.goPage({aStateName: $scope.item.stateName});
            }
        }]
    };
});

app.directive('reviewItemMulti', function () {
    return {
        replace: true,
        restrict: 'E',
        scope: {
            tReviewTitle: '@',
            tReviewContent: '@',
        },
        template: '<div class="col-xs-6"style="padding-left: 30px; font-size: 1.4rem"><div class="row preview title vertical-middle"><div class="col-xs-12  " ng-bind="tReviewTitle"></div></div><div class="row preview content vertical-middle"><div class="col-xs-12" ng-show="tReviewContent.length>0" ng-bind="tReviewContent"></div></div></div>',
        template: '<div class="col-xs-6"style="padding-left: 30px; font-size: 1.4rem"><div class="row preview title vertical-middle"><div class="col-xs-12  " ng-bind="tReviewTitle"></div></div><div class="row preview content vertical-middle"><div class="col-xs-12"  ng-bind="tReviewContent"></div></div></div>'
    };
});
// ng-show="{{tReviewContent.length}}"
app.directive('reviewItemSingle', function () {
    return {
        replace: true,
        restrict: 'E',
        scope: {
            tReviewTitle: '@',
            tReviewContent: '@',
        },
        template: '<div class="col-xs-12" style="padding-left: 30px;font-size: 1.4rem"><div class="row preview title vertical-middle"><div class="col-xs-12  " ng-bind="tReviewTitle"></div></div><div class="row preview content vertical-middle"><div class="col-xs-12" ng-bind="tReviewContent"></div></div></div>'
    };
});


appDirectives.directive('emptyPage', function () {
    return {
        restrict: 'E',
        scope: {},
        template: '<div style="width: 100%;height: 100%;background-color: white;text-align: center;padding-top: 30%"><img style="width: 50%;" src="../static/image/no_result.png"/></div>'
    }
});