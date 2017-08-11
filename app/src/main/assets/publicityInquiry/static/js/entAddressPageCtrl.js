/**
 * Created by George on 2017/6/11.
 */
app.controller('entAddressPageCtrl', ['$scope', '$state', '$stateParams', function ($scope, $state, $stateParams) {


    //创建Map实例
    var map = new BMap.Map("entAddressDiv");

    // 创建地址解析器实例
    var myGeo = new BMap.Geocoder();
    // 将地址解析结果显示在地图上，并调整地图视野
    myGeo.getPoint($stateParams.address, function (point) {
        if (point) {
            map.centerAndZoom(point, 15);
            //设置标注的图标
            // var icon = new BMap.Icon("../static/image/dot_blue.png", new BMap.Size(50, 50));
            //设置标注的经纬度
            var marker = new BMap.Marker(point);
            //把标注添加到地图上
            map.addOverlay(marker);
        }
    });
    //添加鼠标滚动缩放
    // map.enableScrollWheelZoom();
    //
    //添加缩略图控件
    // map.addControl(new BMap.OverviewMapControl({isOpen: false, anchor: BMAP_ANCHOR_BOTTOM_RIGHT}));
    //添加缩放平移控件
    map.addControl(new BMap.NavigationControl());
    //添加比例尺控件
    // map.addControl(new BMap.ScaleControl());

}]);
