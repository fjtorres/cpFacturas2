'use strict';

angular.module('cpFacturasApp').controller('vehiclesController', ['$scope', '$rootScope', '$routeParams', '$modal', '$log', 'vehiclesService', 'vehicleBrandsService', 'vehicleModelsService', VehiclesController]);

function VehiclesController ($scope, $rootScope, $routeParams, $modal, $log, vehiclesService, vehicleBrandsService, vehicleModelsService) {
	// Clear scope vars
    $scope.model = {'id': -1};
    $scope.isUpdate = false;
    $scope.brands = null;
    $scope.selectedBrand = null;
    $scope.selectedModel = null;
    $scope.models = null;
    
    $scope.items = [];
    $scope.currentPage = 1;
    $scope.totalItems = 0;
    $scope.itemsPerPage = 10;
    $scope.sortField = 'firstName';
    $scope.sortDirection = 'ASC';

    if ($routeParams.itemId != undefined) {
        $scope.model = vehiclesService.get({'key': $routeParams.itemId});
        $scope.isUpdate = true;
    }

    $scope.save = function () {
        if ($scope.isUpdate) {
        	vehiclesService.update({}, $scope.model, function(){
                $rootScope.$broadcast('successMessage', "Save vehicle");
                $scope.redirectTo("/customers");
            });
        } else {
        	vehiclesService.save({}, $scope.model, function(){
                $rootScope.$broadcast('successMessage', "Create vehicle");
                $scope.redirectTo("/customers");
            });
        }
    };

    $scope.confirmDelete = function(item){
        
        var modalInstance = $modal.open({
          templateUrl: 'js/app/main/confirmation.tpl.html',
          controller: 'confirmController',
          size: 'md',
          resolve: {
            title: function(){return "messages.confirmation.title";},
            body: function () {return "vehicles.messages.delete.confirmation";}
          }
        });

        modalInstance.result.then(function () {
          $scope.delete(item);
        }, function () {
          // Empty
        });
    };

    $scope.delete = function (item) {
    	vehiclesService.delete(item);
        $rootScope.$broadcast('successMessage', "Delete vehicle");
    };

    $scope.search = function () {
    	vehiclesService.search({'page': $scope.currentPage - 1, 'pageSize': $scope.itemsPerPage, 'sortField': $scope.sortField, 'sortDirection': $scope.sortDirection}, function (result) {
            $scope.items = result.list;
            $scope.totalItems = result.total;
        });
        
    };

    $scope.pageChanged = function () {
        $scope.search();
    };

//    $scope.customerAutocomplete = function (searchValue) {
//    	return {"": "", "": "", "": "", "": "", "": ""}
//    };
    
    $scope.getBrands = function (searchValue) {
    	vehicleBrandsService.query({'searchValue':searchValue}, function(data){
    		$scope.brands = data;
    		if (data.length > 0) {
				$scope.selectedBrand = data[0].id;
			}
    		
    		$scope.getModels();
    	});
    };
    
    $scope.changeBrand = function () {
    	$scope.getModels("");
    };
    
    $scope.getModels = function (searchValue) {
    	if ($scope.selectedBrand != null) {
    		vehicleModelsService.query({'searchValue':searchValue, 'brandId': $scope.selectedBrand}, function(data) {
    			$scope.models = data;
    			if (data.length > 0) {
    				$scope.selectedModel = data[0].id;
    			}
    		});
    	} else {
    		$scope.models = {};
    	}
    };
    
    // Call init methods
    $scope.search();
    $scope.getBrands();
   
}