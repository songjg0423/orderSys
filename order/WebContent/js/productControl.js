var myApp = angular.module('myApp',[]);

myApp.controller('ProductCtrl', function($scope, $http) {
	$scope.page = 1;
    $scope.ProductClass='product';
    $scope.ProductClassChecked='productchecked';
    $scope.productbuy = 'productbuy';
    $scope.productbuyShow = 'productbuyShow';
    $http.post('/order/ProductListQry.do', null, null
    ).success(function(data, status, headers, config) {
    	$scope.productList = data.ProductList;
        }).error(function(data, status, headers, config) {
            alert("error");
        });
    $scope.selectProduct = function(Product) {
        if (Product.checked) {
            Product.productCount = 1;
        }
    };

    $scope.addCount = function(Product) {
        Product.productCount = Product.productCount + 1;
    };

    $scope.subtractCount = function(Product) {
        Product.productCount = Product.productCount - 1 || 1;
    };
    
    $scope.nextStep = function() {
    	$scope.page = 2;
    	var selectProducts = [];
    	var products = $scope.productList;
    	for (var i = 0; i < products.length; i++) {
    		if (products[i].checked) {
    			selectProducts.push(products[i]);
    		}
    	}
    	$scope.selectProducts = selectProducts;
    };
});