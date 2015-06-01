function getQueryString(name){
	var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}

var myApp = angular.module('myApp', []);
myApp.config(['$httpProvider', function($httpProvider){
	$httpProvider.defaults.timeout=300000;
}]);

myApp.filter('orderState', function(){
	return function(input) {
		if (input == '0') {
			return "待处理";
		} else if (input == '1') {
			return "处理中";
		} else {
			return "已完成";
		}
	}
});

myApp.controller('OrderCtrl', function($scope, $http) {
	$scope.page = 1;
	$scope.pageNum = 1;
    var code = getQueryString("code");
    var data = {"code":code,'pageNum':$scope.pageNum};
    $http({
        method:'post',
        url:'/order/OrderListQry.do',
        data: data,
        timeout: 300000
    }).success(function(data,status,headers,config){
    	if (data.ReturnCode != '000000') {
    		alert(data.RetMessage);
    	} else {
    		$scope.orderList = data.OrderList;
    		$scope.TotalPage=data.TotalPage;
    		$scope.TotalNum=data.TotalNum;
    	}
    }).error(function(data,status,headers,config){
    	alert("error");
    });
    

    
    $scope.nextStep = function() {
    	$scope.page = $scope.page + 1;
    };
    
    $scope.preStep = function() {
    	$scope.page = $scope.page - 1;
    };
    
    $scope.showDetail = function(order) {
    	$scope.currentOrder = order;
    	$scope.showDetailDiv = true;
    }
    
    $scope.closeDetail = function(){
    	$scope.showDetailDiv = false;
    }
    
    $scope.nextPage = function() {
    	$scope.pageNum = $scope.pageNum + 1;
    	var data = {'pageNum':$scope.pageNum};
    	$http({
            method:'post',
            url:'/order/OrderListQry.do',
            data: data,
            timeout: 300000
        }).success(function(data,status,headers,config){
        	if (data.ReturnCode != '000000') {
        		alert(data.RetMessage);
        	} else {
        		$scope.orderList = data.OrderList;
        	}
        }).error(function(data,status,headers,config){
        	alert("error");
        });
    }
    
    $scope.prePage = function() {
    	$scope.pageNum = $scope.pageNum - 1;
    	var data = {'pageNum':$scope.pageNum};
    	$http({
            method:'post',
            url:'/order/OrderListQry.do',
            data: data,
            timeout: 300000
        }).success(function(data,status,headers,config){
        	if (data.ReturnCode != '000000') {
        		alert(data.RetMessage);
        	} else {
        		$scope.orderList = data.OrderList;
        	}
        }).error(function(data,status,headers,config){
        	alert("error");
        });
    }
    
});