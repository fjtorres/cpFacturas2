(function() {
  'use strict';

  function VehicleController ($rootScope, $routeParams, genericService, vehiclesService, vehicleBrandsService, vehicleModelsService, customerService) {
		
		var vm = this;
		vm.brands = [];
		vm.selectedBrand = null;
		vm.models = [];
		vm.customers = [];
		vm.years = [2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015]; // TODO LOAD LAST XX YEARS
		vm.doors = [2, 3, 5]; // TODO LOAD FROM SERVER
		vm.fuelTypeList = [{"id": "Diesel", "name": "Tipo 1"}, {"id": "Hybrid", "name": "Tipo 2"}, {"id": "Electric", "name": "Tipo 3"}, {"id": "Petrol", "name": "Tipo 4"}]; // TODO LOAD FROM SERVER
		
		// Clear scope vars
		vm.entity = {'id': -1};
		vm.isUpdate = false;
	
	    if ($routeParams.itemId != undefined) {
	    	vm.model = vehiclesService.get({'key': $routeParams.itemId});
	    	vm.isUpdate = true;
	    }
	
	    vm.onSaveSubmit = function () {
	    	
	        if (vm.isUpdate) {
	        	vehiclesService.update({}, vm.entity, function(){
	        		genericService.translate('vehicles.messages.update.success', function (text) {
	            		$rootScope.$broadcast('successMessage', text);
	            	});
	                $rootScope.redirectTo("/customers");
	            });
	        } else {
	        	vehiclesService.save({}, vm.entity, function(){
	        		genericService.translate('vehicles.messages.create.success', function (text) {
	            		$rootScope.$broadcast('successMessage', text);
	            	});
	                $rootScope.redirectTo("/customers");
	            });
	        }
	    };
	    
	    vm.onBrandChange = function () {
			vm.getModels("");
		};
	    
		vm.getCustomers = function (searchValue) {
			return customerService.autocomplete(searchValue).then(function(response){
				return response.data;
			});
		};
		
	    vm.getBrands = function (searchValue) {
	    	vehicleBrandsService.query({'searchValue':searchValue}, function(data){
	    		vm.brands = data;
	    		if (data.length > 0) {
					vm.selectedBrand = data[0];
				}
	    		
	    		vm.getModels();
	    	});
	    };
	    
	    vm.getModels = function (searchValue) {
	    	if (vm.selectedBrand != null) {
	    		vehicleModelsService.query({'searchValue':searchValue, 'brandId': vm.selectedBrand.id}, function(data) {
	    			vm.models = data;
	    			if (data.length > 0) {
	    				vm.entity.model = data[0];
	    			}
	    		});
	    	} else {
	    		vm.models = [{}];
	    	}
	    };
	    
	    // Call init methods
	    vm.getBrands();
	   
	}
  
	angular.module('cpFacturasApp').controller('vehicleController', ['$rootScope', '$routeParams', 'genericService', 'vehiclesService', 'vehicleBrandsService', 'vehicleModelsService', 'customerService', VehicleController]);

}());