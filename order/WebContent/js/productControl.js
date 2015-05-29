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

myApp.controller('ProductCtrl', function($scope, $http) {
	$scope.page = 1;
    $scope.ProductClass='product';
    $scope.ProductClassChecked='productchecked';
    $scope.productbuy = 'productbuy';
    $scope.productbuyShow = 'productbuyShow';
    $scope.selectCount = 0;
    $scope.TotalPrice = 0;
    var code = getQueryString("code");
    var data = {"code":code};
    $http.post('/order/ProductListQry.do', data
    ).success(function(data, status, headers, config) {
    	if (data.ReturnCode != '000000') {
    		alert(data.RetMessage);
    	} else {
    		$scope.productList = data.ProductList;
    		$scope.Name = data.Name;
    		$scope.Address = data.Address;
    		$scope.MobilePhone = Number(data.MobilePhone);
    	}
    	
        }).error(function(data, status, headers, config) {
            alert("error");
        });
    
    $scope.selectProduct = function(Product) {
        if (Product.checked) {
            Product.productCount = 1;
            $scope.selectCount = $scope.selectCount + 1;
            $scope.TotalPrice = $scope.TotalPrice + Product.productPrice;
        } else {
        	$scope.selectCount = $scope.selectCount - 1;
        	$scope.TotalPrice = $scope.TotalPrice - Product.productCount*Product.productPrice;
        }
    };

    $scope.addCount = function(Product) {
        Product.productCount = Product.productCount + 1;
        $scope.TotalPrice = $scope.TotalPrice + Product.productPrice;
    };

    $scope.subtractCount = function(Product) {
    	if (Product.productCount > 1) {
    		$scope.TotalPrice = $scope.TotalPrice - Product.productPrice;
    	}
        Product.productCount = Product.productCount - 1 || 1;
    };
    
    $scope.nextStep = function() {
    	$scope.page = $scope.page + 1;
    };
    
    $scope.preStep = function() {
    	$scope.page = $scope.page - 1;
    };
    
    $scope.postOrder = function() {
    	var selectedProducts = [];
    	var products = $scope.productList;
    	for (var i = 0; i < products.length; i++) {
    		if (products[i].checked) {
    			selectedProducts.push(products[i]);
    		}
    	}
    	var data = {
    			"Name":$scope.Name,
    			"MobilePhone":$scope.MobilePhone,
    			"Address":$scope.Address,
    			"Remark":$scope.Remark,
    			"ProductList":selectedProducts,
    			"TotalAmount":$scope.TotalPrice
    	};

    	$http({
            method:'post',
            url:'/order/SubmitOrder.do',
            data: data,
            timeout: 300000
        }).success(function(data,status,headers,config){
        	if (data.ReturnCode != '000000') {
        		alert(data.RetMessage);
        	} else {
        		$scope.page = $scope.page+1;
        	}
        }).error(function(data,status,headers,config){
        	alert("error");
        });
    	
    }
});