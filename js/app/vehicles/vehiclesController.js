(function() {
  'use strict';

  function VehiclesController ($scope, $rootScope, $routeParams, $modal, $log, vehiclesService, vehicleBrandsService, vehicleModelsService) {
		
		var vm = this;
		vm.brands = {};
		vm.selectedBrand = null;
		vm.models = {};
		vm.selectedModel = null;
		
		// Clear scope vars
		vm.model = {'id': -1};
		vm.isUpdate = false;
	    
		vm.items = [];
		vm.currentPage = 1;
		vm.totalItems = 0;
		vm.itemsPerPage = 10;
	    vm.sortField = 'firstName';
	    vm.sortDirection = 'ASC';
	
	    if ($routeParams.itemId != undefined) {
	    	vm.model = vehiclesService.get({'key': $routeParams.itemId});
	    	vm.isUpdate = true;
	    }
	
	    vm.onSaveClick = function () {
	        if (vm.isUpdate) {
	        	vehiclesService.update({}, vm.model, function(){
	                $rootScope.$broadcast('successMessage', "Save vehicle");
	                $scope.redirectTo("/customers");
	            });
	        } else {
	        	vehiclesService.save({}, vm.model, function(){
	                $rootScope.$broadcast('successMessage', "Create vehicle");
	                $scope.redirectTo("/customers");
	            });
	        }
	    };
	
	    vm.onConfirmDeleteClick = function(item){
	        
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
	          vm.onDelete(item);
	        }, function () {
	          // Empty
	        });
	    };
	
	    vm.onDelete = function (item) {
	    	vehiclesService.delete(item);
	        $rootScope.$broadcast('successMessage', "Delete vehicle");
	    };
	
	    vm.search = function () {
	    	vehiclesService.search({'page': vm.currentPage - 1, 'pageSize': vm.itemsPerPage, 'sortField': vm.sortField, 'sortDirection': vm.sortDirection}, function (result) {
	    		vm.items = result.list;
	    		vm.totalItems = result.total;
	        });
	        
	    };
	
	    vm.pageChanged = function () {
	    	vm.search();
	    };
	
	//    $scope.customerAutocomplete = function (searchValue) {
	//    	return {"": "", "": "", "": "", "": "", "": ""}
	//    };
	    
	    vm.onBrandChange = function () {
			vm.getModels("");
		};
	    
	    vm.getBrands = function (searchValue) {
	    	vehicleBrandsService.query({'searchValue':searchValue}, function(data){
	    		vm.brands = data;
	    		if (data.length > 0) {
					vm.selectedBrand = data[0].id;
				}
	    		
	    		vm.getModels();
	    	});
	    };
	    
	    vm.getModels = function (searchValue) {
	    	if (vm.selectedBrand != null) {
	    		vehicleModelsService.query({'searchValue':searchValue, 'brandId': vm.selectedBrand}, function(data) {
	    			vm.models = data;
	    			if (data.length > 0) {
	    				vm.selectedModel = data[0].id;
	    			}
	    		});
	    	} else {
	    		vm.models = {};
	    	}
	    };
	    
	    // Call init methods
	    vm.search();
	    vm.getBrands();
	   
	}
  
	angular.module('cpFacturasApp').controller('vehiclesController', ['$scope', '$rootScope', '$routeParams', '$modal', '$log', 'vehiclesService', 'vehicleBrandsService', 'vehicleModelsService', VehiclesController]);

}());