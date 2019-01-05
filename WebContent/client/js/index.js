var app = angular.module("myApp", []);

app.service("products",
function() {
	return [{
		id: "DB00001   dpdDrugIdNumber   02240996",
		name: "三元组",
		price: "下载"
	},
	{
		id: "DB00002   label   Cetuximab" ,
		name: "三元组",
		price: "下载"
	},
	{
		id: "DB00002   state   Liquid",
		name: "三元组",
		price: "下载"
	},
	{
		id: "DB00003   state   Liquid",
		name: "三元组",
		price: "下载"
	},
	{
		id: "DB00004   state   Liquid",
		name: "三元组",
		price: "下载"
	},
	{
		id: "DB00005   state   Liquid",
		name: "三元组",
		price: "下载"
	},
	{
		id: "DB00006  primaryAccessionNo  DB00001",
		name: "123456",
		price: "下载"
	}];
});

app.controller("productController",
function($scope, products) {
	$scope.products = products; //Angular自动注入
	//排序条件
	$scope.order = "-"; //默认是升序,-表示降序
	$scope.orderType = "id"; //以id来排序,不能直接在页面以id这个字段排序
	$scope.changeOrder = function(type) {
		$scope.orderType = type;
		//如果本来是降序，就变为升序，如果是升序，就变为降序
		if ($scope.order === '') {
			$scope.order = '-';
		} else {
			$scope.order = '';
		}
	}
});