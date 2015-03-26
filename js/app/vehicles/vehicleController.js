(function() {
  'use strict';

  function VehicleController ($rootScope, $routeParams, genericService, vehiclesService, vehicleBrandsService, vehicleModelsService) {
		
		var vm = this;
		vm.brands = {};
		vm.selectedBrand = null;
		vm.models = {};
		vm.selectedModel = null;
		
		// Clear scope vars
		vm.model = {'id': -1};
		vm.isUpdate = false;
	
	    if ($routeParams.itemId != undefined) {
	    	vm.model = vehiclesService.get({'key': $routeParams.itemId});
	    	vm.isUpdate = true;
	    }
	
	    vm.onSaveClick = function () {
	        if (vm.isUpdate) {
	        	vehiclesService.update({}, vm.model, function(){
	                $rootScope.$broadcast('successMessage', "Save vehicle");
	                $rootScope.redirectTo("/customers");
	            });
	        } else {
	        	vehiclesService.save({}, vm.model, function(){
	                $rootScope.$broadcast('successMessage', "Create vehicle");
	                $rootScope.redirectTo("/customers");
	            });
	        }
	    };
	    
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
	    vm.getBrands();
	   
	}
  
	angular.module('cpFacturasApp').controller('vehicleController', ['$rootScope', '$routeParams', 'genericService', 'vehiclesService', 'vehicleBrandsService', 'vehicleModelsService', VehicleController]);

}());