/**
 * Created by Shadow on 2016/12/21.
 */

angular.module('timCustom', ['timTag', 'loading']);
angular.module('timTag', [])

    .directive("tTag", function () {
        return {
            replace: false,
            restrict: 'EA',
            transclude: true,
            scope: {bg_color: "=tBgColor", text: "=tContent"},
            template: function (elements, attrs) {
                return '<span class="t-tag ' + (attrs.class || "") + '" style="background: {{bg_color}};">{{text}}</span>'
            }
        };
    });
angular.module('loading', [])
    .directive("loading", function () {
        return {
            replace: true,
            restrict: 'EA',
            transclude: true,
            template: function (elements, attrs) {
                return '<div class="tipsBackground"><img src="../static/lib/custom_angular_ui/loading.svg"  class="loadingImg"></div>'
            }
        };
    });